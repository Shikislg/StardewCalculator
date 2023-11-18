public class Material {
    private String type;
    private int smelted;
    private int raw;
    double ratio;

    public Material(String pType, int pBars, int pOres){
        type = pType;
        smelted = pBars;
        raw = pOres;
        ratio = 5;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public double getRatio() {
        return ratio;
    }
    public void setSmelted(int smelted) {
        this.smelted = smelted;
    }
    public int getSmelted() {
        return smelted;
    }

    public void setRaw(int raw) {
        this.raw = raw;
    }

    public int getRaw() {
        return raw;
    }

    public String getType() {
        return type;
    }
}
