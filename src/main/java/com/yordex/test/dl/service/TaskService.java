package com.yordex.test.dl.service;

import com.yordex.test.dl.domain.Task;
import com.yordex.test.dl.repository.TaskRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

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
}
