package com.aiplus.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class DeveloperAccountDto extends AccountDto {

    private String web_site;
    private String bio;

    private Integer phone_number;
    private String address;
    private String linkedin;
    private String github;
    private String docker_username;
    private String docker_pat;

}
