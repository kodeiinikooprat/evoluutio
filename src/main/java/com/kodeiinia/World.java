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
    
    public World(Integer id, String title, Integer turn) {
        this.id = id;
        this.turn = turn;
        this.title = title;
    }

    public void increaseTurn() {
        this.turn++;
        //return this.turn;
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
