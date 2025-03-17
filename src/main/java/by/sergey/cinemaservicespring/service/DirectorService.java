package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.DirectorDto;

import java.util.Set;

public interface DirectorService {

    DirectorDto saveOrUpdate(DirectorDto directorDto);

    Set<DirectorDto> getAll();

    DirectorDto get(Long id);

    void delete(Long id);
}
