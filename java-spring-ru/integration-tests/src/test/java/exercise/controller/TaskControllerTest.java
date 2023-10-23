package exercise.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;

import org.instancio.Instancio;
import org.instancio.Select;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.HashMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.datafaker.Faker;
import exercise.repository.TaskRepository;
import exercise.model.Task;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private Faker faker;

    @Autowired
    private ObjectMapper objectMapper;


    @BeforeEach
    public void createEntity() {
        var task = Instancio.of(Task.class)
                .ignore(Select.field(Task::getId))
                .supply(Select.field(Task::getTitle), () -> faker.lorem().paragraph())
                .supply(Select.field(Task::getDescription), () -> faker.lorem().paragraph())
                .create();

        taskRepository.save(task);

    }



    @Test
    public void testIndex() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    public void testShow() throws Exception {
        mockMvc.perform(get("/tasks/{id}", 2))
                .andExpect(status().isOk());


    }

    @Test
    public void testCreate() throws Exception  {
        var task = new Task();
        task.setTitle("title1");
        task.setDescription("description1");

        var request = post("/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(task));

        mockMvc.perform(request)
                .andExpect(status().isCreated());

    }

    @Test
    public void testUpdate() throws Exception {

        var data  = new HashMap<>();
        data.put("title", "Big Testing");
        data.put("description", "test for descript");

        var request = put("/tasks/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(data));

        mockMvc.perform(request)
                .andExpect(status().isOk());

        var taskUpdated = taskRepository.findByTitle("Big Testing").get();
        assertThat(taskUpdated.getTitle()).isEqualTo("Big Testing");


    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/tasks/2"))
                .andExpect(status().isOk());

    }



}

