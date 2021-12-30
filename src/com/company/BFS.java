package com.company;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.HashMap;

public class BFS {

    private LinkedList<Coordinate> visited;
    private Map<String, String> previous;
    private int h,w;
    private Coordinate source, end;
    private MazeClear m;

    public BFS(MazeClear m, Coordinate source, Coordinate end)
    {

        this.h = m.getH();
        this.w = m.getW();
        this.source = source;
        this.end = end;

        this.m = new MazeClear(m);

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

            LinkedList<Coordinate> vicini = visiting.neighbours();

            for (Coordinate n : vicini) {

                if (m.checkClearAndSetFalse(n)) {
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
