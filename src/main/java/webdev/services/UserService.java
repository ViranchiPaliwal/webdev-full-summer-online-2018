package webdev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import webdev.models.User;
import webdev.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")	
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
//	@GetMapping("/api/register")	
//	public List<User> findAllUserss() {
//		return (List<User>) userRepository.findAll();
//	}
	
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user){
		return userRepository.save(user);
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user){
		List<User> users = (List<User>) userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
		if(users.isEmpty()) {
			 throw new NullPointerException("User not found exception");
		}
		return users.get(0);
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void DeleteUser(@PathVariable("userId") int id){
		userRepository.deleteById(id);
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int id){
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			return user.get();
		}
		 throw new NullPointerException("User with this id not found.");
	}
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int id,@RequestBody User updatedUser){
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			User existUser = user.get();
			existUser.setUsername(updatedUser.getUsername());
			existUser.setPassword(updatedUser.getPassword());
			existUser.setFirstName(updatedUser.getFirstName());
			existUser.setLastName(updatedUser.getLastName());
			existUser.setRole(updatedUser.getRole());
			userRepository.save(existUser);
			return existUser;
		}
		 throw new NullPointerException("User with this credentials not found.");
	}
	
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user){
		List<User> users = findUserByUsername(user.getUsername());
		if(users.isEmpty()) {
			return createUser(user);
		}
		 throw new NullPointerException("User already exist.");
	}
	
	@PutMapping("/api/profile")
	public User updateProfile(@RequestBody User updatedUser){
		List<User> users = findUserByUsername(updatedUser.getUsername());
		User existUser = users.get(0);
		existUser.setEmail(updatedUser.getEmail());
		existUser.setDateOfBirth(updatedUser.getDateOfBirth());
		existUser.setPhone(updatedUser.getPhone());
		existUser.setRole(updatedUser.getRole());
		userRepository.save(existUser);
		return existUser;
	}
	
	public List<User> findUserByUsername(String username){
		return (List<User>)userRepository.findUserByUsername(username);
	}

}
