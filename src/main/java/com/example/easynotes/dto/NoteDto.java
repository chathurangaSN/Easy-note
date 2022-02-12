package com.example.easynotes.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class NoteDto implements Serializable {


    private Long id;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;

    public NoteDto(){}

    public NoteDto(String title, String content) {
        this.title = title;
        this.content = content;
    }


}
