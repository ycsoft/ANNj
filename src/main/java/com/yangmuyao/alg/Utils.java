package com.yangmuyao.alg;

/**
 * Created by apple on 16/7/10.
 */
public class Utils {

    public static double[][] deepCopy( double [][] src){
        if ( src == null){
            return null;
        }

        double [][] res = null;
        int    rows  = src.length;
        int    cols  = src[0].length;

        res = new double[rows][cols];

        for ( int i = 0 ; i < rows; i++){
            for ( int j = 0; j < cols; j++)
                res[i][j] = src[i][j];
        }
        return res;
    }
}
