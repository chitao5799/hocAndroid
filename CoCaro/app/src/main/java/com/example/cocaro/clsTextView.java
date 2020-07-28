package com.example.cocaro;

public class clsTextView {
    private String textInTextView;
    private String textColorInTV;
    public clsTextView(String textInTextView, String textColorInTV) {
        this.textInTextView = textInTextView;
        this.textColorInTV = textColorInTV;
    }


    public clsTextView(String textInTextView) {
        this.textInTextView = textInTextView;
    }

    public String getTextInTextView() {
        return textInTextView;
    }

    public void setTextInTextView(String textInTextView) {
        this.textInTextView = textInTextView;
    }

    public String getTextColorInTV() {
        return textColorInTV;
    }

    public void setTextColorInTV(String textColorInTV) {
        this.textColorInTV = textColorInTV;
    }
}
