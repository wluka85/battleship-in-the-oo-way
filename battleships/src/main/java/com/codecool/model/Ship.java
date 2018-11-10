package com.codecool.model;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private List<Square> shipParts = new ArrayList<>();
    private int x;
    private int y;
    private int shipLength;
    private boolean vertical;
    private int healthPoints;

    public Ship(int x, int y, int shipLength, boolean vertical) {
        this.x = x;
        this.y = y;
        this.shipLength = shipLength;
        this.vertical = vertical;
        this.healthPoints = shipLength;
        makeShip();
    }

    private void makeShip() {
        for (int i = 0; i < shipLength; i++) {
            if (vertical)
                shipParts.add(new Square(x, y + i));

            else
                shipParts.add(new Square(x + i, y));
        }
    }

    public int getShipSize() {
        return shipParts.size();
    }

    public int getXOfSquare(int indexSquare) {
        return shipParts.get(indexSquare).getX();
    }

    public int getYOfSquare(int indexSquare) {
        return shipParts.get(indexSquare).getY();
    }

    public boolean getVertical() {
        return vertical;
    }

    void decrementHealthPoints() {
        healthPoints -= 1;
    }

    int getHealthPoints() {
        return healthPoints;
    }

    boolean checkIfShip(int x, int y) {
        for (int i = 0; i < shipParts.size(); i++) {
            if (getXOfSquare(i) == x && getYOfSquare(i) == y) {
                return true;
            }
        }
        return false;
    }
}
