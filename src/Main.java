import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.*;
import java.util.Scanner;
import java.io.File;

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
    public ArrayList<Tren> citireFisier(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        Tren tr = new Tren();
        ArrayList<Tren> rute = new ArrayList<Tren>();
        int count = 1;
        String val = "";
        while (sc.hasNextLine()) {
            String linie = sc.nextLine();
            for (int i = 0; i < linie.length(); ++i) {
                if (linie.charAt(i) != ' ') {
                    val = val + linie.charAt(i);
                } else {
                    if (count == 1) {
                        tr.numar = val;
                        ++count;
                        val = "";
                    }
                    else if (count == 2) {
                        tr.setTipTren(val);
                        ++count;
                        val = "";
                    } else if (count == 3) {
                        tr.setPretClasaI(Float.parseFloat(val));
                        ++count;
                        val = "";
                    } else if (count == 4) {
                        tr.setPretClasaII(Float.parseFloat(val));
                        ++count;
                        val = "";
                    } else if (count == 5) {
                        tr.setPlecare(val);
                        ++count;
                        val = "";
                    } else if (count == 6) {
                        tr.setSosire(val);
                        ++count;
                        val = "";
                    } else if (count == 7) {
                        tr.setOra_plecare(val);
                        ++count;
                        val = "";
                    } else if (count == 8) {
                        tr.setOra_sosire(val);
                        ++count;
                        val = "";
                    } else if (count == 9) {
                        String gara = val;
                        val = "";
                        ++i;
                        while (linie.charAt(i) != ' ' && i < linie.length()) {
                            val = val + linie.charAt(i);
                            ++i;
                            if (i == linie.length())
                                break;
                        }
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
        return rute;
    }
}

class CautaTren {

    private boolean contains;

    public ArrayList<Tren> cautaTren(String gara_plecare , String gara_sosire , String tip , ArrayList<Tren> trenuri) {
        ArrayList<Tren> ruteGasite = new ArrayList<>();
        for (Tren tr : trenuri) {
            if (tr.getPlecare().equals(gara_plecare) && (tr.getSosire().equals(gara_sosire) || tr.getGari_parcurse().containsKey(gara_sosire))) {
                ruteGasite.add(tr);
            }
        }
        if (ruteGasite.size() == 0) {
            Tren plecareCompusa = new Tren();
            Tren sosireCompusa = new Tren();

            for (Tren tr : trenuri) {
                if (tr.getPlecare().equals(gara_plecare)) {
                    for (Tren tren : trenuri) {
                        if (tren.getSosire().equals(gara_sosire) && tren.getPlecare().equals(tr.getSosire())) {
                            plecareCompusa = tr;
                            sosireCompusa = tren;
                            ruteGasite.add(plecareCompusa);
                            ruteGasite.add(sosireCompusa);
                        }
                    }
                }
            }

        }
        return ruteGasite;
    }
}



public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/RuteTrenuri");
        CitireFisier cf = new CitireFisier();
        CautaTren ct = new CautaTren();
        ArrayList<Tren> trenuri = new ArrayList<>();
        trenuri = cf.citireFisier(file);

//        Tren tr = new Tren("IRN-1983" , "Personal" , 90 , 70 , "Constanta" , "Bucuresti Nord" , "20:05" , "22:35" ,
//                new HashMap<String , String>() {
//                    { put("Medgidia" , "15:30");
//                        put("Lehliu" , "16:04");
//                    }
//                });

        JFrame frame =  new JFrame("Mersul Trenurilor");
        JLabel image;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1050 , 1500);
        File f= new File("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/Locatii.txt");
        String[] s = new String[7];
        try {
            BufferedReader br = new BufferedReader( new FileReader(f));
            String l;
            int i = 0;
            while ((l = br.readLine())!= null) {
                s[i] = l;
                i++;
            }
        }catch (IOException exception) {
            exception.printStackTrace();
        }

        JComboBox c1= new JComboBox(s);
        c1.setSelectedItem(null);
        c1.setBounds(100,100,30,5);
        JComboBox c2= new JComboBox(s);
        c2.setSelectedItem(null);
        c2.setBounds(100,100,30,5);
        File ff= new File("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/clase.txt");
        String[] c = new String[2];
        try {
            BufferedReader br = new BufferedReader( new FileReader(ff));
            String l;
            int i = 0;
            while ((l = br.readLine())!= null) {
                c[i] = l;
                i++;
            }
        }catch (IOException exception) {
            exception.printStackTrace();
        }
        JComboBox c3 = new JComboBox(c);
        c3.setSelectedItem(null);
        c3.setBounds(100,100,5,2);
        File f5= new File("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/tip.txt");
        String[] bb = new String[4];
        try {
            BufferedReader br = new BufferedReader( new FileReader(f5));
            String l;
            int i = 0;
            while ((l = br.readLine())!= null) {
                bb[i] = l;
                i++;
            }
        }catch (IOException exception) {
            exception.printStackTrace();
        }
        JComboBox c5 = new JComboBox(bb);
        c5.setSelectedItem(null);
        File f4= new File("/Users/eduard/Developer/projects/Mersul Trenurilor Java/Mersul Trenurilor OOP/src/om.txt");
        String[] cc = new String[4];
        try {
            BufferedReader br = new BufferedReader( new FileReader(f4));
            String l;
            int i = 0;
            while ((l = br.readLine())!= null) {
                cc[i] = l;
                i++;
            }
        }catch (IOException exception) {
            exception.printStackTrace();
        }
        c5.setBounds(100,100,3,2);

        JComboBox c4 = new JComboBox(cc);
        c4.setSelectedItem(null);
        c4.setBounds(100,100,5,2);
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

        JLabel l1 = new JLabel("Plecare :");
        l1.setBounds(4,5,150,222);
        JTextField tf1 = new JTextField(30);
        JLabel l2 = new JLabel("Sosire :");

        JLabel l4 = new JLabel("Clasa :");

        JLabel l5 = new JLabel("Tipul de tren :");

        JLabel l6 = new JLabel("Pentru :");

        JLabel l7 = new JLabel("Pret maxim bilet: ");
        JTextField tf7= new JTextField(30);

        JButton button = new JButton("Cautati");

        ArrayList<Tren> ruteTrenuri = trenuri;
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gara_plecare = c1.getSelectedItem().toString();
                String gara_sosire = c2.getSelectedItem().toString();
                int clasa = Integer.parseInt(c3.getSelectedItem().toString());
                String tip_tren = c5.getSelectedItem().toString();
                String persoana = c4.getSelectedItem().toString();
                List<Tren> ruteGasite = new ArrayList<>();
                ruteGasite = ct.cautaTren(gara_plecare , gara_sosire , tip_tren , ruteTrenuri);
                for (Tren tr : ruteGasite) {
                    float pret = tr.getPret(clasa);
                    if (persoana.equals("Student") || persoana.equals("Pensionar")) {
                        pret /= 2;
                    } else if (persoana.equals("Soldat")) {
                        pret = 0;
                    }
                    System.out.println(tr.numar + " " + tr.getTipTren() + " " + tr.getPlecare() + " " + tr.getSosire() + " " + tr.getOra_plecare() + " " + tr.getOra_sosire() + " " + pret);
                }
            }
        });

        panel.add(l1);
        panel.add(c1);
        panel.add(l2);
        panel.add(c2);
        panel.add(l4);
        panel.add(c3);
        panel.add(l5);
        panel.add(c5);
        panel.add(l6);
        panel.add(c4);
        panel.add(l7);
        panel.add(tf7);
        panel.add(button);
        panel.repaint();
        frame.add(panel);
        frame.revalidate();
        frame.repaint();
    }
}