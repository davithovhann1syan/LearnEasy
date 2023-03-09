package com.example.learneasy;

public class VocabularyLessonModel{
    private String type;
    private String heading;
    private String info;
    private long id;


    public VocabularyLessonModel(String type, String heading, String info, long id) {
        this.type = type;
        this.heading = heading;
        this.info = info;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}