package org.acme;

// import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.transaction.Transactional;
import io.quarkus.runtime.StartupEvent;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class MovieService {

    private List<Movie> movies = new ArrayList<>();

    @Transactional
    void onStart(@Observes StartupEvent ev) {
        String filePath = "C:/Users/Ebenezer/Desktop/Nova pasta/code-with-quarkus/src/main/java/org/acme/movielist.csv";
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {
    
            for (CSVRecord record : csvParser) {
                Movie movie = new Movie();
                String yearString = record.get("year");
                try {
                    int releaseYear = Integer.parseInt(yearString);
                    movie.setReleaseYear(releaseYear);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid release year format: " + yearString);
                    continue;
                }
                movie.setTitle(record.get("title"));
                movie.setStudios(record.get("studios"));
                movie.setProducers(record.get("producers"));
                movie.setWinner("yes".equals(record.get("winner")));
                movie.persist();
                movies.add(movie);
            }
            System.out.println("File processing completed.");
        } catch (Exception e) {
            System.err.println("Error processing CSV file: " + e.getMessage());
        }
    }

    public List<Movie> getAllMovies() {
        return movies;
    }
}
