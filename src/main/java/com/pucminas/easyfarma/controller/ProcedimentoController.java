package com.pucminas.easyfarma.controller;

import com.pucminas.easyfarma.domain.Procedimento;
import com.pucminas.easyfarma.service.ProcedimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/procedimento")
public class ProcedimentoController {
    
    @Autowired
    ProcedimentoService service;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        Procedimento obj = service.find(id);
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<?> findAll() {
        List<Procedimento> obj = service.findAll();
        return ResponseEntity.ok().body(obj);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> insert(@RequestBody Procedimento obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Procedimento obj, @PathVariable Integer id) {
        obj.setId(id);
        service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
