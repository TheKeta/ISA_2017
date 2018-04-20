package com.reservationapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
//@EnableAutoConfiguration
@EnableAsync
public class ReservationAppApplication {

	public static void main(String[] args) {
		   SpringApplication.run(ReservationAppApplication.class, args);
	}
	
}
