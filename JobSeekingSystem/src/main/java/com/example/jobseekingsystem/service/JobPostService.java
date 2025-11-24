package com.example.jobseekingsystem.service;

import com.example.jobseekingsystem.modle.JobPost;
import com.example.jobseekingsystem.modle.User;
import com.example.jobseekingsystem.repository.JobPostRepository;
import com.example.jobseekingsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    public List<JobPost> getAllJobPost(){
        return jobPostRepository.findAll();
    }

    public int addJob(Integer id ,JobPost jobPost){

        User oldUser = userRepository.findById(id).orElse(null);
        if(oldUser == null){
            return 1;
        }
        else if(!oldUser.getRole().equalsIgnoreCase("EMPLOYER")) {
            return 2;
        }
           else {
            jobPostRepository.save(jobPost);
            return 3;
        }

    }




    public int UpdateJobPost(Integer id , JobPost jobPost){
        JobPost oldJobPost =jobPostRepository.findById(id).orElse(null);
        User oldUser = userRepository.findById(id).orElse(null);

        if(oldJobPost == null){
            return 1;
        }
        if(!oldUser.getRole().equalsIgnoreCase("EMPLOYER")) {
            return 2;
        }
            oldJobPost.setTitle(jobPost.getTitle());
            oldJobPost.setDescription(jobPost.getDescription());
            oldJobPost.setSalary(oldJobPost.getSalary());
            oldJobPost.setPostingDate(oldJobPost.getPostingDate());
            jobPostRepository.save(oldJobPost);
            return 3;
        }



    public boolean deleteJobPost(Integer id){
       JobPost oldJobPost =jobPostRepository.findById(id).orElse(null);
       if(oldJobPost == null){
           return false;
       }
       jobPostRepository.delete(oldJobPost);
       return true;

    }
}
