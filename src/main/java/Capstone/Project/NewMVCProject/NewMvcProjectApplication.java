package Capstone.Project.NewMVCProject;

import Capstone.Project.NewMVCProject.models.User;
import Capstone.Project.NewMVCProject.models.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NewMvcProjectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(NewMvcProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User.builder()
				.id(1L)
				.firstName("Alex")
				.lastName("Walker")
				.email("alexwalker@yahoo.com")
				.phone("1245874568")
				.username("walker")
				.password("fiaioio")
				.userRole(UserRole.USER)
				.locked(false)
				.enabled(true)
				.build();

	}
}
