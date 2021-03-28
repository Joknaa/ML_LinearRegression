package Views;

import static javax.swing.JOptionPane.*;

import Controllers.CalculationController;
import Views.UI.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class OutputView {
    private static final AppFrame appFrame = new AppFrame();
    public static final Color PICKLED_BLUEWOOD = new Color(52, 66, 91);
    public static final Color BLUE_BAYOUX = new Color(76, 96, 133);
    public static final Color BLUE_HAZE = new Color(190, 200, 218);
    public static JTextArea outputConsole;

    public static void SetUpGUI() {
        appFrame.SetupOnTimeConfiguration();
        appFrame.SetCurrentPanel(new MainPanel());
    }

    //<editor-fold desc="On-Events Actions">
    public static void OnClick_AddP(int p, String inputValue){
        System.out.println(p + " " + inputValue);
    }
    public static void OnClick_AddAlpha(String inputValue){
        try {
            float alpha = Float.parseFloat(inputValue);
            CalculationController.SetAlpha(alpha);
        } catch (Exception e) {
            DisplayError(e.getMessage());
        }
    }
    public static void OnClick_RunFile(JTextArea outputTextArea){
        outputConsole = outputTextArea;
        CalculationController.Start();
    }
    public static void OnClick_OpenFile(JList<String> list){

    }
    //</editor-fold>

    //<editor-fold desc="Setup Comment Components">
    public static void SetupCloseButton(JButton closeButton){
        closeButton.setBackground(BLUE_BAYOUX);
        closeButton.setFont(new java.awt.Font("Source Code Pro", Font.PLAIN, 24));
        closeButton.setForeground(PICKLED_BLUEWOOD);
        closeButton.setText("X");
        closeButton.setToolTipText("Close");
        closeButton.setBorder(null);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setFocusable(false);
        closeButton.setMaximumSize(new java.awt.Dimension(100, 38));
        closeButton.setMinimumSize(new java.awt.Dimension(100, 38));
        closeButton.setPreferredSize(new java.awt.Dimension(100, 38));
        closeButton.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) { System.exit(0); }
        });
    }
    public static void SetupSubmitButton(JButton submitButton, ActionListener actionListener, boolean isEnabled,
                                         String toolTip, Color BGColor) {
        //todo: add some feed back on clicking the buttons
        submitButton.setBorder(BorderFactory.createLineBorder(BLUE_HAZE));
        submitButton.setPreferredSize(new Dimension(100, 28));
        submitButton.setMaximumSize(new Dimension(100, 28));
        submitButton.setMinimumSize(new Dimension(100, 28));
        submitButton.setBackground(BGColor);
        submitButton.setForeground(BLUE_HAZE);
        submitButton.setToolTipText(toolTip);
        submitButton.setEnabled(isEnabled);
        submitButton.setContentAreaFilled(false);
        submitButton.setFocusPainted(false);
        submitButton.setFocusable(false);
        submitButton.setOpaque(false);
        submitButton.addActionListener(actionListener);
    }
    //</editor-fold>

    public static String DisplayInputDialog(String message) {
        return showInputDialog(null, message,
                "Input dialog", PLAIN_MESSAGE);
    }
    public static void DisplayError(String error) {
        showMessageDialog(null, error, "Error", ERROR_MESSAGE);
    }
    public static void Display(String s) {
        outputConsole.setRows(outputConsole.getRows() + 1);
        outputConsole.append(s);
    }
}