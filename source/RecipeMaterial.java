package de.fhdw.bfwc322b.stardewCalc;

public class RecipeMaterial extends Material{
    private int quantity;

    public RecipeMaterial(Material pBaseMaterial, int pQuantity){
        super(pBaseMaterial.getType(), pBaseMaterial.getRaw(),pBaseMaterial.getSmelted());
        quantity = pQuantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
