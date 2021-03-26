package Controllers;

import java.util.Arrays;
import static Controllers.OutputController.Display;
import static Models.DataSetModel.*;

public class CalculationController {
    private static int iterationCounter = 0;
    private static boolean Initialised = false;

    public static void Start(){
        Display("Input: " + Arrays.toString(GetInputDataSet()));
        Display("Output: " + Arrays.toString(GetOutputDataSet()));

        int i = 3;
        while (i > 0) {
            i--;
            if (Initialised) {
                UpdateP0AndP1();
                iterationCounter++;
            } else
                InitialiseP0AndP1();

            Display(Arrays.toString(GetP0AndP1(iterationCounter)));

            CalculatePredictedValues();
            Display(Arrays.toString(GetPredictedData(iterationCounter)));

            CalculateSquaredError();
            Display("Jp : " + (GetSquaredError(iterationCounter)));

        }
        Display("Iteration Count: " + iterationCounter);
    }

    private static void InitialiseP0AndP1() {
        // Get a random value for P1 and P0
        AddP0AndP1(0, 0);
        Initialised = true;
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
        P -= (0.01 /M) * sigma;
        return P;
    }
    private static void CalculateSquaredError() { // Jp = (1/2M) * Sigma[(Hp(x_i) - y_i)²] = 4.3167
        float SquaredError;
        float sigma = 0;
        float[] Hp = GetPredictedData(iterationCounter);
        int[] Y = GetOutputDataSet();
        int M = Y.length;

        for (int i = 0; i < M; i++) {
            sigma += Math.pow(Hp[i] - Y[i], 2);
        }
        float M2 = 2 * M;
        SquaredError = (float) ((1.0 /M2) * sigma);
        AddSquaredError(SquaredError);
    }
}
