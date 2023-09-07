package UI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame{
    private JPanel drawingPanel;
    private JPanel sideBarPanel;

    private JPanel parentPanel;
    private JButton eraserButton;
    private JButton saveButton;
    private JSlider brushSizeSlider;
    private JPanel brushPreviewPanel;
    private JPanel colorPanel;
    private JButton brushButton;

    public JColorChooser getColorChooser() {
        return colorChooser;
    }

    private JColorChooser colorChooser;

    public int getBrushSize() {
        return brushSize;
    }

    private int brushSize = 10;

    public BufferedImage getImg() {
        return img;
    }

    private BufferedImage img;

    private AbstractInputListener inputListener;

    public MainFrame(){
        setContentPane(parentPanel);
        super.setTitle("Draw");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(0,0, 1200,800);

        inputListener = new BrushInputListener(this);
        drawingPanel.addMouseListener(inputListener);
        drawingPanel.addMouseMotionListener(inputListener);
        brushSizeSlider.addChangeListener(e -> {
            brushSize = brushSizeSlider.getValue();
            ((BrushPreviewPanel)brushPreviewPanel).setBrushSize(brushSize);
            brushPreviewPanel.repaint();
        });
        ((BrushPreviewPanel)brushPreviewPanel).setBrushSize(brushSize);
        brushPreviewPanel.repaint();

        brushButton.addActionListener((e->{
            for( MouseListener m : drawingPanel.getMouseListeners() ) {
                drawingPanel.removeMouseListener(m);
            }
            for( MouseMotionListener m : drawingPanel.getMouseMotionListeners() ) {
                drawingPanel.removeMouseMotionListener(m);
            }
            drawingPanel.addMouseMotionListener(inputListener);
            drawingPanel.addMouseListener(inputListener);
        }));
        eraserButton.addActionListener(e->{
            for( MouseListener m : drawingPanel.getMouseListeners() ) {
                drawingPanel.removeMouseListener(m);
            }
            for( MouseMotionListener m : drawingPanel.getMouseMotionListeners() ) {
                drawingPanel.removeMouseMotionListener(m);
            }
            drawingPanel.addMouseMotionListener(new EraserInputListener(this));
            drawingPanel.addMouseListener(new EraserInputListener(this));
        });
        saveButton.addActionListener(e -> {
            try {
                File outputfile = new File("D:\\example.png");
                ImageIO.write(img, "png", outputfile);
                outputfile.createNewFile();
            }
            catch (IOException exception){
                System.out.println(exception.getMessage());
            }

        });

        setVisible(true);
    }

    public void updateDrawingPanel(){
        drawingPanel.getGraphics().drawImage(img,0,0, null);
        drawingPanel.repaint();
    }

    private void createUIComponents() {
        drawingPanel = new DrawPanel();
        img = new BufferedImage(1000,1000, BufferedImage.TYPE_INT_ARGB_PRE);
        ((DrawPanel)drawingPanel).setImage(img);
        brushPreviewPanel = new BrushPreviewPanel();
        colorPanel = new JPanel();
        colorChooser = new JColorChooser();
        colorChooser.setPreviewPanel(new JPanel());
        colorChooser.setSize(200,200);
        colorChooser.setColor(Color.BLACK);

        colorPanel.add(colorChooser);
        revalidate();
    }
}
