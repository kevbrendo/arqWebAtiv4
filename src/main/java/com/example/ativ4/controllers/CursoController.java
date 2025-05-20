package com.example.ativ4.controllers;
import com.example.ativ4.Dto.CursoDto;
import com.example.ativ4.models.Curso;
import com.example.ativ4.models.Professor;
import com.example.ativ4.services.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;

    @GetMapping
    public ResponseEntity<List<Curso>> getAllCursos() {
        return ResponseEntity.ok(cursoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CursoDto> getCursoById(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoService.findById(id);

        if (cursoOptional.isPresent()) {
            Curso curso = cursoOptional.get();
            CursoDto cursoDTO = CursoDto.fromCurso(curso);
            return ResponseEntity.ok(cursoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Curso> createCurso(@RequestBody Curso curso) {
        return new ResponseEntity<>(cursoService.save(curso), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable Long id, @RequestBody Curso curso) {
        if (cursoService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        curso.setId(id);
        return ResponseEntity.ok(cursoService.save(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id) {
        cursoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/detalhe/{id}")
    public ResponseEntity<Optional<Curso>> getCursoDetalhe(@PathVariable Long id) {
        Optional<Curso> cursoOptional = cursoService.findCursoDetalhe(id);
        return ResponseEntity.ok(cursoOptional);
    }

    @PostMapping("/{cursoId}/associar-professor")
    public ResponseEntity<String> associarProfessor(@PathVariable Long cursoId, @RequestBody Long professorId) {
        try {
            cursoService.associarProfessorAoCurso(cursoId, professorId);
            return new ResponseEntity<>("Professor associado ao curso com sucesso.", HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Erro ao associar professor ao curso.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
