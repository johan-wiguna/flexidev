package com.flexidev.village.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.flexidev.village.models.Villager;

@Controller
public class VillageController {

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping("/")
    public String calculate(@RequestParam int ageOfDeathA, @RequestParam int ageOfDeathB, @RequestParam int yearOfDeathA, @RequestParam int yearOfDeathB, Model model) {
        Villager villagerA = new Villager(ageOfDeathA, yearOfDeathA);
        Villager villagerB = new Villager(ageOfDeathB, yearOfDeathB);

        double result = villagerA.getYearOfBirth() - villagerB.getYearOfBirth();

        model.addAttribute("ageOfDeathA", ageOfDeathA);
        model.addAttribute("yearOfDeathA", yearOfDeathA);
        model.addAttribute("ageOfDeathB", ageOfDeathB);
        model.addAttribute("yearOfDeathB", yearOfDeathB);
        model.addAttribute("yearA", villagerA.getYearOfBirth());
        model.addAttribute("yearB", villagerB.getYearOfBirth());
        model.addAttribute("result", result);

        return "index";
    }
}