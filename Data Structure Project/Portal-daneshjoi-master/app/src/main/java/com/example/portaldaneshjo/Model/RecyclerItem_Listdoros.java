package com.example.portaldaneshjo.Model;

public class RecyclerItem_Listdoros {

    String lessonname_ , examdate_ , classtime_ , teachername_ ;
    int lessonid_ , vahed_;

    public RecyclerItem_Listdoros(String lessonname, String examdate, String classtime, String teachername, int lessonid, int vahed) {
        this.lessonname_ = lessonname;
        this.examdate_ = examdate;
        this.classtime_ = classtime;
        this.teachername_ = teachername;
        this.lessonid_ = lessonid;
        this.vahed_ = vahed;
    }

    public String getLessonname() {
        return lessonname_;
    }

    public void setLessonname(String lessonname) {
        this.lessonname_ = lessonname;
    }

    public String getExamdate() {
        return examdate_;
    }

    public void setExamdate(String examdate) {
        this.examdate_ = examdate;
    }

    public String getClasstime() {
        return classtime_;
    }

    public void setClasstime(String classtime) {
        this.classtime_ = classtime;
    }

    public String getTeachername() {
        return teachername_;
    }

    public void setTeachername(String teachername) {
        this.teachername_ = teachername;
    }

    public int getLessonid() {
        return lessonid_;
    }

    public void setLessonid(int lessonid) {
        this.lessonid_ = lessonid;
    }

    public int getVahed() {
        return vahed_;
    }

    public void setVahed(int vahed) {
        this.vahed_ = vahed;
    }
}
