package com.api.escolaapi.professor;

import com.api.escolaapi.professor.DTOs.ProfessorDTO;
import com.api.escolaapi.professor.DTOs.ProfessorDTOGet;
import com.api.escolaapi.professor.DTOs.ProfessorDTOPut;
import com.api.escolaapi.repositorys.ProfessorRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/professores")
public class ProfessorController {
    @Autowired
    private ProfessorRepository repository;

    @PostMapping
    @ResponseBody
    @Transactional
    public RedirectView cadastrar(ProfessorDTO dados) {
        repository.save(new ProfessorClass(dados));
        return new RedirectView("/cadastrar-professor");
    }

    @GetMapping
    public List<ProfessorDTOGet> visualizar() {
        return repository.findAllByAtivoTrue().stream().map(ProfessorDTOGet::new).toList();
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid ProfessorDTOPut dados) {
        var professor = repository.getReferenceById(dados.id());
        professor.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deletar(@PathVariable int id){
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("inativar/{id}")
    @Transactional
    public void inativar(@PathVariable int id){
        var professor = repository.getReferenceById(id);
        professor.desativar();
    }

    // Método HTTP PUT
    @PutMapping("ativar/{id}")
    @Transactional
    public void ativar(@PathVariable int id){
        var professor = repository.getReferenceById(id);
        professor.ativar();
    }

}
