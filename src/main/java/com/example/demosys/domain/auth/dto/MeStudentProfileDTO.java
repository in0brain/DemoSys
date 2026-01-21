package com.example.demosys.domain.auth.dto;

import lombok.Data;

@Data
public class MeStudentProfileDTO {

    private String studentNo;

    private String collegeName;

    private String deptName;

    private String majorName;

    private Integer enrollmentYear;

    private String degreeLevel;

    private String studentStatus;
}
