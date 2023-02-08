import javax.swing.*;
import java.awt.*;
import java.awt.Color;


public class Frame4 extends JFrame{
    private JLabel image;
    Frame4() {

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(20 , 1));
        panel.setBackground(Color.white);
        ImageIcon logo;

        try {
            logo = new ImageIcon(Main.class.getResource("logoCFR.jpeg"));
            Image img = logo.getImage();
            Image newimg = img.getScaledInstance(100 , 100 , Image.SCALE_SMOOTH);
            logo = new ImageIcon(newimg);
            image = new JLabel(logo);
            panel.add(image);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

        JLabel l1 = new JLabel("Achizia dvs. a fost facuta cu succes!");
        panel.add(l1);
        add(panel);

        setSize(950, 900);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}