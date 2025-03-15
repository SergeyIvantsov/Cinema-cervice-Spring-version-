package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.entity.Actor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ActorService {

    ActorDto save(ActorDto actorDto);

    List<ActorDto> findAll();

    Actor getReferenceById(Long id);

}
