import javax.swing.*;
import java.awt.*;

import static de.fhdw.bfwc322b.stardewCalc.GUI.*;

public class LayoutLabel extends JLabel {
    private int row;
    private int column;
    private boolean isOutput = false;
    public LayoutLabel(String text, int pRow){
        super(text);

        row = pRow;
        isOutput = true;
        //Specifically made to accommodate the needs of the output label
        super.setBounds(5, (pRow +1) * ROW_PADDING + pRow * ROW_SIZE +  2 * OUTPUT_ROW_PADDING, 400, 30);
        super.setMaximumSize(new Dimension(400,super.getHeight()));
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean isOutput() {
        return isOutput;
    }

    public int getColumn() {
        return column;
    }

    public LayoutLabel(String text, int pRow, int pColumn){
        super(text);
        column = pColumn;

            /*
              There is two possible positions for a label in this layout.
              If it describes the first text field its position is 5, otherwise it's 200

              The formula for y takes both the padding between lines and the size of each row into account.
              Because there is padding between the top of the screen and the first row, one more instance of padding
              needs to be added.

              The size is constant for this specific layout
            */
        super.setBounds(pColumn == 0 ? 0 : 200, (pRow +1) * ROW_PADDING + pRow * ROW_SIZE, 65, 30);
    }
}