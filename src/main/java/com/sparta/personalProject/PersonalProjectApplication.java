package com.sparta.personalProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.swing.*;

@EnableJpaAuditing
@SpringBootApplication
public class PersonalProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(PersonalProjectApplication.class, args);
	}

}
