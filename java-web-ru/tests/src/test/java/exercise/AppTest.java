package exercise;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import io.javalin.Javalin;
import io.ebean.DB;

import exercise.domain.User;
import exercise.domain.query.QUser;
import io.ebean.Database;

class AppTest {

    private static Javalin app;
    private static String baseUrl;

    // BEGIN
    @BeforeAll
    public static void beforeAll() {
        app = App.getApp();
        app.start(0);
        int port = app.port();
        baseUrl = "http://localhost:" + port;
    }

    @AfterAll
    public static void afterAll() {
        app.stop();
    }

    // END

    // Между тестами база данных очищается
    // Благодаря этому тесты не влияют друг на друга
    @BeforeEach
    void beforeEach() {
        Database db = DB.getDefault();
        db.truncate("users");
        User existingUser = new User("Wendell", "Legros", "a@a.com", "123456");
        existingUser.save();
    }

    @Test
    void testUsers() {

        // Выполняем GET запрос на адрес http://localhost:port/users
        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users")
                .asString();
        // Получаем тело ответа
        String content = response.getBody();

        // Проверяем код ответа
        assertThat(response.getStatus()).isEqualTo(200);
        // Проверяем, что страница содержит определенный текст
        assertThat(response.getBody()).contains("Wendell Legros");
    }

    @Test
    void testNewUser() {

        HttpResponse<String> response = Unirest
                .get(baseUrl + "/users/new")
                .asString();

        assertThat(response.getStatus()).isEqualTo(200);
    }

    @Test
    void testCreateUser() {

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                // Устанавливаем значения полей
                .field("firstName", "Slava")
                .field("lastName", "Rukavanov")
                .field("email", "wwwrr@mail.ru")
                .field("password", "11111111")
                // Выполняем запрос и получаем тело ответ с телом в виде строки
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(302);

        User actualUser = new QUser()
                .firstName.equalTo("Slava")
                .findOne();

        assertThat(actualUser).isNotNull();
        assertThat(actualUser.getFirstName()).isEqualTo("Slava");
        assertThat(actualUser.getLastName()).isEqualTo("Rukavanov");
        assertThat(actualUser.getEmail()).isEqualTo("wwwrr@mail.ru");
        assertThat(actualUser.getPassword()).isEqualTo("11111111");

        assertThat(actualUser.getEmail()).contains("@");

        assertThat(actualUser.getPassword().length()).isGreaterThan(3);
        assertThat(actualUser.getFirstName()).isNotNull();
        assertThat(actualUser.getLastName()).isNotNull();


    }

    @Test
    public void testNotValidUser1() {

        HttpResponse<String> responsePost = Unirest
                .post(baseUrl + "/users")
                // Устанавливаем значения полей
                .field("firstName", "")
                .field("lastName", "Rukavanov")
                .field("email", "wwwrr@mail.ru")
                .field("password", "111")
                // Выполняем запрос и получаем тело ответ с телом в виде строки
                .asString();

        assertThat(responsePost.getStatus()).isEqualTo(422);

    }


}
