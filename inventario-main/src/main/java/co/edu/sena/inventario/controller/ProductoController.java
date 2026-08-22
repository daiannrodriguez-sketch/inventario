package co.edu.sena.inventario.controller;

import co.edu.sena.inventario.model.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    // Lista mutable con 5 productos e incluyendo la categoría
    private final List<Producto> productos = new ArrayList<>(List.of(
        new Producto(1L, "Papa pastusa", 2500.0, 50, "Tubérculos"),
        new Producto(2L, "Tomate", 3200.0, 30, "Hortalizas"),
        new Producto(3L, "Fresa", 8500.0, 20, "Frutas"),
        new Producto(4L, "Zanahoria", 1800.0, 40, "Hortalizas"),
        new Producto(5L, "Cebolla cabezona", 2100.0, 60, "Hortalizas")
    ));

    // 1. GET /productos (Lista general y búsquedas filtradas)
    @GetMapping
    public List<Producto> listarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria) {

        return productos.stream()
            .filter(p -> (nombre == null || p.getNombre().toLowerCase().contains(nombre.toLowerCase())))
            .filter(p -> (categoria == null || p.getCategoria().equalsIgnoreCase(categoria)))
            .collect(Collectors.toList());
    }

    // 2. GET /productos/precio-total (Operación matemática)
    @GetMapping("/precio-total")
    public Double obtenerPrecioTotal() {
        double total = 0.0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    // 3. GET /productos/{id} (Buscar por id)
    @GetMapping("/{id}")
    public Producto buscarProducto(@PathVariable Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // 4. POST /productos (Crear producto)
    @PostMapping
    public Producto crearProducto(@RequestBody Producto nuevoProducto) {
        productos.add(nuevoProducto);
        return nuevoProducto;
    }

    // 5. PUT /productos/{id} (Actualizar producto)
    @PutMapping("/{id}")
    public Producto actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productoActualizado.setId(id);
                productos.set(i, productoActualizado);
                return productoActualizado;
            }
        }
        return null;
    }

    // 6. DELETE /productos/{id} (Eliminar producto)
    @DeleteMapping("/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productos.remove(i);
                return "Producto con ID " + id + " eliminado correctamente.";
            }
        }
        return "Producto no encontrado.";
    }
}