package com.aiplus.user.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents update data for a Client account.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClientAccountUpdateRequest extends AccountUpdateRequest {

    private String web_site;
    private String bio;
    private String address;
    private String company;
    private String job_title;
    private String phone_number;

}
