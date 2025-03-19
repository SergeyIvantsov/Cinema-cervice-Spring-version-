package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.entity.Actor;
import by.sergey.cinemaservicespring.entity.Film;
import by.sergey.cinemaservicespring.repository.ActorRepository;
import by.sergey.cinemaservicespring.service.ActorService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import jakarta.persistence.EntityNotFoundException;
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
    public ActorDto saveOrUpdate(ActorDto actorDto) {
        Actor actor = actorRepository.save(ConverterUtil.convertActor(actorDto));
        if (actor != null) {
            ActorDto addActorDto = ConverterUtil.convertActor(actor);
            return addActorDto;
        } else
            return null;
    }

    @Override
    public Set<ActorDto> getAll() {
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

    @Override
    public void delete(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Actor not found"));

        // Удаляем связи актёра с фильмами
        for (Film film : actor.getFilmsForActors()) {
            film.getActors().remove(actor);
        }

        actorRepository.delete(actor);
        //actorRepository.deleteById(id);
    }


}
