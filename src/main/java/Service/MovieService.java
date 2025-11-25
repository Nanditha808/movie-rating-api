package com.movierating.service;

import com.movierating.dto.MovieRequest;
import com.movierating.dto.MovieResponse;
import com.movierating.dto.RatingResponse;
import com.movierating.model.Movie;
import com.movierating.model.Rating;
import com.movierating.repository.MovieRepository;
import com.movierating.repository.RatingRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, RatingRepository ratingRepository) {
        this.movieRepository = movieRepository;
        this.ratingRepository = ratingRepository;
    }

    @Transactional
    public MovieResponse createMovieWithRating(MovieRequest request) {
        // Check if movie code already exists
        if (movieRepository.existsByMovieCode(request.getMovieCode())) {
            throw new IllegalArgumentException("Movie with code '" + request.getMovieCode() + "' already exists");
        }

        // Create and save movie
        Movie movie = new Movie(
                request.getMovieCode(),
                request.getTitle(),
                request.getDirector(),
                request.getReleaseYear()
        );

        // Create and add initial rating
        Rating rating = new Rating(
                movie,
                request.getReviewerName(),
                request.getScore(),
                request.getReviewText()
        );

        movie.getRatings().add(rating);
        Movie savedMovie = movieRepository.save(movie);

        return convertToMovieResponse(savedMovie);
    }

    public MovieResponse getMovieByCode(String movieCode) {
        Movie movie = movieRepository.findByMovieCodeWithRatings(movieCode)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found with code: " + movieCode));

        return convertToMovieResponse(movie);
    }

    private MovieResponse convertToMovieResponse(Movie movie) {
        MovieResponse response = new MovieResponse();
        response.setId(movie.getId());
        response.setMovieCode(movie.getMovieCode());
        response.setTitle(movie.getTitle());
        response.setDirector(movie.getDirector());
        response.setReleaseYear(movie.getReleaseYear());

        // Convert ratings
        List<RatingResponse> ratingResponses = movie.getRatings().stream()
                .map(this::convertToRatingResponse)
                .collect(Collectors.toList());
        response.setRatings(ratingResponses);

        // Calculate statistics
        response.setTotalRatings(ratingResponses.size());

        if (!ratingResponses.isEmpty()) {
            Double averageRating = ratingRepository.findAverageRatingByMovieId(movie.getId());
            response.setAverageRating(averageRating != null ? Math.round(averageRating * 10.0) / 10.0 : 0.0);

            // Get score distribution
            List<Object[]> distributionData = ratingRepository.getScoreDistributionByMovieId(movie.getId());
            Map<Integer, Long> scoreDistribution = distributionData.stream()
                    .collect(Collectors.toMap(
                            data -> (Integer) data[0],
                            data -> (Long) data[1]
                    ));
            response.setScoreDistribution(scoreDistribution);
        } else {
            response.setAverageRating(0.0);
            response.setScoreDistribution(Map.of());
        }

        return response;
    }

    private RatingResponse convertToRatingResponse(Rating rating) {
        return new RatingResponse(
                rating.getId(),
                rating.getReviewerName(),
                rating.getScore(),
                rating.getReviewText(),
                rating.getRatedOn()
        );
    }
}