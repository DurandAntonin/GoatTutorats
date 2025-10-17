package com.example.goatTutorats.controlers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("error")
public class CustomErrorController implements ErrorController {

    @RequestMapping("/")
    public String handleError(HttpServletRequest request) {
        // get response status code
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        // return html template based on error received
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            switch (statusCode) {
                case 404:
                    return "errors/error-404";

                default:
                    return "errors/default-error";
            }
        }

        return "login";
    }
}
