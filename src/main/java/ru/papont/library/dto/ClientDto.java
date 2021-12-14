package ru.papont.library.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class ClientDto {

    @NotBlank(message = "is mandatory")
    private String firstName;

    @NotBlank(message = "is mandatory")
    private String lastName;

    @NotBlank(message = "is mandatory")
    @Pattern(regexp = "^\\S+@\\S+\\.\\S+$")
    private String email;

    @NotBlank(message = "is mandatory")
    @Pattern(regexp = "^((\\+7|7|8)+([0-9]){10})$")
    private String phone;

}
