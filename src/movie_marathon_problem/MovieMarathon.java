package movie_marathon_problem;

import java.util.*;

public class MovieMarathon {
    public static void main(String[] args){
        processMovies();
    }

    private static void processMovies() {
        List<List<Movie>> inputs = new ArrayList<>();
        List<Movie> currentFranchise = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int emptyLineCounter = 0;

        // input handling
        while (sc.hasNextLine()) {
            String movieInput = sc.nextLine().trim();

            // Handles separate inputs and termination of inputs
            if(movieInput.isEmpty()) {
                emptyLineCounter++;
                if(!currentFranchise.isEmpty()) {
                    inputs.add(currentFranchise);
                    currentFranchise = new ArrayList<>();
                    continue;
                }
                else if(emptyLineCounter == 2) break;
            }
            else {
                emptyLineCounter = 0;
            }

            /* Parses input from the format movieTitle;Duration;reference;(s) if any
             * Parsed data of title and duration gets encapsulated to Movie Object
             * movie;100;film;picture --> ["movie", "100", "film", "picture"] */
            String[] movieInputArray = movieInput.split(";");
            String movieTitle = movieInputArray[0];
            int movieDuration = Integer.parseInt(movieInputArray[1]);
            Movie movie = new Movie(movieTitle, movieDuration);

            /* In the case there is one or more references, they get added to a list which is a field of the
            movie object */
            for(int i = 2; i < movieInputArray.length; i++) {
                movie.addReference(movieInputArray[i]);
            }

            // adding current movie to a sub-list which is denoted as a franchise
            currentFranchise.add(movie);
        }

        results(inputs);
    }

    private static void results(List<List<Movie>> inputs) {
        for(List<Movie> entry : inputs) {
            MovieGraph graph = new MovieGraph(entry);

            List<String> longestMarathon = graph.findLongestMarathon();
            printArray(longestMarathon);
        }
    }

    private static void printArray(List<String> input) {
        for(String s : input) {
            System.out.println(s);
        }
        System.out.println();
    }

}
