/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO.Comandas;

import DAO.Clientes.ClientesDAO;
import DAO.Ingredientes.IngredientesDAO;
import DAO.Mesas.MesasDAO;
import DAO.Productos.ProductosDAO;
import DTOS.Comandas.NuevaComandaDTO;
import DTOS.Comandas.NuevoDetalleComandaDTO;
import DTOS.Ingredientes.IngredienteConCantidadNecesariaDTO;
import Entidades.Clientes.ClientesFrecuentes;
import Entidades.Comandas.Comanda;
import Entidades.Comandas.DetalleComanda;
import Entidades.Comandas.EstadoComanda;
import Entidades.Ingredientes.Ingrediente;
import Entidades.Ingredientes.Unidad_Medida;
import Entidades.Mesa.Mesa;
import Entidades.Productos.Producto;
import ManejadorConexiones.ManejadorConexiones;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class ComandasDAO implements IComandasDAO{

    public ComandasDAO() {
    }
    
    
    
    
    @Override
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
    
     
     @Override
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

     
     
     
     
     
     
     @Override
    public double calcularTotalDetalleComanda(NuevoDetalleComandaDTO detalleDTO) {
    ProductosDAO productosDAO = new ProductosDAO();
    Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

    if (producto == null) {
        throw new RuntimeException("Producto no encontrado: " + detalleDTO.getNombreProducto());
    }

    return detalleDTO.getCantidad() * producto.getPrecio();
}

     
     @Override
    public double calcularTotalComanda(List<NuevoDetalleComandaDTO> detallesDTO) {
        double total = 0;
        
        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            total += calcularTotalDetalleComanda(detalle);
            }
        
        return total;
    }

     
     @Override
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

    
    
    
    
    
    
    @Override
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

    
    
    
    
     @Override   
    public void cambiarEstadoComandaACancelada(String folio) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
    
        try {
            TypedQuery<Comanda> query = em.createQuery(
                "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", folio);
            Comanda comanda = query.getSingleResult();
        
            comanda.setEstado(EstadoComanda.CANCELADA);
        
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al cambiar el estado de la comanda a CANCELADA", e);
        } finally {
            em.close();
        }
    }

    
    @Override
        public void cambiarEstadoComandaAEntregada(String folio) {
        EntityManager em = ManejadorConexiones.getEntityManager();
        em.getTransaction().begin();
    
        try {
            TypedQuery<Comanda> query = em.createQuery(
                "SELECT c FROM Comanda c WHERE c.folio = :folio", Comanda.class);
            query.setParameter("folio", folio);
            Comanda comanda = query.getSingleResult();
        
            comanda.setEstado(EstadoComanda.ENTREGADA);
        
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al cambiar el estado de la comanda a ENTREGADA", e);
        } finally {
            em.close();
        }
    }

    
    @Override
