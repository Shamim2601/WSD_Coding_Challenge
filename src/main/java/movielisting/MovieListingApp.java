package main.java.movielisting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MovieListingApp {
  private List<Movie> movies;
  private List<User> users;
  private User currentUser;

  public MovieListingApp() {
    movies = new ArrayList<>();
    users = new ArrayList<>();
    currentUser = null;
  }

  public static void main(String[] args) {
    MovieListingApp app = new MovieListingApp();

    // Sample movies
    app.addMovie(new Movie("Inception", "Leonardo DiCaprio, Joseph Gordon-Levitt", "Action", "2010", 160000000));
    app.addMovie(new Movie("The Shawshank Redemption", "Tim Robbins, Morgan Freeman", "Drama", "1994", 25000000));
    app.addMovie(new Movie("The Dark Knight", "Christian Bale, Heath Ledger", "Action", "2008", 185000000));

    Scanner scanner = new Scanner(System.in);

    while (true) {
      if (app.currentUser == null) {
        System.out.println("\n===== Movie Listing App =====");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
          case 1:
            System.out.print("Enter email to register: ");
            String emailToRegister = scanner.nextLine();
            app.registerUser(emailToRegister);
            System.out.println("User registered successfully.");
            break;
          case 2:
            System.out.print("Enter email to login: ");
            String emailToLogin = scanner.nextLine();
            app.loginUser(emailToLogin);
            if (app.currentUser != null) {
              System.out.println("Login successful.");
            }
            break;
          case 3:
            System.out.println("Exiting the application.");
            return;
          default:
            System.out.println("Invalid choice. Please try again.");
        }
      }
    }
  }

  public void addMovie(Movie movie) {
    movies.add(movie);
  }

  public void registerUser(String email) {
    User user = new User(email);
    users.add(user);
}

public void loginUser(String email) {
    for (User user : users) {
        if (user.getEmail().equals(email)) {
            currentUser = user;
            return;
        }
    }
    System.out.println("User not found. Please register.");
}

public void logoutUser() {
    currentUser = null;
}
}
