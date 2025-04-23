package com.david.shareNotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class frontEndController {
    @GetMapping("/")
    public String home() {
        return "index";
    }
}
