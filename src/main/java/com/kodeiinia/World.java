/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kodeiinia;

/**
 *
 * @author Sony
 */
public class World {

    private Integer id;
    private int turn;
    private String title;
    
    public World(Integer id, String title) {
        this.id = id;
        this.turn = 0;
        this.title = title;
    }

    public void increaseTurn() {
        this.turn++;

    }

    public int getId() {
        return this.id;
    }
    
    

    public int getTurn() {
        return this.turn;
    }

    public String getTitle() {
        return this.title;
    }
    
    
    
    public String toString () {
        return "current turn " + this.turn;
    }

}
