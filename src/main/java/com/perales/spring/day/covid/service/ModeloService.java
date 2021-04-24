package com.perales.spring.day.covid.service;


import com.google.common.collect.Iterables;
import com.perales.spring.day.covid.model.Modelo;
import com.perales.spring.day.covid.util.Parser;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ModeloService {
    
    @PersistenceContext
    private EntityManager em;
    
    @PersistenceUnit
    private EntityManagerFactory emf;
    
    @Autowired
    private Parser parser;
    
    public void loadModelo(MultipartFile file){
        EntityManager em = emf.createEntityManager();
        try (BufferedReader br = new BufferedReader( new InputStreamReader( file.getInputStream() , "UTF-8") )) {
            List<Modelo> modelos = br.lines().parallel()
                    .filter( line -> !line.contains(Parser.TEXT_FOR_DETECT_FIRST_LINE) )
                    .filter( line -> !line.contains(Parser.TEXT_FOR_DETECT_FIELD_DESCRIPTION) )
                    .map( line -> {
                        ByteBuffer byteBuffer = StandardCharsets.UTF_8.encode(line);
                        line = line.replace("\"", "");
                        return line;
                    })
                    .map( line -> Arrays.asList(line.split("\\,") ) )
                    .map( list -> {
                        Modelo modelo = null;
                        try {
                            modelo = parser.convertirListaModelo(list);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        return modelo;
                    })
                    .collect( Collectors.toList() );
            log.info("****Termino el preprocesamiento y comenzamos a persistir la informacion***");
            Iterables.partition(modelos, 10000).forEach(modelosBatch -> {
                em.getTransaction().begin();
                for(Modelo modelo : modelosBatch){
                    try {
                        persistirModelo(modelo, em);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                em.getTransaction().commit();
                em.close();
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void persistirModelo(Modelo modelo, EntityManager em) {
        em.persist(modelo);
    }
}
