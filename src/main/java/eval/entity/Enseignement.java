package eval.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.*;


@Entity
@Getter @Setter @ToString

public class Enseignement {
    @Id
    @Column(name = "enseignement_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String idCours;

    @NotNull
    private String libelle;

    @ManyToMany(mappedBy = "enseignements")
    private List<Enseignant> enseignants;

    @ManyToOne
    @JoinColumn(name = "ue_id")
    private Ue ue;

    @ManyToOne
    @JoinColumn(name="questionnaire_id")
    private Questionnaire questionnaire;
}
