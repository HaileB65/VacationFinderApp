package Capstone.Project.NewMVCProject;

import Capstone.Project.NewMVCProject.models.User;
import Capstone.Project.NewMVCProject.models.UserRole;
import Capstone.Project.NewMVCProject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewMvcProjectApplication implements CommandLineRunner {
	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(NewMvcProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = User.builder()
				.id(0L)
				.firstName("Alex")
				.lastName("Walker")
				.email("alexwalker@yahoo.com")
				.phone("1245874568")
				.username("walker")
				.password("fiaioio")
				.userRole(UserRole.USER)
				.enabled(true)
				.locked(false)
				.build();

		userService.createNewUser(user);


	}
}
