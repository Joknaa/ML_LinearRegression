package Controllers;

import java.util.Arrays;
import static Controllers.OutputController.Display;
import static Models.DataSetModel.*;

public class CalculationController {
    private static float hp;
    private static int iterationCounter = 0;

    public static void Start(){
        InitialiseP0AndP1();
        CalculatePredictedValues();

        Display("Input: " + Arrays.toString(GetInputDataSet()));
        Display("Output: " + Arrays.toString(GetOutputDataSet()));
        Display(Arrays.toString(GetPredictedData(iterationCounter)));

        AddP0AndP1(CorrectP(0), CorrectP(1));
        iterationCounter++;

        Display(Arrays.toString(GetP0AndP1(iterationCounter)));

        CalculateSquaredError();

    }

    private static void InitialiseP0AndP1() {
        // Get a random value for P1 and P0
        AddP0AndP1(0, 0);
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
        P -= (0.01 /M) * sigma;
        return P;
    }
    private static void CalculateSquaredError() { // Jp = (1/2M) * Sigma[(Hp(x_i) - y_i)²]

    }

}
