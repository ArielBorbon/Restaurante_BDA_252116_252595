/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.Mesa;

/**
 *
 * @author PC Gamer
 */
public class NuevaMesaDTO {
    private int num_mesa; //probablemente usemos esta con un DAO para identificar cual es la verdadera mesa, DAO. identificarMesaporNum

    public NuevaMesaDTO() {
    }

    public NuevaMesaDTO(int num_mesa) {
        this.num_mesa = num_mesa;
    }
    

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }
    
    
    
}
