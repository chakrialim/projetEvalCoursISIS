package eval.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import eval.dto.EtudiantDTO;
import eval.service.EtudiantService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/ajouter")
    public ResponseEntity<EtudiantDTO> ajouterEtudiant(@RequestBody EtudiantDTO etudiantDTO) {
        return ResponseEntity.ok(
            etudiantService.ajouterEtudiant(etudiantDTO)
        );
    }

    @PostMapping("/import")
    public ResponseEntity<String> importExcel(@RequestParam("file") MultipartFile file) {
        etudiantService.importEtudiantsFromExcel(file);
        return ResponseEntity.ok("Importation réussie !");
    }

    
}
