package br.com.qualify.rabbit.controller;

import br.com.qualify.rabbit.entity.Processo;
import br.com.qualify.rabbit.service.ProcessoService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@CrossOrigin
@RequestMapping("/api/processos")
public class ProcessoController {

    @Autowired
    ProcessoService processoService;

    private static final Logger LOG = Logger.getLogger(ProcessoController.class.getName());

    @PostMapping
    public ResponseEntity<Processo> save(@RequestBody Processo processo) {

        try {
            Processo processoSaved = processoService.save(processo);
            System.out.println(String.format("Processo saved: %s", processoSaved.toString()));
            LOG.log(Level.INFO, processoSaved.toString());

            return ResponseEntity.ok(processoSaved);

        } catch (DuplicateKeyException e) {
            LOG.warning(e.toString());
            return (ResponseEntity<Processo>) ResponseEntity.badRequest();
        }


    }
}
