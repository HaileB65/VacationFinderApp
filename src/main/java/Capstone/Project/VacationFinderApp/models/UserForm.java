package Capstone.Project.VacationFinderApp.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    @NotNull(message = "Name is mandatory")
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String username;

    @NotNull
    private String password;
}
