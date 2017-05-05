package com.yordex.test.dl.rest;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.yordex.test.dl.domain.Task;
import com.yordex.test.dl.domain.User;
import com.yordex.test.dl.service.TaskService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api", produces = APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class TaskResource {

    private final TaskService taskService;

    @ResponseBody
    @GetMapping("/tasks")
    public List<Task> todaysTasks(Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        return taskService.todaysTask(user);
    }

    @ResponseBody
    @PostMapping("/tasks/complete/{id}")
    public Task toggleComplete(@PathVariable Long id) {
        return taskService.toggleComplete(id);
    }

    @ResponseBody
    @PostMapping("/tasks/delete/{id}")
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PostMapping("/tasks/create")
    public Task addTask(@RequestBody Task task, Principal principal) {
        User user = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        task.setUser(user);
        return taskService.save(task);
    }
}
