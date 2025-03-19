package by.sergey.cinemaservicespring.service;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.entity.Actor;

import java.util.Set;

public interface ActorService {

    ActorDto saveOrUpdate(ActorDto actorDto);

    Set<ActorDto> findAll();

    Actor getReferenceById(Long id);

    Set<ActorDto> findActorsByIds(Set<Long> ids);


}
