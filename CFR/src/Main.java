import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.io.File;
import java.util.Scanner;



class Tren {
    public String numar;
    private String tipTren;
    private float pretClasaI;
    private float pretClasaII;
    private String plecare;
    private String sosire;
    private String ora_plecare;
    private String ora_sosire;
    private HashMap<String , String> gari_parcurse = new HashMap<String , String>();

    Tren(){}

    Tren(String numar ,String tipTren ,float pretClasaI , float pretClasaII , String plecare , String sosire , String ora_plecare , String ora_sosire, HashMap<String , String> gari_parcurse) {
        this.numar = numar;
        this.tipTren = tipTren;
        this.pretClasaI = pretClasaI;
        this.pretClasaII = pretClasaII;
        this.plecare = plecare;
        this.sosire = sosire;
        this.ora_plecare = ora_plecare;
        this.ora_sosire = ora_sosire;
        this.gari_parcurse = gari_parcurse;
    }

    public void setTipTren(String tip) {
        this.tipTren = tip;
    }

    public void setPretClasaI(float pret) {
        this.pretClasaI = pret;
    }

    public void setPretClasaII(float pret) {
        this.pretClasaII = pret;
    }

    public void setPlecare(String plecare) {
        this.plecare = plecare;
    }

    public void setSosire(String sosire) {
        this.sosire = sosire;
    }

    public void setOra_plecare(String ora) {
        this.ora_plecare = ora;
    }

    public void setOra_sosire(String ora) {
        this.ora_sosire = ora;
    }

    public void adaugaGariParcurse(String gara , String ora) {
        gari_parcurse.put(gara , ora);
    }

    public String getTipTren() {
        return this.tipTren;
    }

    public float getPret(int clasa) {
        if (clasa == 1) {
            return this.pretClasaI;
        } else {
            return this.pretClasaII;
        }
    }

    public String getPlecare() {
        return this.plecare;
    }

    public String getSosire() {
        return this.sosire;
    }

    public String getOra_plecare() {
        return this.ora_plecare;
    }

    public String getOra_sosire() {
        return this.ora_sosire;
    }

    public Map<String , String> getGari_parcurse() {
        return this.gari_parcurse;
    }
}

class CitireFisier {
    public List<Tren> citireFisier(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        Tren tr = new Tren();
        List<Tren> rute = new ArrayList<Tren>();
        int count = 1;
        String val = "";
        while (sc.hasNextLine()) {
            String linie = sc.nextLine();
            System.out.println(linie);
            for (int i = 0; i < linie.length(); ++i) {
                if (linie.charAt(i) != ' ') {
                    val = val + linie.charAt(i);
                } else {
                    if (count == 1) {
                        tr.numar = val;
                        ++count;
                        val = "";
                        System.out.println(tr.numar);
                    }
                    else if (count == 2) {
                        tr.setTipTren(val);
                        ++count;
                        val = "";
                        System.out.println(tr.getTipTren());
                    } else if (count == 3) {
                        tr.setPretClasaI(Float.parseFloat(val));
                        ++count;
                        val = "";
                        System.out.println(tr.getPret(1));
                    } else if (count == 4) {
                        tr.setPretClasaII(Float.parseFloat(val));
                        ++count;
                        val = "";
                        System.out.println(tr.getPret(2));
                    } else if (count == 5) {
                        tr.setPlecare(val);
                        ++count;
                        val = "";
                        System.out.println(tr.getPlecare());
                    } else if (count == 6) {
                        tr.setSosire(val);
                        ++count;
                        val = "";
                        System.out.println(tr.getSosire());
                    } else if (count == 7) {
                        tr.setOra_plecare(val);
                        ++count;
                        val = "";
                        System.out.println(tr.getOra_plecare());
                    } else if (count == 8) {
                        tr.setOra_sosire(val);
                        ++count;
                        val = "";
                        System.out.println(tr.getOra_sosire());
                    } else if (count == 9) {
                        String gara = val;
                        val = "";
                        ++i;
                        while (linie.charAt(i) != ' ' && i < linie.length()) {
//                            System.out.println(linie.charAt(i));
                            val = val + linie.charAt(i);
                            ++i;
                            if (i == linie.length())
                                break;
                        }
                        System.out.println(gara);
                        System.out.println(val);
                        tr.adaugaGariParcurse(gara, val);
                        val = "";
                        if (i == linie.length())
                        {
                            rute.add(tr);
                            tr = new Tren();
                            count = 1;
                            val = "";
                        }
                    }
                }
            }
        }
        System.out.println(rute.size());
        return rute;
    }
}

class CautaTren {
    public void cautaTren(String gara_plecare , String gara_sosire , List<Tren> trenuri) {
        for (Tren tr : trenuri) {
            if (tr.getPlecare().equals(gara_plecare) && (tr.getSosire().equals(gara_sosire) || tr.getGari_parcurse().containsKey(gara_sosire))) {
                System.out.println(tr.numar);
            }
        }
    }

}


public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/RuteTrenuri");
        CitireFisier cf = new CitireFisier();
        CautaTren ct = new CautaTren();
        List<Tren> trenuri = new ArrayList<>();
        trenuri = cf.citireFisier(file);
        ct.cautaTren("Constanta" , "Bucuresti-Nord" , trenuri);

        Tren tr = new Tren("IRN-1983" , "Personal" , 90 , 70 , "Constanta" , "Bucuresti Nord" , "20:05" , "22:35" ,
                new HashMap<String , String>() {
            { put("Medgidia" , "15:30");
              put("Lehliu" , "16:04");
            }
        });
        JFrame frame =  new JFrame("Mersul Trenurilor");
        JLabel image;
        frame.setVisible(true);
        frame.setSize(460 , 600);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10 , 1));
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

        JLabel l1 = new JLabel("Plecare :");
        JTextField tf1 = new JTextField(30);
        JLabel l2 = new JLabel("Sosire :");
        JTextField tf2 = new JTextField(30);
        JButton button = new JButton("Search");

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plecare = tf1.getText();
                System.out.println(plecare);
                System.out.println(tr.getPlecare());
                if (plecare.equals(tr.getPlecare())) {
                    System.out.println(tr);
                }
            }
        });




        panel.add(l1);
        panel.add(tf1);
        panel.add(l2);
        panel.add(tf2);
        panel.add(button);

        frame.add(panel);

        }
    }