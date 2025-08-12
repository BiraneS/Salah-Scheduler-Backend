package com.salahscheduler.salahscheduler;

import java.sql.Timestamp;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class PRAYER_LOG {
	
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

	public PrayerType getPrayerType() {
	    return prayerType;
	}

	public void setPrayerType(String prayerType) {
		this.prayerType = PrayerType.valueOf(prayerType.toUpperCase());
	}

	public LocalDate getPrayerDate() {
		return prayerDate;
	}

	public void setPrayerDate(LocalDate prayerDate) {
		this.prayerDate = prayerDate;
	}

	public LocalTime getPrayerTime() {
		return prayerTime;
	}

	public void setPrayerTime(LocalTime prayerTime) {
		this.prayerTime = prayerTime;
	}

	public Timestamp getLoggedAt() {
		return loggedAt;
	}

	public void setLoggedAt(Timestamp loggedAt) {
		this.loggedAt = loggedAt;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public PrayerStatus getPrayerStatus() {
		return prayerStatus;
	}

	public void setPrayerStatus(PrayerStatus prayerStatus) {
		this.prayerStatus = prayerStatus;
	}

	//This is an Empty constructor for JPA
	public PRAYER_LOG() {
		
	}
	
	public PRAYER_LOG(User user, PrayerType prayerType, LocalDate prayerDate, LocalTime prayerTime, Timestamp loggedAt,
			String notes, PrayerStatus prayerStatus) {
		super();
		this.user = user;
		this.prayerType = prayerType;
		this.prayerDate = prayerDate;
		this.prayerTime = prayerTime;
		this.loggedAt = loggedAt;
		this.notes = notes;
		this.prayerStatus = prayerStatus;
	}
	// id, prayerType, prayerDate, prayerTime,  status, loggedAt, notes,
	//user_id 
	
	//enum status
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private long id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user ;
	
	
	private LocalDate prayerDate;
	private LocalTime prayerTime;
	private Timestamp loggedAt;
	private String notes;
	//Enum
	@Enumerated(EnumType.STRING)

	private PrayerStatus prayerStatus;
	//Enum
	@Enumerated(EnumType.STRING)
	private PrayerType prayerType;
	///this is where u left of, now you need 
	///
	///
//to figure out if you need to do the repository or service for prayyer log.
	
	
	

}
