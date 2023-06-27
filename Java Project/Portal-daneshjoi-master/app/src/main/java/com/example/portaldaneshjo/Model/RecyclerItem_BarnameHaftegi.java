package com.example.portaldaneshjo.Model;

public class RecyclerItem_BarnameHaftegi {

    String teachername , lessonname , classtime , examdate;
    int vahed_N , vahed_A;

    public RecyclerItem_BarnameHaftegi(String teachername, String lessonname, String classtime, String examdate, int vahed_N, int vahed_A) {
        this.teachername = teachername;
        this.lessonname = lessonname;
        this.classtime = classtime;
        this.examdate = examdate;
        this.vahed_N = vahed_N;
        this.vahed_A = vahed_A;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String getLessonname() {
        return lessonname;
    }

    public void setLessonname(String lessonname) {
        this.lessonname = lessonname;
    }

    public String getClasstime() {
        return classtime;
    }

    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getExamdate() {
        return examdate;
    }

    public void setExamdate(String examdate) {
        this.examdate = examdate;
    }

    public int getVahed_N() {
        return vahed_N;
    }

    public void setVahed_N(int vahed_N) {
        this.vahed_N = vahed_N;
    }

    public int getVahed_A() {
        return vahed_A;
    }

    public void setVahed_A(int vahed_A) {
        this.vahed_A = vahed_A;
    }
}
