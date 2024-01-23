package com.flexidev.village.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private List<BigInteger> sequence = new ArrayList<>(); // List to keep track the sequence of Fibonacci numbers
    private List<BigInteger> sumMemo = new ArrayList<>(); // List to keep track the amount of villagers killed on each year

    public BigDecimal getAverageDeaths(int personABirthYear, int personBBirthYear) throws Exception {
        int maxBirthYear = Math.max(personABirthYear, personBBirthYear);

        // Calculate the total deaths of the higher birth year
        calculateTotalDeaths(maxBirthYear);

        BigInteger totalDeathYearA = sumMemo.get(personABirthYear - 1);
        BigInteger totalDeathYearB = sumMemo.get(personBBirthYear - 1);

        BigDecimal averageDeaths = BigDecimal.valueOf(totalDeathYearA.add(totalDeathYearB).doubleValue() / 2.0);

        return averageDeaths;
    }

    private void calculateTotalDeaths(int maxYear) throws Exception {
        if (sequence.isEmpty()) {
            sequence.add(BigInteger.ONE);
            sumMemo.add(BigInteger.ONE);
        }

        for (int i = sequence.size(); i <= maxYear; i++) {
            BigInteger nextValue = sequence.get(i - 1).add(i >= 2 ? sequence.get(i - 2) : BigInteger.valueOf(0));
            sequence.add(nextValue);
            sumMemo.add(sumMemo.get(i - 1).add(nextValue));
        }
    }
}
