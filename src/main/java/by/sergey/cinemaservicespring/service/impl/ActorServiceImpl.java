package by.sergey.cinemaservicespring.service.impl;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.entity.Actor;
import by.sergey.cinemaservicespring.repository.ActorRepository;
import by.sergey.cinemaservicespring.service.ActorService;
import by.sergey.cinemaservicespring.utils.converter.ConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    public List<ActorDto> findAll() {
       return actorRepository.findAll()
                .stream()
               .map(ConverterUtil::convertActor)
               .collect(Collectors.toList());
    }

}
