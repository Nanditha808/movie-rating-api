package Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private String movieCode;
    private String title;
    private String director;
    private Integer releaseYear;

    private List<RatingRespose> rating;

    private Double averageRating;
    private Long totalRatings;
    private Map<Integer,Long> scoreDistribution;
}
