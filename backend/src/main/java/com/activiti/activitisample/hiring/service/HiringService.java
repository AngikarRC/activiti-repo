package com.activiti.activitisample.hiring.service;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HiringService {

    private final RuntimeService runtimeService;
    private final TaskService taskService;

    //private final IdentityService identityService;

    public HiringService(RuntimeService runtimeService, TaskService taskService) {
        this.runtimeService = runtimeService;
        this.taskService = taskService;
    }
    //this will start the process with the inputs
    public String startHiringProcess(Map<String,Object> input){
        ProcessInstance processInstance =
                runtimeService.startProcessInstanceByKey("hireProcess",input);
        return processInstance.getId();
    }

    //this will fetch the task assigned to a particular user
    public List<Map<String,String>> getTasksByAssignee(String assignee){
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(assignee).list();

        List<Map<String,String>> taskList = new ArrayList<>();
        for(Task task:tasks){
            Map<String,String> t = new HashMap<>();
            t.put("taskId",task.getId());
            t.put("name",task.getName());
            t.put("processInstanceId",task.getProcessInstanceId());
            taskList.add(t);
        }
        return taskList;
    }
    //get all the tasks with assignees
    public List<Map<String,String>> getAllTasksWIthAssigness(){
        List<Task> tasks = taskService.createTaskQuery().list();

        List<Map<String,String>> taskList = new ArrayList<>();
        for(Task task:tasks){
            Map<String,String> t = new HashMap<>();
            t.put("taskId",task.getId());
            t.put("taskName",task.getName());
            t.put("assignee",task.getAssignee()!=null ? task.getAssignee():"Unassigned");
            taskList.add(t);
        }
        return taskList;
    }
    /*
    public void claimTask(String taskId, String userId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

        if (task.getAssignee() != null) {
            throw new RuntimeException("Task is already claimed by " + task.getAssignee());
        }

        List<Group> groups = identityService.createGroupQuery().groupMember(userId).list();
        List<String> groupIds = groups.stream().map(Group::getId).toList();

        boolean canClaim = taskService.createTaskQuery()
                .taskId(taskId)
                .taskCandidateGroupIn(groupIds)
                .singleResult() != null;

        if (!canClaim) {
            throw new RuntimeException("User not allowed to claim this task.");
        }

        taskService.claim(taskId, userId);
    }
*/
    public void completeTask(String taskId, Map<String,Object> vars){
        System.out.println("Completeing Task "+ taskId + " with variables ->"+ vars);
        if(vars==null){
            vars = new HashMap<>();
        }
        taskService.complete(taskId,vars);
    }

    public List<Map<String, Object>> getAllActiveTasks() {
        return taskService.createTaskQuery().active().list().stream()
                .map(task -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", task.getId());
                    map.put("name", task.getName());
                    map.put("assignee", task.getAssignee());
                    map.put("processInstanceId", task.getProcessInstanceId());
                    return map;
                }).toList();
    }

}
