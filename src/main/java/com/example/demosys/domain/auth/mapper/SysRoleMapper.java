package com.example.demosys.domain.auth.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysRoleMapper {
    List<String> selectRoleCodesByUserId(@Param("userId") Long userId);
    List<String> selectRoleCodesByUsername(@Param("username") String username);
}
