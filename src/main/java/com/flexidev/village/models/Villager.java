package com.flexidev.village.models;

public class Villager {
    private int ageOfDeath;
    private int yearOfDeath;
    private int yearOfBirth;

    public Villager(int ageOfDeath, int yearOfDeath) {
        this.ageOfDeath = ageOfDeath;
        this.yearOfDeath = yearOfDeath;
    }

    public int getAgeOfDeath() {
        return this.ageOfDeath;
    }

    public void setAgeOfDeath(int ageOfDeath) {
        this.ageOfDeath = ageOfDeath;
    }

    public int getYearOfDeath() {
        return this.yearOfDeath;
    }

    public void setYearOfDeath(int yearOfDeath) {
        this.yearOfDeath = yearOfDeath;
    }

    public int getYearOfBirth() {
        return this.yearOfDeath - this.ageOfDeath;
    }
}
