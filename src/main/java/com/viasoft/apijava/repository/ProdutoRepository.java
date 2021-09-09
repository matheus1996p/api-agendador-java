package com.viasoft.apijava.repository;

import com.viasoft.apijava.model.Produto;
import com.viasoft.apijava.model.RegistrarHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Query(value = "SELECT ITEMAGRO.DESCRICAO, ITEMAGRO.ITEM FROM ITEMAGRO", nativeQuery = true)
    List<Produto> getProdutos();

}
