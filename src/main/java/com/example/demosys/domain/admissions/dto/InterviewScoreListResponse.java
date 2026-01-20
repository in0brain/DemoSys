package com.example.demosys.domain.admissions.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class InterviewScoreListResponse {

    /** 当前页 */
    private Integer page;

    /** 每页大小 */
    private Integer pageSize;

    /** 总条数 */
    private Long total;

    /** 列表 */
    private List<Item> items = new ArrayList<>();

    @Data
    public static class Item {
        private Long candidateId;
        private String examNo;
        private String name;
        private String major;
        private BigDecimal score;
    }
}
