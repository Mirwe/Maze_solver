package com.company;

import java.util.LinkedList;

public class Coordinate implements Comparable<Coordinate>{

    private int row,col;
    private double fCost;
    private String key;

    public Coordinate(int row,int col)
    {
        this.row=row;
        this.col=col;
        this.key = row + "_" + col;
        this.fCost = Double.POSITIVE_INFINITY;
    }

    public Coordinate left(){
        return new Coordinate(row-1,col);
    }

    public Coordinate right(){
        return new Coordinate(row+1,col);
    }
    public Coordinate down(){
        return new Coordinate(row,col-1);
    }
    public Coordinate up(){
        return new Coordinate(row,col+1);
    }

    public LinkedList<Coordinate> neighbours(){
        LinkedList<Coordinate> vicini = new LinkedList<>();
        vicini.add(this.left());
        vicini.add(this.right());
        vicini.add(this.up());
        vicini.add(this.down());
        return vicini;


    }

    public int row(){
        return this.row;
    }

    public int col(){
        return this.col;
    }


    public String label() {
        return this.key;
    }

    public boolean isEqual(Coordinate p){

        return this.row == p.row() && this.col == p.col();

    }

    @Override
    public int compareTo(Coordinate c)
    {
        // comparison based on the total cost of reaching the node

        if(this.fCost>c.fCost) return +1;
        else if(this.fCost<c.fCost) return -1;
        return 0;
    }

    public void setfCost(double f){
        this.fCost = f;
    }


    public void print(){
        System.out.println("row: " + this.row+ " col: "+ this.col + " fCost: "+ this.fCost);
    }

}
