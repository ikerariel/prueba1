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
public class ventaDTO {
Integer idgenerico;  
Integer telefono;  
String numerofac;
String direccion;
Integer id_estado;
Integer id_factura;
Integer id_articulo;
Integer id_venta;
Integer cantidad;
Integer preciounitario;
Integer exenta;
Integer iva5;
Integer iva10;
Integer tipoog;
Integer  id_timbrado;
Integer  idvendedor;
String  descripgenerico;
String  ruc;
String  razonsocial;
String  vendedor;
String  inicio_fecha;
String  vencimientos;
String  fac_caja;
String  est_descripcion;
Integer numero;
Integer id_cliente;
Integer id_usuario;
Integer id_impuesto;
Integer id_cobro;
Integer importe;
Integer tarjnrobol;
Integer id_entiemi;
Integer nrochque;
Integer id_tipocheque;
Integer id_bancocheque;
Integer id_tipotarjeta;
Integer id_apcica;
Integer id_deposito;
Integer cv;
String fac_establecimiento;
String fac_desde;
String fac_hasta;
String cedula;

    public Integer getCv() {
        return cv;
    }

    public void setCv(Integer cv) {
        this.cv = cv;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Integer getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(Integer id_deposito) {
        this.id_deposito = id_deposito;
    }

    public Integer getId_apcica() {
        return id_apcica;
    }

    public void setId_apcica(Integer id_apcica) {
        this.id_apcica = id_apcica;
    }

    public Integer getNrochque() {
        return nrochque;
    }

    public void setNrochque(Integer nrochque) {
        this.nrochque = nrochque;
    }

    public Integer getId_tipocheque() {
        return id_tipocheque;
    }

    public void setId_tipocheque(Integer id_tipocheque) {
        this.id_tipocheque = id_tipocheque;
    }

    public Integer getId_bancocheque() {
        return id_bancocheque;
    }

    public void setId_bancocheque(Integer id_bancocheque) {
        this.id_bancocheque = id_bancocheque;
    }

    public Integer getTarjnrobol() {
        return tarjnrobol;
    }

    public void setTarjnrobol(Integer tarjnrobol) {
        this.tarjnrobol = tarjnrobol;
    }

    public Integer getId_entiemi() {
        return id_entiemi;
    }

    public void setId_entiemi(Integer id_entiemi) {
        this.id_entiemi = id_entiemi;
    }

    public Integer getId_tipotarjeta() {
        return id_tipotarjeta;
    }

    public void setId_tipotarjeta(Integer id_tipotarjeta) {
        this.id_tipotarjeta = id_tipotarjeta;
    }

    public Integer getId_cobro() {
        return id_cobro;
    }

    public void setId_cobro(Integer id_cobro) {
        this.id_cobro = id_cobro;
    }

    public Integer getImporte() {
        return importe;
    }

    public void setImporte(Integer importe) {
        this.importe = importe;
    }


    public ventaDTO(Integer idgenerico, String cedula, String descripgenerico) {
        this.idgenerico = idgenerico;
        this.cedula = cedula;
        this.descripgenerico = descripgenerico;
    }
    public ventaDTO(Integer idgenerico, String descripgenerico) {
        this.idgenerico = idgenerico;
        this.descripgenerico = descripgenerico;
    }

    public ventaDTO(Integer id_timbrado, Integer numero, String inicio_fecha, String vencimientos, String est_descripcion, String fac_caja) {
this.id_timbrado = id_timbrado;
this.numero = numero;
this.inicio_fecha = inicio_fecha;
this.vencimientos = vencimientos;
this.est_descripcion = est_descripcion;
this.fac_caja = fac_caja;


    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRazonsocial() {
        return razonsocial;
    }

    public void setRazonsocial(String razonsocial) {
        this.razonsocial = razonsocial;
    }

    public ventaDTO() {
     
    }

    public String getInicio_fecha() {
        return inicio_fecha;
    }

    public void setInicio_fecha(String inicio_fecha) {
        this.inicio_fecha = inicio_fecha;
    }

    public String getVencimientos() {
        return vencimientos;
    }

    public void setVencimientos(String vencimientos) {
        this.vencimientos = vencimientos;
    }

    public String getFac_caja() {
        return fac_caja;
    }

    public void setFac_caja(String fac_caja) {
        this.fac_caja = fac_caja;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getFac_establecimiento() {
        return fac_establecimiento;
    }

    public void setFac_establecimiento(String fac_establecimiento) {
        this.fac_establecimiento = fac_establecimiento;
    }

    public String getFac_desde() {
        return fac_desde;
    }

    public void setFac_desde(String fac_desde) {
        this.fac_desde = fac_desde;
    }

    public String getFac_hasta() {
        return fac_hasta;
    }

    public void setFac_hasta(String fac_hasta) {
        this.fac_hasta = fac_hasta;
    }

    public Integer getIdgenerico() {
        return idgenerico;
    }

    public void setIdgenerico(Integer idgenerico) {
        this.idgenerico = idgenerico;
    }

    public String getNumerofac() {
        return numerofac;
    }

    public void setNumerofac(String numerofac) {
        this.numerofac = numerofac;
    }

    public Integer getId_impuesto() {
        return id_impuesto;
    }

    public void setId_impuesto(Integer id_impuesto) {
        this.id_impuesto = id_impuesto;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public Integer getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(Integer id_articulo) {
        this.id_articulo = id_articulo;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(Integer id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public Integer getIdvendedor() {
        return idvendedor;
    }

    public void setIdvendedor(Integer idvendedor) {
        this.idvendedor = idvendedor;
    }

    public String getDescripgenerico() {
        return descripgenerico;
    }

    public void setDescripgenerico(String descripgenerico) {
        this.descripgenerico = descripgenerico;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public Integer getTipoog() {
        return tipoog;
    }

    public void setTipoog(Integer tipoog) {
        this.tipoog = tipoog;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Integer getId_factura() {
        return id_factura;
    }

    public void setId_factura(Integer id_factura) {
        this.id_factura = id_factura;
    }

    public Integer getId_venta() {
        return id_venta;
    }

    public void setId_venta(Integer id_venta) {
        this.id_venta = id_venta;
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

    public Integer getExenta() {
        return exenta;
    }

    public void setExenta(Integer exenta) {
        this.exenta = exenta;
    }

    public Integer getIva5() {
        return iva5;
    }

    public void setIva5(Integer iva5) {
        this.iva5 = iva5;
    }

    public Integer getIva10() {
        return iva10;
    }

    public void setIva10(Integer iva10) {
        this.iva10 = iva10;
    }
    
}
