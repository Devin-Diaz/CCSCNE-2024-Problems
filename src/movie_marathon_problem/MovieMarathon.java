package movie_marathon_problem;

import java.util.Scanner;


public class MovieMarathon {
    public static final char DELIMITER = ';';

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String movieInput = sc.nextLine();

            StringBuilder sb = new StringBuilder();
            char[] movieInputArray = movieInput.toCharArray();

            int i = 0;

            while(movieInputArray[i] != DELIMITER) {
                sb.append(movieInputArray[i]);
                i++;
            }
            i++;

            String movieTitle = sb.toString();
            sb = new StringBuilder();

            while (movieInputArray[i] != DELIMITER) {
                sb.append(movieInputArray[i]);
                i++;
            }
            i++;

            int movieDuration = Integer.parseInt(sb.toString());
            sb = new StringBuilder();

            Movie movie = new Movie(movieTitle, movieDuration);

            if (i != movieInputArray.length) {
                for(; i < movieInputArray.length; i++) {
                    while(i < movieInputArray.length && movieInputArray[i] != DELIMITER) {
                        sb.append(movieInputArray[i]);
                        i++;
                    }
                    movie.addReference(sb.toString());
                    sb = new StringBuilder();
                }
            }
        }


    }



}
