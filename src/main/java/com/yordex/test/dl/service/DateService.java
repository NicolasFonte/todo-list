package com.yordex.test.dl.service;

import com.yordex.test.dl.domain.Task;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.springframework.stereotype.Service;

@Service
public class DateService {

    public boolean isTaskForToday(Task task) {
        LocalDate taskDueDate = LocalDate.parse(task.getDueDate().toString());
        return taskDueDate.isEqual(LocalDate.now());
    }

    public boolean doesTaskRepeatsToday(Task task) {
        if (task.getFrequency() != null) {
            LocalDate taskDueDate = LocalDate.parse(task.getDueDate().toString());
            int times = task.getFrequency().getTimes();
            String frequencyType = task.getFrequency().getFrequencyType();
            ChronoUnit chronoUnit = getTimeUnit(frequencyType);

            return IntStream.rangeClosed(1, times)
                    .anyMatch(time -> {
                        LocalDate nextTask = taskDueDate.plus(time, chronoUnit);
                        return nextTask.isEqual(LocalDate.now());
                    });
        }

        return false;
    }

    private ChronoUnit getTimeUnit(String frequencyType) {
        return Stream.of(ChronoUnit.values())
                .filter(value -> value.toString().equals(frequencyType))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("Frequency does not exists: " + frequencyType));
    }
}
