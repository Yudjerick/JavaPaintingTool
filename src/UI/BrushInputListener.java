package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BrushInputListener extends AbstractInputListener {

    public BrushInputListener(MainFrame context){
        super(context);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        draw(e.getX(),e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        draw(e.getX(), e.getY());
    }
    private void draw(int x, int y){
        Graphics2D graphics2D = context.getImg().createGraphics();
        graphics2D.setColor(context.getColorChooser().getColor());
        int brushSize = context.getBrushSize();
        graphics2D.fillOval(x - brushSize/2,y - brushSize/2, brushSize, brushSize);
        context.updateDrawingPanel();
    }
}
