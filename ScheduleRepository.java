package com.salahscheduler.salahscheduler;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long>{
	List<Schedule> findByScheduledDate(LocalDate date);

	Optional<Schedule> findById(long id);

}
