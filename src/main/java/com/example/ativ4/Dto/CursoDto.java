package com.example.ativ4.Dto;

import com.example.ativ4.models.Curso;
import com.example.ativ4.models.Professor;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CursoDto {
    private Long id;
    private String nome;
    private String descricao;
    private List<ProfessorDto> professores;

    public CursoDto(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.professores = new ArrayList<>();
    }


    public static CursoDto fromCurso(Curso curso) {
        CursoDto cursoDTO = new CursoDto();
        cursoDTO.setId(curso.getId());
        cursoDTO.setNome(curso.getNome());
        cursoDTO.setDescricao(curso.getDescricao());

        List<ProfessorDto> professoresDTO = new ArrayList<>();
        if (curso.getProfessores() != null) {
            for (Professor professor : curso.getProfessores()) {
                professoresDTO.add(ProfessorDto.fromProfessor(professor));
            }
        }
        cursoDTO.setProfessores(professoresDTO);
        return cursoDTO;
    }
}