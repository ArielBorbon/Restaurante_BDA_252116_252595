/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Comandas;

import DAO.Clientes.ClientesDAO;
import DAO.Mesas.MesasDAO;
import DAO.Productos.ProductosDAO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import Entidades.Clientes.ClientesFrecuentes;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Entidades.Mesa.Mesa;
import Entidades.Productos.Producto;
import ManejadorConexiones.ManejadorConexiones;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author PC Gamer
 */
public class ComandasDAO {
    
     public Comanda registrarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> detallesDTO) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();

        try {
            MesasDAO mesasDAO = new MesasDAO();
            Mesa mesa = mesasDAO.obtenerMesaPorNumMesa(comandaDTO.getNum_mesa());
            ClientesFrecuentes cliente = null;
            
            if (comandaDTO.getCorreoCliente() != null && !comandaDTO.getCorreoCliente().isBlank()) {
                ClientesDAO clientesDAO = new ClientesDAO();
                cliente = clientesDAO.obtenerPorCorreo(comandaDTO.getCorreoCliente());
            }

            Comanda comanda = new Comanda();
            comanda.setFolio(comandaDTO.getFolio());
            comanda.setFechaHora(comandaDTO.getFecha_hora());
            comanda.setEstado(comandaDTO.getEstado_comanda());
            comanda.setTotal(comandaDTO.getTotal());
            comanda.setMesa(mesa);
            comanda.setClienteFrecuente(cliente);

            for (NuevoDetalleComandaDTO detalleDTO : detallesDTO) {
                
                ProductosDAO productosDAO = new ProductosDAO();
                Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

                DetalleComanda detalle = new DetalleComanda();
                detalle.setProducto(producto);
                detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
                detalle.setNotasEspeciales(detalleDTO.getNotas_producto());
                detalle.setCantidad(detalleDTO.getCantidad());
                detalle.setImporteTotal(detalleDTO.getImporteTotal());
                detalle.setComanda(comanda);

                comanda.getDetalles().add(detalle);
            }


            em.persist(comanda);
            em.getTransaction().commit();
            return comanda;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al registrar la comanda", e);
        } finally {
            em.close();
        }
    }
    
     
     
        public String generarFolioComanda() {
        EntityManager em = ManejadorConexiones.getEntityManager();
        try {
            Calendar hoy = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String fechaFormateada = sdf.format(hoy.getTime());
        
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<Comanda> root = cq.from(Comanda.class);
        
            Predicate mismoDia = cb.equal(cb.function("DATE", Date.class, root.get("fechaHora")), 
                                           cb.function("DATE", Date.class, cb.literal(hoy.getTime())));
            cq.select(cb.count(root)).where(mismoDia);
            Long conteo = em.createQuery(cq).getSingleResult();
        
            String consecutivo = String.format("%03d", conteo + 1);
            return "OB-" + fechaFormateada + "-" + consecutivo;
        } finally {
            em.close();
        }
    
    }

     
     
     
     
     
     
     
    public double calcularTotalDetalleComanda(NuevoDetalleComandaDTO detalleDTO) {
    ProductosDAO productosDAO = new ProductosDAO();
    Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

    if (producto == null) {
        throw new RuntimeException("Producto no encontrado: " + detalleDTO.getNombreProducto());
    }

    return detalleDTO.getCantidad() * producto.getPrecio();
}

     
     
    public double calcularTotalComanda(List<NuevoDetalleComandaDTO> detallesDTO) {
        double total = 0;
        
        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            total += calcularTotalDetalleComanda(detalle);
            }
        
        return total;
    }

     
     
    public Comanda modificarComanda(NuevaComandaDTO comandaDTO, List<NuevoDetalleComandaDTO> nuevosDetallesDTO) {
    EntityManager em = ManejadorConexiones.getEntityManager();
    em.getTransaction().begin();

    try {

        TypedQuery<Comanda> query = em.createQuery(
            "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
        query.setParameter("folio", comandaDTO.getFolio());
        Comanda comanda = query.getSingleResult();

        if (comanda == null) {
            throw new RuntimeException("Comanda no encontrada con el folio: " + comandaDTO.getFolio());
        }

 
        Query eliminarDetalles = em.createQuery(
            "DELETE FROM DetalleComanda d WHERE d.comanda = :comanda");
        eliminarDetalles.setParameter("comanda", comanda);
        eliminarDetalles.executeUpdate();

        ProductosDAO productosDAO = new ProductosDAO();
        for (NuevoDetalleComandaDTO detalleDTO : nuevosDetallesDTO) {
            Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

            DetalleComanda detalle = new DetalleComanda();
            detalle.setProducto(producto);
            detalle.setPrecioUnitario(detalleDTO.getPrecioUnitario());
            detalle.setNotasEspeciales(detalleDTO.getNotas_producto());
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setImporteTotal(detalleDTO.getImporteTotal());
            detalle.setComanda(comanda);

            em.persist(detalle);
            comanda.getDetalles().add(detalle);
        }


        comanda.setEstado(comandaDTO.getEstado_comanda());
        comanda.setFechaHora(comandaDTO.getFecha_hora());
        comanda.setTotal(comandaDTO.getTotal());

        em.merge(comanda);
        em.getTransaction().commit();
        return comanda;
    } catch (Exception e) {
        em.getTransaction().rollback();
        throw new RuntimeException("Error al modificar la comanda", e);
    } finally {
        em.close();
    }
}

    
    
    
    
    
    
    
    public void eliminarComanda(NuevaComandaDTO comandaDTO) {
    EntityManager em = ManejadorConexiones.getEntityManager();
    em.getTransaction().begin();

        try {

            TypedQuery<Comanda> query = em.createQuery(
                "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", comandaDTO.getFolio());
            Comanda comanda = query.getSingleResult();
        
            if (comanda == null) {
                throw new RuntimeException("Comanda no encontrada con el folio: " + comandaDTO.getFolio());
            }
        
            Query eliminarDetalles = em.createQuery(
                "DELETE FROM DetalleComanda d WHERE d.comanda = :comanda");
            eliminarDetalles.setParameter("comanda", comanda);
            eliminarDetalles.executeUpdate();
        

            em.remove(comanda);
        
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar la comanda", e);
        } finally {
            em.close();
        }
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
