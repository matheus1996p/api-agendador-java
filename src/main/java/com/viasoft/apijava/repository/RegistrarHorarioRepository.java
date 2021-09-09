package com.viasoft.apijava.repository;

import com.viasoft.apijava.model.RegistrarHorario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrarHorarioRepository extends JpaRepository<RegistrarHorario, Integer>  {

    @Query(value = "SELECT MAX(ID) FROM REGISTRAR_HORARIO", nativeQuery = true)
    Integer getLastId();
}
