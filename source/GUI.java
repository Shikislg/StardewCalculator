import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicArrowButton;


public class GUI implements ActionListener, MouseListener, ComponentListener {
    public static GUI guiInstance;
    public static final int ROW_SIZE = 30;
    public static final int ROW_PADDING = 20;
    public static final int OUTPUT_ROW_PADDING = 5;
    public static final int TOTAL_ROW_SIZE = 35;

    public static final JFrame frame = new JFrame();

    
    public static final Color opaqueWhite = new Color(100,100,100,150);
    public static Font segoe = new Font("Segoe UI", Font.BOLD, 12);
    public static Font segoeHeader = new Font("Segoe UI",Font.BOLD,15);

    ArrayList<MaterialBlock> materials = new ArrayList<>();

    RoundButton addRow = new RoundButton("Add Material", 15, 3);

    BasicArrowButton openSideMenu = new BasicArrowButton(BasicArrowButton.EAST);
    RoundButton calculate = new RoundButton("Calculate!", 15, 3);

    LayoutLabel output = new LayoutLabel("", 4);

    //Dialog Box
    JPanel materialSetupDialog = new JPanel();
    JTextField nameInput = new JTextField(5);
    JTextField ratioInput = new JTextField(5);


    //Material Creator
    JFrame materialSetup = new JFrame();
    JLabel materialTitle = new JLabel("Create Material");
    LayoutLabel materialNameLabel = new LayoutLabel("Name: ", 0, 1);
    RoundJTextField materialNameInput = new RoundJTextField("", 12, 0, 3);

    MaterialBlock newMaterial;
    RoundButton createMaterial = new RoundButton("Create Material", 15, 1, false, 150,30);
    RoundButton cancelCreation = new RoundButton("Cancel", 15, 1, false, 150,30);

    RoundButton sourceButton;

    //Recipe Setup
    RecipeSetup recipeSetup;


    //Containers because I'm tired of moving everything individually
    Container bodyContainer = new Container();
    Container outputContainer = new Container();
    public GUI(){
        guiInstance = this;

        recipeSetup = new RecipeSetup();

        frame.setLayout(null);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.addComponentListener(this);

        frame.getContentPane().setBackground(new Color(47,79,79));
        frame.setSize(445, 305);
        frame.setResizable(false);
        frame.setTitle("Stardew calculator for may");

        setupDialogBox();
        materials.add(new MaterialBlock(new Material("Iron", 17,211),0));
        materials.add(new MaterialBlock(new Material("Gold",5,50),1));
        materials.add(new MaterialBlock(new Material("Quartz",100,0),2));

        addRow.addActionListener(this);
        calculate.addActionListener(this);
        openSideMenu.addActionListener(this);
        createMaterial.addActionListener(this);
        cancelCreation.addActionListener(this);

        designer();

        frame.add(bodyContainer);
        frame.add(outputContainer);



        frame.setVisible(true);
    }
    //UI generation

    private void designer(){
        //Material setup frame
        materialSetup.setLayout(null);
        materialSetup.setBounds(frame.getX() + frame.getWidth() + (recipeSetup.isVisible() ? recipeSetup.getWidth() : 0), frame.getY() + 75, 395,145);
        materialSetup.getContentPane().setBackground(new Color(47,79,79));
        materialSetup.getRootPane().setBorder(new LineBorder(Color.GRAY));
        materialSetup.setUndecorated(true);
        materialSetup.setResizable(false);

        initializeLabel(materialNameLabel, materialSetup, false);
        materialNameLabel.setBounds(175, 0, 75,30);
        initializeTextField(materialNameInput, materialSetup);
        materialNameInput.setBounds(250,0,130,30);

        newMaterial = new MaterialBlock(new Material("New Material", 0, 0), 0, materialSetup);
        newMaterial.setPosition(0,30);

        createMaterial.setBounds(materialSetup.getWidth() / 2 - createMaterial.getWidth() / 2 - 100, createMaterial.getY(), createMaterial.getWidth(), createMaterial.getHeight());
        cancelCreation.setBounds(materialSetup.getWidth() / 2 - cancelCreation.getWidth() / 2 + 100, cancelCreation.getY(), cancelCreation.getWidth(), cancelCreation.getHeight());
        materialSetup.add(cancelCreation);
        materialSetup.add(createMaterial);

        materialTitle.setBounds(5,0,150,30);
        materialTitle.setFont(new Font("Segoe UI", Font.BOLD, 13));
        materialTitle.setForeground(Color.WHITE);
        materialSetup.add(materialTitle);

        //Buttonsz
        addRow.setBounds(frame.getWidth() / 2 - 150 / 2 - 15, calculate.getY() - 10 - 22, 150, 30);
        frame.add(addRow);

        openSideMenu.setBounds(402, ROW_PADDING - 5, 25,25);
        frame.add(openSideMenu);


        calculate.setFont(segoeHeader);
        frame.add(calculate);

        //Output
        output.setFont(segoeHeader);
        output.setForeground(Color.WHITE);
        output.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(output);

    }
    public void initializeTextField(RoundJTextField tf, JFrame frame){
        Color b = new Color(47,79,79);
        tf.setFont(segoe);
        tf.setForeground(Color.WHITE);
        tf.setCaretColor(Color.WHITE);
        tf.setMargin(new Insets(0,5,0,0));
        tf.setBackground(b);
        frame.add(tf);
    }
    public void initializeLabel(LayoutLabel label, JFrame frame, boolean isMaterial){
        Color c = new Color(57,57,57);
        //isOutput gets set in the label constructor. Output has a unique font
        label.setFont(label.isOutput() ? segoeHeader : segoe);
        if(!label.isOutput()) label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setForeground(Color.WHITE);
        label.setBackground(c);
        if(isMaterial) label.addMouseListener(this);

        frame.add(label);
    }

