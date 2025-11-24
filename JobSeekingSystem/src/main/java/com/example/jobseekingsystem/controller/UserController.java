package com.example.jobseekingsystem.controller;

import com.example.jobseekingsystem.apiresponse.ApiResponse;
import com.example.jobseekingsystem.modle.User;
import com.example.jobseekingsystem.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllusers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }
@PostMapping("/add")
    public ResponseEntity<?> addnewUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        userService.addnewUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User Add Succfully"));

    }

@PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        if(userService.updateUser(id, user) == false){
            return ResponseEntity.status(400).body(new ApiResponse("User ID Not Found"));

        }
         return ResponseEntity.status(200).body(new ApiResponse("User Updated Succfully"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Integer id ){
       if (userService.deleteUser(id) == false){
           return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
       }
        return ResponseEntity.status(200).body(new ApiResponse("User Deleted Succfully"));
    }

}
