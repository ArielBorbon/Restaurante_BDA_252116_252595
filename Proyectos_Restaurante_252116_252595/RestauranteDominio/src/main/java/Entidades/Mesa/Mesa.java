/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades.Mesa;

import Entidades.Comandas.Comanda;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Entidad Mesa
 * 
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */
@Entity
@Table (name = "Mesas")
public class Mesa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id_mesa")
    private Long id;

    
    @Column (name = "num_mesa" , nullable = false )
    private int num_mesa;


    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoMesa estado;
    
    
    @OneToMany(mappedBy = "mesa", cascade = CascadeType.PERSIST)
    private List<Comanda> comandas = new ArrayList<>();
    

    public Mesa() {
    }

    public Mesa(Long id, int num_mesa, EstadoMesa estado) {
        this.id = id;
        this.num_mesa = num_mesa;
        this.estado = estado;
    }

    public Mesa(int num_mesa, EstadoMesa estado) {
        this.num_mesa = num_mesa;
        this.estado = estado;
    }

    public Mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }
    
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public List<Comanda> getComandas() {
        return comandas;
    }

    public void setComandas(List<Comanda> comandas) {
        this.comandas = comandas;
    }

    public EstadoMesa getEstado() {
        return estado;
    }

    public void setEstado(EstadoMesa estado) {
        this.estado = estado;
    }

    
    

    
}
