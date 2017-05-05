package com.yordex.test.dl.service;

import com.yordex.test.dl.domain.Frequency;
import com.yordex.test.dl.domain.Task;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DateServiceTest {

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private DateService dateService;
    private Date date;

    /**
     * Its wrong to mock date - but for now avoiding to handle convertion date to localDate
     */
    @Before
    public void setUp() {
        dateService = new DateService();
        date = Mockito.mock(Date.class);
    }

    @Test
    public void testTodoTaskShouldBeCompletedToday() throws Exception {
        Task task = new Task();
        Mockito.when(date.toString()).thenReturn(FORMAT.format(new Date()));
        task.setDueDate(date);

        boolean isTaskForToday = dateService.isTaskForToday(task);

        Assertions.assertThat(isTaskForToday).isTrue();
    }

    @Test
    public void testTaskCanRepeatToday() throws Exception {
        Task task = new Task();
        // repeat daily - once.
        Frequency frequency = new Frequency();
        frequency.setTimes(1);
        frequency.setFrequencyType("Days");

        task.setFrequency(frequency);

        // assuming due date 1 day before
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date yesterdayDate = Mockito.mock(Date.class);

        Mockito.when(yesterdayDate.toString()).thenReturn(FORMAT.format(calendar.getTime()));
        task.setDueDate(yesterdayDate);

        boolean taskFromYesterdayRepeatedToday = dateService.doesTaskRepeatsToday(task);

        Assertions.assertThat(taskFromYesterdayRepeatedToday).isTrue();
    }

    @Test
    public void testThatTaskWithoutFrequencyCannotBeRepeated() {
        Task task = new Task();

        boolean repeats = dateService.doesTaskRepeatsToday(task);

        Assertions.assertThat(repeats).isFalse();
    }
}