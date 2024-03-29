package exercise.controller;

import exercise.dto.UserDto;
import exercise.model.User;
import exercise.repository.UserRepository;
import exercise.model.UserRole;
import exercise.UserNotFoundException;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    @GetMapping(path = "")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable long id) {

        return userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    @PostMapping(path = "")
    public User createUser(@RequestBody UserDto dto) {
        User user = new User();
        user.setUsername(dto.username());
        user.setEmail(dto.email());
        user.setPassword(encoder.encode(dto.password()));
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }

    @DeleteMapping(path = "/{id}")
   // @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable long id) {

        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }
}
