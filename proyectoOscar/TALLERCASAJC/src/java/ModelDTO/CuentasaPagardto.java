/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDTO;

/**
 *
 * @author user
 */
public class CuentasaPagardto {
    
    private Integer id_cuentpag;
    private String fechavence;
    private Integer saldo;
    private Integer monto;
    private Integer id_compra;
    private String co_cantcuota;
    private Integer id_estado;
    private String est_descripcion;
    
         public CuentasaPagardto() {
    }
    public CuentasaPagardto(Integer id_cuentpag, String fechavence, Integer saldo, Integer monto,
            String co_cantcuota, String est_descripcion) {
        this.id_cuentpag = id_cuentpag;
        this.fechavence = fechavence;
        this.saldo = saldo;
        this.monto = monto;
        this.co_cantcuota = co_cantcuota;
        this.est_descripcion = est_descripcion;
    }

    public CuentasaPagardto(Integer id_cuentpag, String fechavence, Integer saldo, Integer monto, Integer id_compra, Integer id_estado) {
        this.id_cuentpag = id_cuentpag;
        this.fechavence = fechavence;
        this.saldo = saldo;
        this.monto = monto;
        this.id_compra = id_compra;
        this.id_estado = id_estado;
    }

    public Integer getId_cuentpag() {
        return id_cuentpag;
    }

    public void setId_cuentpag(Integer id_cuentpag) {
        this.id_cuentpag = id_cuentpag;
    }

    public String getFechavence() {
        return fechavence;
    }

    public void setFechavence(String fechavence) {
        this.fechavence = fechavence;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getId_compra() {
        return id_compra;
    }

    public void setId_compra(Integer id_compra) {
        this.id_compra = id_compra;
    }

    public String getCo_cantcuota() {
        return co_cantcuota;
    }

    public void setCo_cantcuota(String co_cantcuota) {
        this.co_cantcuota = co_cantcuota;
    }

    public Integer getId_estado() {
        return id_estado;
    }

    public void setId_estado(Integer id_estado) {
        this.id_estado = id_estado;
    }

    public String getEst_descripcion() {
        return est_descripcion;
    }

    public void setEst_descripcion(String est_descripcion) {
        this.est_descripcion = est_descripcion;
    }
    

}
