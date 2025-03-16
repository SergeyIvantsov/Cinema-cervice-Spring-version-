package by.sergey.cinemaservicespring.repository;

import by.sergey.cinemaservicespring.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    Actor getReferenceById(Long id);

    Set<Actor> findByIdIn(Set<Long> ids);



}
