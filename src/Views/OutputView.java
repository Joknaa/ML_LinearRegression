package Views;

import static javax.swing.JOptionPane.*;

import Controllers.CalculationController;
import Views.UI.*;
import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class OutputView {
    private static final AppFrame appFrame = new AppFrame();
    public static final Color BLUE_BAYOUX = new Color(52, 66, 91);
    public static final Color PICKLED_BLUEWOOD = new Color(76, 96, 133);
    public static final Color BLUE_HAZE = new Color(190, 200, 218);

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new MainPanel());
    }

    //<editor-fold desc="On-Events Actions">
    public static void OnClick_RunFile(JTextField p0, JTextField p1, JTextField alpha) {
        try {
            float P0 = Float.parseFloat(p0.getText());
            float P1 = Float.parseFloat(p1.getText());
            float Alpha = Float.parseFloat(alpha.getText());
            CalculationController.SetupParameters(P0, P1, Alpha);
        } catch (NumberFormatException notFloat) {
            Random random = new Random();
            CalculationController.SetupParameters(1/random.nextFloat(), 1/random.nextFloat(), random.nextFloat());
        } catch (Exception e) {
            DisplayError(e.getMessage());
        }
    }
    //</editor-fold>

    public static void DisplayError(String error) {
        showMessageDialog(null, error, "Error", ERROR_MESSAGE);
    }
}