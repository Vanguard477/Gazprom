package com.gazprom;

import com.gazprom.controller.UserController;
import com.gazprom.dto.RequestDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserControllerTest {
    @Autowired
    private UserController userController;


    @Test
    @DisplayName("Получить данные пользователя подписанного на группу по его идентификатору")
    public void existUserIsMemberTheGroup() {
        var requestDto = new RequestDto()
                .setUserId("236791084")
                .setGroupId("195172921");
        var result = userController.getUserInfo("e660b0c5e660b0c5e660b0c500e557543aee660e660b0c58eed4f1edc22b14b8426b135", requestDto);
        assertEquals("Nikolay", result.getFirstName());
        assertEquals("Alexeev", result.getLastName());
        assertTrue(result.isMember());
    }

    @Test
    @DisplayName("Получить данные пользователя подписанного на группу по его идентификатору")
    public void existUserNotMemberTheGroup() {
        var requestDto = new RequestDto()
                .setUserId("236791084")
                .setGroupId("3951729211");
        var result = userController.getUserInfo("e660b0c5e660b0c5e660b0c500e557543aee660e660b0c58eed4f1edc22b14b8426b135", requestDto);
        assertEquals("Nikolay", result.getFirstName());
        assertEquals("Alexeev", result.getLastName());
        assertFalse(result.isMember());
    }

}
