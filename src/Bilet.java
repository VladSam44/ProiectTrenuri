public class Bilet {
    private String numar;
    private String tipTren;
    private String loc;
    private String CNP;

    Bilet(String nr , String tip , String l , String cnp) {
        this.numar = nr;
        this.tipTren = tip;
        this.loc = l;
        this.CNP = cnp;
    }

    public String getNumar() {
        return this.numar;
    }
    public String getTipTren() {
        return this.tipTren;
    }
    public String getLoc() {
        return this.loc;
    }
    public String getCNP() {
        return this.CNP;
    }
    public void setNumar(String nr) {
        this.numar = nr;
    }
    public void setTipTren(String tip) {
        this.tipTren = tip;
    }
    public void setLoc(String loc) {
        this.loc = loc;
    }
    public void setCNP(String cnp) {
        this.CNP = cnp;
    }
}
