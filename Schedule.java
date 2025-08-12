package com.salahscheduler.salahscheduler;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Schedule {
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
	public LocalDate getScheduledDate() {
		return scheduledDate;
	}
	public void setScheduledDate(LocalDate scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public LocalDate getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDate getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	//this is an empty constructor 
	public Schedule(){
		
	}
		public Schedule(User user, LocalDate scheduledDate, Activity activity, LocalTime startTime,
			LocalTime endTime, String notes, LocalDate createdAt, LocalDate updatedAt, Status status) {
		super();
		
		this.user = user;
		this.scheduledDate = scheduledDate;
		this.activity = activity;
		this.startTime = startTime;
		this.endTime = endTime;
		this.notes = notes;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.status = status;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	//SCHEDULE TABLE Fields: id, scheduledDate, startTime,
	//endTime, status, notes, createdAt,
	//updatedAt, user_id, activity_id Purpose:
	//Store specific instances of activities on particular
	//dates Relationships: Many-to-One with User and Activity
	
	//   set status of type Enum
	private long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	private LocalDate scheduledDate;
	
	
	@ManyToOne 
	@JoinColumn(name = "activity_id")

	private Activity activity;
	
	private LocalTime startTime;
	private  LocalTime endTime;
	private String notes;
	private LocalDate createdAt;
	private LocalDate updatedAt;
    private Status status;

}
