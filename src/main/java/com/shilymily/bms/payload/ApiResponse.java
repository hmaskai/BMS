package com.shilymily.bms.payload;

import lombok.Data;

/**
 * Created by hmaskai on 12/21/18.
 */
@Data
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
