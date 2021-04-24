package com.perales.spring.day.covid.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@Builder
public class Modelo {
    //FECHA_ACTUALIZACION	ID_REGISTRO	ORIGEN	SECTOR	ENTIDAD_UM	SEXO	ENTIDAD_NAC	ENTIDAD_RES	MUNICIPIO_RES
    //TIPO_PACIENTE	FECHA_INGRESO	FECHA_SINTOMAS	FECHA_DEF	INTUBADO	NEUMONIA	EDAD	NACIONALIDAD
    //EMBARAZO	HABLA_LENGUA_INDIG	INDIGENA	DIABETES	EPOC	ASMA	INMUSUPR	HIPERTENSION	OTRA_COM
    @Id
    private Integer id;
    private Date fechaActualizacion;
    private String idRegistro;
    private Integer origen;
    private String sector;
    private String entidadUm;
    private Integer sexo;
    private String entidadNacimiento;
    private String entidadResidencia;
    private String municipioResidencia;
    private String tipoPaciente;
    private Date fechaIngreso;
    private Date fechaSintomas;
    private Date fechaDefuncion;
    private Integer intubado;
    private Integer neumonia;
    private Integer edad;
    private String nacionalidad;
    private Integer embarazo;
    private Integer hablaLenguaIndigena;
    private Integer indigena;
    private Integer diabetes;
    private Integer epoc;
    private Integer asma;
    private Integer inmunosuprimido;
    private Integer hipertencion;
    private Integer otraComplicacion;
    private Integer cardiovascular;
    //CARDIOVASCULAR	OBESIDAD	RENAL_CRONICA	TABAQUISMO	OTRO_CASO	TOMA_MUESTRA_LAB	RESULTADO_LAB
    //TOMA_MUESTRA_ANTIGENO	RESULTADO_ANTIGENO	CLASIFICACION_FINAL	MIGRANTE	PAIS_NACIONALIDAD	PAIS_ORIGEN	UCI
    private Integer otroCaso;
    private Integer obesidad;
    private Integer renalCronica;
    private Integer tabaquismo;
    private Integer tomaMuestraLaboratorio;
    private Integer resultadoLaboratorio;
    private Integer tomaMuestraAntigeno;
    private Integer resultadoAntigeno;
    private Integer clasificacionFinal;
    private Integer migrante;
    private String paisNacionalidad;
    private String paisOrigen;
    private Integer Uci;
}
