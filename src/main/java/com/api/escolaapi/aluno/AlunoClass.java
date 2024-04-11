package com.api.escolaapi.aluno;

import com.api.escolaapi.Enums.CursoEnum;
import com.api.escolaapi.Enums.SerieEnum;
import com.api.escolaapi.aluno.DTOs.AlunoDTO;
import com.api.escolaapi.aluno.DTOs.AlunoDTOAtualizar;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDate;

// Anotações da classe Aluno
// Essas anotações veem da dependência JPA
@Table(name = "Aluno")
@Entity(name = "alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlunoClass {

    // Construtor para cadastrar um novo aluno de forma limpa
    public AlunoClass(AlunoDTO dados) {
        this.primeiro_nome = dados.primeiro_nome();
        this.sobrenome = dados.sobrenome();
        this.data_nascimento = dados.data_nascimento();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.curso = dados.curso();
        this.serie = dados.serie();
        this.ativo = true;
    }

    // ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    // Strings
    private String primeiro_nome, sobrenome, email, telefone;
    // LocalDate
    private LocalDate data_nascimento;
    // Enum
    @Enumerated(EnumType.STRING)
    private SerieEnum serie;
    // Enum
    @Enumerated(EnumType.STRING)
    private CursoEnum curso;
    // Booleanos
    private boolean ativo;

    public void atualizarInformacoes(@Valid AlunoDTOAtualizar dados){
        if (dados.serie() != null){
            this.serie = dados.serie();
        } if (dados.telefone() != null){
            this.telefone = dados.telefone();
        } if (dados.email() != null){
            this.email = dados.email();
        }
    }

    public void desativar() {
        this.ativo = false;
    }

    public void ativar(){
        this.ativo = true;
    }
}
