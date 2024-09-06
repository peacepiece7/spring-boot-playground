package com.example.memorydb.user.controller;

import com.example.memorydb.user.model.UserEntity;
import com.example.memorydb.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path="/api/user")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PutMapping("")
    public UserEntity create(
            @RequestBody
            UserEntity userEntity
    ) {

        return userService.save(userEntity);
    }

    @GetMapping("/all")
    public List<UserEntity> findAll() {
        return userService.findAll();
    }

    // delete
    @DeleteMapping("/delete/{id}")
    public void delete(
            @PathVariable
            Long id
    ) {
        userService.delete(id);
    }

    // find by id pathValue
    @GetMapping("/find/{id}")
    public UserEntity findById(
            @PathVariable
            Long id
    ) {
        var res =  userService.wfindById(id);
        return res.get();
    }

    @GetMapping("/score")
    public List<UserEntity> filterScore(
            @RequestParam
            int score
    ) {
        return userService.filterScore(score);
    }
}
