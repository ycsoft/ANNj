package com.yangmuyao.functions;

import com.yangmuyao.alg.AlgException;

/**
 * Created by apple on 16/7/2.
 */
public class YXD_Sin implements IFunction{

    public double[][] value(double[][] x) throws AlgException {

        assert(x != null);
        int     len         = x.length , dim = 1;
        double  [][]result  = null;

        if ( len == 0 ){
            throw new AlgException("输入里无真实有效的数据");
        }

        dim  = x[0].length;

        if ( dim > 1){
            throw new AlgException("输入数据不正确");
        }

        result = new double[len][dim];

        for ( int i = 0 ; i < len; i++){
            result[i][0] = 1 + Math.sin(x[i][0]);
        }

        return result;
    }
}
