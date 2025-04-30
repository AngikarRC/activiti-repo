package com.activiti.activitisample.hiring.controller;

import com.activiti.activitisample.hiring.service.HiringService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hiring")
public class HiringController {
    private final HiringService hiringService;

    public HiringController(HiringService hiringService) {
        this.hiringService = hiringService;
    }

    @PostMapping("/start")
    public ResponseEntity<String> startHiring(@RequestBody Map<String,Object> variables){
        String instanceId = hiringService.startHiringProcess(variables);
        return ResponseEntity.ok("Process started "+instanceId);
    }

    @GetMapping("/tasks/all")
    public ResponseEntity<List<Map<String,String>>> getAllTasksWithAssigness(){
        return ResponseEntity.ok(hiringService.getAllTasksWIthAssigness());
    }
    @GetMapping("/tasks/{assignee}")
    public ResponseEntity<List<Map<String,String>>> getTasks(@PathVariable String assignee){
        return ResponseEntity.ok(hiringService.getTasksByAssignee(assignee));
    }

//    @PostMapping("/claim/{taskId}")
//    public ResponseEntity<String> claimTask(@PathVariable String taskId, @RequestParam String userId) {
//        try {
//            hiringService.claimTask(taskId, userId);
//            return ResponseEntity.ok("Task claimed by " + userId);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Claim failed: " + e.getMessage());
//        }
//    }

    @PostMapping("/complete/{taskId}")
    public ResponseEntity<String> completeTask( @PathVariable String taskId,
                                                @RequestBody(required=false) Map<String,Object> variables){
        hiringService.completeTask(taskId,variables);

        return ResponseEntity.ok("Task "+taskId+ " completed");
    }

    @GetMapping("/all-tasks")
    public ResponseEntity<List<Map<String, Object>>> getAllTasks() {
        return ResponseEntity.ok(hiringService.getAllActiveTasks());
    }
}
