package com.m1guelsb.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.m1guelsb.crudspring.course.CourseEntity;
import com.m1guelsb.crudspring.course.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			CourseEntity course = new CourseEntity();
			course.setName("Javiscripti");
			course.setCategory("ruim");

			courseRepository.save(course);
		};
	}
}
