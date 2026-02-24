package eval.service;
import eval.dto.EtudiantDTO;
import eval.entity.Etudiant;
import eval.repository.EtudiantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EtudiantService {
    @Autowired
    private EtudiantRepository etudiantRepository;

    public EtudiantDTO trouverEtudiant(Long id){
        Etudiant etudiant = etudiantRepository.findById(id);
        EtudiantDTO etudiantDTO = new EtudiantDTO();
        etudiantDTO.EtudiantMapper(etudiant);
        return etudiantDTO;
    }

}
