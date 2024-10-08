package com.api.escolaapi.repositorys;

import com.api.escolaapi.aluno.AlunoClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AlunoRepository extends JpaRepository<AlunoClass, Integer> {
    List<AlunoClass> findAllByAtivoTrue();
    List<AlunoClass> findAllByAtivoFalse();
    List<AlunoClass> findByEmail(String email);
    List<AlunoClass> findByTelefone(String telefone);
}
