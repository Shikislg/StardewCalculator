package de.fhdw.bfwc322b.stardewCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import static de.fhdw.bfwc322b.stardewCalc.GUI.*;
public class RoundJTextField extends JTextField {
    private Shape shape;
    private int column;

    public RoundJTextField(String text, int size, int pRow, int pColumn) {
        super(text,size);
        column = pColumn;
            /*
              There is two possible positions for a label in this layout.
              If it describes the first text field its position is 75, otherwise it's 275

              The formula for y takes both the padding between lines and the size of each row into account.
              Because there is padding between the top of the screen and the first row, one more instance of padding
              needs to be added.

              The size is constant for this specific layout
            */
        super.setBounds(column == 1 ? 65:265 , (pRow +1) * ROW_PADDING + pRow * ROW_SIZE, 125, 30);
        setOpaque(false); // As suggested by @AVD in comment.
    }
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        super.paintComponent(g);
    }
    protected void paintBorder(Graphics g) {
        g.setColor(opaqueWhite);
        //g.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        g.setColor(Color.WHITE);
        g.drawLine(5,this.getHeight()-5,this.getWidth()-5,getHeight()-5);
    }
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new RoundRectangle2D.Float(0, 0, getWidth()-1, getHeight()-1, 15, 15);
        }
        return shape.contains(x, y);
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
