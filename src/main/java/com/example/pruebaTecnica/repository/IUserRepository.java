package com.example.pruebaTecnica.repository;

import com.example.pruebaTecnica.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for User
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
