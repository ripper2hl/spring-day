package com.perales.spring.day.covid.service;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Iterables;
import com.google.gson.Gson;
import com.perales.spring.day.covid.model.Modelo;
import com.perales.spring.day.covid.model.dto.DefuncionesMunicipio;
import com.perales.spring.day.covid.model.dto.Municipio;
import com.perales.spring.day.covid.repository.ModeloRepository;
import com.perales.spring.day.covid.util.Parser;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
    
    @Autowired
    private ModeloRepository modeloRepository;
    
    public static Integer countModelos = 0;
    
    public void loadModelo(MultipartFile file){
        log.info("****Inicia el preprocesamiento***");
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
                        countModelos++;
                        return modelo;
                    })
                    .collect( Collectors.toList() );
            log.info("Se preprocesaron: " + countModelos);
            log.info("****Termino el preprocesamiento y comenzamos a persistir la informacion***");
            Iterables.partition(modelos, 20000).forEach(modelosBatch -> {
                em.getTransaction().begin();
                for(Modelo modelo : modelosBatch){
                    try {
                        persistirModelo(modelo, em);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                em.getTransaction().commit();
            });
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            em.close();
            log.info("Termino la carga");
        }
    }
    
    public List<Modelo> search(Modelo modelo) {
        Session session = (Session) em.getDelegate();
        session.getCriteriaBuilder();
        Example example = Example.create(modelo);
        Criteria cq = session.createCriteria(Modelo.class).add(example);
        return cq.list();
    }
    
    public Optional<Modelo> findById(Integer id){
        return modeloRepository.findById(id);
    };
    
    private void persistirModelo(Modelo modelo, EntityManager em) {
        em.persist(modelo);
    }
    
    public Integer getSizeOfProcessingList(){
        return countModelos;
    }
    
    public Integer countModeloByEntidadResidenciaAndFechaDefuncionExists(String entidadResidencia){
        return modeloRepository.countModeloByEntidadResidenciaAndFechaDefuncionIsNotNull(entidadResidencia);
    }
    
    public Integer countModeloByMunicipioResidenciaAndFechaDefuncionIsNotNull(String municipio){
        return modeloRepository.countModeloByMunicipioResidenciaAndFechaDefuncionIsNotNull(municipio);
    }
    
    public List<DefuncionesMunicipio> defuncionesAgrupadasPorMunicipio() {
        ObjectMapper mapper = new ObjectMapper();
        Gson gson = new Gson();
        String jsonMunicipios = null;
        List<Municipio> municipios = new ArrayList<>();
        try {
            File file = new ClassPathResource( "municipios.json").getFile();
            municipios = mapper.readValue(file, new TypeReference<List<Municipio>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<DefuncionesMunicipio> defuncionesMunicipios = new ArrayList<>();
        for(Municipio municipio : municipios){
            DefuncionesMunicipio defunciones = new DefuncionesMunicipio();
            defunciones.setNombre( municipio.getNombre() );
            defunciones.setAbreviacion( municipio.getClave() );
            defunciones.setCantidad(countModeloByMunicipioResidenciaAndFechaDefuncionIsNotNull(municipio.getId()));
            defuncionesMunicipios.add(defunciones);
        }
        return defuncionesMunicipios;
    }
}
