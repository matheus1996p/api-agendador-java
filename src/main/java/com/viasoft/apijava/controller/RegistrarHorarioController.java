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
import java.util.List;

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
    public ResponseEntity listarData(@RequestParam Date data, @RequestParam String cpf){
        return new ResponseEntity<>(registrarHorarioRepository.findByCpfAndAndData(cpf, data), HttpStatus.OK);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_ATOM_XML_VALUE})
    public ResponseEntity inserirHorario(@RequestBody List<RegistrarHorario> criteria) {
        try {
            return new ResponseEntity<>(registrarHorarioRepository.saveAll(criteria), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping("/atualiza/{status}/{horario}/{data}")
    public ResponseEntity AtualizarStatus(@PathVariable Integer status, @PathVariable String horario, @PathVariable Date data) {
        registrarHorarioRepository.atualizaHorario(status, horario, data);
        return new ResponseEntity<Integer>(HttpStatus.OK);
    }

    @GetMapping("/get_id")
    public ResponseEntity<Integer> getLastIdHorarios() {
        return new ResponseEntity<Integer>(registrarHorarioRepository.getLastId(), HttpStatus.OK);
    }

    @GetMapping("/delete/{horario}/{data}")
    void deleteHorario(@PathVariable String horario, @PathVariable Date data) {
        registrarHorarioRepository.deleteByHorarioAndData(horario, data);
    }

}
