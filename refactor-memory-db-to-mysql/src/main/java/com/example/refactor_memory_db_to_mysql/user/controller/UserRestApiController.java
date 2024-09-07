package com.example.refactor_memory_db_to_mysql.user.controller;


import com.example.refactor_memory_db_to_mysql.user.db.UserRepository;
import com.example.refactor_memory_db_to_mysql.user.model.UserEntity;
import com.example.refactor_memory_db_to_mysql.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/user")
@RequiredArgsConstructor
public class UserRestApiController {
    private final UserRepository userRepository;
    private final UserService userService;

    /**
     * @apiNote 모든 사용자 정보를 가저온다.
     */
    @GetMapping("/all")
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }


    /**
     * @apiNote 사용자 정보를 추가 한다.
     */
    @PostMapping("")
    public UserEntity create(
            @RequestBody
            UserEntity userEntity
    ) {
        return userRepository.save(userEntity);
    }


    /**
     * @apiNote 사용자 정보를 제거한다.
     */
    @Transactional
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable  Long id) {
       userRepository.findById(id).ifPresent(userRepository::delete);
    }

    /**
     * @apiNote 일치하는 사용자 정보를 가져온다.
     */
    @GetMapping("/find/{id}")
    public Optional<UserEntity> findById(@PathVariable Long id) {
      return userRepository.findById(id);
    }

    // 인터페이스인데 이커 어캄
    //  @GetMapping("/score")
    //  public List<UserEntity> filterScore(
    //          @RequestParam
    //          Integer age
    //  ) {
    //      return userRepository.filterAge(age);
    //  }


    @GetMapping("/min_max")
    public List<UserEntity> filterScore(
            @RequestParam int min,
            @RequestParam int max
    ) {
        return userService.filterScore(min,max);
    }

}
