package ru.hexlet.lections.trees.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShortPersonDto {
    private String name;
    private String lastname;
    private Long parentId;
}
