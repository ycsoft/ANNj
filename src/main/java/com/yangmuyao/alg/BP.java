package com.yangmuyao.alg;

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
    public  static final      float epison = 0.01f;

    private BasicNetwork    network = null;


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
        network.addLayer( new BasicLayer(new ActivationSigmoid(),true,5) );
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
        }while( train.getError() > epison );

    }

    public double[][] predict(double[][] x) throws AlgException{
        return new double[0][0];
    }
}
