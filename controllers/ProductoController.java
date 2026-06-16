/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.views.CrearProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author mateo
 */
public class ProductoController {
    private CrearProductoView crearProductoView;
    private Producto producto;

    public ProductoController(CrearProductoView crearProductoView) {
        this.crearProductoView = crearProductoView;
        configurarEventosCrearProducto();
    }
    
    
    public void crearProducto(){
       int codigo =Integer.parseInt(crearProductoView.getTxtCodigo().getText());
       String nombre= crearProductoView.getTxtNombre().getText();
       double precio= Double.parseDouble(crearProductoView.getTxtPrecio().getText());
       
       producto=new Producto(codigo,nombre,precio);
       
        System.out.println("Producto Creado Exitosamente");
    
    }
    
    public void configurarEventosCrearProducto(){
        crearProductoView.getBtnAceptar().addActionListener (new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                crearProducto();
            }
            
        });
    }
}
