package com.example.TestProject.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "User id (generated value)")
    private Integer id;

    @Column(unique = true)
    @ApiModelProperty(notes = "User username")
    private String username;

    @ApiModelProperty(notes = "Encrypted user password")
    private String password;
}
