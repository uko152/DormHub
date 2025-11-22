package com.example.demo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestMessage 
{
    @GetMapping("/test")
    public String hello()
    {
        return "Guten Morgen, liebes Deutschland\n";
    }
}
