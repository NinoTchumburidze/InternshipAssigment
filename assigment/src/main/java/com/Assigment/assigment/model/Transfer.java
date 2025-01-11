package com.Assigment.assigment.model;

public class Transfer {
    private final int weight;
    private final int cost;

    public Transfer(int weight, int cost) {
        this.weight = weight;
        this.cost = cost;
    }

    public int getWeight() {
        return weight;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return "Transfer{" + "weight=" + weight + ", cost=" + cost + '}';
    }
}
