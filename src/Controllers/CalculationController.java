package Controllers;

import Views.UI.GraphPanel;

import java.util.Arrays;
import java.util.List;

import static Models.DataSetModel.*;

public class CalculationController {
    public static final int DEFAULT_MAX_ITERATIONS = 50;
    private static int MAX_ITERATIONS;
    private static int iterationCounter = 0;
    private static float Alpha;

    public static void Start(){
        while (MAX_ITERATIONS-- > 0) {
            CalculatePredictedValues();
            CalculateSquaredError();
            UpdateP0AndP1();
            iterationCounter++;
        }
        GraphPanel.createAndShowGui(GetCostsList(), Alpha, GetP0AndP1(0), MAX_ITERATIONS);
    }

    private static void CalculatePredictedValues() {
        List<Integer> X = GetInputDataSet();
        float[] P = GetP0AndP1(iterationCounter);
        float[] Hp = new float[X.size()];
        for (int i = 0; i < X.size(); i++) {
            Hp[i] = (P[1] * X.get(i)) + P[0];
        }
        AddPredictedData(Hp);
    }
    private static void CalculateSquaredError() { // Jp = (1/2M) * Sigma[(Hp(x_i) - y_i)²] =
        float Cost;
        float sigma = 0;
        float[] Hp = GetPredictedData(iterationCounter);
        List<Integer> Y = GetOutputDataSet();
        int M = Y.size();

        for (int i = 0; i < M; i++) {
            sigma += Math.pow(Hp[i] - Y.get(i), 2);
        }
        float M2 = 2 * M;
        Cost = (float) ((1.0 /M2) * sigma);
        AddCost(Cost);
    }
    private static void UpdateP0AndP1() {
        AddP0AndP1(CorrectP(0), CorrectP(1));
    }
    private static float CorrectP(int p) {
        float sigma = 0;
        float P = GetP0AndP1(iterationCounter)[p];
        float[] Hp = GetPredictedData(iterationCounter);
        List<Integer> Y = GetOutputDataSet();
        List<Integer> X = GetInputDataSet();
        int M = Y.size();

        for (int i = 0; i < M; i++) {
            sigma += (Hp[i] - Y.get(i)) * (p == 0 ? 1 : X.get(i));
        }
        P -= (Alpha /M) * sigma;
        return P;
    }

    public static void SetupParameters(float p0, float p1, float alpha, int iterations) {
        ClearIterativeData();
        AddP0AndP1(p0, p1);
        Alpha = alpha;
        iterationCounter = 0;
        MAX_ITERATIONS = iterations;
        CalculationController.Start();
        System.out.println(Arrays.toString(GetInputDataSet().toArray()));
        System.out.println(Arrays.toString(GetOutputDataSet().toArray()));
        System.out.println(Arrays.toString(GetCostsList().toArray()));

    }
}
