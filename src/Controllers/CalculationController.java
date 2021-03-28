package Controllers;

import Views.UI.GraphPanel;

import java.util.Arrays;

import static Models.DataSetModel.*;

public class CalculationController {
    private static int iterationCounter = 0;
    private static float SquaredError = 1;
    private static float Alpha;
    private static float Max = (float) 0.43;
    private static float MAX_ITERATIONS;

    public static void Start(){
        float delta;

        while (MAX_ITERATIONS-- > 0) {
            CalculatePredictedValues();
            CalculateSquaredError();
            UpdateP0AndP1();
            iterationCounter++;
        }
        GraphPanel.createAndShowGui(GetCostsList(), Alpha, GetP0AndP1(0));
    }

    private static void CalculatePredictedValues() {
        int[] X = GetInputDataSet();
        float[] P = GetP0AndP1(iterationCounter);
        float[] Hp = new float[X.length];
        for (int i = 0; i < X.length; i++) {
            Hp[i] = (P[1] * X[i]) + P[0];
        }
        AddPredictedData(Hp);
    }
    private static void CalculateSquaredError() { // Jp = (1/2M) * Sigma[(Hp(x_i) - y_i)²] =
        float Cost;
        float sigma = 0;
        float[] Hp = GetPredictedData(iterationCounter);
        int[] Y = GetOutputDataSet();
        int M = Y.length;

        for (int i = 0; i < M; i++) {
            sigma += Math.pow(Hp[i] - Y[i], 2);
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
        int[] Y = GetOutputDataSet();
        int[] X = GetInputDataSet();
        int M = Y.length;

        for (int i = 0; i < M; i++) {
            sigma += (Hp[i] - Y[i]) * (p == 0 ? 1 : X[i]);
        }
        P -= (Alpha /M) * sigma;
        return P;
    }

    public static void SetupParameters(float p0, float p1, float alpha) {
        ClearIterativeData();
        AddP0AndP1(p0, p1);
        Alpha = alpha;
        iterationCounter = 0;
        MAX_ITERATIONS = 100;
    }
}
