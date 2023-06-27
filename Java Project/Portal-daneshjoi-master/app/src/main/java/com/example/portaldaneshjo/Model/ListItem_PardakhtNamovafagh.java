package com.example.portaldaneshjo.Model;

public class ListItem_PardakhtNamovafagh {

    private long shomaresefaresh,shenase,mablaghevarizi;
    private String tarikhvariz,saatvariz;

    public ListItem_PardakhtNamovafagh(long shomaresefaresh, long shenase, long mablaghevarizi, String tarikhvariz, String saatvariz) {
        this.shomaresefaresh = shomaresefaresh;
        this.shenase = shenase;
        this.mablaghevarizi = mablaghevarizi;
        this.tarikhvariz = tarikhvariz;
        this.saatvariz = saatvariz;
    }

    public long getShomaresefaresh() {
        return shomaresefaresh;
    }

    public void setShomaresefaresh(long shomaresefaresh) {
        this.shomaresefaresh = shomaresefaresh;
    }

    public long getShenase() {
        return shenase;
    }

    public void setShenase(long shenase) {
        this.shenase = shenase;
    }

    public long getMablaghevarizi() {
        return mablaghevarizi;
    }

    public void setMablaghevarizi(long mablaghevarizi) {
        this.mablaghevarizi = mablaghevarizi;
    }

    public String getTarikhvariz() {
        return tarikhvariz;
    }

    public void setTarikhvariz(String tarikhvariz) {
        this.tarikhvariz = tarikhvariz;
    }

    public String getSaatvariz() {
        return saatvariz;
    }

    public void setSaatvariz(String saatvariz) {
        this.saatvariz = saatvariz;
    }
}
