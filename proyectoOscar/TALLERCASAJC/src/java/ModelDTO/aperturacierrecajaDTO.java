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
public class aperturacierrecajaDTO {

    Integer id_apcica;
    String apertura_fecha;
    String caja;
    Integer idfactura;
    String cajas;
    String cajero;
    String estado;
    String supervisor;
    String factura;
    
    Integer  apcica_ciermonto;
  Integer apcica_apermonto;
  String apcica_ciefecha;
  String numero;
  Integer id_caja;
  Integer id_sucursal;
  Integer id_estado;
  Integer id_deposito;
  Integer idsupervisor;
  Integer idcajero;
  Integer id_timbrado;
  
  Integer cantimoneda;
  Integer montounitario;
 Integer iddenominacionmoneda; 
 Integer nrocheque; 
 Integer nroboletatarjeta; 
       

    public aperturacierrecajaDTO() {
    }
    public aperturacierrecajaDTO(Integer id_apcica) {
        this.id_apcica =id_apcica;
    }
    public aperturacierrecajaDTO(String numero) {
        this.numero =numero;
    }

    public aperturacierrecajaDTO(Integer id_apcica, String apertura_fecha, String caja, String cajero, String supervisor, String estado) {
        this.id_apcica = id_apcica;
        this.apertura_fecha = apertura_fecha;
        this.caja = caja;
        this.cajero = cajero;
        this.supervisor = supervisor;
        this.estado = estado;

    }

    public aperturacierrecajaDTO(String caja, Integer id_apcica, Integer idfactura, String factura) {
        this.caja = caja;
        this.id_apcica = id_apcica;
        this.idfactura = idfactura;
        this.factura = factura;

    }

    public Integer getId_apcica() {
        return id_apcica;
    }

    public void setId_apcica(Integer id_apcica) {
        this.id_apcica = id_apcica;
    }

    public String getApertura_fecha() {
        return apertura_fecha;
    }

    public void setApertura_fecha(String apertura_fecha) {
        this.apertura_fecha = apertura_fecha;
    }

    public String getCaja() {
        return caja;
    }

    public Integer getNrocheque() {
        return nrocheque;
    }

    public void setNrocheque(Integer nrocheque) {
        this.nrocheque = nrocheque;
    }

    public Integer getNroboletatarjeta() {
        return nroboletatarjeta;
    }

    public void setNroboletatarjeta(Integer nroboletatarjeta) {
        this.nroboletatarjeta = nroboletatarjeta;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public Integer getIdfactura() {
        return idfactura;
    }

    public void setIdfactura(Integer idfactura) {
        this.idfactura = idfactura;
    }

    public String getCajas() {
        return cajas;
    }

    public void setCajas(String cajas) {
        this.cajas = cajas;
    }

    public String getCajero() {
        return cajero;
    }

    public void setCajero(String cajero) {
        this.cajero = cajero;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }

    public Integer getApcica_ciermonto() {
        return apcica_ciermonto;
    }

    public void setApcica_ciermonto(Integer apcica_ciermonto) {
        this.apcica_ciermonto = apcica_ciermonto;
    }

    public Integer getApcica_apermonto() {
        return apcica_apermonto;
    }

    public void setApcica_apermonto(Integer apcica_apermonto) {
        this.apcica_apermonto = apcica_apermonto;
    }

    public String getApcica_ciefecha() {
        return apcica_ciefecha;
    }

    public void setApcica_ciefecha(String apcica_ciefecha) {
        this.apcica_ciefecha = apcica_ciefecha;
    }

    public Integer getId_caja() {
        return id_caja;
    }

    public void setId_caja(Integer id_caja) {
        this.id_caja = id_caja;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public Integer getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(Integer id_deposito) {
        this.id_deposito = id_deposito;
    }

    public Integer getIdsupervisor() {
        return idsupervisor;
    }

    public void setIdsupervisor(Integer idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public Integer getIdcajero() {
        return idcajero;
    }

    public void setIdcajero(Integer idcajero) {
        this.idcajero = idcajero;
    }

    public Integer getId_timbrado() {
        return id_timbrado;
    }

    public void setId_timbrado(Integer id_timbrado) {
        this.id_timbrado = id_timbrado;
    }

    public Integer getCantimoneda() {
        return cantimoneda;
    }

    public void setCantimoneda(Integer cantimoneda) {
        this.cantimoneda = cantimoneda;
    }

    public Integer getMontounitario() {
        return montounitario;
    }

    public void setMontounitario(Integer montounitario) {
        this.montounitario = montounitario;
    }

    public Integer getIddenominacionmoneda() {
        return iddenominacionmoneda;
    }

    public void setIddenominacionmoneda(Integer iddenominacionmoneda) {
        this.iddenominacionmoneda = iddenominacionmoneda;
    }

}
