package com.example.TestProject.dto;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Entity
@Table(name = "notes")
@Data
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
@ApiModel(description = "Note")
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Note id (generated value)")
    private Integer id;

    @ApiModelProperty(notes = "Note title")
    private String title;

    @ApiModelProperty(notes = "Note content")
    private String content;

    @Type(type = "jsonb")
    @ApiModelProperty(notes = "Note fields (jsonb type)")
    private Object fields;

    @ManyToOne
    @JoinColumn(name = "author_id")
    @ApiModelProperty(notes = "Author of note")
    private User author;
}
