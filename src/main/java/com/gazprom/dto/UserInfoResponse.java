package com.gazprom.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserInfoResponse {
    private String lastName;
    private String firstName;
    }
