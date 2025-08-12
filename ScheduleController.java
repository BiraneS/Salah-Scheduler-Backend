package com.salahscheduler.salahscheduler;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/schedule")
public class ScheduleController {
	
	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@PostMapping("/{id}")
	public ResponseEntity createSchedule(@RequestBody Schedule schedule ,@PathVariable long id) {
		try {
			User user = userService.findById(id);
			schedule.setUser(user);
			Schedule userSchedule = scheduleService.saveSchedule(schedule);
		
		
		return ResponseEntity.ok(Map.of(
				"Schedule saved Succesfully!", userSchedule) );
		
		}
		catch(Exception e){
			return ResponseEntity.badRequest().body("Failed to create Schedule");

		}			
	}
	//Retrieving schedule
	@GetMapping("/daily/{date}")
	public  ResponseEntity<List<Schedule> >  getUserSchedule (@PathVariable LocalDate date)
	{
		
		List < Schedule > schedules = scheduleService.findScheduleByDate(date);
		if(schedules != null && !schedules.isEmpty()) {
		    
		    return ResponseEntity.ok(schedules);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}
	
	}
	//update
	@PutMapping("/{id}")
	
	public ResponseEntity <Schedule> UpdateSchedule(@RequestBody Schedule schedule , @PathVariable long id){
		Optional<Schedule> updated = scheduleService.findScheduleById(id);
		
		if(updated.isPresent()) {
		Schedule existingSchedule = updated.get();
			
			existingSchedule.setCreatedAt(schedule.getCreatedAt());
			existingSchedule.setEndTime(schedule.getEndTime());
			existingSchedule.setActivity(schedule.getActivity());
			existingSchedule.setUser(schedule.getUser());
			existingSchedule.setUpdatedAt(schedule.getUpdatedAt());
			existingSchedule.setStatus(schedule.getStatus());
			existingSchedule.setStartTime(schedule.getStartTime());
			existingSchedule.setScheduledDate(schedule.getScheduledDate());
			existingSchedule.setNotes(schedule.getNotes());
			
			scheduleService.saveSchedule(existingSchedule);
			return ResponseEntity.ok(existingSchedule);
			
		
		} else {
			return ResponseEntity.notFound().build();

		}
	
	}
	
	//Deleting 
	
	@DeleteMapping("/{id}")

	public ResponseEntity DeletedSchedule(
			@PathVariable long id)
	{
		 
		  Optional deleted = scheduleService.findScheduleById(id);
		  
		  if (deleted.isPresent()) {
			scheduleService.DeleteSchedule(id);
			return  ResponseEntity.ok(Map.of("Successfully Deleted!", id));
		  }
		  return ResponseEntity.notFound().build();
		
		
	}
	
}
