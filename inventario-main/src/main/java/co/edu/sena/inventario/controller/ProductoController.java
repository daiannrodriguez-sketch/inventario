package co.edu.sena.inventario.controller;

import co.edu.sena.inventario.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>(List.of(
        new Producto(1L, "Papa pastusa", 2500.0, 50, "Tubérculos"),
        new Producto(2L, "Tomate", 3200.0, 30, "Hortalizas"),
        new Producto(3L, "Fresa", 8500.0, 20, "Frutas"),
        new Producto(4L, "Zanahoria", 1800.0, 40, "Hortalizas"),
        new Producto(5L, "Cebolla cabezona", 2100.0, 60, "Hortalizas")
    ));

    // 1. GET /productos (Lista general y filtro por Query Params: ?nombre=Tomate)
    @GetMapping
    public List<Producto> listarProductos(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Double precioMaximo) {

        return productos.stream()
            .filter(p -> (nombre == null || p.getNombre().toLowerCase().contains(nombre.toLowerCase())))
            .filter(p -> (categoria == null || p.getCategoria().equalsIgnoreCase(categoria)))
            .filter(p -> (precioMaximo == null || p.getPrecio() <= precioMaximo))
            .collect(Collectors.toList());
    }

    // 2. GET /productos/precio-total
    @GetMapping("/precio-total")
    public Double obtenerPrecioTotal() {
        double total = 0.0;
        for (Producto p : productos) {
            total += p.getPrecio();
        }
        return total;
    }

    // 3. GET /productos/stock-bajo (Reto 8)
    @GetMapping("/stock-bajo")
    public List<Producto> obtenerProductosStockBajo() {
        return productos.stream()
            .filter(p -> p.getCantidad() < 10)
            .collect(Collectors.toList());
    }

    // 4. GET /productos/resumen (Reto 9)
    @GetMapping("/resumen")
    public Map<String, Object> obtenerResumenInventario() {
        if (productos.isEmpty()) {
            return Map.of(
                "totalProductos", 0,
                "productosStockBajo", 0,
                "productoMasCostoso", "N/A",
                "productoMasEconomico", "N/A"
            );
        }

        int totalProductos = productos.size();
        long productosStockBajo = productos.stream().filter(p -> p.getCantidad() < 10).count();
        Producto masCostoso = productos.stream().max(Comparator.comparing(Producto::getPrecio)).orElse(null);
        Producto masEconomico = productos.stream().min(Comparator.comparing(Producto::getPrecio)).orElse(null);

        Map<String, Object> resumen = new HashMap<>();
        resumen.put("totalProductos", totalProductos);
        resumen.put("productosStockBajo", productosStockBajo);
        resumen.put("productoMasCostoso", masCostoso != null ? masCostoso.getNombre() : "N/A");
        resumen.put("productoMasEconomico", masEconomico != null ? masEconomico.getNombre() : "N/A");

        return resumen;
    }


    // Reto final: GET /productos/filtrar?categoria=Hortalizas&precioMaximo=5000
    @GetMapping("/filtrar")
    public List<Producto> filtrarPorCategoriaYPrecio(
            @RequestParam String categoria,
            @RequestParam Double precioMaximo) {

        return productos.stream()
            .filter(p -> p.getCategoria().equalsIgnoreCase(categoria))
            .filter(p -> p.getPrecio() <= precioMaximo)
            .collect(Collectors.toList());
    }

    // 5. GET /productos/{id} (SIEMPRE AL FINAL DE LOS GET)
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarProductoPorId(@PathVariable Long id) {
        for (Producto p : productos) {
            if (p.getId().equals(id)) {
                return ResponseEntity.ok(p);
            }
        }
        return ResponseEntity.status(404).body("Producto no encontrado");
    }

    // 6. POST /productos
    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto nuevoProducto) {
        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().isBlank() ||
            nuevoProducto.getPrecio() == null || nuevoProducto.getPrecio() < 0 ||
            nuevoProducto.getCantidad() == null || nuevoProducto.getCantidad() < 0) {
            
            return ResponseEntity.badRequest().body("Datos del producto inválidos.");
        }

        productos.add(nuevoProducto);
        return ResponseEntity.status(201).body(nuevoProducto);
    }

    // 7. PUT /productos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto productoActualizado) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productoActualizado.setId(id);
                productos.set(i, productoActualizado);
                return ResponseEntity.ok(productoActualizado);
            }
        }
        return ResponseEntity.status(404).body("Producto no encontrado.");
    }

    // 8. DELETE /productos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getId().equals(id)) {
                productos.remove(i);
                return ResponseEntity.ok("Producto con ID " + id + " eliminado correctamente.");
            }
        }
        return ResponseEntity.status(404).body("Producto no encontrado.");
    }
}