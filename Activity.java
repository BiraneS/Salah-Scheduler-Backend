package com.salahscheduler.salahscheduler;

import java.util.Optional;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity

public class Activity {
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public ActivityType getType() {
		return type;
	}
	public void setType(ActivityType type) {
		this.type = type;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	public Activity() {
	    // no-argument constructor required by JPA
		//so it could use an empty constructor when loading from the database
	}
	public Activity(User user, String title, int duration, ActivityType type, Priority priority) {
		super();
		this.user = user;
		this.title = title;
		this.duration = duration;
		this.type = type;
		this.priority = priority;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	 @JoinColumn(name = "user_id")
	private User user;
	
	private String title;
	private int duration;
	private ActivityType type;
	private Priority priority;
	
	
	
}
