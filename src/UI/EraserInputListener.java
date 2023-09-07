package UI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class EraserInputListener extends AbstractInputListener {

    public EraserInputListener(MainFrame context) {
        super(context);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        erase(e.getX(),e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        erase(e.getX(),e.getY());
    }

    private void erase(int x, int y){


        Graphics2D g = context.getImg().createGraphics();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
        g.setColor(new Color(100,255,255,255));
        int brushSize = context.getBrushSize();
        g.fillOval(x - brushSize/2,y - brushSize/2, brushSize, brushSize);

        /*g.setBackground(new Color(0,0,0,0));
        int brushSize = context.getBrushSize();
        g.clearRect(x - brushSize/2,y - brushSize/2, brushSize, brushSize);*/
        context.updateDrawingPanel();
    }
}
