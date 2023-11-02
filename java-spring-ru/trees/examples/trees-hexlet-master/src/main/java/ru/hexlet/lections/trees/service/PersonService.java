package ru.hexlet.lections.trees.service;

import ru.hexlet.lections.trees.dto.PersonDto;
import ru.hexlet.lections.trees.dto.ShortPersonDto;
import ru.hexlet.lections.trees.model.Person;

public interface PersonService {
    Person save(ShortPersonDto shortPersonDto);
    ShortPersonDto getShortPersonDtoById(Long id);

    PersonDto getPersonDtoById(Long id);
}
