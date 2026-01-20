package com.example.demosys.domain.admissions.mapper;

import com.example.demosys.domain.admissions.dto.PublicAdmissionsOfferRow;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OfferPublicationMapper {

    int insertFromDraft(@Param("draftId") String draftId);

    List<PublicAdmissionsOfferRow> selectPublicOffersByKeyword(@Param("keyword") String keyword);
}

