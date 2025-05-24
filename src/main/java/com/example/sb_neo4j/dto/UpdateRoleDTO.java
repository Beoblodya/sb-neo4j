package com.example.sb_neo4j.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateRoleDTO {
    private Long projectId;
    private Long personId;
    private String role;
}
