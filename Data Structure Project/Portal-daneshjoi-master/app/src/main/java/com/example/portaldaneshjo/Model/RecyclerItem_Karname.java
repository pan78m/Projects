package com.example.portaldaneshjo.Model;

public class RecyclerItem_Karname {

    String vaziat , teacher , lessonname , lessoncode ,nomre;
    int score , amali , nazari;

    public RecyclerItem_Karname(String vaziat, String teacher, String lessonname, String lessoncode, int score, int amali, int nazari, String nomre) {
        this.vaziat = vaziat;
        this.teacher = teacher;
        this.lessonname = lessonname;
        this.lessoncode = lessoncode;
        this.score = score;
        this.amali = amali;
        this.nazari = nazari;
        this.nomre = nomre;
    }

    public String getVaziat() {
        return vaziat;
    }

    public void setVaziat(String vaziat) {
        this.vaziat = vaziat;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname;
    }

    public String getLessoncode() {
        return lessoncode;
    }

    public void setLessoncode(String lessoncode) {
        this.lessoncode = lessoncode;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getAmali() {
        return amali;
    }

    public void setAmali(int amali) {
        this.amali = amali;
    }

    public int getNazari() {
        return nazari;
    }

    public void setNazari(int nazari) {
        this.nazari = nazari;
    }

    public String getNomre() {
        return nomre;
    }

    public void setNomre(String nomre) {
        this.nomre = nomre;
    }
}