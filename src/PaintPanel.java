import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


class PaintPanel extends JPanel {
    private BufferedImage buffer;

    PaintPanel() {
        int width = 650;
        int height = 2100;
        setPreferredSize(new Dimension(width, height));
        buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);         //image with 8-bit RGBA color component
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(buffer, 0, 0, null);
    }

    public BufferedImage getBuffer() {
        return buffer;
    }

    public void loadImage(BufferedImage buff) {
        buffer.createGraphics().setColor(Color.WHITE);
        buffer.createGraphics().fillRect(0, 0, getWidth(), getHeight());
        buffer.createGraphics().drawImage(buff, 0, 0, null);
        repaint();
    }
}