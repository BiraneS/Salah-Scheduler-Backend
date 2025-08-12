package com.salahscheduler.salahscheduler;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityRepository activityRepository;
	
	public Activity saveActivity(Activity activity) {
			
		return activityRepository.save(activity);
				
	}
	public List<Activity> findActivitiesByUser(User user) {
		return  activityRepository.findByUser(user);
	   
	}
	public List<Activity> findActivitiesByUser(long userId) {
	    User user = userService.findById(userId);
	    return activityRepository.findByUser(user);
	}
	public Optional<Activity> findActivityById(long Id){
		return activityRepository.findById(Id);
	}
	public void DeleteActivity(long Id){
		activityRepository.deleteById(Id);
	}

}
