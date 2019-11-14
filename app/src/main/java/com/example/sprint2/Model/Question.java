package com.example.sprint2.Model;

import android.os.Parcel;

public class Question {
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int optionImg1,optionImg2,optionImg3,optionImg4;
    private int answerNr;
    private int categoryID;

    public Question() {
    }

    public Question(String question, String option1, String option2, String option3, String option4,
                    int answerNr, int categoryID) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.categoryID = categoryID;
    }

    public Question(String question, int questionImg, String option1, String option2, String option3, String option4,
                    int answerNr, int categoryID) {
        this.question = question;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.answerNr = answerNr;
        this.categoryID = categoryID;
    }

    public Question(String question, int optionImg1, int optionImg2, int optionImg3, int optionImg4,
                    int answerNr, int categoryID) {
        this.question = question;
        this.optionImg1 = optionImg1;
        this.optionImg2 = optionImg2;
        this.optionImg3 = optionImg3;
        this.optionImg4 = optionImg4;
        this.answerNr = answerNr;
        this.categoryID = categoryID;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        option4 = in.readString();
        answerNr = in.readInt();
        categoryID = in.readInt();
    }

    //@Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt( id );
        dest.writeString( question );
        dest.writeString( option1 );
        dest.writeString( option2 );
        dest.writeString( option3 );
        dest.writeString( option4 );
        dest.writeInt( answerNr );
        dest.writeInt( categoryID );
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }


    public int getOptionImg1() {
        return optionImg1;
    }

    public void setOptionImg1(int optionImg1) {
        this.optionImg1 = optionImg1;
    }

    public int getOptionImg2() {
        return optionImg2;
    }

    public void setOptionImg2(int optionImg2) {
        this.optionImg2 = optionImg2;
    }

    public int getOptionImg3() {
        return optionImg3;
    }

    public void setOptionImg3(int optionImg3) {
        this.optionImg3 = optionImg3;
    }

    public int getOptionImg4() {
        return optionImg4;
    }

    public void setOptionImg4(int optionImg4) {
        this.optionImg4 = optionImg4;
    }


    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }
}
