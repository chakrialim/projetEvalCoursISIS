package eval.entity;
import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;



@Entity
@Getter @Setter @NoArgsConstructor  @RequiredArgsConstructor @ToString

public class Enseignant {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String mail;

    @NotNull
    private String nom;

    @NotNull
    private String prenom;


    @ManyToMany
    @JoinTable(
        name = "enseignant_enseignement",
        joinColumns = @JoinColumn(name = "enseignant_mail"),
        inverseJoinColumns = @JoinColumn(name = "enseignement_id")
    )
    private List<Enseignement> enseignements;

}