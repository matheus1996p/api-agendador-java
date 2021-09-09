package com.viasoft.apijava.controller;

import com.viasoft.apijava.model.ConfAgendamento;
import com.viasoft.apijava.model.RegistrarHorario;
import com.viasoft.apijava.repository.ConfAgendamentoRepository;
import com.viasoft.apijava.repository.RegistrarHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marcarHorario")
public class RegistrarHorarioController {

    @Autowired
    private RegistrarHorarioRepository registrarHorarioRepository;

    @GetMapping
    public ResponseEntity listarTodos(){
        return new ResponseEntity<>(registrarHorarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateCanhotos(RegistrarHorario criteria) {
        try {
            if(criteria.getId() == 0) {
                Integer id = getLastIdCanhotos().getBody();
                criteria.setId((id == null ? 1 : id+1));
                return new ResponseEntity<>(registrarHorarioRepository.save(criteria), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(registrarHorarioRepository.save(criteria), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/get_id")
    public ResponseEntity<Integer> getLastIdCanhotos() {
        return new ResponseEntity<Integer>(registrarHorarioRepository.getLastId(), HttpStatus.OK);
    }
}
