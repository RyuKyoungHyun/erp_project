package com.erp.ezen25.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberDTO {
    private Long memberId;
    private String userId;
    private String password;
    private String authority;
    private String email;
    private String name;
    private Integer percent;
}
