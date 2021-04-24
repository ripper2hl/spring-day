package com.perales.spring.day.covid.controller;

import com.perales.spring.day.covid.service.ModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("modelo/")
public class ModeloController {
    
    @Autowired
    private ModeloService modeloService;
    
    @PostMapping(produces = "application/json; charset=UTF-8", consumes = "multipart/form-data;charset=UTF-8")
    public void uploadFile(@RequestPart("file") MultipartFile file){
        modeloService.loadModelo(file);
    }
    
}
