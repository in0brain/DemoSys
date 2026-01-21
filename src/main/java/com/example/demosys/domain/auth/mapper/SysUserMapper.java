package com.example.demosys.domain.auth.mapper;

import com.example.demosys.domain.auth.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserMapper {
    SysUser selectByUsername(@Param("username") String username);

    SysUser selectById(Long id);

    int updateLastLoginAt(@Param("id") Long id);
}
