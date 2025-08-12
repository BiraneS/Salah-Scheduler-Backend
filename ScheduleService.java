package com.salahscheduler.salahscheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScheduleService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	
	//save schedule
	public Schedule saveSchedule(Schedule schedule) {
		return scheduleRepository.save(schedule);
		
	}
	//public List<Activity> findActivitiesByUser(User user) {
	//return  activityRepository.findByUser(user);
	   
//}
	public List <Schedule> findScheduleByDate (LocalDate date){
		return scheduleRepository.findByScheduledDate(date);
		
	}

	public Optional<Schedule> findScheduleById(long id) {
		// TODO Auto-generated method stub
		return scheduleRepository.findById(id);
	}

	public void DeleteSchedule(long id) {
		// TODO Auto-generated method stub
		scheduleRepository.deleteById(id);
		
	}
	
}
