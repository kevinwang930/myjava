package kevin.project.myspring.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServletLearn {

    private final Logger logger = LoggerFactory.getLogger("");

    @GetMapping(path = "/servletlearn")
    public String servletlearn(HttpServletRequest request, HttpServletResponse response) {
        logger.info(request.getRequestURI());
        logger.info(request.getServerName());
        logger.info(request.getRemoteAddr());
        logger.info(request.getRemoteHost());
        logger.info(String.valueOf(response));
        return "success";
    }
}
