package com.gazprom.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ResponseDto {
    private String firstName;
    private String lastName;
    private boolean member;
}
