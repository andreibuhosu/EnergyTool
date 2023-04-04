package com.example.coursework2;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder a = new BCryptPasswordEncoder();
        String raw = "1234";
        String encoded = a.encode(raw);
        System.out.println(encoded);
    }

}
