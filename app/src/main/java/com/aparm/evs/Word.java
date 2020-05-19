package com.aparm.evs;

public class Word {
    private int id;
    private String word, type, forms, translations, text;
    private int number;

    public Word(int id, String word, String type, String forms, String translations, String text, int number) {
        this.id = id;
        this.word = word;
        this.type = type;
        this.forms = forms;
        this.translations = translations;
        this.text = text;
        this.number = number;
    }

    public Word() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getForms() {
        return forms;
    }

    public void setForms(String forms) {
        this.forms = forms;
    }

    public String getTranslations() {
        return translations;
    }

    public void setTranslations(String translations) {
        this.translations = translations;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
