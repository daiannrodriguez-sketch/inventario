package co.edu.sena.inventario.controller;

import co.edu.sena.inventario.model.Productor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productores")
public class ProductorController {

    private final List<Productor> productores = List.of(
        new Productor(1L, "Asociación El Salitre", "Facatativá"),
        new Productor(2L, "Finca La Esperanza", "Madrid"),
        new Productor(3L, "Cultivos Sabana", "Bogota")
    );

    @GetMapping
    public List<Productor> listarProductores() {
        return productores;
    }

    @GetMapping("/{id}")
    public Productor buscarProductor(@PathVariable Long id) {
        for (Productor productor : productores) {
            if (productor.getId().equals(id)) {
                return productor;
            }
        }
        return null;
    }
}