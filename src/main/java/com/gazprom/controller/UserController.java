package com.gazprom.controller;

import com.gazprom.dto.RequestDto;
import com.gazprom.dto.ResponseDto;
import com.gazprom.service.VkService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final VkService vkService;

    @Operation(summary = "Получить информацию о пользователе по идентификатору")
    @PostMapping("/info")
    public ResponseDto getUserInfo(@RequestHeader("vk_service_token") String vkServiceToken, @RequestBody @Valid RequestDto requestDto) {
        var userInfo = vkService.getUserInfo(requestDto.getUserId(), vkServiceToken);
        var member = vkService.checkExistUserIsMemberTheGroup(requestDto.getUserId(), vkServiceToken, requestDto.getGroupId());
        return vkService.getResponseDto(userInfo, member);
    }


}
