import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Frame3 extends JFrame {
    private JLabel image;
    Frame3(String ruta) throws FileNotFoundException {
        super("Bilet");
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
        String[] dateTren = ruta.split(" ");

        File file = new File("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/Bilete.txt");
        Scanner sc = new Scanner(file);
        List<Bilet> bilete = new ArrayList<>();
        int count = 1;
        while (sc.hasNextLine()) {
            String[] linie = sc.nextLine().split(" ");
            if (linie[0].equals(dateTren[0])) {
                ++count;
            }
            Bilet b = new Bilet(linie[0] , linie[1] , linie[2] , linie[3]);
            bilete.add(b);
        }
        System.out.println(bilete.size());

        JLabel l = new JLabel("Numar Tren: " + dateTren[0]);
        l.setFont(new Font("ARIAL",Font.BOLD,15));
        JLabel l2 = new JLabel("Tip tren: " + dateTren[1]);
        l2.setFont(new Font("ARIAL",Font.BOLD,15));
        JLabel l3 = new JLabel("Loc: " + count );
        l3.setFont(new Font("ARIAL",Font.BOLD,15));
        JLabel l4 = new JLabel("CNP: ");
        l4.setFont(new Font("ARIAL",Font.BOLD,15));
        Font font1 = new Font("SansSerif", Font.BOLD, 25);
        TextField t = new TextField();
        t.setPreferredSize(new Dimension(250,40));
        t.setFont(font1);

        JButton button = new JButton();
        button.setBounds(200,100,100,4);
        button.setText("Cumpara");
        int finalCount = count;
        button.addActionListener(new ActionListener() {
            JFrame f2;
            @Override
            public void actionPerformed(ActionEvent e) {
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new BufferedWriter(new FileWriter("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/Bilete.txt")));
                    pw.append(dateTren[0] + " " + dateTren[1] + " " + finalCount + " " + t.getText());
                    pw.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

                f2 = new Frame4();
            }
        });

        panel.add(l);
        panel.add(l2);
        panel.add(l3);
        panel.add(l4);
        panel.add(t);
        panel.add(button);
        add(panel);


        setSize(950,900);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

};
