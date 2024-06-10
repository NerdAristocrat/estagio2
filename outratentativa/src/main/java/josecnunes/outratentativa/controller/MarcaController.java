package josecnunes.outratentativa.controller;

import josecnunes.outratentativa.service.MarcaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import josecnunes.outratentativa.domain.Marca;
import josecnunes.outratentativa.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("marcas")
@Log4j2
@RequiredArgsConstructor
public class MarcaController {
    @Autowired
    private final DateUtil dateUtil;
    private final MarcaService marcaService;

    //localhost:8080/Marca/list

    @GetMapping(path = "list")
    public ResponseEntity<List<Marca>> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(marcaService.listAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Marca> findById(@PathVariable long id){
        return ResponseEntity.ok(marcaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Marca> save(@RequestBody Marca marca){
        return new ResponseEntity<>(marcaService.save(marca), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        marcaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Marca marca){
        marcaService.replace(marca);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
