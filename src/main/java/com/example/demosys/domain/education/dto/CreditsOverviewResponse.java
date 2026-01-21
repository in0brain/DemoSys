package com.example.demosys.domain.education.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class CreditsOverviewResponse {

    private List<Item> items;

    @Data
    public static class Item {
        private Long studentId;
        private String studentName;
        private BigDecimal totalCredit;
        private String updatedAt; // 和你前端保持字符串即可
    }
}
