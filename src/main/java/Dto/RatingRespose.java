package Dto;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
@Getter
@Setter
public class RatingRespose {

    private Long id;
    private String reviwerName;
    private Integer score;
    private String reviwerText;
    private OffsetDateTime ratedOn;
}
