package Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateMovieRequest {

    @NotBlank
    public String movieCode;

    private String title;

    private String director;

    @NotNull
    private  Integer releaseYear;

    @Valid
    @NotNull
    private InitialRatingRequest initialRating;


    public String getMovieCode() {
    }
}
