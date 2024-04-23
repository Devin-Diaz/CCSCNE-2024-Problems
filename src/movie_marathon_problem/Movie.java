package movie_marathon_problem;

import java.util.ArrayList;
import java.util.List;

/* Movie POJO, requires the following fields, String title, int duration, every time a Movie POJO is created,
    an ArrayList is created with it, however whether it's full is optional since not all movies require references */
public class Movie {
    public String title;
    public int duration;
    public List<String> references;

    public Movie(String title, int duration) {
        this.title = title;
        this.duration = duration;
        this.references = new ArrayList<>();
    }

    // Populates are empty ArrayList for Movie POJO assuming it contains references
    public void addReference(String reference) {
        references.add(reference);
    }

}
