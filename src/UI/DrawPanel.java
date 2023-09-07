package UI;

import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel {
    public void setImage(Image image) {
        this.image = image;
    }

    private Image image;
    public DrawPanel(){
        super();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0,null);
    }


}
