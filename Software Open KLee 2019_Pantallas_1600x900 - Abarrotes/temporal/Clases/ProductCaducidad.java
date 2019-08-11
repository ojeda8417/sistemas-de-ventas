/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Clases;

/**
 *
 * @author Raul
 */
public class ProductCaducidad {
    private String codigo;
    private String codbarras;
    private String producto;
    private String marca;
    private String unidad;
    private String categoria;
    private String pcomp;
    private String pvent;
    private String stock;
    private String fechacaducidad;
    private String imagen;

    public ProductCaducidad(String codigo, String codbarras, String producto, String marca, String unidad, String categoria, String pcomp, String pvent, String stock, String fechacaducidad, String imagen) {
        this.codigo = codigo;
        this.codbarras = codbarras;
        this.producto = producto;
        this.marca = marca;
        this.unidad = unidad;
        this.categoria = categoria;
        this.pcomp = pcomp;
        this.pvent = pvent;
        this.stock = stock;
        this.fechacaducidad = fechacaducidad;
        this.imagen = imagen;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodbarras() {
        return codbarras;
    }

    public void setCodbarras(String codbarras) {
        this.codbarras = codbarras;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPcomp() {
        return pcomp;
    }

    public void setPcomp(String pcomp) {
        this.pcomp = pcomp;
    }

    public String getPvent() {
        return pvent;
    }

    public void setPvent(String pvent) {
        this.pvent = pvent;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getFechacaducidad() {
        return fechacaducidad;
    }

    public void setFechacaducidad(String fechacaducidad) {
        this.fechacaducidad = fechacaducidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
    