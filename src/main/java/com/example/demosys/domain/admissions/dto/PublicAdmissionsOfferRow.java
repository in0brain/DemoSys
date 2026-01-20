package com.example.demosys.domain.admissions.dto;

import lombok.Data;

/**
 * 公示查询 - Mapper 返回行（数据库查询结果）
 */
@Data
public class PublicAdmissionsOfferRow {

    private String examNo;

    private String name;

    /** 0/1：是否已公示 */
    private Integer offered;
}
