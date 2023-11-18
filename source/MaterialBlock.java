package de.fhdw.bfwc322b.stardewCalc;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

import static de.fhdw.bfwc322b.stardewCalc.GUI.*;

public class MaterialBlock{

    JLabel boxWrapper;
    JLabel materialText;

    LayoutLabel rawLabel;
    LayoutLabel smeltedLabel;

    RoundJTextField rawInput;
    RoundJTextField smeltedInput;

    private final int row;
    public MaterialBlock(Material pMaterial, int pRow){
        this(pMaterial, pRow, frame);
    }

    public MaterialBlock(Material pMaterial, int pRow, JFrame frame){
        row = pRow;
        rawLabel = new LayoutLabel("Raw: ", row, 0);
        smeltedLabel = new LayoutLabel("Smelted: ", row, 2);

        rawInput = new RoundJTextField(Integer.toString(pMaterial.getRaw()), 12, row, 1);
        smeltedInput = new RoundJTextField(Integer.toString(pMaterial.getSmelted()), 12, row, 3);

        if(guiInstance == null) return;
        guiInstance.initializeLabel(rawLabel, frame,true);
        guiInstance.initializeLabel(smeltedLabel, frame,true);
        guiInstance.initializeTextField(rawInput,frame);
        guiInstance.initializeTextField(smeltedInput,frame);

        materialText = new JLabel(pMaterial.getType());
        //Please just ask me why the width works this way, It's too complicated to fit in a comment

        materialText.setFont(new Font("Segoe UI", Font.BOLD, 12));
        materialText.setForeground(Color.WHITE);
        //Orangensaft
        materialText.setBorder(new EmptyBorder(0,5,0,0));
        materialText.setBackground(new Color(47,79,79));
        materialText.setOpaque(true);

        boxWrapper = new JLabel("");
        boxWrapper.setBounds(rawLabel.getX()+20, rawLabel.getY()-5, 380, 40);
        boxWrapper.setBorder(new LineBorder(Color.WHITE));

        materialText.setSize(materialText.getPreferredSize());
        materialText.setBounds(rawLabel.getX()+25, rawLabel.getY()-15, materialText.getPreferredSize().width, materialText.getPreferredSize().height);
        frame.add(materialText);
        frame.add(boxWrapper);

    }
    public MaterialBlock(String name, int raw, int smelted, int pRow){
        this(new Material(name, raw, smelted), pRow);
    }

    public Material getMaterial() {
        return new Material(materialText.getText(), Integer.parseInt(smeltedInput.getText()), Integer.parseInt(rawInput.getText()));
    }
    public int getRaw(){
        return Integer.parseInt(rawInput.getText());
    }
    public int getSmelted(){
        return Integer.parseInt(smeltedInput.getText());
    }

    public int getRow() {
        return row;
    }

    public void setPosition(int x, int y){
        resetBounds(boxWrapper,x,y);
        resetBounds(materialText,x,y);
        resetBounds(rawInput,x,y);
        resetBounds(smeltedInput,x,y);
        resetBounds(smeltedLabel,x,y);
        resetBounds(rawLabel,x,y);
    }
    private void resetBounds(Component c, int x, int y){
        //The 10 and 15 are trial and error... don't even ask me at this point
        final int NULL_X = rawLabel.getX() + 15;
        final int NULL_Y = rawLabel.getY() -10;
        c.setBounds((c.getX() - NULL_X) + x, (c.getY() - NULL_Y) + y, c.getWidth(), c.getHeight());
    }
}