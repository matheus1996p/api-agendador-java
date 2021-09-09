package com.viasoft.apijava.controller;

import com.viasoft.apijava.model.ConfAgendamento;
import com.viasoft.apijava.repository.ConfAgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/confAgendamento")
public class ConfAgendamentoController {

    @Autowired
    private ConfAgendamentoRepository confAgendamentoRepository;

    @GetMapping
    public ResponseEntity listarTodos(){
        return new ResponseEntity<>(confAgendamentoRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity updateHorario(ConfAgendamento criteria) {
        try {
            if(criteria.getId() == 0) {
                Integer id = getLastIdCanhotos().getBody();
                criteria.setId((id == null ? 1 : id));
                return new ResponseEntity<>(confAgendamentoRepository.save(criteria), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(confAgendamentoRepository.save(criteria), HttpStatus.CREATED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("/get_id")
    public ResponseEntity<Integer> getLastIdCanhotos() {
        return new ResponseEntity<Integer>(confAgendamentoRepository.getLastId(), HttpStatus.OK);
    }
}
