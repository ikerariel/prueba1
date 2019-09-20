/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author Carlos
 */
public class presupuestoDTO {

    Integer id_presucompra;
    String fecha;
    Integer id_estado;
    Integer id_usuario;
    Integer id_proveedor;
    Integer id_deposito;
    Integer idtipomoneda;
    Integer id_articulo;
    Integer cantidad;
    Integer preciounitario;
    Integer cod;
    Integer id_pedidocompra;
    Integer iddetpresuompras;
    String descrip;
    String estado;
    String usuario;
    String proveedor;
    String articulo;
    String moneda;
    String deposito;

    public Integer getIddetpresuompras() {
        return iddetpresuompras;
    }

    public void setIddetpresuompras(Integer iddetpresuompras) {
        this.iddetpresuompras = iddetpresuompras;
    }

    public presupuestoDTO(Integer id_presucompra, Integer id_pedidocompra, String fecha,String proveedor, Integer id_deposito,String deposito,String moneda, Integer id_articulo, String articulo,  Integer preciounitario,Integer iddetpresuompras, Integer cantidad) {
        this.id_presucompra = id_presucompra;
        this.id_pedidocompra = id_pedidocompra;
        this.fecha = fecha;
        this.id_deposito = id_deposito;
        this.id_articulo = id_articulo;
        this.cantidad = cantidad;
        this.preciounitario = preciounitario;
        this.proveedor = proveedor;
        this.articulo = articulo;
        this.moneda = moneda;
        this.deposito = deposito;
        this.iddetpresuompras = iddetpresuompras;
    }

    public presupuestoDTO(Integer id_presucompra, String fecha, String estado, String usuario, String proveedor) {
        this.id_presucompra = id_presucompra;
        this.fecha = fecha;
        this.estado = estado;
        this.usuario = usuario;
        this.proveedor = proveedor;
    }

    public presupuestoDTO(Integer cod, String descrip) {
        this.cod = cod;
        this.descrip = descrip;
    }

    public presupuestoDTO() {
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getPreciounitario() {
        return preciounitario;
    }

    public void setPreciounitario(Integer preciounitario) {
        this.preciounitario = preciounitario;
    }

    public Integer getId_presucompra() {
        return id_presucompra;
    }

    public void setId_presucompra(Integer id_presucompra) {
        this.id_presucompra = id_presucompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(Integer id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Integer getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(Integer id_deposito) {
        this.id_deposito = id_deposito;
    }

    public Integer getIdtipomoneda() {
        return idtipomoneda;
    }

    public void setIdtipomoneda(Integer idtipomoneda) {
        this.idtipomoneda = idtipomoneda;
    }
    
    
}