    private void setupDialogBox(){
        materialSetupDialog.add(new JLabel("Name:"));
        materialSetupDialog.add(nameInput);
        materialSetupDialog.add(Box.createHorizontalStrut(15)); // a spacer
        materialSetupDialog.add(new JLabel("Ratio:"));
        materialSetupDialog.add(ratioInput);
    }
    //End of UI Generation
    private void addRow(Material material){
        frame.setSize(frame.getWidth(), frame.getHeight() + TOTAL_ROW_SIZE);
        calculate.setRow(calculate.getRow()+1);

        addRow.setBounds(5, calculate.getY() - 5, calculate.getWidth(), 5);

        output.setRow(output.getRow()+1);
        output.setBounds(output.getX(), output.getY() + 2 * ROW_PADDING + ROW_SIZE, output.getWidth(), output.getHeight());

        //new Row
        materials.add(new MaterialBlock(material, materials.get(materials.size()-1).getRow()));

        frame.revalidate();
        frame.repaint();
    }

    public Material[] getMaterials(){
        Material[] out = new Material[materials.size()];
        for (int i = 0; i < materials.size(); i++){
            out[i] = materials.get(i).getMaterial();
        }
        return out;
    }

    public static void main(String[] args) { new GUI();}

    @Override
    public void actionPerformed(ActionEvent e) {
        //This is just for testing, remove later
        if(e.getSource() == addRow){
            sourceButton = addRow;
            materialSetup.setVisible(true);
        }
        else if(e.getSource() == recipeSetup.addMaterial){
            sourceButton = recipeSetup.addMaterial;
            materialSetup.setVisible(true);
        }
        else if(e.getSource() == recipeSetup.createRecipe){
            int outputAmount = Integer.parseInt(JOptionPane.showInputDialog("Amount of product created during crafting"));
            Recipe.recipes.add(new Recipe(recipeSetup.getRecipeMaterials(), new Material(recipeSetup.getName(), 1, 0), outputAmount));
        }
        else if (e.getSource() == calculate) {

            output.setText(new StardewCalc().findWeakestMaterialLink(getMaterials(),80));
        }
        else if(e.getSource() == openSideMenu){
            recipeSetup.setVisible(!recipeSetup.isVisible());
            openSideMenu.setDirection(recipeSetup.isVisible() ? BasicArrowButton.WEST : BasicArrowButton.EAST);
            materialSetup.setBounds(frame.getX() + frame.getWidth() + (recipeSetup.isVisible() ? recipeSetup.getWidth() : 0), frame.getY() + 75, materialSetup.getWidth(),materialSetup.getHeight());
        }
        else if(e.getSource() == cancelCreation){
            materialSetup.setVisible(false);
        }
        else if(e.getSource() == createMaterial){
            newMaterial.materialText.setText(materialNameInput.getText());
            if(sourceButton == addRow){
                addRow(newMaterial.getMaterial());
            }else{
                recipeSetup.addMaterialToRecipe(newMaterial.getMaterial());
            }
            materialSetup.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!e.getSource().getClass().equals(LayoutLabel.class)) return;

        assert e.getSource().getClass() == LayoutLabel.class;
        LayoutLabel clickedLabel = (LayoutLabel) e.getSource();

        nameInput.setText(clickedLabel.getText().trim().substring(0,clickedLabel.getText().length()-2));
        int result = JOptionPane.showConfirmDialog(null, materialSetupDialog,
                "Set up new Material", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            //ores are always left and bars are always right, so the label tags are edited accordingly
            clickedLabel.setText(nameInput.getText() + (clickedLabel.getColumn() == 0 ? "Bars: " : "Ores: "));
            //for later
            //System.out.println("y value: " + ratioInput.getText());
        }
    }
    @Override
    public void componentMoved(ComponentEvent e) {
        recipeSetup.setBounds(frame.getX() + frame.getWidth() - 5, frame.getY() + 45, recipeSetup.getWidth(), recipeSetup.getHeight());
        materialSetup.setBounds(frame.getX() + frame.getWidth() + (recipeSetup.isVisible() ? recipeSetup.getWidth() : 0), frame.getY() + 75, materialSetup.getWidth(),materialSetup.getHeight());
    }
    //Useless Methods
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void componentResized(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
    //End of useless methods

}