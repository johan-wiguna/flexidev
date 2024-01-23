package com.flexidev.village.services;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    private List<BigInteger> sequence = new ArrayList<>();
    private List<BigInteger> sumMemo = new ArrayList<>();

    public BigDecimal getAverageDeaths(int personABirthYear, int personBBirthYear) {
        int maxBirthYear = Math.max(personABirthYear, personBBirthYear);
        calculateTotalDeaths(maxBirthYear);

        BigInteger totalDeathYearA = sumMemo.get(personABirthYear - 1);
        BigInteger totalDeathYearB = sumMemo.get(personBBirthYear - 1);

        BigDecimal averageDeaths = BigDecimal.valueOf(totalDeathYearA.add(totalDeathYearB).doubleValue() / 2.0);
        averageDeaths = averageDeaths.setScale(1, RoundingMode.HALF_UP);

        return averageDeaths;
    }

    private void calculateTotalDeaths(int maxYear) {
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
