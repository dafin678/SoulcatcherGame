package id.ac.ui.cs.advprog.soulcatcher.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {  //buat nge-tes, yang /all gak perlu login, yang /logged perlu login
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/logged")
    @PreAuthorize("isAuthenticated()")
    public String loggedAccess() {
        return "logged Content.";
    }
}
