package com.salahscheduler.salahscheduler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.Resources;


@RestController
@RequestMapping("/api/prayers")
public class PrayerLogController {
	//this is for APIS endpoints 
	
	//using CRUD
	

	@Autowired
	private ActivityService activityService;
	@Autowired
	private UserService userService;
	
	@Autowired
	private ScheduleService scheduleService;
	
	@Autowired
	private PrayerLogService prayerLogService;
	
	@PostMapping("/log/{id}")
	public ResponseEntity createPrayer(@RequestBody PRAYER_LOG prayerLog , @PathVariable long id) {
		try {
			User user = userService.findById(id);
			prayerLog.setUser(user);
			PRAYER_LOG userPrayer = prayerLogService.savePrayerLog(prayerLog);

			return ResponseEntity.ok(Map.of(
					"Prayer saved Succesfully!", userPrayer) );

		}catch(Exception e) {
			 System.out.println("Error: " + e.getMessage()); // Add this
			    e.printStackTrace(); 
			return ResponseEntity.badRequest().body("Failed to create prayer");
		}
		
	}
	
	
	//this is where u left of

	
	@GetMapping("/type/{prayerType}")
	public ResponseEntity <List <PRAYER_LOG>> getPrayer(@PathVariable PrayerType prayerType ) {
		List < PRAYER_LOG > prayerLog = prayerLogService.findByPrayerType(prayerType);
if(prayerLog != null && !prayerLog.isEmpty()) {
		    
		    return ResponseEntity.ok(prayerLog);
		} else {
			return ResponseEntity.ok(Collections.emptyList());
		}

		
	}
	///api/prayers/history
	@GetMapping("/history/{userId}")
	public ResponseEntity<List<PRAYER_LOG>> getPrayerHistory(@PathVariable long userId) {
	    User user = userService.findById(userId);
	   
	    List<PRAYER_LOG> prayerHistory = prayerLogService.findPrayerLogsByUser(userId);

if(prayerHistory != null && !prayerHistory.isEmpty()) {
	
		    System.out.println("Your prayer History:");
		    return ResponseEntity.ok(prayerHistory);
		    
		} else {
			System.out.println("No prayer History found");
			return ResponseEntity.ok(Collections.emptyList());
		}
		
		
		
	}
	///GET /api/prayers/qada - Get makeup prayers needed?
	///
	///
	/// GET /api/prayers/daily - Get today's prayer times?
	///
	//
	///GET /api/prayers/date/{date} - Get prayer times for specific date?
	///

	@GetMapping("/qada/{userId}/{prayerStatus}")
	
	public ResponseEntity<List<PRAYER_LOG>> getPrayerQada(@PathVariable PrayerStatus prayerStatus , @PathVariable long userId ) {
		User user =  userService.findById(userId);
		
	    List<PRAYER_LOG> prayerQadad = prayerLogService.findByPrayerStatus(prayerStatus, userId);
	  
	    

	    if(prayerQadad != null && !prayerQadad.isEmpty()) {
	    	
		    System.out.println("Your Makeup prayers:");
		    return ResponseEntity.ok(prayerQadad);
		    
		} else {
			System.out.println("No Makeup prayer found");
			return ResponseEntity.ok(Collections.emptyList());
		}	
	}
	
	@GetMapping("/daily")
	public ResponseEntity <String> getDaily(@RequestParam double lat,@RequestParam double lng)	throws IOException
	{		
		String uri = "http://api.aladhan.com/v1/timings/{YYYY-MM-DD}";
		 LocalDate today = LocalDate.now();
String convertedToString = today.toString();
		 
		CharSequence formattedDate = convertedToString;
		String finalUri = uri.replace("{YYYY-MM-DD}", formattedDate) + "?latitude=" + lat + "&longitude=" + lng;
		RestTemplate restTemplate = new RestTemplate();
		
		String prayerTimesJson = restTemplate.getForObject(finalUri, String.class);	
		
		return ResponseEntity.ok(prayerTimesJson);
		
		
	}
	//GET /api/prayers/date/{date} 
	
	
	@GetMapping("/date/{date}")
	public ResponseEntity<String> getPrayersByDate(@PathVariable String date, 
	                                               @RequestParam double lat, 
	                                               @RequestParam double lng){
		
		
		String uri = "http://api.aladhan.com/v1/timings/{YYYY-MM-DD}";
		String finalUri = uri.replace("{YYYY-MM-DD}", date) + "?latitude=" + lat + "&longitude=" + lng;
		
		
		RestTemplate restTemplate = new RestTemplate();
		String prayerTimesJson = restTemplate.getForObject(finalUri, String.class);
		return ResponseEntity.ok(prayerTimesJson);
	
		
	}

		
	
	

	
	


	
	


}
