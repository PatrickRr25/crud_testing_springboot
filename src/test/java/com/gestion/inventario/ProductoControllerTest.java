package com.gestion.inventario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.gestion.inventario.controller.ProductoController;
import com.gestion.inventario.modelo.Producto;
import com.gestion.inventario.servicio.ProductoServicio;

public class ProductoControllerTest {

    @Mock
    private ProductoServicio productoServicioMock;

    @InjectMocks
    private ProductoController productoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void listarProductos() {
        
        List<Producto> listaProductos = new ArrayList<>();
        Producto producto1 = new Producto();
        producto1.setId(1);
        producto1.setNombre("Producto 1");
        producto1.setPrecio(10);
        Producto producto2 = new Producto();
        producto2.setId(2);
        producto2.setNombre("Producto 2");
        producto2.setPrecio(20);
        listaProductos.add(producto1);
        listaProductos.add(producto2);
        when(productoServicioMock.listarProducto()).thenReturn(listaProductos);

        // Act
        List<Producto> resultado = productoController.listarProductos();

        // Assert
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("Producto 1", resultado.get(0).getNombre());
        assertEquals(Double.valueOf(10.0), resultado.get(0).getPrecio());
        assertEquals("Producto 2", resultado.get(1).getNombre());
        assertEquals(Double.valueOf(20.0), resultado.get(1).getPrecio());
        verify(productoServicioMock, times(1)).listarProducto();
    }

    @Test
    public void obtenerProductoConIdExistente() {
 
        Integer idProducto = 1;
        Producto producto = new Producto();
        producto.setId(idProducto);
        producto.setNombre("Producto 1");
        producto.setPrecio(10);
        when(productoServicioMock.obtenerProductoPorId(idProducto)).thenReturn(producto);

        // Act
        ResponseEntity<Producto> resultado = productoController.obtenerProducto(idProducto);

        // Assert
        assertNotNull(resultado);
        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals("Producto 1", resultado.getBody().getNombre());
        assertEquals(Double.valueOf(10.0), resultado.getBody().getPrecio());
        verify(productoServicioMock, times(1)).obtenerProductoPorId(idProducto);
    }

    @Test
    public void obtenerProductoConIdNoExistente() {
 
        Integer idProducto = 1;
        when(productoServicioMock.obtenerProductoPorId(idProducto)).thenReturn(null);

        // Act
        ResponseEntity<Producto> resultado = productoController.obtenerProducto(idProducto);

        // Assert
        assertNotNull(resultado);
        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        verify(productoServicioMock, times(1)).obtenerProductoPorId(idProducto);
    }
    
    
}
    
    
