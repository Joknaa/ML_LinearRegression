package Views.UI;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

import static Views.OutputView.*;
import static javax.swing.GroupLayout.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements IPanel, ActionListener {
    //<editor-fold desc="Variables Declarations">">
    private final JPanel logoPanel = new JPanel();
    private final JPanel headerPanel = new JPanel();
    private final JPanel ConsolePanel = new JPanel();
    private final JPanel inputPanel = new JPanel();
    private final JPanel buttonsPanel = new JPanel();
    private final JLabel logoLabel = new JLabel(new ImageIcon("Resources/library_100px.png"));
    private final JLabel fileChooserLabel = new JLabel("Add DataSet");
    private final JLabel alphaLabel = new JLabel("Alpha");
    private final JLabel p0Label = new JLabel("P0");
    private final JLabel p1Label = new JLabel("P1");
    private final JTextField alphaField = new JTextField();
    private final JTextField p0Field = new JTextField();
    private final JTextField p1Field = new JTextField();
    private final JScrollPane consoleScrollPan = new JScrollPane();
    private final JTextArea greetingTextArea = new JTextArea();
    private final JTextArea outputTextArea = new JTextArea();
    private final JTextArea logoTextArea = new JTextArea();
    private final JButton addFileButton = new JButton("Add File");
    private final JButton closeButton = new JButton("X");
    private final JButton openButton = new JButton("OPEN");
    private final JButton runButton = new JButton("Run");
    private final JSlider alphaSlider = new JSlider();
    //</editor-fold>

    public MainPanel(){
        SetupLogoPanel();
        SetupHeaderPanel();
        SetupConsolePanel();
        SetupInputPanel();
        SetupButtonsPanel();
        SetupMainPanel();
    }

    private void SetupLogoPanel() {
        SetupLogoTextArea();
        SetupLogoPanelLayout();
        logoPanel.setBackground(PICKLED_BLUEWOOD);
        logoPanel.setPreferredSize(new Dimension(450, 500));
    }
    private void SetupLogoTextArea() {
        logoTextArea.setEditable(false);
        logoTextArea.setBackground(PICKLED_BLUEWOOD);
        logoTextArea.setColumns(5);
        logoTextArea.setFont(new Font("Source Code Pro", Font.BOLD, 30));
        logoTextArea.setForeground(BLUE_BAYOUX);
        logoTextArea.setRows(1);
        logoTextArea.setTabSize(1);
        logoTextArea.setText("MACHINE LEARNING");
        logoTextArea.setAutoscrolls(false);
        logoTextArea.setFocusable(false);
    }
    private void SetupLogoPanelLayout() {
        GroupLayout LogoPanelLayout = new GroupLayout(logoPanel);
        logoPanel.setLayout(LogoPanelLayout);
        LogoPanelLayout.setHorizontalGroup(
                LogoPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(LogoPanelLayout.createSequentialGroup()
                                .addComponent(logoLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logoTextArea, DEFAULT_SIZE, 344, Short.MAX_VALUE)
                        )
        );
        LogoPanelLayout.setVerticalGroup(
                LogoPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(LogoPanelLayout.createSequentialGroup()
                                .addGroup(LogoPanelLayout.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(LogoPanelLayout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(logoTextArea, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                                .addGap(41, 41, 41))
                                        .addComponent(logoLabel))
                                .addGap(0, 0, Short.MAX_VALUE)
                        )
        );
    }

    private void SetupHeaderPanel() {
        headerPanel.setBackground(BLUE_BAYOUX);
        SetupGreetingLabel();
        SetupHeaderPanelLayout();
    }
    private void SetupGreetingLabel() {
        greetingTextArea.setFont(new Font("Source Code Pro", Font.BOLD, 30));
        greetingTextArea.setBackground(BLUE_BAYOUX);
        greetingTextArea.setForeground(PICKLED_BLUEWOOD);
        greetingTextArea.setAutoscrolls(false);
        greetingTextArea.setFocusable(false);
        greetingTextArea.setText(" Linear Regression ");
    }
    private void SetupHeaderPanelLayout() {
        GroupLayout headerPanelLayout = new GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
                headerPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(greetingTextArea, Alignment.TRAILING, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(Alignment.TRAILING, headerPanelLayout.createSequentialGroup()
                                .addContainerGap(151, Short.MAX_VALUE)
                                .addGap(247, 247, 247))
        );
        headerPanelLayout.setVerticalGroup(
                headerPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(headerPanelLayout.createSequentialGroup()
                                .addComponent(greetingTextArea)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private void SetupConsolePanel() {
        SetupConsoleScrollPan();
        SetupConsolePanelLayout();
        ConsolePanel.setBackground(BLUE_BAYOUX);
        ConsolePanel.setPreferredSize(new Dimension(450, 500));
    }
    private void SetupConsoleScrollPan() {
        SetupOutputConsole();
        consoleScrollPan.setBackground(PICKLED_BLUEWOOD);
        consoleScrollPan.setBorder(null);
        consoleScrollPan.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        consoleScrollPan.setEnabled(false);
        consoleScrollPan.setFocusable(false);
        consoleScrollPan.setRequestFocusEnabled(false);
        consoleScrollPan.getVerticalScrollBar().setBackground(BLUE_BAYOUX);
        consoleScrollPan.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = BLUE_HAZE;
            }
        });
        consoleScrollPan.setViewportView(outputTextArea);
    }
    private void SetupOutputConsole() {
        outputTextArea.setFont(new Font("Source Code Pro", Font.PLAIN, 15));
        outputTextArea.setForeground(BLUE_HAZE);
        outputTextArea.setBackground(Color.black);
        outputTextArea.setFocusable(true);
        outputTextArea.setEnabled(false);
        outputTextArea.setPreferredSize(new Dimension(400,400));
        outputTextArea.setLineWrap(true);
        outputTextArea.setWrapStyleWord(true);
    }
    private void SetupConsolePanelLayout() {
        GroupLayout descriptionPanelLayout = new GroupLayout(ConsolePanel);
        ConsolePanel.setLayout(descriptionPanelLayout);
        descriptionPanelLayout.setHorizontalGroup( descriptionPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGap(10, 10, Short.MAX_VALUE)
                .addGroup(descriptionPanelLayout.createParallelGroup(Alignment.CENTER)
                        .addGroup(descriptionPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(consoleScrollPan, 400, 400, 400)
                        .addContainerGap())
                )
        );
        descriptionPanelLayout.setVerticalGroup( descriptionPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(descriptionPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(descriptionPanelLayout.createSequentialGroup()
                                .addComponent(consoleScrollPan, 200, 200, 200)
                                .addContainerGap(28, Short.MAX_VALUE)
                        )
                )
        );
    }

    private void SetupInputPanel() {
        SetupInputLabel(fileChooserLabel, p0Label, p1Label, alphaLabel);
        SetupInputField( p0Field, p1Field, alphaField);
        SetupSubmitButton(addFileButton, this, true, "add DataSet", BLUE_HAZE);

        SetupInputPanelLayout();
        inputPanel.setBackground(PICKLED_BLUEWOOD);
    }
    private static void GetDataFromFile() throws NullPointerException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);

        if (chooser.getSelectedFile() == null)
            throw new NullPointerException("No file has been selected");
    }

    private void SetupInputLabel(JLabel...Labels) {
        for (JLabel label : Labels) {
            label.setFont(new Font("Source Code Pro", Font.PLAIN, 24));
            label.setForeground(BLUE_HAZE);
        }
    }
    private void SetupInputField(JTextField...inputFields) {
        for (JTextField inputField : inputFields) {
            inputField.setBackground(BLUE_BAYOUX);
            inputField.setForeground(BLUE_HAZE);
            inputField.setPreferredSize(new Dimension(40, 25));
            inputField.addActionListener(this);
        }
    }
    private void SetupInputPanelLayout() {
        GroupLayout inputPanelLayout = new GroupLayout(inputPanel);
        inputPanel.setLayout(inputPanelLayout);
        inputPanelLayout.setHorizontalGroup(
                inputPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(alphaField, PREFERRED_SIZE, 62, PREFERRED_SIZE)
                                .addGap(193, 193, 193))
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(173, 173, 173)
                                                .addComponent(addFileButton, PREFERRED_SIZE, 102, PREFERRED_SIZE))
                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                .addGap(124, 124, 124)
                                                .addComponent(alphaSlider, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                                .addContainerGap(DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.TRAILING)
                                                        .addComponent(fileChooserLabel)
                                                        .addGroup(Alignment.LEADING, inputPanelLayout.createSequentialGroup()
                                                                .addGap(16, 16, 16)
                                                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.TRAILING)
                                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                                .addComponent(p0Label)
                                                                                .addGap(23, 23, 23)
                                                                                .addComponent(p0Field, PREFERRED_SIZE, 62, PREFERRED_SIZE))
                                                                        .addGroup(inputPanelLayout.createSequentialGroup()
                                                                                .addComponent(p1Label)
                                                                                .addGap(23, 23, 23)
                                                                                .addComponent(p1Field, PREFERRED_SIZE, 62, PREFERRED_SIZE)))))
                                                .addGap(150, 150, 150))
                                        .addGroup(Alignment.TRAILING, inputPanelLayout.createSequentialGroup()
                                                .addComponent(alphaLabel)
                                                .addGap(176, 176, 176))))
        );
        inputPanelLayout.setVerticalGroup(
                inputPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(inputPanelLayout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(fileChooserLabel, PREFERRED_SIZE, 29, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(addFileButton)
                                .addGap(38, 38, 38)
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(p0Field, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(p0Label, PREFERRED_SIZE, 26, PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(inputPanelLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(p1Field, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(p1Label, PREFERRED_SIZE, 26, PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addComponent(alphaLabel, PREFERRED_SIZE, 29, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(alphaSlider, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(alphaField, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addContainerGap(87, Short.MAX_VALUE))
        );
    }

    private void SetupButtonsPanel() {
        buttonsPanel.setBackground(BLUE_BAYOUX);
        SetupSubmitButton(openButton, this, true, "Open the file", PICKLED_BLUEWOOD);
        SetupSubmitButton(runButton, this, true, "Run the file", PICKLED_BLUEWOOD);
        SetupButtonsPanelLayout();
    }
    private void SetupButtonsPanelLayout() {
        GroupLayout buttonsPanelLayout = new GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup( buttonsPanelLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(Alignment.LEADING, buttonsPanelLayout.createSequentialGroup()
                        .addGap(100, 100, 100)
                        .addComponent(openButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(runButton, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                        .addGap(100, 100, 100)
                )
        );
        buttonsPanelLayout.setVerticalGroup( buttonsPanelLayout.createParallelGroup(Alignment.CENTER)
                .addGap(50, 50, 50)
                .addComponent(runButton, PREFERRED_SIZE, 23, PREFERRED_SIZE)
                .addComponent(openButton, PREFERRED_SIZE, 23, PREFERRED_SIZE)
        );
    }

    private void SetupMainPanel() {
        SetupCloseButton(closeButton);
        SetupMainPanelLayout();
        setBackground(BLUE_BAYOUX);
        setPreferredSize(new Dimension(900, 500));
    }
    private void SetupMainPanelLayout() {
        GroupLayout MainPanelLayout = new GroupLayout(this);
        this.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
                MainPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGroup(MainPanelLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(logoPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(inputPanel, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(MainPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                .addGroup(MainPanelLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(ConsolePanel, DEFAULT_SIZE, 433, Short.MAX_VALUE)
                                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                .addComponent(buttonsPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)))
                                                .addGap(8, 8, 8))
                                        .addGroup(MainPanelLayout.createSequentialGroup()
                                                .addComponent(headerPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(closeButton, PREFERRED_SIZE, 21, PREFERRED_SIZE)
                                                .addGap(0, 7, Short.MAX_VALUE))))
        );
        MainPanelLayout.setVerticalGroup(
                MainPanelLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(MainPanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(MainPanelLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(headerPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                        .addComponent(closeButton, PREFERRED_SIZE, 23, PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(ConsolePanel, PREFERRED_SIZE, 196, PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonsPanel, PREFERRED_SIZE, 57, PREFERRED_SIZE)
                                .addGap(84, 84, 84))
                        .addGroup(Alignment.TRAILING, MainPanelLayout.createSequentialGroup()
                                .addComponent(logoPanel, PREFERRED_SIZE, 100, PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(inputPanel, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
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
        if (event.getSource().equals(p0Field))
            OnClick_AddP(0, p0Field.getText());
        else if (event.getSource().equals(p1Field))
            OnClick_AddP(1, p1Field.getText());
        else if (event.getSource().equals(alphaField))
            OnClick_AddAlpha(alphaField.getText());
        else if (event.getSource().equals(runButton))
            OnClick_RunFile(outputTextArea);

    }
}