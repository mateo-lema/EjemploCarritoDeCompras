package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.DAO.ProductoDAO;
import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.views.ActualizarProductoView;
import ec.edu.ups.carrito.views.BuscarProductoView;
import ec.edu.ups.carrito.views.CrearProductoView;
import ec.edu.ups.carrito.views.EliminarProductoView;
import ec.edu.ups.carrito.views.ListarProductosView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

public class ProductoController {

    private CrearProductoView crearProductoView;
    private BuscarProductoView buscarProductoView;
    private EliminarProductoView eliminarProductoView;
    private ActualizarProductoView actualizarProductoView;
    private ListarProductosView listarProductosView;
    private ProductoDAO productoDAO;

    public ProductoController(EliminarProductoView eliminarProductoView, BuscarProductoView buscarProductoView, CrearProductoView crearProductoView, ActualizarProductoView actualizarProductoView, ListarProductosView listarProductosView, ProductoDAO productoDAO) {
        this.crearProductoView = crearProductoView;
        configurarEventosCrearProducto();
        this.buscarProductoView = buscarProductoView;
        configurarEventosBuscarProducto();
        this.eliminarProductoView = eliminarProductoView;
        configurarEventosEliminarProducto();
        configurarEventosBuscarProductoEliminar();
        this.actualizarProductoView = actualizarProductoView;
        configurarEventosBuscarProductoActualizar();
        configurarEventosActualizarProducto();
        this.listarProductosView = listarProductosView;
        this.productoDAO = productoDAO;
    }

    public void crearProducto() {

        int codigo = Integer.parseInt(crearProductoView.getTxtCodigo().getText());
        String nombre = crearProductoView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearProductoView.getTxtPrecio().getText());

        Producto product = productoDAO.buscar(codigo);

        if (product != null) {
            crearProductoView.mostrarInformacion("Ya existe un producto con ese código");
        } else {
            Producto producto = new Producto(codigo, nombre, precio);
            productoDAO.crear(producto);

            crearProductoView.mostrarInformacion("Producto creado exitosamente");
        }

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

        int respuesta = JOptionPane.showConfirmDialog(eliminarProductoView, "¿Deseas eliminar el producto?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.YES_OPTION) {
            productoDAO.eliminar(codigo);

            eliminarProductoView.mostrarInformacion("Producto eliminado");

            eliminarProductoView.getTxtCodigoProductoEliminar().setText("");
            eliminarProductoView.getTxtNombreProductoEliminar().setText("");
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

                int codigo = Integer.parseInt(eliminarProductoView.getTxtCodigoProductoEliminar().getText());
                Producto producto = productoDAO.buscar(codigo);

                if (producto != null) {eliminarProductoView.getTxtNombreProductoEliminar().setText(producto.getNombre());
                } else {
                    eliminarProductoView.mostrarInformacion("No se encontró el producto");
                }
            }
        });
    }

    public void buscarProductoActualizar() {

        int codigo = Integer.parseInt(
                actualizarProductoView.getTxtCodigoProductoActualizar().getText());

        Producto producto = productoDAO.buscar(codigo);

        if (producto != null) {actualizarProductoView.getTxtActualizarNombre().setText(producto.getNombre());

            actualizarProductoView.getTxtActualizarPrecio().setText(String.valueOf(producto.getPrecio()));

        } else {

            JOptionPane.showMessageDialog(
                    actualizarProductoView,
                    "No se encontró el producto"
            );
        }
    }

    public void configurarEventosBuscarProductoActualizar() {

        actualizarProductoView.getBtnBuscarActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProductoActualizar();
            }
        });
    }

    public void actualizarProducto() {

        int codigo = Integer.parseInt(actualizarProductoView.getTxtCodigoProductoActualizar().getText());

        int respuesta = JOptionPane.showConfirmDialog(actualizarProductoView, "¿Deseas actualizar el producto?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {

            String nombre = actualizarProductoView.getTxtActualizarNombre().getText();

            double precio = Double.parseDouble(actualizarProductoView.getTxtActualizarPrecio().getText());

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
    }

    public void configurarEventosActualizarProducto() {

        actualizarProductoView.getBtnActualizar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }
        });
    }

    public void listarProductos() {

        List<Producto> productos = productoDAO.listar();

        listarProductosView.cargarDatos(productos);

    }
}
