package com.example.qnmd;

/**
 * Created by Administrator on 22/2/2018.
 */

public class StudentDetails {


    private String name;
    private String question;

    public StudentDetails(String a,String b) {
        // This is default constructor.
        question=a;
        name=b;
    }

    public String getStudentName() {

        return name;
    }

    public void setStudentName(String name) {

        this.name = name;
    }

    public String getQuestion() {

        return question;
    }

    public void setQuestion(String question) {

        this.question = question;
    }

}
