package com.flexidev.village.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private List<Integer> sequence = new ArrayList<>();
    private List<Integer> sumMemo = new ArrayList<>();

    public double getAverageDeaths(int personABirthYear, int personBBirthYear) {
        int maxBirthYear = Math.max(personABirthYear, personBBirthYear);
        calculateTotalDeaths(maxBirthYear);

        int totalDeathYearA = sumMemo.get(personABirthYear - 1);
        int totalDeathYearB = sumMemo.get(personBBirthYear - 1);

        return (totalDeathYearA + totalDeathYearB) / 2.0;
    }

    private void calculateTotalDeaths(int maxYear) {
        if (sequence.isEmpty()) {
            sequence.add(1);
            sumMemo.add(1);
        }

        for (int i = sequence.size(); i <= maxYear; i++) {
            int nextValue = sequence.get(i - 1) + (i >= 2 ? sequence.get(i - 2) : 0);
            sequence.add(nextValue);
            sumMemo.add(sumMemo.get(i - 1) + nextValue);
        }
    }
}
