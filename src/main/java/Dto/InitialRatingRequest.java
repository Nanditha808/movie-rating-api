package Dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitialRatingRequest {

    @NotBlank
    private  String reviewerName;

    @Min(1)
    @Max(10)
    private Integer score;

    @Size(max = 2000)
    private  String reviewText;
}
