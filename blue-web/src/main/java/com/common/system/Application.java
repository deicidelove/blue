package com.common.system;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public ModelAndView greeting(ModelAndView modelAndView) {
    	modelAndView.addObject("bean",111);
        modelAndView.setViewName("/index.html");
		return modelAndView;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}