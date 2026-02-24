package eval.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import eval.entity.Etudiant;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

    Etudiant findById(Long id);
    
}
