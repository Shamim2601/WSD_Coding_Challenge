package main.java.movielisting;

public class Movie {
    private String title;
    private String cast;
    private String category;
    private String releaseDate;
    private double budget;

    public Movie(String title, String cast, String category, String releaseDate, double budget) {
        this.title = title;
        this.cast = cast;
        this.category = category;
        this.releaseDate = releaseDate;
        this.budget = budget;
    }

    public String getTitle() {
        return title;
    }

    public String getCast() {
        return cast;
    }

    public String getCategory() {
        return category;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n" +
               "Cast: " + cast + "\n" +
               "Category: " + category + "\n" +
               "Release Date: " + releaseDate + "\n" +
               "Budget: $" + String.format("%.2f", budget) + "\n";
    }
}