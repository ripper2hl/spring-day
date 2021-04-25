package com.perales.spring.day.covid.controller;

import com.perales.spring.day.covid.model.Modelo;
import com.perales.spring.day.covid.model.dto.DefuncionesMunicipio;
import com.perales.spring.day.covid.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping("modelo/")
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;
    
    private static final String FECHA_DEFAULT_INICIO = "2020-04-20";
    private static final String FECHA_DEFAULT_FIN = "2020-04-25";
    
    @PostMapping(produces = "application/json; charset=UTF-8", consumes = "multipart/form-data;charset=UTF-8")
    public void uploadFile(@RequestPart("file") MultipartFile file){
        modeloService.loadModelo(file);
    }
    
    @GetMapping
    public List<Modelo> search(Modelo modelo ){
        return modeloService.search(modelo);
    }
    
    @GetMapping("/{id}")
    public Optional<Modelo> findById(Integer id ){
        return modeloService.findById(id);
    }
    
    @GetMapping("/defunciones/entidad/{entidad}")
    public Integer defuncionesAgrupadasPorEntidad(@PathVariable String entidad,
                                                  @RequestParam(defaultValue = FECHA_DEFAULT_INICIO) String fechaSintomasInicio,
                                                  @RequestParam(defaultValue = FECHA_DEFAULT_FIN) String fechaSintomasFin ) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return modeloService.countModeloByEntidadResidenciaAndFechaDefuncionExists(entidad,
                simpleDateFormat.parse(fechaSintomasInicio),
                simpleDateFormat.parse(fechaSintomasFin) );
    }
    
    @GetMapping("/defunciones/municipio/{municipio}")
    public Integer defuncionesAgrupadasPorMunicipio(@PathVariable String municipio , @RequestParam(defaultValue = FECHA_DEFAULT_INICIO) String fechaSintomasInicio,
                                                    @RequestParam(defaultValue = FECHA_DEFAULT_FIN) String fechaSintomasFin) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return modeloService.countModeloByMunicipioResidenciaAndFechaDefuncionIsNotNull(municipio,simpleDateFormat.parse(fechaSintomasInicio),
                simpleDateFormat.parse(fechaSintomasFin));
    }
    
    @GetMapping("/defunciones/municipio/")
    public List<DefuncionesMunicipio> defuncionesAgrupadasPorMunicipio( @RequestParam(defaultValue = FECHA_DEFAULT_INICIO) String fechaSintomasInicio,
                                                                        @RequestParam(defaultValue = FECHA_DEFAULT_FIN) String fechaSintomasFin) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return modeloService.defuncionesAgrupadasPorMunicipio(simpleDateFormat.parse(fechaSintomasInicio),
                simpleDateFormat.parse(fechaSintomasFin));
    }
    
    @GetMapping("/entidad/{entidad}")
    public Integer casosPorEntidadPorClasificacionFinalPorSexo(@PathVariable String entidad, Integer clasificacionFinal,
                                                               Integer sexo, @RequestParam(defaultValue = FECHA_DEFAULT_INICIO) String fechaSintomasInicio,
                                                               @RequestParam(defaultValue = FECHA_DEFAULT_FIN) String fechaSintomasFin) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return modeloService.casosPorEntidadPorClasificacionFinalPorSexo(entidad, clasificacionFinal, sexo, simpleDateFormat.parse(fechaSintomasInicio),
                simpleDateFormat.parse(fechaSintomasFin));
    }
    
    @GetMapping("/processing")
    public Integer getSizeOfProcessingList(){
        return modeloService.getSizeOfProcessingList();
    }
}