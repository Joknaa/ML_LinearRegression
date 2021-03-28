package Models;

import java.util.ArrayList;
import java.util.List;

public class DataSetModel {
    private static final List<Integer> dataSet_X = new ArrayList<>();
    private static final List<Integer> dataSet_Y = new ArrayList<>();
    private static final List<float[]> predictedData = new ArrayList<>();
    private static final List<float[]> P = new ArrayList<>();
    private static final List<Float> Cost = new ArrayList<>();

    public static void AddDataSet_X(int x){dataSet_X.add(x); }
    public static void AddDataSet_Y(int y){dataSet_Y.add(y); }
    public static List<Integer> GetInputDataSet(){ return dataSet_X; }
    public static List<Integer> GetOutputDataSet(){ return dataSet_Y; }

    public static void AddPredictedData(float[] data) { predictedData.add(data);}
    public static float[] GetPredictedData(int iteration) { return predictedData.get(iteration);}

    public static void AddP0AndP1(float p0, float p1){ P.add(new float[]{p0, p1});}
    public static float[] GetP0AndP1(int iteration){ return P.get(iteration);}

    public static void AddCost(float cost){ Cost.add(cost);}
    public static float GetCost(int iteration){ return Cost.get(iteration);}
    public static List<Float> GetCostsList(){ return Cost;}

    public static void ClearIterativeData(){
        predictedData.clear();
        P.clear();
        Cost.clear();
    }

}
