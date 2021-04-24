package com.perales.spring.day.covid.controller;

import com.perales.spring.day.covid.model.Modelo;
import com.perales.spring.day.covid.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("modelo/")
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;
    
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
    
    @GetMapping("/processing")
    public Integer getSizeOfProcessingList(){
        return modeloService.getSizeOfProcessingList();
    }
}