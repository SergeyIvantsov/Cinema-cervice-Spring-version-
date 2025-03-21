package by.sergey.cinemaservicespring.repository;


import by.sergey.cinemaservicespring.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepository extends JpaRepository<Director, Long> {


}
