package com.yordex.test.dl.service;

import com.yordex.test.dl.domain.Task;
import com.yordex.test.dl.domain.User;
import com.yordex.test.dl.repository.TaskRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final DateService dateService;

    public Task toggleComplete(Long id) {
        Task task = taskRepository.findOne(id);
        task.setComplete(!task.isComplete());
        return taskRepository.save(task);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public void delete(Long id) {
        taskRepository.delete(id);
    }

    public List<Task> todaysTask(User user) {
        return taskRepository.findByUser(user).stream()
                .filter(task -> isToday(task) || repeatedToday(task))
                .collect(Collectors.toList());
    }

    private boolean repeatedToday(Task task) {
        return dateService.doesTaskRepeatsToday(task);
    }

    private boolean isToday(Task task) {
        return dateService.isTaskForToday(task);
    }
}
