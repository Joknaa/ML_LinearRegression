package Views.UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static Views.OutputView.*;
import static javax.swing.GroupLayout.*;
import static javax.swing.SwingConstants.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MainPanel extends JPanel implements ActionListener, ChangeListener {
    //<editor-fold desc="Variables Declarations">">
    private final JLabel headerLogo = new JLabel(new ImageIcon("Resources/plot_50px_BLUEWOOD.png"));
    private final JLabel fileChooserLabel = new JLabel("No File Selected");
    private final JTextField p0Field = new JTextField("P0");
    private final JTextField p1Field = new JTextField("P1");
    private final JTextField alphaField = new JTextField("Alpha");
    private final JTextField iterationField = new JTextField("Iterations");
    private final JTextArea headerText = new JTextArea("Machine Learning:\nLinear Regression");
    private final JButton fileChooserButton = new JButton("Add File");
    private final JButton closeButton = new JButton("X");
    private final JButton runButton = new JButton("Run");
    private final JSlider alphaSlider = new JSlider();
    private final JSeparator p0Separator = new JSeparator();
    private final JSeparator p1Separator = new JSeparator();
    private final JSeparator alphaSeparator = new JSeparator();
    private final JSeparator iterationSeparator = new JSeparator();
    //</editor-fold>

    public MainPanel(){
        SetupHeader();
        SetupCloseButton();
        SetupFileChooser();
        SetupInputField(p0Field, p1Field, alphaField, iterationField);
        SetupSeparators(p0Separator, p1Separator, alphaSeparator, iterationSeparator);
        SetupAlphaSlider();
        SetupButtons(runButton, fileChooserButton);
        SetupPanelLayout();
    }

    private void SetupHeader() {
        headerText.setEditable(false);
        headerText.setBackground(PICKLED_BLUEWOOD);
        headerText.setColumns(5);
        headerText.setFont(new Font("Source Code Pro", Font.PLAIN, 24));
        headerText.setForeground(BLUE_BAYOUX);
        headerText.setRows(1);
        headerText.setTabSize(1);
        headerText.setAutoscrolls(false);
        headerText.setFocusable(false);
    }
    private void SetupCloseButton(){
        closeButton.setBackground(PICKLED_BLUEWOOD);
        closeButton.setFont(new Font("Source Code Pro", Font.PLAIN, 24));
        closeButton.setForeground(BLUE_HAZE);
        closeButton.setToolTipText("Close");
        closeButton.setBorder(null);
        closeButton.setContentAreaFilled(false);
        closeButton.setFocusPainted(false);
        closeButton.setFocusable(false);
        closeButton.setMaximumSize(new Dimension(100, 38));
        closeButton.setMinimumSize(new Dimension(100, 38));
        closeButton.setPreferredSize(new Dimension(100, 38));
        closeButton.addActionListener(evt -> System.exit(0));
    }
    private void SetupFileChooser() {
        fileChooserLabel.setFont(new Font("Source Code Pro", Font.PLAIN, 14));
        fileChooserLabel.setForeground(BLUE_HAZE);
        fileChooserLabel.setHorizontalAlignment(CENTER);
    }
    private void SetupInputField(JTextField...inputFields){
        HashMap<JTextField, String> placeHolder = new HashMap<>();
        placeHolder.put(p0Field, "P0");
        placeHolder.put(p1Field, "P1");
        placeHolder.put(alphaField, "Alpha");
        placeHolder.put(iterationField, "Iterations");
        for (JTextField field : inputFields) {
            field.setBackground(PICKLED_BLUEWOOD);
            field.setForeground(BLUE_HAZE);
            field.setHorizontalAlignment(CENTER);
            field.setBorder(null);
            field.addFocusListener(new FocusAdapter() {
                public void focusGained(FocusEvent evt) {
                    if (field.getText().trim().equals(placeHolder.get(field)))
                        field.setText("");
                }
                public void focusLost(FocusEvent e) {
                    if (field.getText().trim().isEmpty())
                        field.setText(placeHolder.get(field));
                }
            });
        }
    }
    private void SetupSeparators(JSeparator...separators){
        for (JSeparator separator : separators) {
            separator.setBackground(BLUE_HAZE);
        }
    }
    private void SetupAlphaSlider(){
        alphaSlider.setBackground(PICKLED_BLUEWOOD);
        alphaSlider.setForeground(BLUE_BAYOUX);
        alphaSlider.setMinimum(0);
        alphaSlider.setMaximum(100);
        alphaSlider.setPaintTicks(true);
        alphaSlider.setMinorTickSpacing(1);
        alphaSlider.addChangeListener(this);
    }
    private void SetupButtons(JButton...buttons){
        for (JButton button : buttons) {
            button.setBackground(BLUE_BAYOUX);
            button.setForeground(BLUE_HAZE);
            button.setBorder(BorderFactory.createLineBorder(BLUE_HAZE));
            button.setContentAreaFilled(false);
            button.setFocusPainted(false);
            button.setFocusable(false);
            button.setMaximumSize(new Dimension(100, 38));
            button.setMinimumSize(new Dimension(100, 38));
            button.setPreferredSize(new Dimension(100, 20));
            button.addActionListener(this);
        }
    }
    private void SetupPanelLayout() {
        setBackground(PICKLED_BLUEWOOD);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup( layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(headerLogo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                                .addComponent(fileChooserButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(fileChooserLabel, PREFERRED_SIZE, 161, PREFERRED_SIZE))
                                                        .addGap(20, 20, 20))
                                                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                                .addComponent(p0Field)
                                                                .addComponent(p0Separator, PREFERRED_SIZE, 70, PREFERRED_SIZE))
                                                        .addGap(21, 21, 21)
                                                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                                .addComponent(p1Field)
                                                                .addComponent(p1Separator, PREFERRED_SIZE, 79, PREFERRED_SIZE))
                                                        .addGap(15, 15, 15))
                                                .addComponent(alphaSlider, Alignment.TRAILING, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                                .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                                                                .addComponent(iterationField)
                                                                .addGroup(layout.createParallelGroup(Alignment.TRAILING, false)
                                                                        .addComponent(iterationSeparator)
                                                                        .addComponent(alphaField, Alignment.LEADING)
                                                                        .addComponent(alphaSeparator, Alignment.LEADING)
                                                                        .addComponent(runButton, Alignment.LEADING, PREFERRED_SIZE, 79, PREFERRED_SIZE)))
                                                        .addGap(58, 58, 58)))
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(headerText, PREFERRED_SIZE, 250, PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(closeButton, PREFERRED_SIZE, 21, PREFERRED_SIZE))))
        );
        layout.setVerticalGroup( layout.createParallelGroup(Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addComponent(headerText, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addComponent(closeButton, PREFERRED_SIZE, 23, PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(headerLogo)))
                        .addGap(33, 33, 33)
                        .addComponent(fileChooserButton)
                        .addGap(1, 1, 1)
                        .addComponent(fileChooserLabel, PREFERRED_SIZE, 29, PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(p0Field, PREFERRED_SIZE, 31, PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(p0Separator, PREFERRED_SIZE, 10, PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(p1Field, PREFERRED_SIZE, 31, PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(p1Separator, PREFERRED_SIZE, 10, PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(alphaSlider, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alphaField, PREFERRED_SIZE, 31, PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(alphaSeparator, PREFERRED_SIZE, 10, PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(iterationField, PREFERRED_SIZE, 31, PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(iterationSeparator, PREFERRED_SIZE, 10, PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(runButton, PREFERRED_SIZE, 23, PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                )
        );
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(runButton))
            OnClick_RunFile(p0Field, p1Field, alphaField, iterationField);
        else if (event.getSource().equals(fileChooserButton))
            GetDataSet(fileChooserLabel);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        alphaField.setText(String.valueOf(((float) alphaSlider.getValue())/100));
    }



}