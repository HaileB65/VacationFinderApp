//package Capstone.Project.VacationFinder.services;
//
//import Capstone.Project.VacationFinder.models.Privilege;
//import Capstone.Project.VacationFinder.models.Role;
//import Capstone.Project.VacationFinder.models.User;
//import Capstone.Project.VacationFinder.repositories.PrivilegeRepository;
//import Capstone.Project.VacationFinder.repositories.RoleRepository;
//import Capstone.Project.VacationFinder.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.Arrays;
//import java.util.Collection;
//import java.util.List;
//
//@Component
//public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
//
//    boolean alreadySetup = false;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    private RoleRepository roleRepository;
//
//    @Autowired
//    private PrivilegeRepository privilegeRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//
//
//    @Override
//    @Transactional
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        if (alreadySetup) return;
//
//        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
//        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");
//        List<Privilege> adminPrivileges = Arrays.asList(readPrivilege, writePrivilege);
//
//        createRoleIfNotFound("ROLE_GUEST", Arrays.asList(readPrivilege));
//        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));
//        createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
//
//        Role guestRole = roleRepository.findByName("ROLE_GUEST");
//        if (!userRepository.existsByUsername("guest")) {
//            User guest = User.builder()
//                    .firstName("Robin")
//                    .lastName("Smith")
//                    .email("smithrobin@gmail.com")
//                    .phone("1245684578")
//                    .username("guest")
//                    .password("password")
//                    .enabled(true)
//                    .locked(false)
//                    .build();
//
//            guest.setRoles(Arrays.asList(guestRole));
//
//            userService.createNewUser(guest);
//        }
//
//        Role userRole = roleRepository.findByName("ROLE_USER");
//        if (!userRepository.existsByUsername("user")) {
//            User user = User.builder()
//                    .firstName("Alex")
//                    .lastName("Walker")
//                    .email("alexwalker@yahoo.com")
//                    .phone("1245874568")
//                    .username("user")
//                    .password("password")
//                    .enabled(true)
//                    .locked(false)
//                    .build();
//
//            user.setRoles(Arrays.asList(userRole));
//
//            userService.createNewUser(user);
//        }
//
//        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
//        if (!userRepository.existsByUsername("admin")) {
//            User admin = User.builder()
//                    .firstName("Kyle")
//                    .lastName("Jackson")
//                    .email("kylejackson@yahoo.com")
//                    .phone("8567487596")
//                    .username("admin")
//                    .password("password")
//                    .enabled(true)
//                    .locked(false)
//                    .build();
//
//            admin.setRoles(Arrays.asList(adminRole));
//
//            userService.createNewUser(admin);
//        }
//
//        alreadySetup = true;
//    }
//
//    @Transactional
//    Privilege createPrivilegeIfNotFound(String name) {
//
//        Privilege privilege = privilegeRepository.findByName(name);
//        if (privilege == null) {
//            privilege = new Privilege(name);
//            privilegeRepository.save(privilege);
//        }
//        return privilege;
//    }
//
//    @Transactional
//    Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
//
//        Role role = roleRepository.findByName(name);
//        if (role == null) {
//            role = new Role(name);
//            role.setPrivileges(privileges);
//            roleRepository.save(role);
//        }
//        return role;
//    }
//}