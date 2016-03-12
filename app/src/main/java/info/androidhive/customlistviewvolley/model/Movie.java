package info.androidhive.customlistviewvolley.model;

import java.util.ArrayList;

public class Movie {
    private String title, thumbnailUrl;
    private int year;
    private double rating;
    private String genre;
    private String duree;
    private String synopsis;
    private String distributeur;
    private String realisateur;

    public Movie() {
    }

    public Movie(String name, String thumbnailUrl, int year, double rating,
                 String genre) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;
        this.year = year;
        this.rating = rating;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public String getDuree() {
        return duree;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public void setDistributeur(String distributeur) {
        this.distributeur = distributeur;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}