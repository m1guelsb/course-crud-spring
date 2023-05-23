package com.m1guelsb.crudspring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.m1guelsb.crudspring.modules.course.CourseEntity;
import com.m1guelsb.crudspring.modules.course.CourseRepository;
import com.m1guelsb.crudspring.modules.course.enums.CategoryEnum;
import com.m1guelsb.crudspring.modules.lesson.LessonEntity;

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
			course.setCategory(CategoryEnum.FRONT_END);

			LessonEntity lesson = new LessonEntity();
			lesson.setName("Introduction");
			lesson.setSourceURL("https://www.youtube.com/watch?v=4kEO7VjKRB8");
			lesson.setCourse(course);

			course.getLessons().add(lesson);

			courseRepository.save(course);
		};
	}
}
