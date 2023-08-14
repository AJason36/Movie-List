package com.nostratech.movie.dto;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsersQueryDTO implements Serializable{
    private String username;
}
