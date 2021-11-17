package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            Student lova = new Student(
                    1L,
                    "Lova Mialy",
                    "ramalomi@gmail.com",
                    LocalDate.of(1992, Month.MARCH, 11)
            );
            Student landy = new Student(2L,
                    "Landy Vola",
                    "landyvolatiarisoa@gmail.com",
                    LocalDate.of(1996, Month.MAY, 11)
            );

            studentRepository.saveAll(Arrays.asList(lova, landy));
        };
    }
}
