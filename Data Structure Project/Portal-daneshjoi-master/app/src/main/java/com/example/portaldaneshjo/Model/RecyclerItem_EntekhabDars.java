package com.example.portaldaneshjo.Model;

public class RecyclerItem_EntekhabDars {

    int moshakhase , zarfiat;
    String lessontime , examdate , techername;

    public RecyclerItem_EntekhabDars(int moshakhase, int zarfiat, String lessontime, String examdate, String techername) {
        this.moshakhase = moshakhase;
        this.zarfiat = zarfiat;
        this.lessontime = lessontime;
        this.examdate = examdate;
        this.techername = techername;
    }

    public int getMoshakhase() {
        return moshakhase;
    }

    public void setMoshakhase(int moshakhase) {
        this.moshakhase = moshakhase;
    }

    public int getZarfiat() {
        return zarfiat;
    }

    public void setZarfiat(int zarfiat) {
        this.zarfiat = zarfiat;
    }

    public String getLessontime() {
        return lessontime;
    }

    public void setLessontime(String lessontime) {
        this.lessontime = lessontime;
    }

    public String getExamdate() {
        return examdate;
    }

    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }

    public String getTechername() {
        return techername;
    }

    public void setTechername(String techername) {
        this.techername = techername;
    }
}
