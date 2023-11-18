package de.fhdw.bfwc322b.stardewCalc;

import java.util.ArrayList;

public class Recipe {
    //required materials
    //required quantities
    public static ArrayList<Recipe> recipes = new ArrayList<>();
    RecipeMaterial[] material;
    Material output;
    int outputAmount;
    public Recipe(RecipeMaterial[] mats, Material output, int outputAmount){
        material = mats;
        this.output = output;
        this.outputAmount = outputAmount;
    }

    public Material getOutput() {
        return output;
    }

    public RecipeMaterial[] getMaterial() {
        return material;
    }

    public void setMaterial(RecipeMaterial[] material) {
        this.material = material;
    }

    public void setOutput(Material output) {
        this.output = output;
    }
}
