package com.example.TestProject.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoteRequestBody {

    private Integer id;

    private String title;

    private String content;

    private Object fields;
}
