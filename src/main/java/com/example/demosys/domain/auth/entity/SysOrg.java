package com.example.demosys.domain.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class SysOrg implements Serializable {

    private Long id;

    private Long parentId;

    private String orgCode;

    private String orgName;

    /**
     * root / college / dept
     */
    private String orgType;

    /**
     * 你的项目里如果是 tinyint(1)：1启用/0禁用
     * 那这里用 Integer 最稳；如果是 active/inactive 就改 String
     */
    private Integer status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
