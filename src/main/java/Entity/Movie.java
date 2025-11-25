package Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true,nullable = false)
    private String movieCode;

    @Column(name="title")
    private String title;

    @Column(name="director")
    private String director;
    private Integer releaseYear;
    private Long version;

    @OneToMany(mappedBy ="movie",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Rating> ratings=new ArrayList<>();

}
