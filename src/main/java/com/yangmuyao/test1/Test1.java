package com.yangmuyao.test1;

import com.yangmuyao.alg.BP;
import com.yangmuyao.data.SinTanExp;
import com.yangmuyao.functions.YXD_Sin;

/**
 * Created by apple on 16-6-25.
 */


public class Test1 {

    public static void main(String [] args) throws Exception{

        int   ind = 1,outd = 1;
        int   len = 400;

        double [][]x = new double[len][ind];
        double [][]y = null;

        for( int i = 0 ; i < len; i++)
        {
            x[i][0] = 0.01 * i;
        }
        y = new YXD_Sin().value(x);

        BP  bp = new BP();

        bp.setDimension(ind,outd);

        bp.setSample(x,y);

        bp.train();

    }


}
