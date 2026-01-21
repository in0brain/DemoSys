package com.example.demosys.domain.education.mapper;

import com.example.demosys.domain.education.dto.ApprovalNode;
import com.example.demosys.domain.education.dto.EnrollmentStatus;
import com.example.demosys.domain.education.entity.Enrollment;
import com.example.demosys.domain.education.entity.EnrollmentAudit;
import com.example.demosys.domain.education.entity.EnrollmentItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface EnrollmentMapper {
    int insertEnrollment(Enrollment enrollment);

    int insertEnrollmentItems(@Param("enrollmentId") Long enrollmentId,
                              @Param("items") List<EnrollmentItem> items);

    List<Enrollment> selectByStudentId(@Param("studentId") Long studentId);

    List<Enrollment> selectTodos(@Param("currentNode") ApprovalNode currentNode,
                                 @Param("status") EnrollmentStatus status);

    List<EnrollmentItem> selectItemsByEnrollmentId(@Param("enrollmentId") Long enrollmentId);

    List<EnrollmentAudit> selectAuditsByEnrollmentId(@Param("enrollmentId") Long enrollmentId);

    int updateAdvisorApprove(@Param("enrollmentId") Long enrollmentId);

    int updateCollegeApprove(@Param("enrollmentId") Long enrollmentId);

    int updateReject(@Param("enrollmentId") Long enrollmentId,
                     @Param("currentNode") ApprovalNode currentNode,
                     @Param("status") EnrollmentStatus status);

    int insertAudit(EnrollmentAudit audit);

    List<Map<String, Object>> selectApprovedCoursesByStudentId(@Param("studentId") Long studentId);

    BigDecimal sumCreditsByEnrollmentId(@Param("enrollmentId") Long enrollmentId);

    Enrollment selectById(@Param("enrollmentId") Long enrollmentId);

}
