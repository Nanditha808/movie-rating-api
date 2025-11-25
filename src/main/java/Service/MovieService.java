package Service;

import Dto.CreateMovieRequest;
import Dto.MovieResponse;
import Entity.Movie;
import Repository.MovieRepository;
import Repository.RatingRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private RatingRepository ratingRepository;

//    public MovieService(MovieRepository movieRepository,RatingRepository ratingRepository){
//        this.movieRepository=movieRepository;
//        this.ratingRepository=ratingRepository;
//    }

    @Transactional
    public MovieResponse createMovieWithInitialRating(CreateMovieRequest req) {
        if (movieRepository.existsByMovieCode(req.getMovieCode())) {
            throw new DataIntegrityViolationException("movie with code already exist");
        }
        Movie movie=new Movie()(req.getMovieCode(),req.getTitle())
}
