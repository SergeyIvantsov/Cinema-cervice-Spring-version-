package by.sergey.cinemaservicespring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ActorDto {

    private Long id;

    private String actorName;

    private String actorSurname;

    public ActorDto(String fullName) {
        String[] parts = fullName.split(" ");
        if (parts.length == 2) {
            this.actorName = parts[0];
            this.actorSurname = parts[1];
        }else {
            System.out.println("Ошибка, неверно введены данные актера!");
        }
    }
}