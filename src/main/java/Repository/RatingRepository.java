package Repository;

import Entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating,Long> {
    List<Rating> findByMovieIdOrderByRatedDesc(Long movieId);

}
