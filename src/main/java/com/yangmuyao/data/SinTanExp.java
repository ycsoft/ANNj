package com.yangmuyao.data;


import com.yangmuyao.alg.AlgException;
import com.yangmuyao.functions.IFunction;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import org.apache.commons.math3.analysis.function.Sin;

import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by apple on 16/6/26.
 */
public class SinTanExp implements IFunction {

    public double[][] value(double[][] x) throws AlgException {
        return new double[0][];
    }
}
