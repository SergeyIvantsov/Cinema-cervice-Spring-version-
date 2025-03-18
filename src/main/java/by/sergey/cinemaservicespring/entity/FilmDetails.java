package by.sergey.cinemaservicespring.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "film")
@EqualsAndHashCode(exclude = "film")
@Builder
@Table(name = "film_details")
public class FilmDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String country;

    @Column
    private Integer duration;

    @Column
    private Double budget;

    @Column
    private Float rating;

    @Column(name = "price_for_month")
    private Integer priceForMonth;

    @Column(name = "price_for_year")
    private Integer priceForYear;

    @OneToOne(mappedBy = "filmDetails", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Film film;


}
