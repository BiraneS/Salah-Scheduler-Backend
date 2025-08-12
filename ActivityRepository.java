package com.salahscheduler.salahscheduler;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long>  {
	List<Activity> findByUser(User user);

}
