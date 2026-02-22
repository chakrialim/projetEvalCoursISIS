package eval.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Reponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String contenu;

    @ManyToMany(mappedBy = "reponses")
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "formulaire_reponse_id")
    private FormulaireReponse formulaireReponse;

}
