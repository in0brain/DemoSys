package com.example.demosys.domain.auth.dto;

import lombok.Data;

@Data
public class MeUserProfileDTO {

    private String phone;

    private String email;

    /** 导师 / 专家职称，学生为空 */
    private String title;
}
