package com.company;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;

public class BFS {

    private boolean[][] m;
    private LinkedList<Coordinate> visited;
    private Map<String, String> previous;
    private int h,w;
    private Coordinate source, end;

    public BFS(boolean[][] m, Coordinate source, Coordinate end)
    {

        this.h = m.length;
        this.w = m[0].length;
        this.source = source;
        this.end = end;

        this.m = new boolean[h][w];
        for(int row=0; row<h; row++){
            for(int col=0; col<w; col++){
                this.m[row][col] = m[row][col];
            }
        }

        previous = new HashMap<String, String>();
        visited = new LinkedList<>();

        launchBFS();

    }

    public void launchBFS(){
        Queue<Coordinate> toVisit = new LinkedList<>();
        toVisit.add(this.source);

        while(toVisit.size() > 0){
            Coordinate visiting = toVisit.remove();
            visited.add(visiting);

            LinkedList<Coordinate> vicini = visiting.neighbors();

            for (Coordinate n : vicini) {

                if (isValid(n)) {
                    previous.put(n.label(), visiting.label());

                    if (n.isEqual(end)) {
                        System.out.println("Solution found");
                        return;
                    } else
                        toVisit.add(n);
                }
            }

        }

        System.out.println("Impossible Maze");

    }

    public boolean isValid(Coordinate point){
       if(point.row() >= 0 && point.row() < h) {
           if (point.col() >= 0 && point.col() < w) {

               boolean isClear = this.m[point.row()][point.col()];
               this.m[point.row()][point.col()] = false;

               return isClear;
           }
       }
       return false;
    }

    public LinkedList<String> getPath(){

        String n = previous.get(end.label());
        LinkedList<String> path = new LinkedList<>();

        while(n != source.label()){
            path.add(n);
            n = previous.get(n);
        }

        return path;

    }

}
