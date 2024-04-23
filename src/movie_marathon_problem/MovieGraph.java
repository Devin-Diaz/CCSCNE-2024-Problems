package movie_marathon_problem;

import java.util.*;

public class MovieGraph {
    private final Map<String, List<String>> graph = new HashMap<>();
    private final Map<String, Integer> durations = new HashMap<>();
    private final Map<String, Integer> prerequisites = new HashMap<>();

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

    public List<String> findLongestMarathon() {
        Set<String> visited = new HashSet<>();
        List<String> path = new ArrayList<>();
        List<String> bestPath = new ArrayList<>();

        int[] longestDuration = new int[1];

        for(String movie : durations.keySet()) {
            if(!prerequisites.containsKey(movie)) {
                depthFirstSearch(movie, visited, path, 0, bestPath, longestDuration);
            }
        }
        return bestPath;
    }

    private void depthFirstSearch(String movie, Set<String> visited, List<String> path, int currentDuration, List<String> bestPath, int[] longestDuration )  {
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
        else {
            for(String nextMovie : graph.get(movie)) {
                if(!visited.contains(nextMovie)) {
                    depthFirstSearch(nextMovie, visited, path, currentDuration, bestPath, longestDuration);
                }
            }
        }

        visited.remove(movie);
        path.remove(path.size() - 1);
    }

}
