package com.activiti.activitisample.hiring;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ActivitiSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(ActivitiSampleApplication.class, args);
	}
   /*
	@Bean
	CommandLineRunner init(RuntimeService runtimeService){
		return args -> {
			System.out.println("Available process");
			Map<String,Object> inputVariables = new HashMap<>();
			inputVariables.put("applicantName","Ramesh");
			inputVariables.put("phoneNumber","9831626373");
			inputVariables.put("email","angikar.roychowdhury@gmail.com");
			inputVariables.put("telephoneInterviewOutcome",true);
			inputVariables.put("techOk",true);
			inputVariables.put("financialOk",true);

			ProcessInstance instance = runtimeService.startProcessInstanceByKey("hireProcess",inputVariables);
			System.out.println("Started Process Instance:"+instance.getId());
		};
	}*/
}
