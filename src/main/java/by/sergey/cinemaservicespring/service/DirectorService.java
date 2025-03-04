package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.entity.Director;

import java.util.List;

public interface DirectorService {

    List<DirectorDto> getAll();
}
