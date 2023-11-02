package ru.hexlet.lections.trees.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.hexlet.lections.trees.dto.PersonDto;
import ru.hexlet.lections.trees.dto.ShortPersonDto;
import ru.hexlet.lections.trees.model.Person;
import ru.hexlet.lections.trees.service.PersonService;

@Tag(name = "Person", description = "The Person API")
@RestController
@RequestMapping("/v1.0/people")
public class PersonController {
    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Gets short person DTO", tags = "person")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the person",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )})
    })
    @GetMapping("/short/{id}/")
    public ShortPersonDto getShortPersonById(@PathVariable("id") Long id) {
        return this.personService.getShortPersonDtoById(id);
    }

    @GetMapping("/{id}/")
    public PersonDto getPersonById(@PathVariable("id") Long id) {
        return this.personService.getPersonDtoById(id);
    }

    @PostMapping("/")
    public Person createPerson(@RequestBody ShortPersonDto shortPersonDto) {
        return this.personService.save(shortPersonDto);
    }
}
