package com.yangmuyao.alg;

import org.encog.Encog;
import org.encog.mathutil.rbf.RBFEnum;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.rbf.RBFNetwork;
import org.encog.neural.rbf.training.SVDTraining;

/**
 * Created by apple on 16/6/30.
 */
public class RBF implements IAnnAlg {

    //
    //输入样本
    private     double[][]   samp_x;
    private     double[][]   samp_y;
    private     int          samp_len = 0;
    //XD.Yang
    //输入及输出维度
    private int     input_dim  = 0;
    private int     output_dim = 0;
    //
    //最大训练次数
    private int     maxIter = 10000;
    //
    //容许误差
    private double  epsion  = 1.0E-5;

    private RBFNetwork  network = null;

    public RBF( int maxiter, double err){
        if ( maxiter > 1 )
            this.maxIter = maxiter;
        if ( err < 1 && err > 0)
            epsion = err;
    }

    public void setDimension(int ind, int outd) {

        input_dim  = ind;
        output_dim = outd;

    }

    public void setSample(double[][] x, double[][] y) throws Exception {

        if ( x == null || y == null ){
            throw new AlgException("输入样本设置不正确");
        }
        this.samp_len = x.length;
        this.input_dim = x[0].length;
        this.output_dim = y[0].length;

        this.samp_x = Utils.deepCopy(x);//new double[samp_len][input_dim];
        this.samp_y = Utils.deepCopy(y);//new double[samp_len][output_dim];

        createNN();
    }

    public double[][] predict(double[][] x) throws AlgException {
        //return new double[0][0];
        double [][] res = null;
        int    rows = x.length;
        int    cols = x[0].length;
        int    i = 0;


        if ( x == null || x.length == 0 )
            return res;
        res =  new double[rows][cols];

            MLDataSet dataSet = new BasicMLDataSet(x,x);
            for ( MLDataPair pair : dataSet){
                MLData input = pair.getInput();
                MLData output = network.compute(input);
                res[i++][0] = output.getData(0);
            }

        return res;
    }

    public void train() throws AlgException {
        if ( network == null){
            throw new AlgException("网络尚未准备好");
        }

        MLDataSet   dataSet = new BasicMLDataSet(samp_x,samp_y);
        MLTrain     train   = new SVDTraining(network,dataSet);

        int         epoch = 1;
        double      err   = 0.0;

        do{
            train.iteration();
            err = train.getError();
            System.out.println("Iteration: #" + epoch + "\tError:" + train.getError());
            epoch++;
        }while (epoch > maxIter && err > epsion);
    }

    private void createNN() throws Exception{

        if ( input_dim <= 0 || output_dim <= 0){
            throw  new Exception("输入或输出的维度设置不正确");
        }
        if ( samp_len <= 0 || input_dim <= 0 ){
            throw new AlgException("输入数据尚未设置,无法创建RBF网络");
        }

//        String architecture = "?->GAUSSIAN(c=4)->?";
//        MLMethodFactory factory = new MLMethodFactory();
//        network = (RBFNetwork)factory.create(MLMethodFactory.TYPE_RBFNETWORK,architecture,1,1);

        network = new RBFNetwork(1,7,1,RBFEnum.Gaussian);
    }

    public void close(){
        Encog.getInstance().shutdown();
    }
}
