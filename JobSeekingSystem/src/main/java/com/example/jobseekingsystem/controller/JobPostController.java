package com.example.jobseekingsystem.controller;

import com.example.jobseekingsystem.apiresponse.ApiResponse;
import com.example.jobseekingsystem.modle.JobPost;
import com.example.jobseekingsystem.service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/jobpost")
@RequiredArgsConstructor
public class JobPostController {
    private final JobPostService jobPostService;


    @GetMapping("/get")
    public ResponseEntity<?>getAllJobs(){
        return ResponseEntity.status(200).body(jobPostService.getAllJobPost());
    }


    @PostMapping("/add/{id}")
    public ResponseEntity<?>addJob(@PathVariable Integer id , @RequestBody @Valid JobPost jobPost , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        int jobstatus=jobPostService.addJob(id, jobPost);
        if(jobstatus == 1){
            return ResponseEntity.status(400).body(new ApiResponse("There is no ID matched"));
        }
        else if(jobstatus == 2){
            return ResponseEntity.status(400).body(new ApiResponse("Job Poster Title Not EMPLOYER"));
        }

        else {
            return ResponseEntity.status(200).body(new ApiResponse("Job add Succfully"));
        }

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?>updateJob(@PathVariable Integer id , @RequestBody @Valid JobPost jobPost , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        int updatejob=jobPostService.UpdateJobPost(id, jobPost);

        if(updatejob == 1){
            return ResponseEntity.status(400).body(new ApiResponse("There is no Job to Updated"));
        }
        else if(updatejob == 2){
            return ResponseEntity.status(400).body(new ApiResponse("Job Poster Title Not EMPLOYER"));
        }
        else {
            return ResponseEntity.status(200).body(new ApiResponse("Job Updated  Succfully"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteJob(@PathVariable Integer id ){

        if(jobPostService.deleteJobPost(id) == false){
            return ResponseEntity.status(400).body(new ApiResponse(" Job Not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("Updated Suucfully"));

    }



}
