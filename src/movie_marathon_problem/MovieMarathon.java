package movie_marathon_problem;

import java.util.*;

public class MovieMarathon {
    public static void main(String[] args){

        /* A list will contain a list of movies considered as franchises, which is considered a single input
         * Inputs are differentiated by 1 empty line of input
         * If there is 2 empty lines in a row, it indicates the termination of inputs */
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

        findLongestMarathon(inputs);

    }

    /**
     * @param currentFranchise: a single input of multiple movies that will be iterated to construct Map/Graph
     * @return
     * Map where the key is a Movie and Value is a list of existing referenced movies in the franchise
     * by that Movie in the Key. [A;100;B;C , B;100] --> { A : [B] , B : [] }
     */
    private static Map<Movie, List<Movie>> buildGraph(List<Movie> currentFranchise) {
        Map<Movie, List<Movie>> graph = new HashMap<>();

        for(Movie movie : currentFranchise) {
            graph.put(movie, new ArrayList<>());
            for(String reference : movie.references) {
                Movie referencedMovie = findReferencedMovie(currentFranchise, reference);
                if(referencedMovie != null) {
                    graph.get(movie).add(referencedMovie);
                }
            }
        }
        return graph;
    }

    /**
     * @param currentFranchise: iterate through our current sub-list to search for movie titles
     * @param movieTitle: a reference from a movie, we check if any Movie titles match it
     * @return A movie that is a reference to another movie or null if a match isn't found
     */
    private static Movie findReferencedMovie(List<Movie> currentFranchise, String movieTitle) {
        for(Movie movie : currentFranchise) {
            if(movie.title.equals(movieTitle)) {
                return movie;
            }
        }
        return null;
    }

    /* Will hold the starting movie of the franchise in key, the value will hold the total duration of all franchises
    associated with that movie */
    private static final Map<Movie, Integer> longestPathLength = new HashMap<>();

    /**
     * @param movie: the current Movie node from which the DFS begins
     * @param graph:
     * Graph represented as adjacency list where each key is a movie and its associated value is a list
     * of movies that the key movie references
     *
     * @return Returns maximum duration of the longest path starting from the given movie node
     * Avoids redundant calculations by checking of starting movie node already exists in map
     * Iterates over all movies that are referenced by the current movie 'movie'
     * For each referenced movie 'nextMovie' it recursively calls itself to explore further depth and references
     * Each child duration 'childDuration' represents the longest path duration starting from 'nextMovie'
     */
    private static int depthFirstSearch(Movie movie, Map<Movie, List<Movie>> graph) {
        if(longestPathLength.containsKey(movie)) {
            return longestPathLength.get(movie);
        }

        int maxDuration = 0;
        for(Movie nextMovie : graph.get(movie)) {
            int childDuration = depthFirstSearch(nextMovie, graph);
            maxDuration = Math.max(maxDuration, childDuration);
        }

        maxDuration += movie.duration;
        longestPathLength.put(movie, maxDuration);

        return maxDuration;
    }

    /**
     *
     * @param inputs: 2-D arraylist, where each sub-list represents a franchise and all its movies
     * We build a graph for each franchise, then we iterate over every movie in that franchise, doing DFS for that
     * movie with help from our graph as it organized referenced movies.
     */
    public static void findLongestMarathon(List<List<Movie>> inputs) {
        for (List<Movie> franchise : inputs) {
            Map<Movie, List<Movie>> graph = buildGraph(franchise);
            int maxDuration = 0;
            Movie startMovie = null;

            for (Movie movie : franchise) {
                int duration = depthFirstSearch(movie, graph);
                if (duration > maxDuration) {
                    maxDuration = duration;
                    startMovie = movie;
                }
            }

            if (startMovie != null) {
                System.out.println("Longest Marathon starts at: " + startMovie.title + " with total duration: " + maxDuration + " minutes");
            } else {
                System.out.println("No valid marathon found for this franchise.");
            }
        }
    }

}
