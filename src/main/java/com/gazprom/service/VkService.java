package com.gazprom.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.gazprom.dto.GroupDto;
import com.gazprom.dto.ResponseDto;
import com.gazprom.dto.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@Accessors(chain = true)
@RequiredArgsConstructor
public class VkService {
    @Value("${vk.api.url}")
    private String vkApiUrl;

    public UserInfoResponse getUserInfo(String userId, String accessToken) {
        log.info("Начался процесс получение информации пользователя с идентификатором: {}", userId);
        RestTemplate restTemplate = new RestTemplate();
        var url = vkApiUrl + "/users.get";
        var parameter = "?user_ids=" + userId +
                "&fields=first_name,last_name" +
                "&v=5.199" +
                "&access_token=" + accessToken;
        var jsonNode = restTemplate.getForObject(url + parameter, JsonNode.class).get("response");
        var firstElement = jsonNode.get(0);
        log.info("Процесс получение информации пользователя с идентификатором: {}, закончился", userId);
        return new UserInfoResponse()
                .setFirstName(firstElement.get("first_name").asText())
                .setLastName(firstElement.get("last_name").asText());

    }

    public boolean checkExistUserIsMemberTheGroup(String userId, String accessToken, String groupId) {
        log.info("Начался процесс проверки подписан ли пользователь с идентификатором: {}, на группу с идентификатором: {}", userId, groupId);
        RestTemplate restTemplate = new RestTemplate();
        var url = vkApiUrl + "/groups.get";
        var parameter = "?user_id=" + userId +
                "&extended=1" +
                "&v=5.199" +
                "&access_token=" + accessToken;

        var jsonNode = restTemplate.getForObject(url + parameter, JsonNode.class).get("response");
        List<GroupDto> groups = new ArrayList<>();
        for (var group : jsonNode.get("items")) {
            groups.add(new GroupDto()
                    .setId(group.get("id").asText()));
        }
        log.info("Процесс проверки подписан ли пользователь с идентификатором: {}, на группу с идентификатором: {}, закончился", userId, groupId);
        return groups.contains(new GroupDto()
                .setId(groupId));
    }

    public ResponseDto getResponseDto(UserInfoResponse userInfo, boolean member) {
        return new ResponseDto()
                .setLastName(userInfo.getLastName())
                .setFirstName(userInfo.getFirstName())
                .setMember(member);
    }
}
