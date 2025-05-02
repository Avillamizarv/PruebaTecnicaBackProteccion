package com.example.pruebaTecnica.controller;

import com.example.pruebaTecnica.model.User;
import com.example.pruebaTecnica.service.interfaces.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for User
 *
 * @author Adriana Villamizar Vera
 * @since 1.0
 */
@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private IUserService iUserService;

    /**
     * Return a list of all Users
     *
     * @return List<User>
     */
    @Operation(summary = "Obtiene todos los usuarios")
    @GetMapping(value = "/getUsers")
    public ResponseEntity<List<User>> findAll() {
        List<User> usersList = iUserService
                .findAll();
        if (usersList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(usersList,
                HttpStatus.OK);
    }

    /**
     * Create User
     *
     * @return UserDTO
     */
    @PostMapping(value = "/newUser")
    public ResponseEntity<User> createUser(@RequestBody User aUser) {
        var user = this.iUserService.createUser(aUser);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    /**
     * Update User
     */

    @PutMapping("/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody User aUser) {
        this.iUserService.updateUser(aUser);
        return new ResponseEntity<>(aUser, HttpStatus.CREATED);
    }

    /**
     * Delete User
     */
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable(value = "id") Long aId) {
        this.iUserService.deleteUser(aId);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    //Injections
    @Autowired
    public void setiUserService(IUserService iUserService) {
        this.iUserService = iUserService;
    }
}

