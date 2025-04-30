package com.activiti.activitisample.hiring.service;

import org.springframework.stereotype.Component;

@Component("resumeService")
public class ResumeService {
    public void storeResume(){
        System.out.println("Resume is saved successfully");
    }
}
