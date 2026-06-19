/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.DAO.ProductoDAO;
import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.views.ActualizarProductoView;
import ec.edu.ups.carrito.views.BuscarProductoView;
import ec.edu.ups.carrito.views.CrearProductoView;
import ec.edu.ups.carrito.views.EliminarProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

/**
 *
 * @author mateo
 */
public class ProductoController {

    private CrearProductoView crearProductoView;
    private BuscarProductoView buscarProductoView;
    private EliminarProductoView eliminarProductoView;
    private ActualizarProductoView actualizarProductoView;
    private ProductoDAO productoDAO;

    public ProductoController(CrearProductoView crearProductoView, ProductoDAO productoDAO) {
        this.crearProductoView = crearProductoView;
        this.productoDAO = productoDAO;
        configurarEventosCrearProducto();
    }

    public ProductoController(BuscarProductoView buscarProductoView, ProductoDAO productoDAO) {
        this.buscarProductoView = buscarProductoView;
        this.productoDAO = productoDAO;
        configurarEventosBuscarProducto();
    }

    public ProductoController(EliminarProductoView eliminarProductoView, ProductoDAO productoDAO) {
        this.eliminarProductoView = eliminarProductoView;
        this.productoDAO = productoDAO;
        configurarEventosEliminarProducto();
        configurarEventosBuscarProductoEliminar();
    }

    public ProductoController(ActualizarProductoView actualizarProductoView, ProductoDAO productoDAO) {
        this.actualizarProductoView = actualizarProductoView;
        this.productoDAO = productoDAO;
        configurarEventosActualizarProducto();
    }

    public void crearProducto() {
        int codigo = Integer.parseInt(crearProductoView.getTxtCodigo().getText());
        String nombre = crearProductoView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearProductoView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.crear(producto);
        crearProductoView.mostrarInformacion("Producto creado exitosamente");

    }

    public void configurarEventosCrearProducto() {
        crearProductoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }

        });
    }

    public void buscarProducto() {
        int codigo = Integer.parseInt(buscarProductoView.getTxtCodigoProducto().getText());

        Producto producto = productoDAO.buscar(codigo);
        if (producto != null) {
            String nombre = producto.getNombre();
            double precio = producto.getPrecio();

            buscarProductoView.getTxtNombreProducto().setText(nombre);
            buscarProductoView.getTxtPrecioProducto().setText(String.valueOf(precio));
        } else {
            buscarProductoView.mostrarInformacion("No se encontro el producto");

        }

    }

    public void configurarEventosBuscarProducto() {
        buscarProductoView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }

        });
    }

    public void eliminarProducto() {

        int codigo = Integer.parseInt(
                eliminarProductoView.getTxtCodigoProductoEliminar().getText()
        );

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {

            int respuesta = JOptionPane.showConfirmDialog(
                    eliminarProductoView,
                    "¿Deseas eliminar el producto?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {
                productoDAO.eliminar(producto.getCodigo());

                eliminarProductoView.mostrarInformacion("Producto eliminado");

            }

        } else {
            eliminarProductoView.mostrarInformacion("No se encontró el producto");
        }
    }

    public void configurarEventosEliminarProducto() {
        eliminarProductoView.getBtnEliminarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }

        });
    }

    public void configurarEventosBuscarProductoEliminar() {
        eliminarProductoView.getBtnBuscarEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int codigo = Integer.parseInt(
                        eliminarProductoView.getTxtCodigoProductoEliminar().getText()
                );

                Producto producto = productoDAO.buscar(codigo);

                if (producto != null) {
                    eliminarProductoView.getTxtNombreProductoEliminar()
                            .setText(producto.getNombre());
                } else {
                    eliminarProductoView.mostrarInformacion("No se encontró el producto");
                }
            }
        });
    }

    public void actualizarProducto() {

        int codigo = Integer.parseInt(actualizarProductoView.getTxtCodigoProductoActualizar().getText());

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {

            int respuesta = JOptionPane.showConfirmDialog(
                    actualizarProductoView,
                    "¿Deseas actualizar el producto?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
            );

            if (respuesta == JOptionPane.YES_OPTION) {

                String nombre = actualizarProductoView.getTxtActualizarNombre().getText();

                double precio = Double.parseDouble(
                        actualizarProductoView.getTxtActualizarPrecio().getText()
                );

                Producto productoActualizado = new Producto();
                productoActualizado.setCodigo(codigo);
                productoActualizado.setNombre(nombre);
                productoActualizado.setPrecio(precio);

                productoDAO.actualizar(codigo, productoActualizado);

                JOptionPane.showMessageDialog(
                        actualizarProductoView,
                        "Producto actualizado correctamente"
                );
            }

        } else {
            JOptionPane.showMessageDialog(
                    actualizarProductoView,
                    "No se encontró el producto"
            );
        }
    }

    public void configurarEventosActualizarProducto() {

        actualizarProductoView.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });
    }
}
