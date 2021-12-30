package com.company;

public class Main {

    public static void main(String[] args) {
        Maze m = new Maze("maze.txt");
        //Maze m = new Maze(10,10);
        m.solveMaze();

    }
}
