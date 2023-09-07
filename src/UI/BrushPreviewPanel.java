package UI;

import javax.swing.*;
import java.awt.*;

public class BrushPreviewPanel extends JPanel {
    public void setBrushSize(int brushSize) {
        this.brushSize = brushSize;
    }

    private int brushSize;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawOval((int)(getWidth()/2) - brushSize/2,(int)(getHeight()/2) - brushSize/2, brushSize,brushSize);
    }
}
