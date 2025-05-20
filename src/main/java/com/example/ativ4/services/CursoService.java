package com.example.ativ4.services;

import com.example.ativ4.models.Curso;
import com.example.ativ4.models.Professor;
import com.example.ativ4.repositories.CursoRepository;
import com.example.ativ4.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Optional<Curso> findById(Long id) {
        return cursoRepository.findById(id);
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteById(Long id) {
        cursoRepository.deleteById(id);
    }

    public Optional<Curso> findCursoDetalhe(Long cursoId) {
        return cursoRepository.findById(cursoId);
    }

    public Curso getCursoById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Curso não encontrado com o ID: " + id));
    }

    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Professor não encontrado com o ID: " + id));
    }

    public void associarProfessorAoCurso(Long cursoId, Long professorId) {
        Curso curso = getCursoById(cursoId);
        Professor professor = getProfessorById(professorId);

        curso.getProfessores().add(professor);
        cursoRepository.save(curso);
    }
}
