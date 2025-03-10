package by.sergey.cinemaservicespring.repository;

import by.sergey.cinemaservicespring.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ActorRepository extends JpaRepository<Actor, Integer> {
    Actor getReferenceById(Long id);
//    Actor getActorById(Long id);
}
