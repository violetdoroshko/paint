import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.filechooser.FileSystemView;

class GUIFrame extends JFrame {


    private PaintPanel panel1;
    private PaintPanel panel2;
    private JRadioButton magenta;
    private JRadioButton cyan;
    private JRadioButton orange;
    private Color color;
    private JFileChooser fileChooser;
    private File file;

    private int xOldCoordinate;
    private int yOldCoordinate;
    private int xNewCoordinate;
    private int yNewCoordinate;

    GUIFrame() {
        super("Paint");
        JButton open1 = new JButton("Open");
        JButton save1 = new JButton("Save");

        JButton open2 = new JButton("Open");
        JButton save2 = new JButton("Save");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel1 = new PaintPanel();
        panel2 = new PaintPanel();

        magenta = new JRadioButton("Magenta");
        cyan = new JRadioButton("Cyan");
        orange = new JRadioButton("Orange");
        ButtonGroup colorGroup = new ButtonGroup();
        color = Color.RED;
        colorGroup.add(magenta);
        colorGroup.add(cyan);
        colorGroup.add(orange);
        magenta.setSelected(true);

        JPanel northPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1, 6));
        northPanel.add(new JPanel());
        northPanel.add(magenta);
        northPanel.add(cyan);
        northPanel.add(orange);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridLayout(1, 5));
        southPanel.add(save1);
        southPanel.add(open1);
        southPanel.add(new JPanel());
        southPanel.add(save2);
        southPanel.add(open2);

        JScrollPane pane1 = new JScrollPane(panel1);
        JScrollPane pane2 = new JScrollPane(panel2);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        container.add(pane1, BorderLayout.WEST);
        container.add(pane2, BorderLayout.EAST);

        container.add(northPanel, BorderLayout.NORTH);
        container.add(southPanel, BorderLayout.SOUTH);

        fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOldCoordinate = e.getX();
                yOldCoordinate = e.getY();
            }
        });
        panel1.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                xNewCoordinate = e.getX();
                yNewCoordinate = e.getY();

                Graphics g = panel1.getGraphics();
                Graphics buf = panel1.getBuffer().getGraphics();
                chooseColor();
                buf.setColor(color);
                g.setColor(color);

                buf.drawLine(xOldCoordinate, yOldCoordinate, xNewCoordinate, yNewCoordinate);
                g.drawLine(xOldCoordinate, yOldCoordinate, xNewCoordinate, yNewCoordinate);

                xOldCoordinate = xNewCoordinate;
                yOldCoordinate = yNewCoordinate;
            }
        });
        panel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Graphics g = panel1.getGraphics();
                Graphics buf = panel1.getBuffer().getGraphics();
                chooseColor();
                buf.setColor(color);
                g.setColor(color);

                g.fillRect(e.getX(), e.getY(), 1, 1);
                buf.fillRect(e.getX(), e.getY(), 1, 1);
            }
        });
        open1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileChooser.setDialogTitle("Select a .png file: ");
                int returnValue = fileChooser.showOpenDialog(GUIFrame.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    try {
                        BufferedImage buf = ImageIO.read(file);
                        panel1.loadImage(buf);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        save1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileChooser.setDialogTitle("Save image: ");
                int returnValue = fileChooser.showSaveDialog(null);
                file = fileChooser.getSelectedFile();
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(panel1.getBuffer(), "png", file);
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            }
        });


        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xOldCoordinate = e.getX();
                yOldCoordinate = e.getY();
            }
        });
        panel2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                xNewCoordinate = e.getX();
                yNewCoordinate = e.getY();

                Graphics g = panel2.getGraphics();
                Graphics buf = panel2.getBuffer().getGraphics();
                chooseColor();
                buf.setColor(color);
                g.setColor(color);

                buf.drawLine(xOldCoordinate, yOldCoordinate, xNewCoordinate, yNewCoordinate);
                g.drawLine(xOldCoordinate, yOldCoordinate, xNewCoordinate, yNewCoordinate);

                xOldCoordinate = xNewCoordinate;
                yOldCoordinate = yNewCoordinate;
            }
        });
        panel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Graphics g = panel2.getGraphics();
                Graphics buf = panel2.getBuffer().getGraphics();
                chooseColor();
                buf.setColor(color);
                g.setColor(color);

                g.fillRect(e.getX(), e.getY(), 1, 1);
                buf.fillRect(e.getX(), e.getY(), 1, 1);
            }
        });
        open2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileChooser.setDialogTitle("Select a .png file: ");
                int returnValue = fileChooser.showOpenDialog(GUIFrame.this);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    file = fileChooser.getSelectedFile();
                    try {
                        BufferedImage buf = ImageIO.read(file);
                        panel2.loadImage(buf);
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
            }
        });
        save2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                fileChooser.setDialogTitle("Save image: ");
                int returnValue = fileChooser.showSaveDialog(null);
                file = fileChooser.getSelectedFile();
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(panel2.getBuffer(), "png", file);
                    } catch (IOException ex) {
                        ex.getMessage();
                    }
                }
            }
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });
    }

    private void onClose() {
        dispose();
    }

    private void chooseColor() {
        if (magenta.isSelected()) {
            color = Color.magenta;
        } else if (cyan.isSelected()) {
            color = Color.cyan;
        } else if (orange.isSelected()) {
            color = Color.orange;
        }
    }
}