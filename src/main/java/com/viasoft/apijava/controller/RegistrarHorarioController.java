package com.viasoft.apijava.controller;

import com.viasoft.apijava.model.ConfAgendamento;
import com.viasoft.apijava.model.RegistrarHorario;
import com.viasoft.apijava.repository.ConfAgendamentoRepository;
import com.viasoft.apijava.repository.RegistrarHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/marcarHorario")
public class RegistrarHorarioController {

    @Autowired
    private RegistrarHorarioRepository registrarHorarioRepository;

    @GetMapping
    public ResponseEntity listarTodos(@RequestParam Date data){
        return new ResponseEntity<>(registrarHorarioRepository.findAllByData(data), HttpStatus.OK);
    }

    @GetMapping("/minhaAgenda")
    public ResponseEntity listarData(){
        return new ResponseEntity<>(registrarHorarioRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE,
            produces = {MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity inserirHorario(RegistrarHorario criteria) {
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
