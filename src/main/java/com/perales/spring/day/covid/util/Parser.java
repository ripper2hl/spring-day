package com.perales.spring.day.covid.util;

import com.perales.spring.day.covid.model.Modelo;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class Parser {
    private static final int FECHA_ACTUALIZACION_POSICION = 0;
    private static final int ID_REGISTRO = 1;
    private static final int ORIGEN_POSICION = 2;
    private static final int SECTOR_POSICION = 3;
    private static final int ENTIDAD_UM_POSICION = 4;
    private static final int SEXO_POSICION = 5;
    private static final int ENTIDAD_NAC_POSICION = 6;
    private static final int ENTIDAD_RES_POSICION = 7;
    private static final int MUNICIPIO_RES_POSICION = 8;
    private static final int TIPO_PACIENTE = 9;
    private static final int FECHA_INGRESO = 10;
    private static final int FECHA_SINTOMAS = 11;
    private static final int FECHA_DEF = 12;
    private static final int INTUBADO = 13;
    private static final int NEUMONIA = 14;
    private static final int EDAD = 15;
    private static final int NACIONALIDAD = 16;
    private static final int EMBARAZO = 17;
    private static final int HABLA_LENGUA_INDIG = 18;
    private static final int DIABETES = 19;
    private static final int EPOC = 20;
    private static final int ASMA = 21;
    private static final int INMUSUPR = 22;
    private static final int HIPERTENSION = 23;
    private static final int OTRA_COM = 24;
    private static final int OBESIDAD = 25;
    private static final int CARDIOVASCULAR = 26;
    private static final int RENAL_CRONICA = 27;
    private static final int TABAQUISMO = 28;
    private static final int OTRO_CASO = 29;
    private static final int TOMA_MUESTRA_LAB = 30;
    private static final int RESULTADO_LAB = 31;
    private static final int TOMA_MUESTRA_ANTIGENO = 32;
    private static final int RESULTADO_ANTIGENO = 33;
    private static final int CLASIFICACION_FINAL = 34;
    private static final int MIGRANTE = 35;
    private static final int PAIS_NACIONALIDAD = 36;
    private static final int PAIS_ORIGEN = 37;
    private static final int UCI = 38;
    
    public static final int POSICIONES_MAXIMAS_SEPARADOR = 39;
    public static final String TEXT_FOR_DETECT_FIRST_LINE = "FECHA_ACTUALIZACION";
    public static final String TEXT_FOR_DETECT_FIELD_DESCRIPTION = "FECHA_ACTUALIZACION";
    
    public Modelo convertirListaModelo(List<String> lista) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        
        Modelo modelo = Modelo.builder()
                .fechaActualizacion(  simpleDateFormat.parse(lista.get(FECHA_ACTUALIZACION_POSICION) ))
                .idRegistro( lista.get( ID_REGISTRO  ) )
                .origen( Integer.valueOf( lista.get( ORIGEN_POSICION ) ) )
                .sector( lista.get( SECTOR_POSICION  ) )
                .entidadUm(lista.get( ENTIDAD_UM_POSICION ))
                .sexo( Integer.valueOf( lista.get( SEXO_POSICION ) ))
                .entidadNacimiento(lista.get( ENTIDAD_NAC_POSICION ))
                .entidadResidencia(lista.get( ENTIDAD_RES_POSICION ))
                .municipioResidencia(lista.get( MUNICIPIO_RES_POSICION ))
                .tipoPaciente(lista.get( TIPO_PACIENTE ))
                .fechaIngreso( simpleDateFormat.parse( lista.get( FECHA_INGRESO ) ) )
                .fechaSintomas( simpleDateFormat.parse( lista.get( FECHA_SINTOMAS ) ) )
                .fechaDefuncion( simpleDateFormat.parse( lista.get( FECHA_DEF ) ) )
                .intubado( Integer.valueOf( lista.get( INTUBADO ) ))
                .neumonia(Integer.valueOf(  lista.get( NEUMONIA )) )
                .edad( Integer.valueOf( lista.get( EDAD )))
                .nacionalidad(lista.get( NACIONALIDAD ))
                .embarazo(Integer.valueOf( lista.get( EMBARAZO )))
                .hablaLenguaIndigena(Integer.valueOf(lista.get( HABLA_LENGUA_INDIG )))
                .diabetes(Integer.valueOf(lista.get( DIABETES )))
                .epoc(Integer.valueOf(lista.get(EPOC)))
                .asma(Integer.valueOf(lista.get( ASMA )))
                .inmunosuprimido(Integer.valueOf(lista.get( INMUSUPR )))
                .hipertencion(Integer.valueOf(lista.get( HIPERTENSION )))
                .otraComplicacion(Integer.valueOf(lista.get( OTRA_COM )))
                .cardiovascular(Integer.valueOf(lista.get( CARDIOVASCULAR )))
                .obesidad(Integer.valueOf(lista.get( OBESIDAD )))
                .renalCronica(Integer.valueOf(lista.get( RENAL_CRONICA )))
                .tabaquismo(Integer.valueOf(lista.get( TABAQUISMO )))
                .tomaMuestraLaboratorio(Integer.valueOf(lista.get( TOMA_MUESTRA_LAB )))
                .resultadoLaboratorio(Integer.valueOf(lista.get( RESULTADO_LAB )))
                .tomaMuestraAntigeno(Integer.valueOf(lista.get( TOMA_MUESTRA_ANTIGENO )))
                .resultadoAntigeno(Integer.valueOf(lista.get( RESULTADO_ANTIGENO )))
                .clasificacionFinal(Integer.valueOf(lista.get(CLASIFICACION_FINAL)))
                .migrante(Integer.valueOf(lista.get( MIGRANTE )))
                .paisNacionalidad(lista.get( PAIS_NACIONALIDAD ))
                .paisOrigen(lista.get( PAIS_ORIGEN )).build();
        return modelo;
    };
}
