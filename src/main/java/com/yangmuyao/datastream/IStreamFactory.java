package com.yangmuyao.datastream;

/**
 * Created by apple on 16/7/9.
 */
public interface IStreamFactory {

    /**
     * 从文件中读取数据
     * @param fname 文件名
     * @return      数据
     * @throws Exception
     */
    public double[][] readln( String fname ) throws Exception;

    /**
     * 将数据写入文件
     * @param fname 文件名
     * @param data  数据
     * @throws Exception
     */
    public void       writedata(String fname ,double [][] data) throws  Exception;

}
