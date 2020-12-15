package br.com.qualify.rabbit.service;

import br.com.qualify.rabbit.config.LogAMQPConfig;
import br.com.qualify.rabbit.config.ProcessoAMQPConfig;
import br.com.qualify.rabbit.entity.ErrorLog;
import br.com.qualify.rabbit.entity.Processo;
import br.com.qualify.rabbit.repository.ProcessoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProcessoService {

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;


    public Processo save(Processo processo) {
        try {
        Processo processoSaved = processoRepository.save(processo);
            sendProcessosToRabbit(processoSaved);
            return processoSaved;

        } catch (Exception e) {
            sendLogToRabbit(new ErrorLog("DEV", "SRV_01", "error", e.getMessage()));
            throw new DuplicateKeyException("Duplicado");
        }

    }

    public void sendProcessosToRabbit(Processo processo) {
        try {
            String json = new ObjectMapper().writeValueAsString(processo);
            rabbitTemplate.convertAndSend(ProcessoAMQPConfig.EXCHANGE_NAME, "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendLogToRabbit(ErrorLog error) {
        try {
            String json = new ObjectMapper().writeValueAsString(error);
            rabbitTemplate.convertAndSend(LogAMQPConfig.EXCHANGE_NAME, "", json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Cacheable(cacheNames = "Processo", key="#root.method.name")
    public List<Processo> findAll() {
        return processoRepository.findAll();
    }

    @Cacheable(cacheNames = "Processo", key="#id")
    public Processo findbyIdentifier(final  Long id) {

        return processoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Id not found: " + id));
    }

    @CacheEvict(cacheNames = "Processo", allEntries = true)
    public Processo create(final Processo processo) {
        return processoRepository.save(processo);
    }

    @CachePut(cacheNames = Processo.CACHE_NAME, key="#processo.getId()")
    public Processo update(final Processo processo) {
        if(processo.getId() == null) {
            throw new EntityNotFoundException("Id is empty");
        }

        return processoRepository.save(processo);
    }

    @CacheEvict(cacheNames = Processo.CACHE_NAME, key="#id")
    public void delete(final Long id) {
        if(id == null) {
            throw new EntityNotFoundException("id is empty");
        }

        processoRepository.deleteById(id);
    }
}
