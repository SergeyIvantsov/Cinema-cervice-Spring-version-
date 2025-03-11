package by.sergey.cinemaservicespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @NotBlank(message = "не должно быть пустым")
    @Size(min = 3, max = 16, message = "Длина username должна быть от 3 до 16 символов")
    @Pattern(regexp = "[a-zA-Z]*", message = "только латинские буквы")
    @Column (name = "user_name")
    private String userName;

    @Column
    private String password;

    @OneToOne(mappedBy ="user", cascade= CascadeType.ALL,
            fetch = FetchType.EAGER)
    private Account account;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();




}
