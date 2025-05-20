package com.example.ativ4.Dto;

import com.example.ativ4.models.Professor;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfessorDto {
    private Long id;
    private String nome;

    public static ProfessorDto fromProfessor(Professor professor) {
        ProfessorDto professorDTO = new ProfessorDto();
        professorDTO.setId(professor.getId());
        professorDTO.setNome(professor.getNome());
        return professorDTO;
    }
}
