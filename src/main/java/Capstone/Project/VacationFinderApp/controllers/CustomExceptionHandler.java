package Capstone.Project.VacationFinderApp.controllers;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class CustomExceptionHandler
{
    @GetMapping("/error")
    public String showErrorPage() {

        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex, Model model) {
        System.out.println(ex);
        return "error";
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public String handleUsernameNotFoundExceptions(Exception ex, Model model) {
        System.out.println(ex);
        return "error";
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public String handlePageNotFoundException(Exception ex, Model model) {
        System.out.println(ex);
        return "page-not-found-error";
    }
}
