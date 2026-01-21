package com.example.demosys.domain.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SysUserProfile implements Serializable {

    private Long id;

    private Long userId;

    private String phone;

    private String email;

    /**
     * 导师职称 / 专家头衔（学生一般为空）
     */
    private String title;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
