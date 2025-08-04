package com.gazprom.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Schema(description = "Запрос с идентификатором пользователя, идентификатором группы и токен")
public class RequestDto {
    @Schema(description = "Идентификатор пользователя", example = "1234567")
    @Size(min = 1, max = 9, message = "Идентификатор пользователя должен содержать от 1 до 9 символов")
    @NotBlank(message = "Идентификатор пользователя не может быть пустым")
    @Pattern(regexp = "^[0-9]+$", message = "userId должен содержать только цифры")
    private String userId;
    @Schema(description = "Идентификатор группы", example = "1234567")
    @Size(min = 1, max = 9, message = "Идентификатор группы должен содержать от 1 до 9 символов")
    @NotBlank(message = "Идентификатор группы не может быть пустым")
    @Pattern(regexp = "^[0-9]+$", message = "groupId должен содержать только цифры")
    private String groupId;
}
