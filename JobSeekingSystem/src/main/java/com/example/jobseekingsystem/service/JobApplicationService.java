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

    public int addjobApplicant (Integer jobid, Integer userid ,JobApplication jobApplication){

        JobPost job =jobPostRepository.findById(jobid).orElse(null);
        User user = userRepository.findById(userid).orElse(null);

        if(job == null){
            return 1;
        }
        if(user == null){
            return 2;
        }
        if(!job.getId().equals(jobid) ){
            return 3;
        }
        if(!user.getId().equals(userid)){
            return 4;
        }
        if(!user.getRole().equalsIgnoreCase("job_seeker")){
            return 5;
        }
        jobApplication.setUser_id(user.getId());
        jobApplication.setJobPosted_id(job.getId());
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
