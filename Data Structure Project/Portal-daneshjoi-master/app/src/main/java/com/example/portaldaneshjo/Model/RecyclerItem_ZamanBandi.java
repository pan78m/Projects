package com.example.portaldaneshjo.Model;

public class RecyclerItem_ZamanBandi {

    String startdate , enddate , salvorod , time , controlmoadel;

    public RecyclerItem_ZamanBandi(String startdate, String enddate, String salvorod, String time, String controlmoadel) {
        this.startdate = startdate;
        this.enddate = enddate;
        this.salvorod = salvorod;
        this.time = time;
        this.controlmoadel = controlmoadel;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getSalvorod() {
        return salvorod;
    }

    public void setSalvorod(String salvorod) {
        this.salvorod = salvorod;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getControlmoadel() {
        return controlmoadel;
    }

    public void setControlmoadel(String controlmoadel) {
        this.controlmoadel = controlmoadel;
    }
}
