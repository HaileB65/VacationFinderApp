package Capstone.Project.VacationFinderApp.models;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserForm {

    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    @NotNull
    @Size(min=2, max=30)
    private String lastName;

    @NotNull
    @Size(min=2, max=30)
    private String email;

    @NotNull
    @Size(min=9, max=15)
    private String phone;

    @NotNull
    @Size(min=2, max=30)
    private String username;

    @NotNull
    @Size(min=2, max=30)
    private String password;
}
