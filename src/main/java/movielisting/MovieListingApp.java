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
            app.logoutUser();
            break;
          default:
            System.out.println("Invalid choice. Please try again.");
        }
      }else {
        System.out.println("\n===== Movie Listing App =====");
        System.out.println("1. Search Movies");
        System.out.println("2. Add Movie to Favorites");
        System.out.println("3. Remove Movie from Favorites");
        System.out.println("4. View User Profile");
        System.out.println("5. Search Favorites");
        System.out.println("6. Logout");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter search keyword: ");
                String keyword = scanner.nextLine();
                List<Movie> searchResult = app.searchMoviesByTitle(keyword);
                searchResult.addAll(app.searchMoviesByCast(keyword));
                searchResult.addAll(app.searchMoviesByCategory(keyword));
                if (searchResult.isEmpty()) {
                    System.out.println("No movies found for the given keyword.");
                } else {
                    System.out.println("Search result:");
                    for (Movie movie : searchResult) {
                        System.out.println(movie);
                    }
                }
                break;
            case 2:
                System.out.print("Enter movie title to add to favorites: ");
                String titleToAdd = scanner.nextLine();
                List<Movie> moviesToAdd = app.searchMoviesByTitle(titleToAdd);
                if (moviesToAdd.isEmpty()) {
                    System.out.println("Movie not found.");
                } else {
                    System.out.println("Select a movie to add to favorites:");
                    for (int i = 0; i < moviesToAdd.size(); i++) {
                        System.out.println((i + 1) + ". " + moviesToAdd.get(i).getTitle());
                    }
                    System.out.print("Enter choice: ");
                    int movieChoice = scanner.nextInt();
                    app.addToFavorites(app.currentUser, moviesToAdd.get(movieChoice - 1));
                    System.out.println("Movie added to favorites.");
                }
                break;
            case 3:
                User user = app.currentUser;
                if (user.getFavorites().isEmpty()) {
                    System.out.println("No movies in favorites to remove.");
                } else {
                    System.out.println("Favorites:");
                    for (int i = 0; i < user.getFavorites().size(); i++) {
                        System.out.println((i + 1) + ". " + user.getFavorites().get(i).getTitle());
                    }
                    System.out.print("Enter index of movie to remove from favorites: ");
                    int indexToRemove = scanner.nextInt();
                    app.removeFromFavorites(user, user.getFavorites().get(indexToRemove - 1));
                    System.out.println("Movie removed from favorites.");
                }
                break;
            case 4:
                app.viewUserProfile(app.currentUser);
                break;
            case 5:
                System.out.print("Enter search keyword for favorites: ");
                String keywordForFavorites = scanner.nextLine();
                app.searchFavorites(app.currentUser, keywordForFavorites);
                break;
            case 6:
                app.logoutUser();
                System.out.println("Logged out successfully.");
                break;
            case 7:
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
