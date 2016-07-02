package com.yangmuyao.functions;

/**
 * Created by apple on 16/7/2.
 */

import com.yangmuyao.alg.AlgException;

/**
 * 函数借口,提供输入-输出的映射
 */
public interface IFunction {

    public double [][]  value( double [][] x) throws AlgException;

}


