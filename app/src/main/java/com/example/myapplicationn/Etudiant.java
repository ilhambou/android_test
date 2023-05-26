package com.example.myapplicationn;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

public class Etudiant {
    private int id;
    private String nom, prenom,classe,phone;
    private Bitmap photo;
    private List<Note> notes;

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public Etudiant(int id, String nom, String prenom, String classe, String phone, Bitmap photo, List<Note> notes) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.classe = classe;
        this.phone = phone;
        this.photo = photo;
        this.notes = notes;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getClasse() {
        return classe;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrenom() {
        return prenom;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Etudiant(int id , String nom , String prenom ,  String classe, String phone ,Bitmap photo)
    {
        this.id = id;
        this.nom = nom ;
        this.prenom = prenom ;
        this.phone = phone;
        this.photo = photo ;
    }

    public void addNote(Note n )
    {
        if(this.notes == null)
        {
            this.notes = new ArrayList<Note>();

        }
        this.notes.add(n);
    }
}
