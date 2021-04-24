package com.perales.spring.day.covid.service;


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
            List<Modelo> colonias = br.lines().parallel()
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
                        if(modelo != null){
                            log.info( modelo.toString() );
                        }else{
                            log.error("No fue posible guardar el siguiente registro: " + list);
                        }
                        return modelo;
                    }).filter( modelo -> modelo != null )
                    .collect( Collectors.toList() );
        
//            Iterables.partition(colonias, 1000).forEach( coloniasBatch -> {
//                em.getTransaction().begin();
//                for(Modelo modelo : modelosBatch){
//                    try {
//                        revisarModelo(modelo, em);
//                    }catch (Exception e){
//                        e.printStackTrace();
//                    }
//                }
//                em.getTransaction().commit();
//                em.close();
//            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
