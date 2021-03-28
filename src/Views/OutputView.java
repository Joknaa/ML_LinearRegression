package Views;

import static javax.swing.JOptionPane.*;

import Controllers.CalculationController;
import Views.UI.*;
import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class OutputView {
    private static final AppFrame appFrame = new AppFrame();
    public static final Color BLUE_BAYOUX = new Color(52, 66, 91);
    public static final Color PICKLED_BLUEWOOD = new Color(76, 96, 133);
    public static final Color BLUE_HAZE = new Color(190, 200, 218);
    public static JTextArea outputConsole;

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new MainPanel());
    }

    //<editor-fold desc="On-Events Actions">
    public static void OnClick_RunFile(JTextField p0, JTextField p1, JTextField alpha){
        try {
            float P0 = Float.parseFloat(p0.getText());
            float P1 = Float.parseFloat(p1.getText());
            float Alpha = Float.parseFloat(alpha.getText());
            System.out.println(P0);
            System.out.println(P1);
            System.out.println(Alpha);
            CalculationController.SetupParameters(P0, P1, Alpha);
            CalculationController.Start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //</editor-fold>

    public static void DisplayError(String error) {
        showMessageDialog(null, error, "Error", ERROR_MESSAGE);
    }
}