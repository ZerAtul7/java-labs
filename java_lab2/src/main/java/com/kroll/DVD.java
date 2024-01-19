package com.kroll;

public class DVD extends Item {
    private final String director;
    private final String releaseYear;

    public DVD(String title, String director, String releaseYear, String identifier) {
        super(title, identifier);
        this.director = director;
        this.releaseYear = releaseYear;
    }

    public String getDirector() {
        return director;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    @Override
    public String getType() {
        return "DVD";
    }

    @Override
    public String toString() {
        return getType() + ": " + super.getTitle() + " (Режисер: " + director + ", Рік випуску: " + releaseYear + ", Ідентифікатор: " + super.getIdentifier() + ")";
    }
}



