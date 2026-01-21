package com.example.demosys.domain.education.service.impl;

import com.example.demosys.common.exception.BizException;
import com.example.demosys.domain.education.dto.*;
import com.example.demosys.domain.education.entity.Course;
import com.example.demosys.domain.education.entity.Enrollment;
import com.example.demosys.domain.education.entity.EnrollmentAudit;
import com.example.demosys.domain.education.entity.EnrollmentItem;
import com.example.demosys.domain.education.mapper.CourseMapper;
import com.example.demosys.domain.education.mapper.CreditMapper;
import com.example.demosys.domain.education.mapper.EnrollmentMapper;
import com.example.demosys.domain.education.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentMapper enrollmentMapper;
    private final CourseMapper courseMapper;
    private final CreditMapper creditMapper;

    // =========================
    // 临时：模拟当前用户（后续接 JWT/SecurityContext 替换）
    // =========================
    private Long getCurrentUserId() {
        return 1001L;
    }

    private String getCurrentUserName() {
        return "张三";
    }

    private Long getCurrentAuditorId() {
        return 2001L;
    }

    private String getCurrentAuditorName() {
        return "李老师";
    }

    private String getCurrentAuditorRole() {
        // TODO: 未来从 roles 判断 TEACHER / ADMIN
        return "TEACHER";
    }

    // =========================
    // 1) 学生提交
    // =========================
    @Override
    @Transactional
    public EnrollmentsResponse submit(EnrollmentsRequest req) {
        if (req == null || req.getCourses() == null || req.getCourses().isEmpty()) {
            throw new BizException("课程列表不能为空");
        }

        List<EnrollmentsRequest.Item> in = req.getCourses().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        if (in.isEmpty()) throw new BizException("课程列表不能为空");

        // 可选：用课程库补齐 courseName/credit
        Map<String, Course> courseByCode = new HashMap<>();
        List<String> codes = in.stream()
                .map(EnrollmentsRequest.Item::getCourseCode)
                .filter(s -> s != null && !s.isBlank())
                .distinct()
                .collect(Collectors.toList());

        if (!codes.isEmpty()) {
            List<Course> found = courseMapper.selectByCourseCodes(codes);
            for (Course c : found) courseByCode.put(c.getCourseCode(), c);
        }

        LocalDateTime now = LocalDateTime.now();

        Enrollment e = new Enrollment();
        e.setEnrollmentNo("ENR" + System.currentTimeMillis());
        e.setStudentId(getCurrentUserId());
        e.setStudentName(getCurrentUserName());
        e.setStatus(EnrollmentStatus.SUBMITTED);
        e.setCurrentNode(ApprovalNode.ADVISOR);
        e.setSubmitTime(now);
        e.setFinishTime(null);
        e.setCreatedAt(now);
        e.setUpdatedAt(now);

        enrollmentMapper.insertEnrollment(e);
        if (e.getId() == null) throw new BizException("创建申请失败：未生成主键");

        List<EnrollmentItem> items = new ArrayList<>();
        for (EnrollmentsRequest.Item it : in) {
            String code = it.getCourseCode();
            if (code == null || code.isBlank()) {
                throw new BizException("课程编号 courseCode 不能为空");
            }

            Course ref = courseByCode.get(code);

            EnrollmentItem item = new EnrollmentItem();
            item.setEnrollmentId(e.getId());
            item.setCourseId(it.getCourseId());
            item.setCourseCode(code);

            String name = (it.getCourseName() != null && !it.getCourseName().isBlank())
                    ? it.getCourseName()
                    : (ref != null ? ref.getCourseName() : null);
            item.setCourseName(name);

            BigDecimal credit = it.getCredit() != null ? it.getCredit()
                    : (ref != null ? ref.getCredit() : null);

            if (credit == null) {
                throw new BizException("课程学分 credit 不能为空（课程：" + code + "）");
            }

            item.setCredit(credit);
            item.setCreatedAt(now);
            items.add(item);
        }

        enrollmentMapper.insertEnrollmentItems(e.getId(), items);

        return buildEnrollmentResponse(e, items, Collections.emptyList());
    }

    // =========================
    // 2) 学生查看我的申请列表
    // =========================
    @Override
    public List<EnrollmentsResponse> listMine() {
        Long studentId = getCurrentUserId();
        List<Enrollment> rows = enrollmentMapper.selectByStudentId(studentId);
        if (rows == null || rows.isEmpty()) return Collections.emptyList();

        List<EnrollmentsResponse> out = new ArrayList<>();
        for (Enrollment e : rows) {
            List<EnrollmentItem> items = enrollmentMapper.selectItemsByEnrollmentId(e.getId());
            List<EnrollmentAudit> audits = enrollmentMapper.selectAuditsByEnrollmentId(e.getId());
            out.add(buildEnrollmentResponse(e, items, audits));
        }
        return out;
    }

    // =========================
    // 3) 导师/学院待办
    // =========================
    @Override
    public ApprovalsEnrollmentsResponse listTodos(String node) {
        ApprovalNode currentNode;
        EnrollmentStatus status;

        if ("ADVISOR".equalsIgnoreCase(node)) {
            currentNode = ApprovalNode.ADVISOR;
            status = EnrollmentStatus.SUBMITTED;
        } else if ("COLLEGE".equalsIgnoreCase(node)) {
            currentNode = ApprovalNode.COLLEGE;
            status = EnrollmentStatus.ADVISOR_APPROVED;
        } else {
            throw new BizException("node 只能是 ADVISOR 或 COLLEGE");
        }

        // ✅ 推荐：mapper 方法参数改成枚举（见上面的“Mapper 参数改动”）
        List<Enrollment> todos = enrollmentMapper.selectTodos(currentNode, status);

        List<ApprovalsEnrollmentsResponse.Item> items = new ArrayList<>();
        for (Enrollment e : todos) {
            List<EnrollmentItem> courseItems = enrollmentMapper.selectItemsByEnrollmentId(e.getId());
            BigDecimal total = sumCredits(courseItems);

            ApprovalsEnrollmentsResponse.Item it = new ApprovalsEnrollmentsResponse.Item();
            it.setId(e.getId());
            it.setEnrollmentNo(e.getEnrollmentNo());
            it.setStudentId(e.getStudentId());
            it.setStudentName(e.getStudentName());
            it.setStatus(e.getStatus());
            it.setCurrentNode(e.getCurrentNode());
            it.setSubmitTime(toText(e.getSubmitTime()));
            it.setCourseCount(courseItems == null ? 0 : courseItems.size());
            it.setTotalCredit(total);

            List<EnrollmentsResponse.CourseItem> cs = new ArrayList<>();
            for (EnrollmentItem ci : (courseItems == null ? Collections.<EnrollmentItem>emptyList() : courseItems)) {
                EnrollmentsResponse.CourseItem c = new EnrollmentsResponse.CourseItem();
                c.setCourseId(ci.getCourseId());
                c.setCourseCode(ci.getCourseCode());
                c.setCourseName(ci.getCourseName());
                c.setCredit(ci.getCredit());
                cs.add(c);
            }
            it.setCourses(cs);

            items.add(it);
        }

        ApprovalsEnrollmentsResponse resp = new ApprovalsEnrollmentsResponse();
        resp.setItems(items);
        return resp;
    }

    // =========================
    // 4) 审批通过（导师/学院）
    // =========================
    @Override
    @Transactional
    public EnrollmentsApproveResponse approve(EnrollmentsApproveRequest req) {
        if (req == null || req.getEnrollmentId() == null) {
            throw new BizException("enrollmentId 不能为空");
        }
        Long enrollmentId = req.getEnrollmentId();

        Enrollment cur = enrollmentMapper.selectById(enrollmentId);
        if (cur == null) throw new BizException("申请不存在：" + enrollmentId);

        LocalDateTime now = LocalDateTime.now();

        ApprovalNode node = cur.getCurrentNode();
        int updated = 0;

        // 导师通过
        if (node == ApprovalNode.ADVISOR && cur.getStatus() == EnrollmentStatus.SUBMITTED) {
            updated = enrollmentMapper.updateAdvisorApprove(enrollmentId);

            if (updated == 1) {
                EnrollmentAudit audit = new EnrollmentAudit();
                audit.setEnrollmentId(enrollmentId);
                audit.setNode(ApprovalNode.ADVISOR);
                audit.setAuditorId(getCurrentAuditorId());
                audit.setAuditorName(getCurrentAuditorName());
                audit.setAuditorRole(getCurrentAuditorRole());
                audit.setAction(ApprovalAction.APPROVE);
                audit.setActionTime(now);
                audit.setCreatedAt(now);
                enrollmentMapper.insertAudit(audit);
            }

        }
        // 学院通过（终审）
        else if (node == ApprovalNode.COLLEGE && cur.getStatus() == EnrollmentStatus.ADVISOR_APPROVED) {
            updated = enrollmentMapper.updateCollegeApprove(enrollmentId);

            if (updated == 1) {
                EnrollmentAudit audit = new EnrollmentAudit();
                audit.setEnrollmentId(enrollmentId);
                audit.setNode(ApprovalNode.COLLEGE);
                audit.setAuditorId(getCurrentAuditorId());
                audit.setAuditorName(getCurrentAuditorName());
                audit.setAuditorRole(getCurrentAuditorRole());
                audit.setAction(ApprovalAction.APPROVE);
                audit.setActionTime(now);
                audit.setCreatedAt(now);
                enrollmentMapper.insertAudit(audit);

                // ✅ 只在 update 成功后累加，避免重复审批导致重复累加
                BigDecimal delta = enrollmentMapper.sumCreditsByEnrollmentId(enrollmentId);
                if (delta == null) delta = BigDecimal.ZERO;
                creditMapper.upsertAddCredit(cur.getStudentId(), delta);
            }

        } else {
            throw new BizException("当前状态不可审批：node=" + node + ", status=" + cur.getStatus());
        }

        if (updated != 1) {
            throw new BizException("审批失败：状态已变化或重复操作");
        }

        Enrollment latest = enrollmentMapper.selectById(enrollmentId);

        EnrollmentsApproveResponse resp = new EnrollmentsApproveResponse();
        resp.setEnrollmentId(enrollmentId);
        resp.setStatus(latest.getStatus());
        resp.setCurrentNode(latest.getCurrentNode());
        return resp;
    }

    // =========================
    // 5) 驳回（任一节点可驳回）
    // =========================
    @Override
    @Transactional
    public EnrollmentsRejectResponse reject(EnrollmentsRejectRequest req) {
        if (req == null || req.getEnrollmentId() == null) {
            throw new BizException("enrollmentId 不能为空");
        }
        Long enrollmentId = req.getEnrollmentId();

        Enrollment cur = enrollmentMapper.selectById(enrollmentId);
        if (cur == null) throw new BizException("申请不存在：" + enrollmentId);

        ApprovalNode node = cur.getCurrentNode();
        EnrollmentStatus status = cur.getStatus();

        if (node == ApprovalNode.DONE) {
            throw new BizException("该申请已结束，不能驳回");
        }

        int updated = enrollmentMapper.updateReject(enrollmentId, node, status);
        if (updated != 1) {
            throw new BizException("驳回失败：状态已变化或重复操作");
        }

        LocalDateTime now = LocalDateTime.now();

        EnrollmentAudit audit = new EnrollmentAudit();
        audit.setEnrollmentId(enrollmentId);
        audit.setNode(node);
        audit.setAuditorId(getCurrentAuditorId());
        audit.setAuditorName(getCurrentAuditorName());
        audit.setAuditorRole(getCurrentAuditorRole());
        audit.setAction(ApprovalAction.REJECT);
        audit.setActionTime(now);
        audit.setCreatedAt(now);
        enrollmentMapper.insertAudit(audit);

        EnrollmentsRejectResponse resp = new EnrollmentsRejectResponse();
        resp.setEnrollmentId(enrollmentId);
        resp.setStatus(EnrollmentStatus.REJECTED);
        resp.setCurrentNode(ApprovalNode.DONE);
        return resp;
    }

    // =========================
    // 6) 详情
    // =========================
    @Override
    public EnrollmentsResponse getDetail(Long enrollmentId) {
        if (enrollmentId == null) throw new BizException("enrollmentId 不能为空");

        Enrollment e = enrollmentMapper.selectById(enrollmentId);
        if (e == null) throw new BizException("申请不存在：" + enrollmentId);

        List<EnrollmentItem> items = enrollmentMapper.selectItemsByEnrollmentId(enrollmentId);
        List<EnrollmentAudit> audits = enrollmentMapper.selectAuditsByEnrollmentId(enrollmentId);
        return buildEnrollmentResponse(e, items, audits);
    }

    // =========================
    // DTO 组装
    // =========================
    private EnrollmentsResponse buildEnrollmentResponse(Enrollment e,
                                                        List<EnrollmentItem> items,
                                                        List<EnrollmentAudit> audits) {
        EnrollmentsResponse resp = new EnrollmentsResponse();
        resp.setId(e.getId());
        resp.setEnrollmentNo(e.getEnrollmentNo());
        resp.setStudentId(e.getStudentId());
        resp.setStudentName(e.getStudentName());
        resp.setStatus(e.getStatus());
        resp.setCurrentNode(e.getCurrentNode());
        resp.setSubmitTime(toText(e.getSubmitTime()));
        resp.setFinishTime(toText(e.getFinishTime()));

        List<EnrollmentsResponse.CourseItem> cs = new ArrayList<>();
        for (EnrollmentItem it : (items == null ? Collections.<EnrollmentItem>emptyList() : items)) {
            EnrollmentsResponse.CourseItem c = new EnrollmentsResponse.CourseItem();
            c.setCourseId(it.getCourseId());
            c.setCourseCode(it.getCourseCode());
            c.setCourseName(it.getCourseName());
            c.setCredit(it.getCredit());
            cs.add(c);
        }
        resp.setCourses(cs);

        List<EnrollmentsResponse.AuditItem> as = new ArrayList<>();
        for (EnrollmentAudit a : (audits == null ? Collections.<EnrollmentAudit>emptyList() : audits)) {
            EnrollmentsResponse.AuditItem ai = new EnrollmentsResponse.AuditItem();
            ai.setNode(a.getNode());
            ai.setAuditorRole(a.getAuditorRole());
            ai.setAuditorName(a.getAuditorName());
            ai.setAction(a.getAction());
            ai.setActionTime(toText(a.getActionTime()));
            as.add(ai);
        }
        resp.setAudits(as);

        return resp;
    }

    private BigDecimal sumCredits(List<EnrollmentItem> items) {
        BigDecimal s = BigDecimal.ZERO;
        if (items == null) return s;
        for (EnrollmentItem it : items) {
            if (it.getCredit() != null) s = s.add(it.getCredit());
        }
        return s;
    }

    private String toText(LocalDateTime t) {
        return t == null ? null : t.toString();
    }
}
