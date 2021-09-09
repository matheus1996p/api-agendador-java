package com.viasoft.apijava.repository;

import com.viasoft.apijava.model.ConfAgendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfAgendamentoRepository extends JpaRepository<ConfAgendamento, Integer> {

    @Query(value = "SELECT MAX(ID) FROM CONF_AGENDAMENTO", nativeQuery = true)
    Integer getLastId();
}
