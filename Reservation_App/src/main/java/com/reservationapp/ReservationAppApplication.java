package com.reservationapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class ReservationAppApplication {

	public static void main(String[] args) {
		   SpringApplication.run(ReservationAppApplication.class, args);
	}
}
