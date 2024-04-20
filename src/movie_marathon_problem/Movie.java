package movie_marathon_problem;

import java.util.ArrayList;
import java.util.List;


/**
 * Movie object that will contain a title, duration, and list of references
 * Data parsed from user will be encapsulated into Movie object
 */
public class Movie {
    private String title;
    private int duration;
    private List<String> references;

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.references = new ArrayList<>();
    }

    public void addReference(String reference) {
        references.add(reference);
    }

    public String toString() {
        return title +";" + duration + ";" + references;
    }


}
