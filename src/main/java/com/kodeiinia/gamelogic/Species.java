package com.kodeiinia.gamelogic;



public class Species {

    private int id;
    private String name;
    private int numberOfAnimals;

    public Species(int id, String name, int numberOfAnimals) {
        this.id = id;
        this.name = name;
        this.numberOfAnimals = numberOfAnimals;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfAnimals() {
        return numberOfAnimals;
    }

    public void setNumberOfAnimals(int numberOfAnimals) {
        this.numberOfAnimals = numberOfAnimals;
    }

    @Override
    public String toString() {
        return this.name + " ("
                + this.id + ") has "
                + this.numberOfAnimals + " members.";
    }

}
