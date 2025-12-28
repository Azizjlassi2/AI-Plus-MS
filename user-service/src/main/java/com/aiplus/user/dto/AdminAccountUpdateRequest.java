package com.aiplus.user.dto;

import lombok.Builder;
import lombok.Data;

/**
 * Represents update data for an Admin account.
 */
@Data
@Builder
public class AdminAccountUpdateRequest extends AccountUpdateRequest {
    // add admin-specific fields if needed
}
