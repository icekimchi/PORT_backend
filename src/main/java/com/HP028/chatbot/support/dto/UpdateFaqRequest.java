package com.HP028.chatbot.support.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFaqRequest {

    private List<String> deleteMediaFileUrl = new ArrayList<>();

    private String title;

    private String content;

}
