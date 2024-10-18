package org.example.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserDtoMg {

    private String name;

    private String email;

    private Integer age;

    private String status;

    private LocalDateTime registeredAt;

    private LocalDateTime unRegisteredAt;

    private LocalDateTime lastLoginAt;


    public UserDtoMg(String name, String email, Integer age, String status, LocalDateTime registeredAt, LocalDateTime unRegisteredAt, LocalDateTime lastLoginAt) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.status = status;
        this.registeredAt = registeredAt;
        this.unRegisteredAt = unRegisteredAt;
        this.lastLoginAt = lastLoginAt;
    }

    @Override
    public String toString() {
        return "UserDtoMg{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", status='" + status + '\'' +
                ", registeredAt=" + registeredAt +
                ", unRegisteredAt=" + unRegisteredAt +
                ", lastLoginAt=" + lastLoginAt +
                '}';
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public LocalDateTime getUnRegisteredAt() {
        return unRegisteredAt;
    }

    public void setUnRegisteredAt(LocalDateTime unRegisteredAt) {
        this.unRegisteredAt = unRegisteredAt;
    }

    public LocalDateTime getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }
}
