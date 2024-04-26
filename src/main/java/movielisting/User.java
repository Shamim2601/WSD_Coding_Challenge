package main.java.movielisting;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String email;
    private List<Movie> favorites;

    public User(String email) {
        this.email = email;
        this.favorites = new ArrayList<>();
    }

    public String getEmail() {
        return email;
    }

    public void addFavorite(Movie movie) {
        favorites.add(movie);
    }

    public void removeFavorite(Movie movie) {
        favorites.remove(movie);
    }

    public List<Movie> getFavorites() {
        return favorites;
    }

    @Override
    public String toString() {
        return "Email: " + email + "\n" +
               "Favorites: " + favorites.size() + " movies\n";
    }
}

