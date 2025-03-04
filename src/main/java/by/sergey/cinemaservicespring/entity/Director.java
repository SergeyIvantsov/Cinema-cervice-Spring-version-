package by.sergey.cinemaservicespring.entity;

import lombok.*;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@ToString (exclude = "filmsByDirector")
@EqualsAndHashCode (exclude = "filmsByDirector")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "directors")

public class Director implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_director")
    private Long id;

    @Column (name = "director_name")
    private String directorName;

    @Column(name = "director_surname")
    private String directorSurname;

    @OneToMany (mappedBy = "director")
    private Set<Film> filmsByDirector = new HashSet<>();

}
