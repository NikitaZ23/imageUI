package com.example.imageUI.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TagDto {
    int id;
    String name;
    LocalDateTime created;
    LocalDateTime modified;
}
