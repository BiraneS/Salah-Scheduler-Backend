package com.salahscheduler.salahscheduler;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrayerLogRepository extends JpaRepository<PRAYER_LOG, Long>{

	List<PRAYER_LOG> findByUser(User user);

	

	List<PRAYER_LOG> findByPrayerDate(LocalDate date);




	List<PRAYER_LOG> findByPrayerType(PrayerType prayerType);



	List<PRAYER_LOG> findByPrayerStatus(PrayerStatus prayerStatus);

	
	List<PRAYER_LOG> findByPrayerStatusAndUserId(PrayerStatus prayerStatus, long userId);





}
