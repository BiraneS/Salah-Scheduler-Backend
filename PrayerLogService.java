package com.salahscheduler.salahscheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PrayerLogService {
	@Autowired
	private UserService userService;
	@Autowired
	private ActivityRepository activityRepository;
	@Autowired
	private ScheduleRepository scheduleRepository;
	@Autowired
	private PrayerLogRepository prayerLogRepository;
	

	//savePrayerLog()
	//findPrayerLogsByUser()
	//findPrayerLogsByDate()
	
	
	
	public PRAYER_LOG savePrayerLog (PRAYER_LOG prayer_Log) {
		
		return prayerLogRepository.save(prayer_Log);
	}
	
	
	public List <PRAYER_LOG> findPrayerLogsByUser(User user){
		return prayerLogRepository.findByUser(user);	
	}
	
	public List<PRAYER_LOG> findByPrayerDate(LocalDate date){
		return prayerLogRepository.findByPrayerDate(date);
	}


	


	public List<PRAYER_LOG> findByPrayerType(PrayerType prayerType) {
	
		return prayerLogRepository.findByPrayerType(prayerType);
	}
	
	public List<PRAYER_LOG> findPrayerLogsByUser(long userId) {
	    User user = userService.findById(userId);
	    return prayerLogRepository.findByUser(user);
	}


	public List<PRAYER_LOG> findByPrayerStatus( PrayerStatus prayerStatus ,  long userId) {
		// TODO Auto-generated method stub
		return prayerLogRepository.findByPrayerStatusAndUserId(prayerStatus, userId);
	}
	

	
	
}
