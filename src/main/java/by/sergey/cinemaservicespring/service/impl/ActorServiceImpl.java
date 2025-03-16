package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.entity.Actor;
import by.sergey.cinemaservicespring.repository.ActorRepository;
import by.sergey.cinemaservicespring.service.ActorService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public ActorDto save(ActorDto actorDto) {
        Actor actor = ConverterUtil.convertActor(actorDto);
        Actor savedActor = actorRepository.save(actor);
        if (savedActor != null) {
            return ConverterUtil.convertActor(savedActor);
        } else
            return null;
    }

    @Override
    public Set<ActorDto> findAll() {
        return actorRepository.findAll()
                .stream()
                .map(ConverterUtil::convertActor)
                .collect(Collectors.toSet());
    }

    @Override
    public Actor getReferenceById(Long id) {
       return actorRepository.findById(id).get();
    }

    @Override
    public Set<ActorDto> findActorsByIds(Set<Long> ids) {
        Set<Actor> allActorsById = actorRepository.findByIdIn(ids);
        Set<ActorDto> allActorsDto = allActorsById.stream()
                .map(ConverterUtil::convertActor).collect(Collectors.toSet());
        return allActorsDto;
    }



}
