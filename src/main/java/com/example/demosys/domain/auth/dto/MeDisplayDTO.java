package com.example.demosys.domain.auth.dto;

import lombok.Data;

import java.util.List;

@Data
public class MeDisplayDTO {

    /** 基础用户信息 */
    private MeUserDTO user;

    /** 角色列表（role_code） */
    private List<String> roles;

    /** 权限点列表（perm_code） */
    private List<String> permissions;

    /** 通用扩展信息（sys_user_profile） */
    private MeUserProfileDTO profile;

    /** 学生学籍信息（仅 student 非空） */
    private MeStudentProfileDTO student;
}
