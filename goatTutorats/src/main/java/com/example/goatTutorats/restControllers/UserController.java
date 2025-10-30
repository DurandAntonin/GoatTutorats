package com.example.goatTutorats.restControllers;

import com.example.goatTutorats.dtos.UserDTO;
import com.example.goatTutorats.entities.User;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.services.CustomUserDetailsService;
import com.example.goatTutorats.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * This class is a REST controller for managing users.
 */
@CrossOrigin("*")
@RestController
@RequestMapping("user")
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {

    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;

    /**
     * Constructor for UserController.
     * @param userDetailsService user details service
     * @param userService user service
     */
    public UserController(CustomUserDetailsService userDetailsService, UserService userService) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
    }

    @Operation(summary = "Get a user by his username", description = "Get a user by his username")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/get-user-by-username/{username}")
    public User getUserByUsername(@PathVariable String username){
        try{
            return (User) this.userDetailsService.loadUserByUsername(username);
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @Operation(summary = "Get a user by his id", description = "Get a user by his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/get-user-by-id/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable UUID userId){
        try{
            return new ResponseEntity<>(this.userService.getUserById(userId), HttpStatus.OK);
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @Operation(summary = "Get all users", description = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User information",
                    content = @Content(schema = @Schema(implementation = User.class))),
    })
    @GetMapping("/get-all-users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(),  HttpStatus.OK);
    }

    @Operation(summary = "Update user", description = "Update user information such as username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema()))
    })
    @PatchMapping("/update-user/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody UserDTO userDTO){
        try{
            return new ResponseEntity<>(userService.updateUser(userId, userDTO), HttpStatus.OK);
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }
    }

    @Operation(summary = "Create a new user", description = "Add a new user to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User created",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "409", description = "User with username provided already exists in db",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(this.userService.createUser(userDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a user", description = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully",
                    content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<User> deleteUser(@PathVariable UUID userId){
        try{
            this.userService.deleteUser(userId);
        }
        catch (CustomEntityNotFoundException exception){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, exception.getMessage(), exception);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
