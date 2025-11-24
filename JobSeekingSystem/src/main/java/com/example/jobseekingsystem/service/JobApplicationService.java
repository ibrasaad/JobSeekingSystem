package com.example.jobseekingsystem.service;

import com.example.jobseekingsystem.modle.JobApplication;
import com.example.jobseekingsystem.modle.JobPost;
import com.example.jobseekingsystem.modle.User;
import com.example.jobseekingsystem.repository.JobApplicationRepository;
import com.example.jobseekingsystem.repository.JobPostRepository;
import com.example.jobseekingsystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;


    public List<JobApplication> getallJobApplications(){
        return jobApplicationRepository.findAll();
    }

    public int addjobApplicant (Integer id, Integer id2 ,JobApplication jobApplication){

        JobPost job =jobPostRepository.getById(id);
        User user = userRepository.getById(id);

        if(job == null){
            return 1;
        }
        if(user == null){
            return 2;
        }
        if(!job.getId().equals(id) ){
            return 3;
        }
        if(!user.getId().equals(id2)){
            return 4;
        }
        if(!user.getRole().equals("job_seeker")){
            return 5;
        }
        jobApplicationRepository.save(jobApplication);
        return 6;
    }

    public boolean deleteJob(Integer id){
        JobApplication jb =jobApplicationRepository.getById(id);

        if(!jb.getId().equals(id)){
            return false;
        }
        jobApplicationRepository.delete(jb);
        return true;
    }







}
