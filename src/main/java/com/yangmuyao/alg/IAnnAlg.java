package com.yangmuyao.alg;

/**
 * Created by apple on 16/6/29.
 */
public interface IAnnAlg {


    public void setDimension( int ind, int outd );
    /**
     * 设置训练样本
     * @param x : 自变量
     * @param y : 目标输出
     */
    public void setSample(double [][]x, double [][]y) throws Exception;

    /**
     * 预测给定输入的输出
     * @param x
     * @return
     */
    public double[][] predict( double [][] x) throws  AlgException;

    /**
     * 训练神经网络
     * @throws AlgException
     */
    public void train() throws  AlgException;

    /**
     * 关闭,退出
     */
    public void close();

}
