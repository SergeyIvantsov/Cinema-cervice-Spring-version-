package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.DirectorDto;

import java.util.Set;

public interface DirectorService {

    DirectorDto save(DirectorDto directorDto);

    Set<DirectorDto> getAll();
}
