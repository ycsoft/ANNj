package com.yangmuyao.datastream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by apple on 16/7/9.
 */
public class DataReaderWriter implements IStreamFactory {

    public double[][] readln( String fname ) throws Exception
    {

        ArrayList<String> sl = new ArrayList<String>();
        BufferedReader  reader = new BufferedReader( new FileReader(fname));
        int             cols = 0;
        int             rows = 0;
        int             i    = 0;
        int             j    = 0;
        DataSet         dataSet = null;
        //
        //文件中的数据以逗号分割,最后一列为输出值
        //
        String line = reader.readLine();
        while( line != null )
        {
            if ( line.length() > 3){
                sl.add(line);
            }
            line = reader.readLine();
        }
        rows = sl.size();
        cols = sl.get(0).split(",").length;

        dataSet = new DataSet(rows,cols);
        for ( String ls: sl){

            String [] nums = ls.split(",");
            for ( i = 0 ; i < cols; i++ ){
                dataSet.setData(j,i,Double.parseDouble(nums[i]));
            }
            j++;
        }

        return dataSet.getData();
    }

    public void  writedata(String fname ,double [][] data) throws  Exception{

        PrintWriter printWriter = new PrintWriter( new FileWriter(fname) );
        int         i = 0;
        int         j = 0;
        int         rows = 0;
        int         cols = 0;

        assert ( data != null && data.length > 0 && data[0].length > 0);

        rows = data.length;
        cols = data[0].length;

        for ( i = 0 ; i < rows; i++){
            StringBuffer    sbuf = new StringBuffer();
            for ( j = 0; j < cols; j++){
                sbuf.append(data[i][j]);
                if ( j < cols-1)
                    sbuf.append(",");
            }
            printWriter.println(sbuf.toString());
        }

        printWriter.close();
    }
}
