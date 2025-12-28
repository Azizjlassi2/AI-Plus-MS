package com.aiplus.user.dto;

import lombok.Data;

/**
 * DTO for updating Docker credentials.
 */
@Data
public class DockerCredentialsUpdateRequest {
    private String dockerUsername;
    private String dockerPat; // Personal Access Token for Docker Hub

}
