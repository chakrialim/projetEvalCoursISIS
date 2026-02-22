package eval.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;  
import java.util.List;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "question_contenu")
    private String contenu;

    @ManyToMany
    @JoinTable(
        name = "question_reponse",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "reponse_id")
    )
    private List<Reponse> reponses;

    @ManyToMany
    @JoinTable(
        name = "question_questionnaire",
        joinColumns = @JoinColumn(name = "question_id"),
        inverseJoinColumns = @JoinColumn(name = "questionnaire_id")
    )
    private List<Questionnaire> questionnaires;



}
