package com.example.demosys.domain.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SysStudentProfile implements Serializable {

    private Long id;

    /**
     * 关联 sys_user.id
     */
    private Long userId;

    /**
     * 学号（入学年份 + userId 等规则生成）
     */
    private String studentNo;

    /**
     * 学院组织ID（sys_org.id）
     */
    private Long collegeOrgId;

    /**
     * 系/部门组织ID（sys_org.id）
     */
    private Long deptOrgId;

    /**
     * 专业名称（或未来 major_id）
     */
    private String majorName;

    /**
     * 入学年份
     */
    private Integer enrollmentYear;

    /**
     * 硕/博：master / doctor
     */
    private String degreeLevel;

    /**
     * 学籍状态：in_school / graduated / suspended 等
     */
    private String studentStatus;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
