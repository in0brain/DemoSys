package com.example.demosys.domain.auth.mapper;

import com.example.demosys.domain.auth.entity.SysOrg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysOrgMapper {

    /**
     * 根据组织 ID 查询组织信息
     */
    SysOrg selectById(@Param("id") Long id);
}
