package Models;

import java.util.ArrayList;
import java.util.List;

public class DataSetModel {
    private static final int[] dataSet_X = new int[]{1, 2, 4};
    private static final int[] dataSet_Y = new int[]{2, 1, 5};
    private static final List<float[]> predictedData = new ArrayList<>();
    private static List<float[]> P = new ArrayList<>();

    public static int[] GetInputDataSet(){ return dataSet_X; }
    public static int[] GetOutputDataSet(){ return dataSet_Y; }

    public static void AddPredictedData(float[] data) { predictedData.add(data);}
    public static float[] GetPredictedData(int iteration) { return predictedData.get(iteration);}

    public static void AddP0AndP1(float p0, float p1){ P.add(new float[]{p0, p1});}
    public static float[] GetP0AndP1(int iteration){ return P.get(iteration);}

}
