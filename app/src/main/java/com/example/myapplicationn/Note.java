package com.example.myapplicationn;

public class Note {
    private float note ;
    private int coef ;

    public Note(float note, int coef) {
        this.note = note;
        this.coef = coef;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public int getCoef() {
        return coef;
    }

    public void setCoef(int coef) {
        this.coef = coef;
    }
}
