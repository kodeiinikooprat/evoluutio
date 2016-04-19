package com.kodeiinia.gamelogic;


public class Species {

    private int id;
    private String name;
    private int numberOfAnimals;
    private int worldRef;

    public Species(int id, String name, int numberOfAnimals, int worldRef) {
        this.id = id;
        this.name = name;
        this.numberOfAnimals = numberOfAnimals;
        this.worldRef = worldRef;
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
    
    public void grow() {
        int animalsNow = this.getNumberOfAnimals();
        int animalsAfter = animalsNow * 2;
        this.setNumberOfAnimals(animalsAfter);
    }

    public int getWorldRef() {
        return worldRef;
    }
    

    @Override
    public String toString() {
        return this.name + " ("
                + this.id + ") has "
                + this.numberOfAnimals + " members.";
    }

}
