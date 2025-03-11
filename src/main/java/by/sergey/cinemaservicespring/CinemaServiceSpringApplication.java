package by.sergey.cinemaservicespring;

import by.sergey.cinemaservicespring.dto.FilmDto;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class CinemaServiceSpringApplication {


    public static void main(String[] args) {
        SpringApplication.run(CinemaServiceSpringApplication.class, args);


    }


}
