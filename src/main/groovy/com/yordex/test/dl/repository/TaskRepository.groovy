package com.yordex.test.dl.repository

import com.yordex.test.dl.domain.Task
import org.springframework.data.jpa.repository.JpaRepository

public interface TaskRepository extends JpaRepository<Task, Long>{
}
