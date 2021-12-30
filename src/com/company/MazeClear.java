package com.company;

public class MazeClear {
    //boolean matrix each cell tells whether the cell is clear or not

    public boolean[][] MazeClear;
    private int h,w;

    public MazeClear(int w, int h){
        this.h = h;
        this.w = w;
        this.MazeClear = new boolean[h][w];

    }

    public MazeClear(MazeClear m){
        this.h = m.getH();
        this.w = m.getW();
        this.MazeClear = new boolean[h][w];

        for(int row=0; row<h; row++){
            for(int col=0; col<w; col++){
                this.MazeClear[row][col] = m.MazeClear[row][col];
            }
        }

    }

    public boolean checkIsIn(Coordinate point){
        if(point.row() >= 0 && point.row() < h)
            if (point.col() >= 0 && point.col() < w)
                return true;

        return false;
    }

    public boolean checkClear(Coordinate point){
        return this.MazeClear[point.row()][point.col()];

    }

    public boolean checkClearAndSetFalse(Coordinate point){
        if(checkIsIn(point)) {

            boolean isClear = this.MazeClear[point.row()][point.col()];
            this.MazeClear[point.row()][point.col()] = false;

            return isClear;
        }

        return false;
    }

    public int getH(){
        return this.h;
    };

    public int getW(){
        return this.w;
    }

    public void setVisited(Coordinate c){
        this.MazeClear[c.row()][c.col()] = true;
    }
}
