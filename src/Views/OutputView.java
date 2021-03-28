package Views;

import static Controllers.CalculationController.DEFAULT_MAX_ITERATIONS;
import static Models.DataSetModel.AddDataSet_X;
import static Models.DataSetModel.AddDataSet_Y;
import static javax.swing.JOptionPane.*;
import Controllers.CalculationController;
import Views.UI.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class OutputView {
    private static final AppFrame appFrame = new AppFrame();
    public static final Color BLUE_BAYOUX = new Color(52, 66, 91);
    public static final Color PICKLED_BLUEWOOD = new Color(76, 96, 133);
    public static final Color BLUE_HAZE = new Color(190, 200, 218);

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new MainPanel());
    }

    public static void OnClick_RunFile(JTextField p0, JTextField p1, JTextField alpha, JTextField iterations) {
        try {
            float P0 = Float.parseFloat(p0.getText());
            float P1 = Float.parseFloat(p1.getText());
            float Alpha = Float.parseFloat(alpha.getText());
            int Iterations = Integer.parseInt(iterations.getText());
            CalculationController.SetupParameters(P0, P1, Alpha, Iterations);
        } catch (NumberFormatException e) {
            Random random = new Random();
            CalculationController.SetupParameters(1/random.nextFloat(), 1/random.nextFloat(), random.nextFloat(),
                    DEFAULT_MAX_ITERATIONS);
        } catch (Exception e) {
            DisplayError(e.getMessage());
        }
    }

    public static void GetDataSet() {
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            if (chooser.getSelectedFile() == null)
                throw new NullPointerException("No file has been selected");

            File dataSetFile = chooser.getSelectedFile();
            ExtractDataSet(dataSetFile);
        }catch (Exception e) {
            DisplayError(e.getMessage());
        }

    }

    private static void ExtractDataSet(File dataSetFile) throws FileNotFoundException {
        Scanner fileSc = new Scanner(dataSetFile);
        while (fileSc.hasNextLine()) {
            String line = fileSc.nextLine();
            AddDataSet_X(Integer.parseInt(line.split(",")[0]));
            AddDataSet_Y(Integer.parseInt(line.split(",")[1]));
        }
    }

    public static void DisplayError(String error) {
        showMessageDialog(null, error, "Error", ERROR_MESSAGE);
    }
}