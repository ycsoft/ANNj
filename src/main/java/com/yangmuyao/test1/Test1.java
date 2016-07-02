package com.yangmuyao.test1;

import com.yangmuyao.alg.BP;
import com.yangmuyao.data.SinTanExp;

/**
 * Created by apple on 16-6-25.
 */


public class Test1 {

    public static void main(String [] args) throws Exception{

        int   ind = 1,outd = 1;
        int   len = 400;

        double [][]x = new double[len][ind];
        double [][]y = new double[len][outd];

        for( int i = 0 ; i < len; i++)
        {
            x[i][0] = 0.01 * i;
            y[i][0] = Math.sin( x[i][0] );
        }

        BP  bp = new BP();

        bp.setDimension(ind,outd);

        bp.setSample(x,y);

        bp.train();

    }


}
