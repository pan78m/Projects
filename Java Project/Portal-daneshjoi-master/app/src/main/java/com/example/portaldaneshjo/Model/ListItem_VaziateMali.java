package com.example.portaldaneshjo.Model;

public class ListItem_VaziateMali {

    private String itemmali;
    private long shomarefish;
    private String tarikhfish;
    private String shomarehesabejari;
    private long bedehkar,bestankar;

    public ListItem_VaziateMali( String itemmali, long shomarefish, String tarikhfish, String shomarehesabejari, long bedehkar, long bestankar) {
        this.itemmali = itemmali;
        this.shomarefish = shomarefish;
        this.tarikhfish = tarikhfish;
        this.shomarehesabejari = shomarehesabejari;
        this.bedehkar = bedehkar;
        this.bestankar = bestankar;
    }

    public ListItem_VaziateMali(long bedehkar) {
        this.bedehkar = bedehkar;
    }



    public String getItemmali() {
        return itemmali;
    }

    public void setItemmali(String itemmali) {
        this.itemmali = itemmali;
    }

    public long getShomarefish() {
        return shomarefish;
    }

    public void setShomarefish(long shomarefish) {
        this.shomarefish = shomarefish;
    }

    public String getTarikhfish() {
        return tarikhfish;
    }

    public void setTarikhfish(String tarikhfish) {
        this.tarikhfish = tarikhfish;
    }

    public String getShomarehesabejari() {
        return shomarehesabejari;
    }

    public void setShomarehesabejari(String shomarehesabejari) {
        this.shomarehesabejari = shomarehesabejari;
    }

    public double getBedehkar() {
        return bedehkar;
    }

    public void setBedehkar(long bedehkar) {
        this.bedehkar = bedehkar;
    }

    public double getBestankar() {
        return bestankar;
    }

    public void setBestankar(long bestankar) {
        this.bestankar = bestankar;
    }

}
