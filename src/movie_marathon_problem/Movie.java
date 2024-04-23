package movie_marathon_problem;

import java.util.ArrayList;
import java.util.List;

/**
 * Movie object that will contain a title, duration, and list of references if any
 * Data parsed from user will be encapsulated into Movie object
 * No private fields as I'm lazy and didn't want to make getter methods (hopefully Google doesn't see this)
 */
public class Movie {
    public String title;
    public int duration;
    public List<String> references;

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.references = new ArrayList<>();
    }

    public void addReference(String reference) {
        references.add(reference);
    }

}
