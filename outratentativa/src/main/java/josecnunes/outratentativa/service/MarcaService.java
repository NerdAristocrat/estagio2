package josecnunes.outratentativa.service;

import josecnunes.outratentativa.domain.Marca;
import josecnunes.outratentativa.repository.MarcaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MarcaService {
    private static List<Marca> marcas;
    static {
        marcas = new ArrayList<>(List.of(new Marca(1L, "Chevrolet"), new Marca(2L, "VolksWagen")));
    }
    //private final MarcaRepository marcaRepository;
    public List<Marca> listAll(){
        return  marcas;
    }

    public Marca findById(long id){
        return  marcas.stream()
                .filter(Marca -> Marca.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Marca ID not found"));
    }

    public Marca save (Marca marca) {
        marca.setId(ThreadLocalRandom.current().nextLong(3, 10000000));
        marcas.add(marca);
        return marca;
    }

    public void delete (long id){
        marcas.remove(findById(id));
    }

    public void replace(Marca marca){
        delete(marca.getId());
        marcas.add(marca);
    }
}
