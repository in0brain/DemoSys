package com.example.demosys.domain.auth.mapper;

import com.example.demosys.domain.auth.entity.SysUserProfile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserProfileMapper {

    /**
     * 根据 user_id 查询用户扩展信息
     * 对应 sys_user_profile 表
     */
    SysUserProfile selectByUserId(@Param("userId") Long userId);
}
