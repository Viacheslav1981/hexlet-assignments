package ru.hexlet.lections.trees.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDto {
    private String name;
    private String lastname;
    private List<String> parentNames;
}
