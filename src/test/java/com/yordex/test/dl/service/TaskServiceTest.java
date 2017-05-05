package com.yordex.test.dl.service;

import com.yordex.test.dl.domain.Task;
import com.yordex.test.dl.domain.User;
import com.yordex.test.dl.repository.TaskRepository;
import java.util.Collections;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class TaskServiceTest {

    private TaskRepository taskRepository = Mockito.mock(TaskRepository.class);
    private DateService dateService = Mockito.mock(DateService.class);

    private TaskService taskService;

    @Before
    public void setUp() {
        taskService = new TaskService(taskRepository, dateService);
    }

    @Test
    public void testTaskCanBeMarkedAsCompleted() {
        Task task = new Task();
        task.setComplete(false);
        Mockito.when(taskRepository.findOne(1L)).thenReturn(task);

        taskService.toggleComplete(1L);

        ArgumentCaptor<Task> captor = ArgumentCaptor.forClass(Task.class);
        Mockito.verify(taskRepository).save(captor.capture());
        Task taskCompleted = captor.getValue();

        Assertions.assertThat(taskCompleted.isComplete()).isTrue();
    }

    @Test
    public void testTodaysTasksCanBeRetrievedIfDueDateToday() {
        Task task = new Task();
        task.setName("My task for today");
        List<Task> tasks = Collections.singletonList(task);
        Mockito.when(taskRepository.findByUser(Mockito.any(User.class))).thenReturn(tasks);
        Mockito.when(dateService.isTaskForToday(task)).thenReturn(true);

        List<Task> todaysTask = taskService.todaysTask(new User());

        Assertions.assertThat(todaysTask).containsOnly(task);
    }

    @Test
    public void testTodaysTasksCanBeRetrievedIfRepeatsToday() {
        Task task = new Task();
        task.setName("My task repeated today");
        List<Task> tasks = Collections.singletonList(task);
        Mockito.when(taskRepository.findByUser(Mockito.any(User.class))).thenReturn(tasks);
        Mockito.when(dateService.isTaskForToday(task)).thenReturn(false);
        Mockito.when(dateService.doesTaskRepeatsToday(task)).thenReturn(true);

        List<Task> todaysTask = taskService.todaysTask(new User());

        Assertions.assertThat(todaysTask).containsOnly(task);
    }

    // TODO : add dummy tests for delete/save/etc
}