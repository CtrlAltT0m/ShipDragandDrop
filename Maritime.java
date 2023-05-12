import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Maritime extends JFrame {
    private JLabel[] navyIcons;

    public Maritime() {
        setTitle("Sea Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the blue square background
        JPanel seaPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Set layout and size of the seaPanel
        seaPanel.setLayout(null);
        seaPanel.setPreferredSize(new Dimension(800, 600));

        // Create the navy ships, submarines, and aircraft icons
        navyIcons = new JLabel[9];
        navyIcons[0] = createIcon("C:/DEV/destroyer_icon.png", 50, 50);


        // Add icons to the seaPanel
        for (JLabel icon : navyIcons) {
            seaPanel.add(icon);
        }

        // Set the content pane of the frame to the seaPanel
        setContentPane(seaPanel);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JLabel createIcon(String imagePath, int x, int y) {
        try {
            BufferedImage image = ImageIO.read(new File(imagePath));
            ImageIcon icon = new ImageIcon(image);
            JLabel label = new JLabel(icon);
            label.setSize(100, 100);
            label.setLocation(x, y);
            label.addMouseListener(new DragAndDropListener());
            return label;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class DragAndDropListener extends MouseAdapter {
        private JLabel label;
        private int initialX;
        private int initialY;

        @Override
        public void mousePressed(MouseEvent e) {
            label = (JLabel) e.getSource();
            initialX = e.getX();
            initialY = e.getY();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int newX = label.getX() + e.getX() - initialX;
            int newY = label.getY() + e.getY() - initialY;
            label.setLocation(newX, newY);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Maritime();
        });
    }
}
