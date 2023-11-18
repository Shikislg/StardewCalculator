package de.fhdw.bfwc322b.stardewCalc;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

import static de.fhdw.bfwc322b.stardewCalc.GUI.*;

public class RecipeSetup extends JFrame{
    public ArrayList<MaterialBlock> recipeMaterials = new ArrayList<MaterialBlock>();
    public JLabel title = new JLabel("Create Recipe");
    public LayoutLabel recipeNameLabel = new LayoutLabel("Name: ", 0, 1);
    public RoundJTextField recipeNameInput = new RoundJTextField("", 12, 0, 3);
    public RoundButton addMaterial = new RoundButton("Add Material", 15, 0, false, 150, 30);
    public RoundButton createRecipe = new RoundButton("Create Recipe!",15,2, false, 280, 40);

    public RecipeSetup(){
        setLayout(null);
        setBounds(frame.getX() + frame.getWidth() - 5, 45, 400, 200);
        getContentPane().setBackground(new Color(47, 79,79));
        getRootPane().setBorder(new LineBorder(Color.GRAY));
        setUndecorated(true);
        setResizable(false);

        addMaterial.setFont(segoe);
        addMaterial.addActionListener(guiInstance);

        addMaterial.setBounds(getWidth() / 2 - addMaterial.getWidth() / 2 - 5, addMaterial.getY(), addMaterial.getWidth(), addMaterial.getHeight());
        add(addMaterial);

        createRecipe.setFont(segoeHeader);
        createRecipe.addActionListener(guiInstance);
        createRecipe.setBounds(getWidth() / 2 - createRecipe.getWidth() / 2 - 5, createRecipe.getY(), createRecipe.getWidth(),createRecipe.getHeight());
        add(createRecipe);

        title.setBounds(5,0,150,30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 13));
        title.setForeground(Color.WHITE);
        add(title);

        guiInstance.initializeLabel(recipeNameLabel, this, false);
        recipeNameLabel.setBounds(205, 0, 75,30);
        guiInstance.initializeTextField(recipeNameInput, this);
        recipeNameInput.setBounds(280,0,110,30);
    }
    void addMaterialToRecipe(Material material){
        final int MAT_BLOCK_INSET = 15;
        MaterialBlock matBlock = new MaterialBlock(material,recipeMaterials.size()+1, this);
        recipeMaterials.add(matBlock);
        matBlock.setPosition(0,(matBlock.getRow() * 2) * (ROW_PADDING / 2) + matBlock.getRow() * ROW_SIZE - MAT_BLOCK_INSET);

        addMaterial.setRow(addMaterial.getRow() + 1);
        if (recipeMaterials.size() > 1){
            setSize(getWidth(), getHeight() + ROW_PADDING + ROW_SIZE);
            createRecipe.setBounds(createRecipe.getX(), createRecipe.getY()  + ROW_PADDING + ROW_SIZE, createRecipe.getWidth(), createRecipe.getHeight());
        }

        revalidate();
        repaint();
    }
    public RecipeMaterial[] getRecipeMaterials(){
        RecipeMaterial[] recipe = new RecipeMaterial[recipeMaterials.size()];
        for (int i = 0; i < recipeMaterials.size(); i++){
            recipe[i] = new RecipeMaterial(recipeMaterials.get(i).getMaterial(), recipeMaterials.get(i).getSmelted());
        }
        return recipe;
    }
}
