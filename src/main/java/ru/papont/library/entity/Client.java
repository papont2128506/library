package ru.papont.library.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.papont.library.dto.ClientDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    private List<BookInUse> bookInUses = new ArrayList<>();


    public Client(ClientDto dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.email = dto.getEmail();
        this.phone = dto.getPhone();
    }
}
