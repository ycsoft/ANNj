package com.yangmuyao.test1;

import com.yangmuyao.alg.RBF;
import com.yangmuyao.datastream.DataReaderWriter;
import com.yangmuyao.functions.YXD_Sin;
import org.encog.util.arrayutil.NormalizationAction;
import org.encog.util.arrayutil.NormalizedField;

import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by apple on 16-6-25.
 */


public class Test1 {


    public void change( double [] x){
        x[0] = 1;
        x[1] = 2;
    }

    public static void main(String [] args) throws Exception{

        int   ind = 1,outd = 1;
        int   len = 200;
        double  maxvalue = 7.0;
        double  step     = maxvalue / len;

        double [][]x = new double[len][ind];
        double [][]y = null;

        DataReaderWriter    readerWriter = new DataReaderWriter();
        //
        //NumberFormat    format =  NumberFormat.getNumberInstance();
        //
        DecimalFormat   format = new DecimalFormat("00.00");
        //
        //format.setMaximumFractionDigits(4);
        // X的取之范围:[0,4)
        //
        for( int i = 0 ; i < len; i++)
        {
            x[i][0] =  step * i;
        }
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=-==-=-=-=-=-=-=-=");
        //
        //简单模拟正弦函数
        //
        y = new YXD_Sin().value(x);

        readerWriter.writedata("x-ideal.txt",x);
        readerWriter.writedata("y-ideal.txt",y);

        //
        //训练数据归一化
        //
        NormalizedField field = new NormalizedField(NormalizationAction.Normalize,"x",maxvalue,0.0,1.0,0.0);
        NormalizedField yfield = new NormalizedField(NormalizationAction.Normalize,"y",2.0,0.0,1.0,0.0);

        //
        //训练数据的输入输出均需要做归一化处理
        //
        for ( int i = 0 ; i < len; i++)
        {
            x[i][0] = field.normalize(x[i][0]) ;
            y[i][0] = yfield.normalize(y[i][0]);
        }

//        BP  bp = new BP(0.00000001,8);
        RBF bp = new RBF(10000,1.0E-4);
        //XD_SVM bp = new XD_SVM();
        bp.setDimension(ind,outd);

        bp.setSample(x,y);

        bp.train();
        double [][] ppy =  bp.predict(x);

        for ( int i = 0 ; i < len; i++)
        {
            //System.out.println("理想输出 : " + yfield.deNormalize(y[i][0]) + " 预测输出 :" + yfield.deNormalize(ppy[i][0]));
        }


        //
        // 在原始数据的基础上施加随机噪声,判断BP网络的预测精度
        //
        Random   rand = new Random();

        double [][] testx= new double[300][ind];

        step = maxvalue / 300.0;
        //
        //添加白噪声,测试BP预测能力
        //
        for ( int i = 0 ; i < testx.length; i++ )
        {
            testx[i][0] += step * i;
        }
        double [][] ideal_y = new YXD_Sin().value(testx);

        //
        //存储测试数据
        //
        readerWriter.writedata("test-x.txt",testx);
        readerWriter.writedata("test-ideal-y.txt",ideal_y);


        for ( int i = 0 ; i < testx.length; i++)
            testx[i][0] = field.normalize(testx[i][0]);
        double [][]py = bp.predict(testx);

        for ( int i = 0 ; i < testx.length; i++)
            py[i][0] = yfield.deNormalize(py[i][0]);

        readerWriter.writedata("test-py.txt",py);

        for ( int i = 0 ; i < testx.length; i++)
            System.out.println("x:" + field.deNormalize(testx[i][0]) + " ideal:" + ideal_y[i][0] + " Predicct: " + py[i][0] +
                    " Error:" + Math.abs(py[i][0] - ideal_y[i][0]));





//        double [][] x1 = new double[len][ind];
//        double [][] y1 = new double[len][outd];
//        for (int i = 0 ; i < len; i++){
//            x1[i][0] = rand.nextDouble() * 6 ;//+ rand.nextDouble()*0.01;
//        }
//        y1 = new YXD_Sin().value(x1); //理想输出
//
//        //
        //计算网络预测输出,先将输入归一化
        //
//        for ( int i = 0 ; i < len; i++){
//            x1[i][0] = field.normalize(x1[i][0]);
//        }
//
//        double [][] py1 = bp.predict(x1);
//
//        for ( int i = 0 ; i < len; i++){
//            System.out.println("理想输出:" + (y1[i][0]) + "  预测输出:" + yfield.deNormalize(py1[i][0]));
//        }



        bp.close();

    }


}
