package com.example.jobseekingsystem.controller;

import com.example.jobseekingsystem.apiresponse.ApiResponse;
import com.example.jobseekingsystem.modle.JobApplication;
import com.example.jobseekingsystem.service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/jobapplication")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<?> getAllJobs(){
        return ResponseEntity.status(200).body(jobApplicationService.getallJobApplications());
    }



    @PostMapping("/add/{id}/{id2}")
    public ResponseEntity<?>addJob(@PathVariable Integer id, @PathVariable Integer id2 , @RequestBody @Valid JobApplication jobApplication , Errors errors){
        if(errors.hasErrors()){
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }

        int jobstatus=jobApplicationService.addjobApplicant(id, id2, jobApplication);
        if(jobstatus == 1){
            return ResponseEntity.status(400).body(new ApiResponse("job not Exists"));
        }
        else if(jobstatus == 2){
            return ResponseEntity.status(400).body(new ApiResponse("user not Exists"));
        }
        else if(jobstatus == 3){
            return ResponseEntity.status(400).body(new ApiResponse("job ID dosent match "));
        }
        else if(jobstatus == 4){
            return ResponseEntity.status(400).body(new ApiResponse("user ID dosent match"));
        }
        else if(jobstatus == 5){
            return ResponseEntity.status(400).body(new ApiResponse("Role is not job_seeker"));
        }

        else {
            return ResponseEntity.status(200).body(new ApiResponse("Job add Succfully"));
        }

    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?>deleteUser(@PathVariable Integer id ){
        boolean deleted =jobApplicationService.deleteJob(id);
        if(deleted == false) {
            return ResponseEntity.status(400).body(new ApiResponse("ID Not Found"));
        }

        return ResponseEntity.status(200).body(new ApiResponse("User Deleted Succfully"));
    }


}