public boolean verificarStockNecesarioProductos(List<NuevoDetalleComandaDTO> detallesDTO) {
    EntityManager em = ManejadorConexiones.getEntityManager();
    
    try {
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        ProductosDAO productosDAO = new ProductosDAO();

        Map<String, Double> ingredientesNecesarios = new HashMap<>();

        for (NuevoDetalleComandaDTO detalleDTO : detallesDTO) {
            String nombreProducto = detalleDTO.getNombreProducto();
            int cantidadPlatillos = detalleDTO.getCantidad();

            List<IngredienteConCantidadNecesariaDTO> ingredientesProducto =
                ingredientesDAO.obtenerIngredientesConCantidadPorProducto(nombreProducto);

            for (IngredienteConCantidadNecesariaDTO ingredienteDTO : ingredientesProducto) {
                String nombreIngrediente = ingredienteDTO.getNombreIngrediente();
                String unidadMedida = ingredienteDTO.getUnidadMedida();

                double cantidadRequerida = ingredienteDTO.getCantidadIngredienteNecesaria() * cantidadPlatillos;

                String clave = nombreIngrediente + "|" + unidadMedida.toString();

                ingredientesNecesarios.merge(clave, cantidadRequerida, Double::sum);
            }
        }

        for (Map.Entry<String, Double> entrada : ingredientesNecesarios.entrySet()) {
            String clave = entrada.getKey();
            double cantidadNecesaria = entrada.getValue();

            String[] partes = clave.split("\\|");
            String nombreIngrediente = partes[0];
            Unidad_Medida unidad = Unidad_Medida.valueOf(partes[1]);
            String unidadString = unidad.toString();
            Ingrediente ingrediente = ingredientesDAO.buscarIngredientePorNombreYUnidad(nombreIngrediente, unidadString);

            if (ingrediente == null || ingrediente.getStock() < cantidadNecesaria) {
                return false; 
            }
        }

        return true; 
    } catch (Exception e) {
        throw new RuntimeException("Error al verificar stock de ingredientes necesarios", e);
    } finally {
        em.close();
    }
}


    @Override
    public void restarStockIngredientesPorProductosComanda(List<NuevoDetalleComandaDTO> detallesDTO) {
    EntityManager em = ManejadorConexiones.getEntityManager();
    em.getTransaction().begin();

    try {
        IngredientesDAO ingredientesDAO = new IngredientesDAO();
        ProductosDAO productosDAO = new ProductosDAO();

        Map<String, Double> ingredientesPorRestar = new HashMap<>();

        for (NuevoDetalleComandaDTO detalle : detallesDTO) {
            Producto producto = productosDAO.buscarProductoPorNombre(detalle.getNombreProducto());
            int cantidadPlatillos = detalle.getCantidad();

            List<IngredienteConCantidadNecesariaDTO> ingredientes =
                ingredientesDAO.obtenerIngredientesConCantidadPorProducto(producto.getNombre());

            for (IngredienteConCantidadNecesariaDTO ingredienteDTO : ingredientes) {
                String nombre = ingredienteDTO.getNombreIngrediente();
                String unidad = ingredienteDTO.getUnidadMedida();
                double cantidadTotal = ingredienteDTO.getCantidadIngredienteNecesaria() * cantidadPlatillos;

                String clave = nombre + "|" + unidad.toString();

                ingredientesPorRestar.merge(clave, cantidadTotal, Double::sum);
            }
        }


        for (Map.Entry<String, Double> entry : ingredientesPorRestar.entrySet()) {
            String[] partes = entry.getKey().split("\\|");
            String nombre = partes[0];
            Unidad_Medida unidad = Unidad_Medida.valueOf(partes[1]);
            double cantidadARestar = entry.getValue();

            Ingrediente ingrediente = ingredientesDAO.buscarIngredientePorNombreYUnidad(nombre, unidad.toString());

            if (ingrediente == null) {
                throw new RuntimeException("Ingrediente no encontrado: " + nombre + " (" + unidad + ")");
            }

            double nuevoStock = ingrediente.getStock() - cantidadARestar;
            if (nuevoStock < 0) {
                throw new RuntimeException("Stock insuficiente al restar: " + nombre + " (" + unidad + ")");
            }

            ingrediente.setStock(nuevoStock);
            em.merge(ingrediente);
        }

        em.getTransaction().commit();
    } catch (RuntimeException e) {
        em.getTransaction().rollback();
        throw new RuntimeException("Error al restar stock de ingredientes", e);
    } finally {
        em.close();
    }
}
    
    
    
    
    
    @Override
    public List<Comanda> mostrarComandasTodas() {
    EntityManager em = ManejadorConexiones.getEntityManager();

    try {
        String jpql = "SELECT c FROM Comanda c";
        TypedQuery<Comanda> query = em.createQuery(jpql, Comanda.class);
        return query.getResultList();
    } finally {
        em.close();
    }
}

    
    
    @Override
    public void modificarNota(NuevoDetalleComandaDTO detalleDTO, String nuevaNota) {
    EntityManager em = ManejadorConexiones.getEntityManager();

    try {
        em.getTransaction().begin();


        ProductosDAO productosDAO = new ProductosDAO();
        Producto producto = productosDAO.buscarProductoPorNombre(detalleDTO.getNombreProducto());

        String jpqlDetalle = """
            SELECT d FROM DetalleComanda d
            WHERE d.comanda.folio = :folio
            AND d.producto = :producto
            AND d.cantidad = :cantidad
            AND d.precioUnitario = :precioUnitario
        """;

        TypedQuery<DetalleComanda> query = em.createQuery(jpqlDetalle, DetalleComanda.class);
        query.setParameter("folio", detalleDTO.getFolioComanda());
        query.setParameter("producto", producto);
        query.setParameter("cantidad", detalleDTO.getCantidad());
        query.setParameter("precioUnitario", detalleDTO.getPrecioUnitario());

        DetalleComanda detalle = query.getResultStream().findFirst()
            .orElseThrow(() -> new RuntimeException("No se encontró el detalle de comanda para el folio y producto dados."));


        detalle.setNotasEspeciales(nuevaNota);
        em.merge(detalle);

        em.getTransaction().commit();
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException("Error al modificar la nota del detalle de comanda", e);
    } finally {
        em.close();
    }
}

    
    @Override
    public List<Comanda> mostrarComandasAbiertas() {
    EntityManager em = ManejadorConexiones.getEntityManager();

    try {
        String jpql = "SELECT c FROM Comanda c WHERE c.estado = :estado";
        TypedQuery<Comanda> query = em.createQuery(jpql, Comanda.class);
        query.setParameter("estado", EstadoComanda.ABIERTA);
        return query.getResultList();
    } finally {
        em.close();
    }
}

    
    
    

    
    
    
    
}
