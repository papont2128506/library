package ru.papont.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.papont.library.dto.ClientDto;

import javax.persistence.*;

@Data
@Entity
@Table(name = "clients")
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public Client(ClientDto dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
    }
}
