package com.yangmuyao.alg;

import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.svm.KernelType;
import org.encog.ml.svm.SVM;
import org.encog.ml.svm.SVMType;
import org.encog.ml.svm.training.SVMSearchTrain;
import org.encog.ml.train.MLTrain;

/**
 * Created by apple on 16/7/10.
 */
public class XD_SVM implements IAnnAlg {

    private     int input_dim = 0;
    private     int output_dim = 0;
    private     int sample_len = 0;

    private     double [][] sample_x = null;
    private     double [][] sample_y = null;

    private SVM     svm = null;

    public void setDimension(int ind, int outd) {
        if ( ind <= 0 || outd <= 0){
            System.out.println("维数不能为0或负数");
            return;
        }

        input_dim = ind;
        output_dim = outd;
    }

    public void setSample(double[][] x, double[][] y) throws Exception {

        if ( x == null || y == null ){
            throw new AlgException("训练数据有误");
        }

        sample_len = x.length;
        input_dim = x[0].length;
        output_dim = y[0].length;

        sample_x =  Utils.deepCopy(x);//new double[sample_len][input_dim];
        sample_y =  Utils.deepCopy(y);//new double[sample_len][output_dim];

        svm = new SVM(sample_len, SVMType.EpsilonSupportVectorRegression, KernelType.RadialBasisFunction);
    }

    public double[][] predict(double[][] x) throws AlgException {

        if ( x == null )
            return null;

        double  [][] res = new double[x.length][1];

        for ( int i = 0 ; i < x.length ; i++){
            BasicMLData mlData = new BasicMLData(x[i]);
            MLData      out = svm.compute(mlData);
            res[i][0]   = out.getData(0);
        }

        return res;
    }

    public void train() throws AlgException {

        MLDataSet   dataSet = new BasicMLDataSet(sample_x,sample_y);
        MLTrain train = new SVMSearchTrain(svm,dataSet);

        int epoch = 1;
        do {
            train.iteration();
            System.out.println("Epoch: #"+ epoch + "\t Error:" + train.getError());
        }while( epoch++ < 10000);
    }

    public void close() {

    }
}
