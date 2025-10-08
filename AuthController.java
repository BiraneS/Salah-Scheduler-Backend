package com.salahscheduler.salahscheduler;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



//Handling user registrations and logins
//
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired 
    private UserService userService;
    //registering

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User newUser) {
        try {

			//find existing users email
            User existingUser = userService.findByEmail(newUser.getEmail());
            if (existingUser != null) {
                return ResponseEntity.badRequest().body("User with this email already exists");
            }


			//saving users
			
            User savedUser = userService.saveUser(newUser);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Registration failed: " + e.getMessage());
        }
    }
   

    @Autowired
    private JwtUtil jwtUtil;
    //creating login

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody User loginUser) {
        User user = userService.findByEmail(loginUser.getEmail());

        if (user != null && user.getPassword().equals(loginUser.getPassword())) {
            // Generate JWT token
            String token = jwtUtil.generateToken(user.getEmail());

            // Log success
            System.out.println("User logged in successfully!");

            // Return token and user data
            return ResponseEntity.ok(Map.of(
            	    "message", "Login successful",
            	    "token", token
            	));
       
        } else {
            // Log failure
            System.out.println("Login failed - invalid credentials");

            return ResponseEntity
                    .status(401)
                    .body(Map.of("error", "Invalid email or password"));
        }
    }
    //retrieving the profile
	//getting it
    
    @GetMapping("/profile/{userId}")
    public ResponseEntity<User> getProfile(@PathVariable long userId){
	   
	   User user = userService.findById(userId);
	   
	   return ResponseEntity.ok(user);   
   }
    	//PUT /api/auth/profile
    //updating the profile
    
   @PutMapping("/profile/{userId}")
   public ResponseEntity<User> updateUser(@PathVariable long userId, @RequestBody User updatedUser){
	   
	   User updated = userService.findById(userId);
	  
		if(updated != null) {
			User existingUser = updated;
			//Name
			//Location (latitude, longitude)
		//	Preferences (timezone, madhab)
		//	Maybe email

			//updating...
			
			existingUser.setName(updatedUser.getName());
			existingUser.setLongitude(updatedUser.getLongitude());
			existingUser.setLatitude(updatedUser.getLatitude());
			existingUser.setEmail(updatedUser.getEmail());
			
		
			userService.saveUser(existingUser);
			
		return ResponseEntity.ok(existingUser);
			
		} else {
			return ResponseEntity.notFound().build();
			
		}	
	
	   
   }
	 
    
    
}
