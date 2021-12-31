package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;


public class Maze {

    private static final char WALL = '|';
    private static final char START = 'S';
    private static final char EXIT = 'E';
    private static final char PATH = '°';

    private int h, w;

    private MazeClear m;
    private Coordinate startPosition;
    private Coordinate endPosition;
    private String text;



    public Maze(String filename) {
        // Read txt file containing the maze for initialization
        try {

            File mazeTxt = new File(filename);
            Scanner myReader = new Scanner(mazeTxt);
            int count = 0;

            String data = "";
            text = "";

            System.out.println("Reading input maze...");

            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
                text += data +"\n";

                if(data.indexOf(START) != -1)
                    startPosition = new Coordinate(count, data.indexOf(START));
                if(data.indexOf(EXIT) != -1)
                    endPosition = new Coordinate(count, data.indexOf(EXIT));

                count += 1;
            }

            myReader.close();

            this.h=count;
            this.w=data.length();

            //isClear = new boolean[h][w];
            m = new MazeClear(w,h);

            String[] lines = text.split("\n");

            for(int row=0; row<h; row++){
                for(int col=0; col<w; col++){

                    if (lines[row].charAt(col) == WALL)
                        m.MazeClear[row][col] = false;
                    else
                        m.MazeClear[row][col] = true;
                }
            }



        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void solveMaze(){


        // Comparison between BFS solver and A* solver
        System.out.println("Solving maze with BFS algorithm...");
        long startTime = System.nanoTime();
        BFS bfs = new BFS(this.m, this.startPosition, this.endPosition);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;

        System.out.println("Time Spent: " + duration + " milliseconds\n");


        System.out.println("Solving maze with A* algorithm...");
        long startTimeA = System.nanoTime();
        Astar astar = new Astar(this.m, this.startPosition, this.endPosition);
        long endTimeA = System.nanoTime();
        long durationA = (endTimeA - startTimeA)/1000000;

        System.out.println("Time Spent: " + durationA+ " milliseconds");


        LinkedList<String> path = astar.getPath();
        if(path != null)
            drawSolution(path);

    }

    public void drawSolution(LinkedList<String> path){

        String[] lines = text.split("\n");

        System.out.println("\nShortest Path length: " + path.size() + "\n");
        System.out.println("Solution: ");

        for(int i=0; i<path.size(); i++){
            String[] coordinates = path.get(i).split("_");
            int row = Integer.parseInt(coordinates[0]);
            int col = Integer.parseInt(coordinates[1]);
            lines[row] = lines[row].substring(0,col)+PATH+lines[row].substring(col+1);
        }

        for(int j=0; j< lines.length; j++)
            System.out.println(lines[j]);

    }
}
