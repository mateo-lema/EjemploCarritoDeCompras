/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ec.edu.ups.carrito.DAO;

import ec.edu.ups.carrito.models.Producto;

/**
 *
 * @author mateo
 */
public interface ProductoDAO {
    //Interface utiliza public y abstract por defecto. Por eso abajo se pone directo void
    void crear(Producto producto);
    Producto buscar(int codigo);
    void actualizar(int codigo, Producto producto);
    void eliminar(int codigo);
}
