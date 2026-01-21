package com.example.demosys.domain.auth.mapper;

import com.example.demosys.domain.auth.entity.SysStudentProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysStudentProfileMapper {

    /**
     * 根据 user_id 查询学生学籍信息
     * 仅当 user_type = student 时调用
     */
    SysStudentProfile selectByUserId(@Param("userId") Long userId);
}
