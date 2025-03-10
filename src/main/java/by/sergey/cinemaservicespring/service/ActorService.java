package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.ActorDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ActorService {

    ActorDto save(ActorDto actorDto);

    List<ActorDto> findAll();

}
