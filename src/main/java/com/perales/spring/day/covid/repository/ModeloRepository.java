package com.perales.spring.day.covid.repository;

import com.perales.spring.day.covid.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Repository
@Transactional(readOnly = true)
public interface ModeloRepository extends JpaRepository<Modelo, Integer> {
    
    Integer countModeloByEntidadResidenciaAndFechaDefuncionIsNotNullAndFechaSintomasGreaterThanEqualAndFechaSintomasLessThanEqual(String entidad, Date start , Date end);
    
    Integer countModeloByMunicipioResidenciaAndFechaDefuncionIsNotNullAndFechaSintomasGreaterThanEqualAndFechaSintomasLessThanEqual(String municipio, Date start , Date end);
    
    Integer countModeloByEntidadResidenciaAndClasificacionFinalAndSexoAndFechaSintomasGreaterThanEqualAndFechaSintomasLessThanEqual(String entidad, Integer clasificacionFinal, Integer sexo, Date start , Date end);
}
