package Repository;

import Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<Movie>findBYMovieCode(String movieCode);
    boolean existsByMovieCode(String movieCode);
}
