//package tests;
//
//import Capstone.Project.VacationFinderApp.controllers.DestinationController;
//import Capstone.Project.VacationFinderApp.models.Destination;
//import Capstone.Project.VacationFinderApp.repositories.DestinationRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.mock.web.MockHttpServletResponse;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.BDDMockito.given;
//
//@ExtendWith(MockitoExtension.class)
//public class DestinationControllerTest {
//
//    private MockMvc mvc;
//
//    @Mock
//    private DestinationRepository destinationRepository;
//
//    @InjectMocks
//    private DestinationController destinationController;
//
//    @Test
//    public void canRetrieveDestinationByName() throws Exception {
//        given(destinationRepository.findByName("Japan"))
//                .willReturn(new Destination("Japan"));
//
//        MockHttpServletResponse response = mvc.perform(get("/form")
//                        .accept(MediaType.APPLICATION_JSON)
//                        .andReturn().getResponse();
//
//    }
//}
