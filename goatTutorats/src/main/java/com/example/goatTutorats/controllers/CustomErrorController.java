package com.example.goatTutorats.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class CustomErrorController implements ErrorController {

    @RequestMapping("/")
    public String handleError(HttpServletRequest request, Model model) {
        // get response status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // return html template based on error received
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            switch (statusCode) {
                case 404:
                    return "errors/error-404";

                default:
                    // store error message in model to display it in html template
                    model.addAttribute("errorMessage", request.getAttribute(RequestDispatcher.ERROR_MESSAGE));

                    return "errors/default-error";
            }
        }

        return "login";
    }
}
