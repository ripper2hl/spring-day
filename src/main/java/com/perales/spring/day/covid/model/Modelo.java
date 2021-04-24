package com.perales.spring.day.covid.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "modelo")
public class Modelo implements Serializable {
    //FECHA_ACTUALIZACION	ID_REGISTRO	ORIGEN	SECTOR	ENTIDAD_UM	SEXO	ENTIDAD_NAC	ENTIDAD_RES	MUNICIPIO_RES
    //TIPO_PACIENTE	FECHA_INGRESO	FECHA_SINTOMAS	FECHA_DEF	INTUBADO	NEUMONIA	EDAD	NACIONALIDAD
    //EMBARAZO	HABLA_LENGUA_INDIG	INDIGENA	DIABETES	EPOC	ASMA	INMUSUPR	HIPERTENSION	OTRA_COM
    private static final long serialVersionUID = 7622719610604643048L;
    
    @Id
    @GeneratedValue(
            generator = "sequence_modelo",
            strategy = GenerationType.SEQUENCE
    )
    @SequenceGenerator(
            name = "sequence_modelo",
            allocationSize = 10
    )
    private Integer id;
    
    @Column
    private Date fechaActualizacion;
    
    @Column
    private String idRegistro;
    
    @Column
    private Integer origen;
    
    @Column
    private String sector;
    
    @Column
    private String entidadUm;
    
    @Column
    private Integer sexo;
    
    @Column
    private String entidadNacimiento;
    
    @Column
    private String entidadResidencia;
    
    @Column
    private String municipioResidencia;
    
    @Column
    private String tipoPaciente;
    
    @Column
    private Date fechaIngreso;
    
    @Column
    private Date fechaSintomas;
    
    @Column
    private Date fechaDefuncion;
    
    @Column
    private Integer intubado;
    
    @Column
    private Integer neumonia;
    
    @Column
    private Integer edad;
    
    @Column
    private String nacionalidad;
    
    @Column
    private Integer embarazo;
    
    @Column
    private Integer hablaLenguaIndigena;
    
    @Column
    private Integer indigena;
    
    @Column
    private Integer diabetes;
    
    @Column
    private Integer epoc;
    @Column
    private Integer asma;
    
    @Column
    private Integer inmunosuprimido;
    
    @Column
    private Integer hipertencion;
    
    @Column
    private Integer otraComplicacion;
    
    @Column
    private Integer cardiovascular;
    //CARDIOVASCULAR	OBESIDAD	RENAL_CRONICA	TABAQUISMO	OTRO_CASO	TOMA_MUESTRA_LAB	RESULTADO_LAB
    //TOMA_MUESTRA_ANTIGENO	RESULTADO_ANTIGENO	CLASIFICACION_FINAL	MIGRANTE	PAIS_NACIONALIDAD	PAIS_ORIGEN	UCI
    
    @Column
    private Integer otroCaso;
    
    @Column
    private Integer obesidad;
    
    @Column
    private Integer renalCronica;
    
    @Column
    private Integer tabaquismo;
    
    @Column
    private Integer tomaMuestraLaboratorio;
    
    @Column
    private Integer resultadoLaboratorio;
    
    @Column
    private Integer tomaMuestraAntigeno;
    
    @Column
    private Integer resultadoAntigeno;
    
    @Column
    private Integer clasificacionFinal;
    
    @Column
    private Integer migrante;
    
    @Column
    private String paisNacionalidad;
    
    @Column
    private String paisOrigen;
    
    @Column
    private Integer uci;
}
