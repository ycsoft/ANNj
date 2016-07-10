package com.yangmuyao.datastream;

/**
 * Created by apple on 16/7/9.
 */

/**
 * 为啦便于数据读取而封装的数据结构
 */
public class DataSet {
    private int     rows = 0;

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    private int     cols = 0;

    private double[][] data = null;


    public DataSet( double  [][]  data){
        this.data = data;
    }

    public  DataSet( int rows, int cols) {
        if ( rows < 0 || cols < 0){
            return;
        }

        data = new double[rows][cols];
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void setData(int row, int col, double value){
        assert ( row > 0 && col > 0);

        data[row][col] = value;
    }

}
