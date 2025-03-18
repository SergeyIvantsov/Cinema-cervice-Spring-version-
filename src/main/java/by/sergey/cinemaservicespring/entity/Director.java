package by.sergey.cinemaservicespring.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @OneToMany(mappedBy = "director", cascade = CascadeType.REMOVE)
    private Set<Film> filmsByDirector = new HashSet<>();

}
