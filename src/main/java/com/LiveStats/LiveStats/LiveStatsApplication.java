package com.LiveStats.LiveStats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class LiveStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiveStatsApplication.class, args);
	}

}
