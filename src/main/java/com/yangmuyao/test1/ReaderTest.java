package com.yangmuyao.test1;


import com.yangmuyao.datastream.DataReaderWriter;

/**
 * Created by apple on 16/7/9.
 */
public class ReaderTest {

    public static void main(String [] args) throws  Exception{

        DataReaderWriter  reader = new DataReaderWriter();

        double[][] data = reader.readln("1.txt");

        reader.writedata("2.txt",data);
    }
}
