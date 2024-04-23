package movie_marathon_problem;

import java.util.*;

/**
 * This class represents a graph of movies where each movie is a node and edges
 * represent prerequisite relationships (i.e., one movie must be viewed before another).
 * It contains methods to parse movie data, represent it as a graph, and find the longest
 * marathon path using depth-first search (DFS).
 */
public class MovieGraph {

    // directed graph where KEY is Movie title and value is list of movie titles that are references to the key movie
    private final Map<String, List<String>> graph = new HashMap<>();

    // Key is the movie titles and the value is the duration of that movie, during DFS duration gets built upon
    private final Map<String, Integer> durations = new HashMap<>();

    // A count of how many edges each node has. Key is movie title and value is number of connected edges
    private final Map<String, Integer> prerequisites = new HashMap<>();


    /**
     * @param entry a list of type Movie that contains parsed inputs of title, duration and list of references
     * <p>
     * Add movie title, and it's duration to durations map on each iteration
     * If movie is not contained in graph, we add it and give it an empty list
     * <p>
     * We then iterate through that movies references in original input and we add that reference in graph and
     * give it an empty ArrayList.
     * <p>
     *
     * Now we do some reverse adding to our graph where the value of the key will be movies we need to watch before
     * watching the movie at the key itself
     * <p>
     *
     * Lastly, in prerequisites map, we add how many reference movies are needed to watch for that specific movie
     * <p>
     */
    public MovieGraph(List<Movie> entry) {
        for(Movie movie : entry) {
            durations.put(movie.title, movie.duration);
            graph.putIfAbsent(movie.title, new ArrayList<>()); //add if not already contained

            for(String refs : movie.references) {
                graph.putIfAbsent(refs, new ArrayList<>());
                graph.get(refs).add(movie.title);
                prerequisites.put(movie.title, prerequisites.getOrDefault(movie.title, 0) + 1);
            }
        }
    }

    /**
     * Finds the longest marathon of movies based on their durations and prerequisite relationships.
     * It initializes necessary structures for DFS and iterates through each movie to start the
     * DFS from movies with no prerequisites (in-degree of zero).
     *
     * @return A list containing the sequence of movies that forms the longest marathon based on total duration.
     * The list will be empty if no valid marathon path exists (e.g., due to cycles or lack of data).
     */
    public List<String> findLongestMarathon() {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        List<String> bestPath = new ArrayList<>();

        int[] longestDuration = new int[1];

        // Start DFS from each movie that has no prerequisites
        for(String movie : durations.keySet()) {
            if(!prerequisites.containsKey(movie)) { // Movie nodes that don't have edges
                depthFirstSearch(movie, visited, path, 0, bestPath, longestDuration);
            }
        }
        return bestPath;
    }

    /**
     * Performs a recursive depth-first search to find the longest path from the given movie node.
     * It tracks the path of movies and their cumulative durations, updating the best path found
     * if a longer duration is achieved. This method handles cycles by using a 'visited' set.
     *
     * @param movie          The current movie being visited.
     * @param visited        A set of movies that are currently being visited to avoid cycles.
     * @param path           The current path of movies being traversed.
     * @param currentDuration The cumulative duration of the movies in the current path.
     * @param bestPath       The best path found so far, updated when a longer path is found.
     * @param longestDuration An array containing the maximum duration found so far; used to store
     *                        the longest path's duration due to its mutable nature in recursion.
     */
    private void depthFirstSearch(String movie, Set<String> visited, List<String> path, int currentDuration,
                                  List<String> bestPath, int[] longestDuration )  {

        // Checking off benchmarks when recursing through a marathon
        visited.add(movie);
        path.add(movie);
        currentDuration += durations.get(movie);

        if(graph.get(movie).isEmpty()) { // at the end of the marathon
            if(currentDuration > longestDuration[0]) {
                longestDuration[0] = currentDuration;
                bestPath.clear();
                bestPath.addAll(new ArrayList<>(path));
            }
        }
        else { // recursion of DFS
            for(String nextMovie : graph.get(movie)) {
                if(!visited.contains(nextMovie)) {
                    depthFirstSearch(nextMovie, visited, path, currentDuration, bestPath, longestDuration);
                }
            }
        }
         // Used for backtracking once we reach the end of a sub marathon
        visited.remove(movie);
        path.remove(path.size() - 1);
    }

}
