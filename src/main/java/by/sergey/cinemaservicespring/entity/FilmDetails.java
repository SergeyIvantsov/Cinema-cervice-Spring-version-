//package by.sergey.cinemaservicespring.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.io.Serializable;
//
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString (exclude = "filmDetails")
//@EqualsAndHashCode (exclude = "filmDetails")
//@Builder
//@Data
//@Table(name = "film_details")
//public class FilmDetails implements Serializable {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
//    private Long id;
//
//    @Column
//    private Integer budget;
//
//    @Column
//    private String studio;
//
//    @Column
//    private String country;
//
//    @Column
//    private Double rating;
//
//    @Column(name = "price_for_month")
//    private Double priceForMonth;
//
//    @Column(name = "price_for_year")
//    private Double priceForYear;
//
//    @OneToOne(fetch = FetchType.EAGER)
//    @PrimaryKeyJoinColumn
//    private Film film;
//
//}
