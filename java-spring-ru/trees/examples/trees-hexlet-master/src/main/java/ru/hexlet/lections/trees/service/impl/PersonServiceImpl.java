package ru.hexlet.lections.trees.service.impl;

import ru.hexlet.lections.trees.dto.PersonDto;
import ru.hexlet.lections.trees.dto.ShortPersonDto;
import ru.hexlet.lections.trees.model.Person;
import ru.hexlet.lections.trees.repository.PersonRepository;
import ru.hexlet.lections.trees.service.PersonService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl implements PersonService {
    private static final String SEVERAL_PEOPLE_PATH_PATTERN = "%s.%s";
    private static final String DOT_STRING = "\\.";
    private static final String EMPTY_STRING = "";
    private PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person save(ShortPersonDto shortPersonDto) {
        return personRepository.save(toPerson(shortPersonDto));
    }

    @Override
    public ShortPersonDto getShortPersonDtoById(Long id) {
        Person person = personRepository.findPersonById(id);
        return toShortPersonDto(person);
    }

    @Override
    public PersonDto getPersonDtoById(Long id) {
        Person person = this.personRepository.findPersonById(id);
        return toPersonDto(person);
    }

    private PersonDto toPersonDto(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setName(person.getName());
        personDto.setLastname(person.getLastname());
        personDto.setParentNames(getParentNamesByPath(person.getPath()));

        return personDto;
    }

    private List<String> getParentNamesByPath(String path) {
        List<Person> peopleList = getPeopleByPath(path);
        return peopleList != null
                ? peopleList.stream()
                .map(Person::getName)
                .collect(Collectors.toList())
                : null;
    }

    private List<Person> getPeopleByPath(String path) {
        List<Long> parentIds = getParentIdsByPath(path);
        return parentIds != null
                ? this.personRepository.findPeopleByIds(parentIds)
                : null;
    }

    private ShortPersonDto toShortPersonDto(Person person) {
        ShortPersonDto shortPersonDto = new ShortPersonDto();
        shortPersonDto.setName(person.getName());
        shortPersonDto.setLastname(person.getLastname());
        shortPersonDto.setParentId(getLastParentIdByPath(person.getPath()));
        return shortPersonDto;
    }

    private Person toPerson(ShortPersonDto shortPersonDto) {
        Person person = new Person();
        person.setName(shortPersonDto.getName());
        person.setLastname(shortPersonDto.getLastname());
        person.setPath(getPathByParentId(shortPersonDto.getParentId()));
        return person;
    }

    private String getPathByParentId(Long parentId) {
        if (parentId != null) {
            String parentPath = this.personRepository.findParentPathById(parentId);
            if (parentPath != null) {
                return String.format(SEVERAL_PEOPLE_PATH_PATTERN, parentPath, parentId);
            }
            return String.valueOf(parentId);
        }
        return null;
    }

    private Long getLastParentIdByPath(String path) {
        List<Long> parentIds = getParentIdsByPath(path);
        if (parentIds != null) {
            return parentIds.get(parentIds.size() - 1);
        }
        return null;
    }

    private List<Long> getParentIdsByPath(String path) {
        if (path != null && !EMPTY_STRING.equals(path)) {
            return Arrays.stream(path.split(DOT_STRING))
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
        }
        return null;
    }
}
