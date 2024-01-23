package com.flexidev.village.controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.flexidev.village.models.Villager;
import com.flexidev.village.services.CalculatorService;

@Controller
public class VillageController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @PostMapping("/")
    public String calculate(@RequestParam int ageOfDeathA, @RequestParam int ageOfDeathB, @RequestParam int yearOfDeathA, @RequestParam int yearOfDeathB, Model model) {
        try {
            Villager villagerA = new Villager(ageOfDeathA, yearOfDeathA);
            Villager villagerB = new Villager(ageOfDeathB, yearOfDeathB);

            // Get each villager's year of birth
            int yearOfBirthA = villagerA.getYearOfBirth();
            int yearOfBirthB = villagerB.getYearOfBirth();

            if (yearOfBirthA < 0 || yearOfBirthB < 0) {
                model.addAttribute("isError", true);
                model.addAttribute("errorMessage", "Year of birth cannot be less than 0.");
                model.addAttribute("result", -1);

                return "index";
            }

            BigDecimal result = calculatorService.getAverageDeaths(yearOfBirthA, yearOfBirthB);

            model.addAttribute("yearOfBirthA", yearOfBirthA);
            model.addAttribute("yearOfBirthB", yearOfBirthB);
            model.addAttribute("isError", false);
            model.addAttribute("result", result);
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("isError", true);
            model.addAttribute("errorMessage", "An error occurred.");
            model.addAttribute("result", -1);
        }

        // Keep the submitted form value
        model.addAttribute("ageOfDeathA", ageOfDeathA);
        model.addAttribute("yearOfDeathA", yearOfDeathA);
        model.addAttribute("ageOfDeathB", ageOfDeathB);
        model.addAttribute("yearOfDeathB", yearOfDeathB);

        return "index";
    }
}
