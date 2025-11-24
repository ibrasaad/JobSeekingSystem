package com.example.jobseekingsystem.repository;

import com.example.jobseekingsystem.modle.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobPostRepository extends JpaRepository <JobPost , Integer> {
}
