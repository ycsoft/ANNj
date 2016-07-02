package com.yangmuyao.data;


import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import org.apache.commons.math3.analysis.function.Sin;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by apple on 16/6/26.
 */
public class SinTanExp implements IFunction {

    private  double [] x = null;
    public SinTanExp(){
        this.x = null;
    }
    public double [] value( double []x ){

        int len  = x.length;
        int i = 0;
        this.x = x;
        double res[] = new double[len];

        for (i = 0; i < len; i++){

            res[i] = Math.cos(x[i])*Math.tan(x[i]*x[i])*Math.exp(x[i]);
        }
        return res;
    }


    public void saveToFile(String file) throws Exception{

        FileOutputStream fos = new FileOutputStream(file);
        int len = x.length;
        int i = 0;
        double [] y = value(x);
        DataTable   dataTable = new DataTable(Double.class, Double.class);


        fos.write("x,y\n".getBytes());
        for ( i = 0 ; i < len; i++){
            StringBuffer sbf = new StringBuffer();
            sbf.append(x[i]).append(",").append(y[i]).append("\n");
            System.out.println(x[i]+","+y[i]);
            fos.write(sbf.toString().getBytes());
            dataTable.add(x[i],y[i]);
        }
        XYPlot  xyPlot = new XYPlot(dataTable);

        fos.flush();
        fos.close();
    }





}
