package by.sergey.cinemaservicespring.utils.converter;


import by.sergey.cinemaservicespring.dto.ActorDto;
import by.sergey.cinemaservicespring.entity.Actor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ActorDtoToEntityConverter implements Converter<ActorDto,Actor> {
    @Override
    public Actor convert(ActorDto source) {
        return   Actor.builder().id(source.getId())
                .actorName(source.getActorName())
                .actorSurname(source.getActorSurname()).build();
    }



}
