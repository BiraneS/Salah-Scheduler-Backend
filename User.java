package com.salahscheduler.salahscheduler;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
@Entity

public class User {
public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getTimezone() {
		return timezone;
	}
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}
	public String getMadhab() {
		return madhab;
	}
	public void setMadhab(String madhab) {
		this.madhab = madhab;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	//*id (primary key)
	//email (for login)
	//password (encrypted)
	//name (display name)
	//location data (latitude/longitude for prayer times)
	//timezone (for accurate prayer calculations)
//	madhab (Islamic school of thought preference)
	//timestamps (when account was created/updated)
	//
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 @Column(unique = true)
	private String email;
	private String password;
	private String name;
	private Double latitude;
	private Double longitude;
	private String timezone;
	private String madhab;
	@CreationTimestamp

	private LocalDateTime createdAt;
	@UpdateTimestamp
	

	private LocalDateTime updatedAt;
	
	// JPA loads data from the database,
	//it first creates an empty object using the no-argument constructor,
	//then uses reflection to set the field values from the database
	public User() {
	    // no-argument constructor required by JPA
	}
	public User(String email, String password, String name, Double latitude, Double longitude, String timezone, String madhab) {
	    this.email = email;
	    this.password = password;
	    this.name = name;
	    this.latitude = latitude;
	    this.longitude = longitude;
	    this.timezone = timezone;
	    this.madhab = madhab;
	}
	public boolean isPresent() {
		// TODO Auto-generated method stub
		return false;
	}
	


}

