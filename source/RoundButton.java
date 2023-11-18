package de.fhdw.bfwc322b.stardewCalc;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import static de.fhdw.bfwc322b.stardewCalc.GUI.ROW_PADDING;
import static de.fhdw.bfwc322b.stardewCalc.GUI.ROW_SIZE;

public class RoundButton extends JButton {

    private Shape shape;
    private final int arc;
    private final boolean isQuadratic;
    private int row;

    public int getRow(){ return row;}

    public void setRow(int pRow){
        row = pRow;
        setBounds(getX(),(row+2) * ROW_PADDING + row * ROW_SIZE, getWidth(),getHeight());
    }


    public RoundButton(String text, int arc, int row) {
        this(text, arc,  row, true);
    }

    public RoundButton(String pText, int pArc, int pRow, boolean pIsQuadratic) {
        super(pText);
        row = pRow;
        arc = pArc;
        isQuadratic= pIsQuadratic;

        initComponent(395, 40);
    }
    public RoundButton(String pText, int pArc, int pRow, boolean pIsQuadratic, int width, int height){
        super(pText);
        row = pRow;
        arc = pArc;
        isQuadratic = pIsQuadratic;

        initComponent(width, height);
    }

    private void initComponent(int width, int height) {
        if(isQuadratic) {
            final Dimension size = getPreferredSize();
            size.width = size.height = Math.max(size.width, size.height);
            setPreferredSize(size);
        }
            /*
               This layout management is NOT meant to be reused for other projects, it is very specifically
               catering towards this specific use-case!

               Button takes up an entire row, so the x position is constant

               The formula for y takes both the padding between lines and the size of each row into account.
               Because there is padding between the top of the screen and the first row, one more instance of padding
               needs to be added.

               Button takes up an entire row, so the size is constant
             */
        setBounds(10, (row+2) * ROW_PADDING + row * ROW_SIZE, width,height);
        setContentAreaFilled(false);
        setFocusable(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);


        g2d.setColor(getModel().isArmed() ? Color.orange: getBackground());
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, arc, arc));
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        g2d.setColor(getBackground().darker());
        g2d.draw(new RoundRectangle2D.Double(0, 0, getWidth()-1, getHeight()-1, arc, arc));
    }

    @Override
    public boolean contains( int x, int y ) {
        if(shape == null || !shape.getBounds().equals(getBounds())) {
            this.shape = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc);
        }
        return shape.contains(x, y);
    }
}