package com.salahscheduler.salahscheduler;

import java.util.Collections;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("/api/activities")
public class ActivityController {
	
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
   //creating activity
	@PostMapping("/{id}")
	public ResponseEntity createActivity(@RequestBody Activity activity , @PathVariable Long id) {
		
		try {
			User user = userService.findById(id);
			
			activity.setUser(user);
				///have to save activity
			Activity userActivity = activityService.saveActivity(activity);
			
				return ResponseEntity.ok(Map.of(
						"Successfully saved Activity! ", userActivity) )	;			
			
		}catch(Exception e){
			return ResponseEntity.badRequest().body("failed");

		}

	}
	//retrieving activity
	@GetMapping("/user/{userId}") 
	public  ResponseEntity<List<Activity> >  getUserActivities(@PathVariable long userId ) 
	{
		List<Activity> user = activityService.findActivitiesByUser(userId);
		
		if(user != null && !user.isEmpty()) {
		    // has activities
		    return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
		
	}
	//updating activity
	@PutMapping("/{Id}") 
	public ResponseEntity<Activity> UpdateActivity (@RequestBody Activity activity ,
			@PathVariable long Id) {
		
		Optional<Activity> updated = activityService.findActivityById(Id);

		//if were able to update...
		//now were updating the activities here
		
		if(updated.isPresent()) {

			
			Activity existingActivity = updated.get();
			
			existingActivity.setTitle(activity.getTitle());
			existingActivity.setType(activity.getType());
			existingActivity.setPriority(activity.getPriority());				
			existingActivity.setDuration(activity.getDuration());
		
			activityService.saveActivity(existingActivity);
			
		return ResponseEntity.ok(existingActivity);
			
		} else {
			return ResponseEntity.notFound().build();
			
		}		
	}
	
	//deleting activity
	  @DeleteMapping("/{Id}")
	  public ResponseEntity DeletedActivity(
				@PathVariable long Id){
		  
		  Optional deleted = activityService.findActivityById(Id);
		  
		  if (deleted.isPresent()) {
			activityService.DeleteActivity(Id);
			return  ResponseEntity.ok(Map.of("Successfully Deleted!", Id));
		  }
		  return ResponseEntity.notFound().build();
		 
		
		  
	  }
	
	
}
