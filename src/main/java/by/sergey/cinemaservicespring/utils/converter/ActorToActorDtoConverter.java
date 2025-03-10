package by.sergey.cinemaservicespring.utils.converter;

import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.entity.Actor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActorToActorDtoConverter implements Converter<Actor, ActorDto> {
    @Override
    public ActorDto convert(Actor source) {
        return   ActorDto.builder().id(source.getId())
                .actorName(source.getActorName())
                .actorSurname(source.getActorSurname()).build();
    }
}

