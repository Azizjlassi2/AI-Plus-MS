package com.aiplus.user.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Represents a client account with specific fields like website, bio, address,
 * company, job title, and phone number. Manages relationships with favorite AI
 * models.
 * 
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientAccount extends Account {

    private String web_site;
    private String bio;
    private String address;
    private String company;
    private String job_title;

    private String phone_number;

}
