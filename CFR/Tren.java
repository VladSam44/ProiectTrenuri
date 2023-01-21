import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tren {
    private String statiePlecare;
    private String statieSosire;
    private String tipTren;
    private String intervalTimp;
    private float costBilet;
    private String clasa;
    private static final List<String> tipuriTren = Arrays.asList("Personal", "Accelerat", "Rapid", "Intercity");
    private static final List<String> intervaleTimp = Arrays.asList("Dimineata", "Amiaza", "Seara", "Noaptea");
    private static final List<String> statii = Arrays.asList("Bucuresti", "Timisoara", "Cluj", "Iasi", "Constanta");
    private static final Map<String, Map<String, Float>> costuriBilete = new HashMap<String, Map<String, Float>>() {{
        put("Personal", new HashMap<String, Float>() {{
            put("Prima clasa", 50.0f);
            put("A doua clasa", 30.0f);
        }});
        put("Accelerat", new HashMap<String, Float>() {{
            put("Prima clasa", 70.0f);
            put("A doua clasa", 50.0f);
        }});
        put("Rapid", new HashMap<String, Float>() {{
            put("Prima clasa", 90.0f);
            put("A doua clasa", 70.0f);
        }});
        put("Intercity", new HashMap<String, Float>() {{
            put("Prima clasa", 110.0f);
            put("A doua clasa", 90.0f);
        }});
    }};

    public Tren(String statiePlecare, String statieSosire, String tipTren, String intervalTimp, String clasa) {
        if (statii.contains(statiePlecare) && statii.contains(statieSosire) && tipuriTren.contains(tipTren) && intervaleTimp.contains(intervalTimp) && costuriBilete.get(tipTren).containsKey(clasa)) {
            this.statiePlecare = statiePlecare;
            this.statieSosire = statieSosire;
            this.tipTren = tipTren;
            this.intervalTimp = intervalTimp;
            this.clasa = clasa;
            this.costBilet = costuriBilete.get(tipTren).get(clasa);
        } else {
            throw new IllegalArgumentException("Valoarea introdusa nu este valida");
        }
    }

    public Tren() {
        this.statiePlecare = "Necunoscut";
        this.statieSosire = "Necunoscut";
        this.tipTren = "Necunoscut";
        this.intervalTimp = "Necunoscut";
        this.costBilet = 0.0f;
        this.clasa = "Necunoscut";
    }

    public String getStatiePlecare() {
        return statiePlecare;
    }

    public void setStatiePlecare(String statiePlecare) {
        if (statii.contains(statiePlecare))
            this.statiePlecare = statiePlecare;
        else
            throw new IllegalArgumentException("Statia de plecare introdusa nu este valida");
    }

    public String getStatieSosire() {
        return statieSosire;
    }

    public void setStatieSosire(String statieSosire) {
        if (statii.contains(statieSosire))
            this.statieSosire = statieSosire;
        else
            throw new IllegalArgumentException("Statia de sosire introdusa nu este valida");
    }

    public String getTipTren() {
        return tipTren;
    }

    public void setTipTren(String tipTren) {
        if (tipuriTren.contains(tipTren)) {
            this.tipTren = tipTren;
            this.costBilet = costuriBilete.get(tipTren).get(clasa);
        } else
            throw new IllegalArgumentException("Tipul de tren introdus nu este valid");
    }

    public String getIntervalTimp() {
        return intervalTimp;
    }

    public void setIntervalTimp(String intervalTimp) {
        if (intervaleTimp.contains(intervalTimp))
            this.intervalTimp = intervalTimp;
        else
            throw new IllegalArgumentException("Intervalul de timp introdus nu este valid");
    }

    public float getCostBilet() {
        return costBilet;
    }

    public String getClasa() {
        return clasa;
    }

    public void setClasa(String clasa) {
        if (costuriBilete.get(tipTren).containsKey(clasa)) {
            this.clasa = clasa;
            this.costBilet = costuriBilete.get(tipTren).get(clasa);
        } else
            throw new IllegalArgumentException("Clasa introdusa nu este valida");
    }

    @Override
    public String toString() {
        return "Tren{" +
                "statiePlecare='" + statiePlecare + '\'' +
                ", statieSosire='" + statieSosire + '\'' +
                ", tipTren='" + tipTren + '\'' +
                ", intervalTimp='" + intervalTimp + '\'' +
                ", costBilet=" + costBilet +
                ", clasa='" + clasa + '\'' +
                '}';
    }
}