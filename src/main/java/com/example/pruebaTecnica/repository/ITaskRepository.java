package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for Task
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Repository
public interface ITaskRepository extends JpaRepository<Task, Integer> {
}
