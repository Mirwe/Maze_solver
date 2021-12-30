package com.company;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

public class Astar {

    private Coordinate from, to;

    private LinkedList<Coordinate> visited;
    private PriorityQueue<Coordinate> pQueue; //priority queue, it prioritize the Coordinates with lower fCost
    private Map<String, String> cameFrom;
    private int w,h;
    private boolean[][] m;
    private boolean solutionFound;


    public Astar(boolean[][] m, Coordinate from, Coordinate to){

        this.solutionFound = false;
        this.from = from;
        this.to = to;
        this.h = m.length;
        this.w = m[0].length;
        this.m = m;
        this.visited = new LinkedList<>();
        this.cameFrom = new HashMap<String, String>();
        this.pQueue = new PriorityQueue<>();

        pQueue.add(from);

        while(pQueue.size() > 0){

            Coordinate current = pQueue.poll();
            visited.add(current);

            if(current.isEqual(to)) {
                this.solutionFound = true;
                System.out.println("Solution found");
                break;
            }

            else{

                LinkedList<Coordinate> vicini = current.neighbors();

                for (Coordinate n : vicini) {
                    if (isValid(n)) {
                        cameFrom.put(n.label(), current.label());
                        n.setfCost(cameFrom.size() + h(n));
                        pQueue.add(n);

                    }

                }

            }

        }

        if (!this.solutionFound)
            System.out.println("Impossible Maze");

    }

    public double h(Coordinate c){
        return Math.sqrt((to.row() - c.row()) +(to.col() - c.col()));
    }

    public boolean isValid(Coordinate point){
        if(point.row() >= 0 && point.row() < h)
            if(point.col() >= 0 && point.col() < w){

                boolean isClear = this.m[point.row()][point.col()];
                this.m[point.row()][point.col()] = false;

                return isClear;
            }

        return false;
    }

    public LinkedList<String> getPath(){

        if (this.solutionFound){
            String n = cameFrom.get(to.label());
            LinkedList<String> path = new LinkedList<>();

            while(n != from.label()){
                path.add(n);
                n = cameFrom.get(n);
            }

            return path;
        }

        return null;

    }
}
