package eval.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.util.List;

@Entity
public class Questionnaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "questionnaire_actif")
    private boolean actif = false;

    @Column(name = "questionnaire_nb_participations")
    private int nbParticipations = 0;

    @ManyToMany(mappedBy = "questionnaires")
    private List<Question> questions;

    @OneToMany(mappedBy = "questionnaire")
    private List<Enseignement> enseignements;

    @OneToMany(mappedBy = "questionnaire")
    private List<FormulaireReponse> formulaireReponses;


}
