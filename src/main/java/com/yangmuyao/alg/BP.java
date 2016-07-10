package com.yangmuyao.alg;

import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.back.Backpropagation;

/**
 * Created by apple on 16/6/29.
 */
public class BP implements IAnnAlg {

    private double [][] x     = null;
    private double [][] y     = null;
    private int     in_dim    = 0;
    private int     out_dim   = 0;
    public  double epison = 4.5E-4;
    private     int     hidden = 0;

    private BasicNetwork    network = null;


    public BP(double err, int hidden){
        if ( err < 0 || hidden <= 1)
            return;
        epison = err;
        this.hidden =  hidden;
    }
    public BP(){
        epison = 0.04;
    }
    public BasicNetwork getNetwork(){
        return network;
    }

    public void setDimension(int ind, int outd) {
        in_dim  = ind;
        out_dim = outd;

        createNN();
    }

    public void setSample(double[][] x, double[][] y) throws AlgException {

        int len = x.length , j = 0;

        if ( len != y.length ){
            System.out.println("Sample data error");
            throw  new AlgException("Sample data exception");
        }

        //
        //深拷贝
        this.x = new  double[len][in_dim];
        this.y = new  double[len][out_dim];

        for ( int i = 0 ; i < len; i++)
        {
            for ( j = 0 ; j < in_dim ; j++ )
                this.x[i][j] = x[i][j];

            for ( j = 0 ; j < out_dim; j++)
                this.y[i][j] = y[i][j];

        }

    }

    private void createNN(){

        network  = new BasicNetwork();

        network.addLayer( new BasicLayer(null,true,in_dim) );
        network.addLayer( new BasicLayer(new ActivationSigmoid(),true,hidden) );
        network.addLayer( new BasicLayer( new ActivationSigmoid(),false,out_dim) );

        network.getStructure().finalizeStructure();
        network.reset();
    }

    public void train(){

        MLDataSet   datas = new BasicMLDataSet(x,y);

        MLTrain     train = new Backpropagation(network,datas);

        int epoch = 1;
        do{
            epoch += 1;
            train.iteration();
            System.out.println("Iteration: " + epoch + " Error:" + train.getError());
        }while( train.getError() > epison && epoch < 140000);

    }

    public double[][] predict(double[][] x) throws AlgException{


        int len = x.length;

        double [][] res = new double[len][out_dim];

        for ( int i = 0 ; i < len; i++)
        {
            network.compute(x[i], res[i]);
        }

        return res;
    }

    public void close(){
        Encog.getInstance().shutdown();
    }
}
