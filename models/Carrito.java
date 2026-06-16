/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mateo
 */
public class Carrito {
    
    private List<ItemCarrito> items;

    public Carrito() {
        items=new ArrayList<>();
    }
    
    public void agregarProducto(Producto producto, int cantidad){
        ItemCarrito item = new ItemCarrito(producto,cantidad);
        
        items.add(item);
        
        
    }
    
    public ItemCarrito buscarItemPorCodigoDeProducto(int codigo){
        for (ItemCarrito item : items) {
            if (item.getProducto().getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }
    
    

    public void eliminarProducto(int codigoProducto){
        ItemCarrito item = buscarItemPorCodigoDeProducto(codigoProducto);
        if(item != null){
            items.remove(item);
        }
        
    }
    
    public double calcularTotal(){
        double total=0.00;
        for(ItemCarrito item: items){
            total += item.calcularSubtotal();
        }
        return total;
    }

    
    
    
}
