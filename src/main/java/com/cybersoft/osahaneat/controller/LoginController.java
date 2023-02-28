package com.cybersoft.osahaneat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    //permitAll()
    //authen()
    @PostMapping("/signin")
         @RequestParam String username,
        @RequestParam String password
    )
    {
        return new ResponseEntity<>("Hello login", HttpStatus.OK);
    }

    @PostMapping("/signup")
    //express security
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> signup() {

        return new ResponseEntity<>("Hello signup", HttpStatus.OK);
    }


}
