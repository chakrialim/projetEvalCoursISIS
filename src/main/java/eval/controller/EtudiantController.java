package eval.controller;

import org.springframework.web.bind.annotation.RestController;

import eval.dto.EtudiantDTO;
import eval.service.EtudiantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {
    @Autowired
    private EtudiantService etudiantService;

    @RequestMapping("/trouver/{id}")
    public ResponseEntity<EtudiantDTO> trouverEtudiant(@PathVariable Long id){
        EtudiantDTO etudiant = etudiantService.trouverEtudiant(id);
        return ResponseEntity.ok(etudiant);
    }
    
}
