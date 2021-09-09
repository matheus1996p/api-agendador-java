package com.viasoft.apijava.controller;

import com.viasoft.apijava.repository.ProdutoRepository;
import com.viasoft.apijava.repository.RegistrarHorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/listaProdutos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity listarTodos(){
        return new ResponseEntity<>(produtoRepository.getProdutos(), HttpStatus.OK);
    }

}
