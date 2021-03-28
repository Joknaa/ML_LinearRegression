package Controllers;

import Views.UI.GraphPanel;

import java.util.Arrays;
import static Controllers.OutputController.Display;
import static Models.DataSetModel.*;

public class CalculationController {
    private static int iterationCounter = 0;
    private static boolean initialised = false;
    private static float SquaredError = 1;
    private static float Alpha;
    private static float Max = (float) 0.43;
    private static float MAX_ITERATIONS = 100;

    public static void Start(){
        Display("Input: " + Arrays.toString(GetInputDataSet()));
        Display("Output: " + Arrays.toString(GetOutputDataSet()));
        float delta;

        while (MAX_ITERATIONS-- > 0) {
            if (initialised) {
                UpdateP0AndP1();
                iterationCounter++;
            } else
                InitialiseP0AndP1();

            CalculatePredictedValues();
            CalculateSquaredError();

            //<editor-fold desc="Display stuff">
            SquaredError = GetCost(iterationCounter);

            Display("\n-->I = " + iterationCounter);
            System.out.println("-->I = " + iterationCounter);
            Display("\noutputdata: " + Arrays.toString(GetOutputDataSet()));
            Display("\nPredicted: " + Arrays.toString(GetPredictedData(iterationCounter)));
            Display("\n-- Jp : " + (GetCost(iterationCounter)));
            System.out.println("-- Jp : " + GetCost(iterationCounter));
            if (iterationCounter > 0) {
                delta = GetCost(iterationCounter) - GetCost(iterationCounter -1);
                Display("\nDelta : " + delta);
                System.out.println("Delta : " + delta);
            }
            Display("\n----------------------");
            //</editor-fold>
        }
        Display("\nIteration Count: " + iterationCounter);
        GraphPanel.createAndShowGui(GetCostsList(), Alpha, GetP0AndP1(0));
        System.out.println("alpha = " + Alpha);
    }

    private static void InitialiseP0AndP1() {
        // Get a random value for P1 and P0
        AddP0AndP1(0, 0);
        initialised = true;
    }
    private static void UpdateP0AndP1() {
        AddP0AndP1(CorrectP(0), CorrectP(1));
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

    public static void Reset() {
        iterationCounter = 0;
        initialised = false;
        MAX_ITERATIONS = 100;
        ClearIterativeData();
    }

    public static void SetAlpha(float alpha){
        System.out.println("setting alpha = " + alpha);
        Alpha = alpha;
    }
}
