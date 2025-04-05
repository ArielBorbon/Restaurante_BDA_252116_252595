/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.IngredienteBO;

import DAO.Ingredientes.IngredientesDAO;
import DTOS.Ingredientes.NuevoIngredienteDTO;
import Entidades.Ingredientes.Ingrediente;
import NegocioException.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC Gamer
 */
public class IngredienteBO implements IIngredienteBO {
     private IngredientesDAO ingredienteDAO;

    public IngredienteBO(IngredientesDAO ingredienteDAO) throws NegocioException {
        if (ingredienteDAO == null) {
            throw new NegocioException("El DAO no puede ser null");
        }
        this.ingredienteDAO = ingredienteDAO;
    }
    
    
    
    


    
    
     
     @Override
    public Ingrediente registrarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception {
        if (nuevoIngrediente == null) {
            throw new NegocioException("El DTO del ingrediente no puede ser nulo.");
        }
        if (nuevoIngrediente.getNombre() == null || nuevoIngrediente.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (nuevoIngrediente.getUnidad_medida() == null || nuevoIngrediente.getUnidad_medida().trim().isEmpty()) {
            throw new NegocioException("La unidad de medida no puede estar vacía.");
        }
        if (nuevoIngrediente.getStock() < 0) {
            throw new NegocioException("El stock del ingrediente no puede ser negativo.");
        }

        Ingrediente ingredienteExistente = ingredienteDAO.buscarIngredientePorNombreYUnidad(
            nuevoIngrediente.getNombre(), nuevoIngrediente.getUnidad_medida()
        );

        if (ingredienteExistente != null) {
            throw new NegocioException("El ingrediente ya existe en la base de datos.");
        }

        return ingredienteDAO.registrarIngrediente(nuevoIngrediente);
    }
    
    
    
    
    
    
    
     @Override
        public void eliminarIngredienteBO(NuevoIngredienteDTO nuevoIngrediente) throws Exception {
        if (nuevoIngrediente == null) {
            throw new NegocioException("El DTO del ingrediente no puede ser nulo.");
        }

        if (nuevoIngrediente.getNombre() == null || nuevoIngrediente.getNombre().trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (nuevoIngrediente.getUnidad_medida() == null || nuevoIngrediente.getUnidad_medida().trim().isEmpty()) {
            throw new NegocioException("La unidad de medida no puede estar vacía.");
        }
        
        
         if (ingredienteDAO.tieneRelacionesActivas(
            nuevoIngrediente.getNombre(), 
            nuevoIngrediente.getUnidad_medida()
        )) {
            throw new NegocioException("No se puede eliminar: el ingrediente está en uso por productos.");
        }


        Ingrediente ingredienteExistente = ingredienteDAO.buscarIngredientePorNombreYUnidad(
            nuevoIngrediente.getNombre(), nuevoIngrediente.getUnidad_medida()
        );

        if (ingredienteExistente == null) {
            throw new NegocioException("No se encontró el ingrediente en la base de datos.");
        }


        ingredienteDAO.eliminarIngrediente(ingredienteExistente); 
    }
    
    
     @Override
        public List<Ingrediente> obtenerListaIngredientesBO() {
        return ingredienteDAO.mostrarListaIngredientes();
    }
        
        
        


     @Override
     public void actualizarIngredienteBO(NuevoIngredienteDTO nuevoIngredienteDTO, double nuevoStock) throws Exception {

    if (nuevoIngredienteDTO == null) {
        throw new NegocioException("El DTO del ingrediente no puede ser nulo.");
    }
    if (nuevoIngredienteDTO.getNombre() == null || nuevoIngredienteDTO.getNombre().trim().isEmpty()) {
        throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
    }
    if (nuevoIngredienteDTO.getUnidad_medida() == null || nuevoIngredienteDTO.getUnidad_medida().trim().isEmpty()) {
        throw new NegocioException("La unidad de medida del ingrediente no puede estar vacía.");
    }

    if (nuevoStock < 0) {
        throw new NegocioException("El stock no puede ser negativo.");
    }

    Ingrediente ingrediente = ingredienteDAO.buscarIngredientePorNombreYUnidad(
        nuevoIngredienteDTO.getNombre(), 
        nuevoIngredienteDTO.getUnidad_medida()
    );

    if (ingrediente == null) {
        throw new NegocioException("No se encontró el ingrediente con el nombre y unidad de medida especificados.");
    }


    ingredienteDAO.actualizarIngrediente(nuevoIngredienteDTO, nuevoStock);
}

    
    
     @Override
        public Ingrediente buscarIngredientePorNombreYUnidadBO(String nombre, String unidadMedida) throws Exception {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (unidadMedida == null || unidadMedida.trim().isEmpty()) {
            throw new NegocioException("La unidad de medida del ingrediente no puede estar vacía.");
        }

        Ingrediente ingrediente = ingredienteDAO.buscarIngredientePorNombreYUnidad(nombre, unidadMedida);

        if (ingrediente == null) {
            throw new NegocioException("No se encontró el ingrediente con el nombre '" + nombre + "' y unidad de medida '" + unidadMedida + "'.");
        }

        return ingrediente;
    }
        
        
     @Override
        public boolean tieneRelacionesActivasBO(String nombre, String unidadMedida) throws NegocioException {

        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre del ingrediente no puede estar vacío.");
        }
        if (unidadMedida == null || unidadMedida.trim().isEmpty()) {
            throw new NegocioException("La unidad de medida no puede estar vacía.");
        }

        try {
          
            return ingredienteDAO.tieneRelacionesActivas(nombre, unidadMedida);
            
        } catch (Exception e) {
            Logger.getLogger(IngredienteBO.class.getName())
                  .log(Level.SEVERE, "Error al verificar relaciones del ingrediente", e);
            throw new NegocioException("No se pudo verificar si el ingrediente está en uso.");
        }
    }
}
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    
    
    
    


        
        
        
        
        
    













    

