package com.mesung.book.springboot.web;

import com.mesung.book.springboot.web.dto.HelloReponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloReponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount, HttpServletResponse response) {

        Cookie cookie = new Cookie("custNo", "112233");
        cookie.setPath("/");

        response.addCookie(cookie);

        return new HelloReponseDto(name, amount);
    }
}
