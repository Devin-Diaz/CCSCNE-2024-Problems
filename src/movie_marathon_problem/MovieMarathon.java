package movie_marathon_problem;

import java.util.*;

/**
 * The {@code MovieMarathon} class is the main class for the movie marathon problem
 * that manages the input, processing, and output of movie data to determine the longest
 * marathon of movies based on durations and prerequisite relationships.
 */
public class MovieMarathon {

    /**
     * The main method that initiates the process to handle movie data.
     * @param args not used in this application.
     */
    public static void main(String[] args){
        processMovies();
    }

    /**
     * Handles the input from the standard input, processes each line of movie data,
     * and organizes movies into separate franchises or movie series.
     * It expects input in a specific format ("movieTitle;Duration;prerequisite(s)")
     * and uses an empty line to separate different sets of movie data.
     */
    private static void processMovies() {
        List<List<Movie>> inputs = new ArrayList<>();
        List<Movie> currentFranchise = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int emptyLineCounter = 0;

        while (sc.hasNextLine()) {
            String movieInput = sc.nextLine().trim();

            // Handle separate inputs and termination of inputs based on consecutive empty lines
            if(movieInput.isEmpty()) {
                emptyLineCounter++;
                if(!currentFranchise.isEmpty()) {
                    inputs.add(currentFranchise);
                    currentFranchise = new ArrayList<>();
                    continue;
                }
                else if(emptyLineCounter == 2) break; // Two consecutive empty lines terminate input
            }
            else {
                emptyLineCounter = 0;
            }

            /* Parses input from the format movieTitle;Duration;reference;(s) if any
             * Parsed data of title and duration gets encapsulated to Movie Object
             * movie;100;film;picture --> ["movie", "100", "film", "picture"] */
            Movie movie = getMovie(movieInput);

            // adding current movie to a sub-list which is denoted as a franchise
            currentFranchise.add(movie);
        }
        sc.close();
        results(inputs);
    }

    /**
     * Parses a single line of movie input and converts it into a {@code Movie} object.
     * The movie input is expected to follow the format "title;duration;reference(s)".
     *
     * @param movieInput the string input representing a movie and its attributes.
     * @return a {@code Movie} object containing the parsed data.
     */
    private static Movie getMovie(String movieInput) {
        String[] movieInputArray = movieInput.split(";");
        String movieTitle = movieInputArray[0];
        int movieDuration = Integer.parseInt(movieInputArray[1]);
        Movie movie = new Movie(movieTitle, movieDuration);

        /* In the case there is one or more references, they get added to a list which is a field of the
        movie object */
        for(int i = 2; i < movieInputArray.length; i++) {
            movie.addReference(movieInputArray[i]);
        }
        return movie;
    }

    /**
     * Processes each group of movies (franchise) by constructing a movie graph,
     * finding the longest marathon, and printing the result.
     *
     * @param inputs a list of movie groups, each group representing a separate franchise.
     */
    private static void results(List<List<Movie>> inputs) {
        for(List<Movie> entry : inputs) {
            MovieGraph graph = new MovieGraph(entry);

            List<String> longestMarathon = graph.findLongestMarathon();
            printArray(longestMarathon);
        }
    }

    /**
     * Prints the elements of a list, each element on a new line, with a blank line following the list.
     * @param input the list of strings to be printed.
     */
    private static void printArray(List<String> input) {
        for(String s : input) {
            System.out.println(s);
        }
        System.out.println();
    }

}
