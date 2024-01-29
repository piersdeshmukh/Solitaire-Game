package com.patience;

public enum Colours {
    RED("\033[0;31m"),
    BLACK("\033[0;30m"),
    WHITE("\033[0;37m");
    private String colour;

    Colours(String colour)
    {
        this.colour=colour;
    }
    public String getColour() {
        return colour;
    }
}