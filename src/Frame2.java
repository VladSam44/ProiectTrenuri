import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Frame2 extends JFrame {
    private JLabel image;
    Frame2 (List<Tren> rute , String plecare , String sosire){
        super("Bilete");
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

        JPanel subpanel = new JPanel();
        JLabel l = new JLabel("Rute disponibile: ");
        l.setFont(new Font("ARIAL",Font.BOLD,25));
        subpanel.add(l);
        JComboBox c3 = new JComboBox();
        c3.setSelectedItem(null);
        c3.setBounds(100,100,4,5);
        for (Tren tr : rute) {
            Map<String , String> gariParcurse = new HashMap<>();
            gariParcurse = tr.getGari_parcurse();
            String gara = "";

            if (tr.getSosire().equals(sosire)) {
                c3.addItem(tr.numar + " " + tr.getTipTren() + " " + tr.getPlecare() + " : " + tr.getOra_plecare() + " " + tr.getSosire() + " : " + tr.getOra_sosire());
            } else {
                for (Map.Entry<String , String> entry : gariParcurse.entrySet()) {
                    if (entry.getKey().equals(sosire)) {
                        c3.addItem(tr.numar + " " + tr.getTipTren() + " " + tr.getPlecare() + " : " + tr.getOra_plecare() + " " + entry.getKey() + " : " + entry.getValue());
                    }
                }

            }
        }

        subpanel.add(c3);
        JButton button = new JButton("Cumparati");

        button.addActionListener(new ActionListener() {
            JFrame f;
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    f = new Frame3(c3.getSelectedItem().toString());
                } catch (FileNotFoundException ex) {
                    ex.getMessage();
                }
            }
        });


        panel.add(l);
        panel.add(c3);
        add(panel);
        panel.add(button);

        setSize(1000,1000);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

}