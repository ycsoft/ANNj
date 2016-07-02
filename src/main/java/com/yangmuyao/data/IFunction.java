package com.yangmuyao.data;

/**
 * Created by apple on 16/6/26.
 */
public interface IFunction {

    public  double []  value( double [] x );
    public  void saveToFile(String file) throws Exception;
}
