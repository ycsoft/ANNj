package com.yangmuyao.alg;

import org.encog.neural.pattern.RadialBasisPattern;

/**
 * Created by apple on 16/6/30.
 */
public class RBF implements IAnnAlg {

    RadialBasisPattern  pattern = new RadialBasisPattern();
    

    public void setDimension(int ind, int outd) {

    }

    public void setSample(double[][] x, double[][] y) throws AlgException {

    }

    public double[][] predict(double[][] x) throws AlgException {
        return new double[0][];
    }

    public void train() throws AlgException {

    }
}
