package com.viasoft.apijava.repository;

import com.viasoft.apijava.model.RegistrarHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface RegistrarHorarioRepository extends JpaRepository<RegistrarHorario, Integer>  {

    @Query(value = "SELECT MAX(ID) FROM REGISTRAR_HORARIO", nativeQuery = true)
    Integer getLastId();

    List<RegistrarHorario> findAllByData(Date data);

    List<RegistrarHorario> findByCpfAndAndData(String cpf, Date data);

    @Transactional
    @Modifying
    void deleteByHorarioAndData(String horario, Date data);

    @Transactional
    @Modifying
    @Query(value = "UPDATE REGISTRAR_HORARIO SET STATUS = :status WHERE HORARIO = :horario and DATA = :data", nativeQuery = true)
    void atualizaHorario(@Param("status") Integer status, @Param("horario") String horario, @Param("data") Date data);
}
