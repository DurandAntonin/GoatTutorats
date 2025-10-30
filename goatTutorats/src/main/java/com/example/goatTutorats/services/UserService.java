package com.example.goatTutorats.services;

import com.example.goatTutorats.dtos.UserDTO;
import com.example.goatTutorats.entities.User;
import com.example.goatTutorats.exceptions.CustomEntityNotFoundException;
import com.example.goatTutorats.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;

/**
 * Service for managing User entities.
 */
@Service
public class UserService {
    /**
     * User repository.
     */
    private final UserRepository userRepository;

    /**
     * Password encoder.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Inject User repository and Password encoder.
     * @param userRepository User repository
     * @param passwordEncoder Password encoder
     */
    public UserService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Get all users.
     * @return list of users
     */
    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    /**
     * Get user by its id.
     * @param userId the user id
     * @return the user
     */
    public User getUserById(UUID userId){
        return this.userRepository.findById(userId)
                .orElseThrow(() -> new CustomEntityNotFoundException(String.format("No tutor found with id %s", userId)));
    }

    /**
     * Update user information.
     * @param userId the user id
     * @param userDTO the user data transfer object
     * @return the updated user
     */
    @Transactional
    public User updateUser(UUID userId, UserDTO userDTO){
        // retrieve user information in db
        User userToUpdate = this.getUserById(userId);

        // update user
        BeanUtils.copyProperties(userDTO, userToUpdate, "id", "roles");

        // encode password
        userToUpdate.setPassword(this.passwordEncoder.encode(userToUpdate.getPassword()));

        return this.userRepository.save(userToUpdate);
    }

    /**
     * Create a new user.
     * @param userDTO the user data transfer object
     * @return the created user
     */
    @Transactional
    public User createUser(UserDTO userDTO){
        // create empty user
        User userToCreate = new User();
        userToCreate.setRoles(new HashSet<>());

        // copy user information provided in query
        BeanUtils.copyProperties(userDTO, userToCreate, "id", "roles");

        // encode password
        userToCreate.setPassword(this.passwordEncoder.encode(userToCreate.getPassword()));

        return this.userRepository.save(userToCreate);
    }

    /**
     * Delete user by its id.
     * @param userId the user id
     */
    @Transactional
    public void deleteUser(@PathVariable UUID userId){
        // retrieve user to delete
        User userToDelete = this.getUserById(userId);
        this.userRepository.delete(userToDelete);
    }
}
