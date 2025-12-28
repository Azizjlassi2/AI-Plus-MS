package com.aiplus.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientAccountDto extends AccountDto {

    private Long id;

    private String web_site;
    private String bio;
    private String address;
    private String company;
    private String job_title;

    private Integer phone_number;

}
