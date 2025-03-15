package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.DirectorDto;
import by.sergey.cinemaservicespring.entity.Director;

import java.util.List;
import java.util.Set;

public interface DirectorService {

    Set<DirectorDto> getAll();
}
