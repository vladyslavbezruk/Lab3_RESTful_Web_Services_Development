package com.lab3;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Анотація для позначення класу як REST контролера
@RestController
// Базовий шлях для всіх запитів до цього контролера
@RequestMapping("/users")
public class UserController {

    // Локальний список для зберігання користувачів
    private List<User> users = new ArrayList<>();

    // Отримати список всіх користувачів
    @GetMapping
    public List<User> getAllUsers() {
        return users;
    }

    // Отримати користувача за його ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable int id) {
        // Пошук користувача за ID у списку
        Optional<User> user = users.stream().filter(u -> u.getId() == id).findFirst();
        // Повернути знайденого користувача або статус 404, якщо не знайдено
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Додати нового користувача
    @PostMapping
    public User createUser(@RequestBody User user) {
        // Додати користувача до списку
        users.add(user);
        return user;
    }

    // Оновити інформацію про користувача
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id, @RequestBody User updatedUser) {
        // Пошук користувача у списку за ID
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                // Оновити інформацію про користувача
                users.set(i, updatedUser);
                return ResponseEntity.ok(updatedUser);
            }
        }
        // Якщо користувача не знайдено, повернути статус 404
        return ResponseEntity.notFound().build();
    }

    // Видалити користувача за ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        // Видалити користувача зі списку, якщо знайдено
        if (users.removeIf(u -> u.getId() == id)) {
            // Повернути статус 204 No Content
            return ResponseEntity.noContent().build();
        }
        // Якщо користувача не знайдено, повернути статус 404
        return ResponseEntity.notFound().build();
    }
}

// Клас для представлення користувача
class User {
    private int id; // Унікальний ідентифікатор користувача
    private String name; // Ім'я користувача
    private String email; // Email користувача

    // Геттери та сеттери для доступу до полів
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}