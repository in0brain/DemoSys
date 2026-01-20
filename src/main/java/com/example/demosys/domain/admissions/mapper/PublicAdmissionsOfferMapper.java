package com.example.demosys.domain.admissions.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PublicAdmissionsOfferMapper {

    /**
     * 公示查询：按姓名/考号 keyword 查询已公示结果
     */
    List<PublicOfferRow> query(@Param("keyword") String keyword);

    class PublicOfferRow {
        public String name;
        public String examNo;
        public String offerStatus; // 固定：已公示
    }
}
