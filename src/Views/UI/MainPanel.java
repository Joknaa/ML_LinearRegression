package Views.UI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import static Views.OutputView.*;
import static javax.swing.SwingConstants.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class MainPanel extends JPanel implements IPanel, ActionListener, ChangeListener {
    //<editor-fold desc="Variables Declarations">">
    private final JLabel headerLogo = new JLabel(new ImageIcon("Resources/plot_50px_BLUEWOOD.png"));
    private final JLabel fileChooserLabel = new JLabel("File.txt");
    private final JTextField alphaField = new JTextField("Alpha");
    private final JTextField p0Field = new JTextField("P0");
    private final JTextField p1Field = new JTextField("P1");
    private final JTextArea headerText = new JTextArea("Machine Learning:\nLinear Regression");
    private final JButton fileChooserButton = new JButton("Add File");
    private final JButton closeButton = new JButton("X");
    private final JButton runButton = new JButton("Run");
    private final JSlider alphaSlider = new JSlider();
    private final JSeparator p0Separator = new JSeparator();
    private final JSeparator p1Separator = new JSeparator();
    private final JSeparator alphaSeparator = new JSeparator();
    //</editor-fold>

    public MainPanel(){
        SetupHeader();
        SetupCloseButton();
        SetupFileChooser();
        SetupInputField(p0Field, p1Field, alphaField);
        SetupSeparators(p0Separator, p1Separator, alphaSeparator);
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
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(headerLogo)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(fileChooserButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                        .addComponent(fileChooserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(40, 40, 40))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(p0Field)
                                                                        .addComponent(p0Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(21, 21, 21)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(p1Field)
                                                                        .addComponent(p1Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(15, 15, 15))
                                                        .addComponent(alphaSlider, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(alphaField)
                                                                        .addComponent(alphaSeparator)
                                                                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(58, 58, 58)))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(headerText, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup( layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(headerText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(headerLogo)))
                        .addGap(33, 33, 33)
                        .addComponent(fileChooserButton)
                        .addGap(1, 1, 1)
                        .addComponent(fileChooserLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(p0Field, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(p0Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                        .addComponent(p1Field, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, 0)
                                        .addComponent(p1Separator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(32, 32, 32)
                        .addComponent(alphaSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alphaField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(alphaSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                        .addComponent(runButton, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(16, 16, 16)
                )
        );
    }

    @Override
    public JPanel GetPanel() { return this; }
    @Override
    public void Activate(){ this.setVisible(true);}
    @Override
    public void Deactivate(){ this.setVisible(false);}
    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(runButton))
            OnClick_RunFile(p0Field, p1Field, alphaField);

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        alphaField.setText(String.valueOf(((float) alphaSlider.getValue())/100));
    }

    private static void GetDataFromFile() throws NullPointerException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        if (chooser.getSelectedFile() == null)
            throw new NullPointerException("No file has been selected");
    }

}