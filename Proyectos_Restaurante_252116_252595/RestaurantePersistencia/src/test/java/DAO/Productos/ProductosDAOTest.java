///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
// */
//package DAO.Productos;
//
//import DAO.Ingredientes.IngredientesDAO;
//import DTOS.Ingredientes.NuevoIngredienteDTO;
//import DTOS.Productos.NuevoProductoDTO;
//import DTOS.Productos.NuevoProductoOcupaIngredienteDTO;
//import Entidades.Ingredientes.Ingrediente;
//import Entidades.Productos.Estado_Producto;
//import Entidades.Productos.Producto;
//import Entidades.Productos.ProductoOcupaIngrediente;
//import Entidades.Productos.Tipo_Producto;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//
///**
// *
// * @author rayoa
// */
//public class ProductosDAOTest {
//    
//    public ProductosDAOTest() {
//    }
//
//    @org.junit.jupiter.api.BeforeAll
//    public static void setUpClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterAll
//    public static void tearDownClass() throws Exception {
//    }
//
//    @org.junit.jupiter.api.BeforeEach
//    public void setUp() throws Exception {
//    }
//
//    @org.junit.jupiter.api.AfterEach
//    public void tearDown() throws Exception {
//    }
//    
//
//
//    /**
//     * Test of mostrarListaProductosDisponibles method, of class ProductosDAO.
//     */
////    @Test
////    public void testMostrarListaProductosDisponibles() {
////        ProductosDAO pdao = new ProductosDAO();
////        List<Producto> listaProductos = pdao.mostrarListaProductosDisponibles();
////        int cantidadProductos = listaProductos.size();
////        assertEquals(3, cantidadProductos);
////        
////        
////    }
////
////    /**
////     * Test of filtrarPorNombreProductoDisponibles method, of class ProductosDAO.
////     */
////    @org.junit.jupiter.api.Test
////    public void testFiltrarPorNombreProductoDisponibles() {
////
////                ProductosDAO pdao = new ProductosDAO();
////        List<Producto> listaProductosFiltrados = pdao.filtrarPorNombreProductoDisponibles("Pastel Chocolatoso");
////        System.out.println("IMPRESION DAO");
////        for (Producto producto : listaProductosFiltrados) {
////            System.out.println(producto.getNombre() + " " + producto.getEstado());
////        }
////        int cantidadProductosFiltrados = listaProductosFiltrados.size();
////        assertEquals(2 , cantidadProductosFiltrados);
////        List<Producto> listaProductosFiltrados2 = pdao.filtrarPorNombreProductoDisponibles("Pastel Loco");
////        int cantidadProductosPastelLoco = listaProductosFiltrados2.size();
////        for (Producto producto : listaProductosFiltrados2) {
////            System.out.println(producto.getNombre() + " " + producto.getEstado());
////        }
////        assertEquals(1, cantidadProductosPastelLoco);
////        
////        List<Producto> listaProductosFiltrados3 = pdao.filtrarPorNombreProductoDisponibles("");
////        int cantidadProductosPastelTodos = listaProductosFiltrados3.size();
////        assertEquals(3, cantidadProductosPastelTodos);
////        
////        
////        
////        
////    }
////
////    /**
////     * Test of filtrarPorTipoProductoDisponibles method, of class ProductosDAO.
////     */
////    @org.junit.jupiter.api.Test
////    public void testFiltrarPorTipoProductoDisponibles() {
////
////        
////        ProductosDAO pdao = new ProductosDAO();
////        List<Producto> listaProductosFiltradosTipo = pdao.filtrarPorTipoProductoDisponibles(Tipo_Producto.POSTRE);
////        int cantidadPostres = listaProductosFiltradosTipo.size();
////        List<Producto> listaProductosFiltradosTipoPLATOFUERTE = pdao.filtrarPorTipoProductoDisponibles(Tipo_Producto.PLATO_FUERTE);
////        int cantidadPLATILLOSFUERTES = listaProductosFiltradosTipoPLATOFUERTE.size();
////        List<Producto> listaProductosFiltradosTipoENTRADA = pdao.filtrarPorTipoProductoDisponibles(Tipo_Producto.ENTRADA);
////        int cantidadENTRADAS = listaProductosFiltradosTipoENTRADA.size();
////        assertEquals (cantidadPostres , 3);
////        assertEquals (cantidadPLATILLOSFUERTES , 0);
////        assertEquals (cantidadENTRADAS , 1);
////    }
////
////    /**
////     * Test of filtrarPorNombreYTipoProductoDisponibles method, of class ProductosDAO.
////     */
////    @org.junit.jupiter.api.Test
////    public void testFiltrarPorNombreYTipoProductoDisponibles() {
////
////        ProductosDAO pdao = new ProductosDAO();
////        List<Producto> ListaProductosAmbosFiltros = pdao.filtrarPorNombreYTipoProductoDisponibles("Boneless de Salida", Tipo_Producto.ENTRADA);
////        int cantidadFiltro = ListaProductosAmbosFiltros.size();
////        for (Producto producto : ListaProductosAmbosFiltros) {
////            System.out.println(producto.getNombre());
////        }
////        assertEquals(cantidadFiltro , 1);
////        
////        List<Producto> ListaProductosAmbosFiltros2 = pdao.filtrarPorNombreYTipoProductoDisponibles("Pastel", Tipo_Producto.POSTRE);
////        for (Producto producto : ListaProductosAmbosFiltros2) {
////            System.out.println(producto.getNombre());
////            
////        }
////        int cantidadFiltro2 = ListaProductosAmbosFiltros2.size();
////       assertEquals (3 , cantidadFiltro2);
////    }
////    /**
////     * Test of registrarProducto method, of class ProductosDAO.
////     */
////    @org.junit.jupiter.api.Test
////    public void testRegistrarProducto() {
////        ProductosDAO pdao = new ProductosDAO();
////        NuevoProductoDTO dto = new NuevoProductoDTO();
////        dto.setTipo(Tipo_Producto.BEBIDA);
////        dto.setEstado(Estado_Producto.HABILITADO);
////        dto.setNombre("Pepsi");
////        dto.setPrecio(200);
////        Producto productoLoco = new Producto();
////        Producto productoAñadido = pdao.registrarProducto(dto);
////        List<Producto> productoAñadidoFiltrado = pdao.filtrarPorNombreYTipoProductoDisponibles("Pepsi", Tipo_Producto.BEBIDA);
////        for (Producto producto : productoAñadidoFiltrado) {
////            productoLoco = producto;
////        }
////        
////        assertEquals (productoAñadido , productoLoco);
////    }
////
////    /**
////     * Test of deshabilitarProducto method, of class ProductosDAO.
////     */
////    @org.junit.jupiter.api.Test
////    public void testDeshabilitarProducto() {
////
////        ProductosDAO pdao = new ProductosDAO();
////        NuevoProductoDTO dto = new NuevoProductoDTO();
////        dto.setTipo(Tipo_Producto.BEBIDA);
////        dto.setEstado(Estado_Producto.HABILITADO);
////        dto.setNombre("Pepsi");
////        dto.setPrecio(200);
////        pdao.deshabilitarProducto(dto);
////        
////        
////        
////        
////    }
//
//    /**
//     * Test of registrarProductoConIngredientes method, of class ProductosDAO.
//     */
//     @Test
//    public void testRegistrarProductoConIngredientes() {
//
//        IngredientesDAO ingredientesDAO = new IngredientesDAO();
//        
//        NuevoIngredienteDTO dtoHarina = new NuevoIngredienteDTO();
//        dtoHarina.setNombre("Harina");
//        dtoHarina.setUnidad_medida("kg");
//        dtoHarina.setStock(100);
//        Ingrediente harina = ingredientesDAO.registrarIngrediente(dtoHarina);
//        assertNotNull(harina, "La Harina debe estar registrada en la BD");
//
//        NuevoIngredienteDTO dtoAzucar = new NuevoIngredienteDTO();
//        dtoAzucar.setNombre("Azúcar");
//        dtoAzucar.setUnidad_medida("kg");
//        dtoAzucar.setStock(50);
//        Ingrediente azucar = ingredientesDAO.registrarIngrediente(dtoAzucar);
//        assertNotNull(azucar, "El Azúcar debe estar registrado en la BD");
//        
//        NuevoProductoDTO nuevoProducto = new NuevoProductoDTO();
//        nuevoProducto.setNombre("Pastel de Cumpleaños");
//        nuevoProducto.setPrecio(300.0);
//        nuevoProducto.setTipo(Tipo_Producto.POSTRE);
//        nuevoProducto.setEstado(Estado_Producto.HABILITADO);
//        
//
//        List<NuevoProductoOcupaIngredienteDTO> listaIngredientes = new ArrayList<>();
//        
//        NuevoProductoOcupaIngredienteDTO prodIngrHarina = new NuevoProductoOcupaIngredienteDTO();
//        prodIngrHarina.setNombreIngrediente("Harina");
//        prodIngrHarina.setUnidadMedida("kg");
//        prodIngrHarina.setCantidadNecesariaProducto(2.0);
//        listaIngredientes.add(prodIngrHarina);
//        
//        NuevoProductoOcupaIngredienteDTO prodIngrAzucar = new NuevoProductoOcupaIngredienteDTO();
//        prodIngrAzucar.setNombreIngrediente("Azúcar");
//        prodIngrAzucar.setUnidadMedida("kg");
//        prodIngrAzucar.setCantidadNecesariaProducto(1.0);
//        listaIngredientes.add(prodIngrAzucar);
//        
//
//        ProductosDAO productosDAO = new ProductosDAO();
//        Producto productoRegistrado = productosDAO.registrarProductoConIngredientes(nuevoProducto, listaIngredientes);
//        
//
//        assertNotNull(productoRegistrado, "El producto debe haberse registrado correctamente");
//        assertEquals("Pastel de Cumpleaños", productoRegistrado.getNombre());
//        assertEquals(Estado_Producto.HABILITADO, productoRegistrado.getEstado());
//        
//
//        List<ProductoOcupaIngrediente> relaciones = productoRegistrado.getProductos();
//        assertNotNull(relaciones, "La lista de relaciones producto-ingrediente no debe ser nula");
//        assertEquals(2, relaciones.size(), "Debe haber dos relaciones creadas");
//        
//
//        boolean contieneHarina = relaciones.stream().anyMatch(rel -> 
//                rel.getIngrediente().getNombre().equals("Harina") && 
//                rel.getIngrediente().getUnidad_medida().equals("kg"));
//        boolean contieneAzucar = relaciones.stream().anyMatch(rel -> 
//                rel.getIngrediente().getNombre().equals("Azúcar") && 
//                rel.getIngrediente().getUnidad_medida().equals("kg"));
//        
//        assertTrue(contieneHarina, "El producto debe estar asociado con Harina");
//        assertTrue(contieneAzucar, "El producto debe estar asociado con Azúcar");
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    @org.junit.jupiter.api.Test
//public void testModificarProducto() {
//
//    ProductosDAO productosDAO = new ProductosDAO();
//    IngredientesDAO ingredientesDAO = new IngredientesDAO();
//
//
//    NuevoIngredienteDTO dtoHarina = new NuevoIngredienteDTO();
//    dtoHarina.setNombre("Harina");
//    dtoHarina.setUnidad_medida("kg");
//    dtoHarina.setStock(100);
//    ingredientesDAO.registrarIngrediente(dtoHarina);
//
//    NuevoIngredienteDTO dtoAzucar = new NuevoIngredienteDTO();
//    dtoAzucar.setNombre("Azúcar");
//    dtoAzucar.setUnidad_medida("kg");
//    dtoAzucar.setStock(50);
//    ingredientesDAO.registrarIngrediente(dtoAzucar);
//
//
//    NuevoProductoDTO nuevoProducto = new NuevoProductoDTO();
//    nuevoProducto.setNombre("Pastel de Chocolate");
//    nuevoProducto.setPrecio(300.0);
//    nuevoProducto.setTipo(Tipo_Producto.POSTRE);
//    nuevoProducto.setEstado(Estado_Producto.HABILITADO);
//
//    List<NuevoProductoOcupaIngredienteDTO> listaIngredientes = new ArrayList<>();
//
//    NuevoProductoOcupaIngredienteDTO prodIngrHarina = new NuevoProductoOcupaIngredienteDTO();
//    prodIngrHarina.setNombreIngrediente("Harina");
//    prodIngrHarina.setUnidadMedida("kg");
//    prodIngrHarina.setCantidadNecesariaProducto(2.0);
//    listaIngredientes.add(prodIngrHarina);
//
//    NuevoProductoOcupaIngredienteDTO prodIngrAzucar = new NuevoProductoOcupaIngredienteDTO();
//    prodIngrAzucar.setNombreIngrediente("Azúcar");
//    prodIngrAzucar.setUnidadMedida("kg");
//    prodIngrAzucar.setCantidadNecesariaProducto(1.0);
//    listaIngredientes.add(prodIngrAzucar);
//
//    productosDAO.registrarProductoConIngredientes(nuevoProducto, listaIngredientes);
//
//
//    NuevoProductoDTO productoModificado = new NuevoProductoDTO();
//    productoModificado.setNombre("Pastel de Chocolate");
//    productoModificado.setPrecio(999.0); 
//    productoModificado.setTipo(Tipo_Producto.SNACK); 
//
//    List<NuevoProductoOcupaIngredienteDTO> nuevaListaIngredientes = new ArrayList<>();
//
//
//    NuevoProductoOcupaIngredienteDTO prodIngrHarinaMod = new NuevoProductoOcupaIngredienteDTO();
//    prodIngrHarinaMod.setNombreIngrediente("Harina");
//    prodIngrHarinaMod.setUnidadMedida("kg");
//    prodIngrHarinaMod.setCantidadNecesariaProducto(2.5);
//    nuevaListaIngredientes.add(prodIngrHarinaMod);
//
//    NuevoIngredienteDTO dtoLeche = new NuevoIngredienteDTO();
//    dtoLeche.setNombre("Leche");
//    dtoLeche.setUnidad_medida("litro");
//    dtoLeche.setStock(30);
//    ingredientesDAO.registrarIngrediente(dtoLeche);
//
//    NuevoProductoOcupaIngredienteDTO prodIngrLeche = new NuevoProductoOcupaIngredienteDTO();
//    prodIngrLeche.setNombreIngrediente("Leche");
//    prodIngrLeche.setUnidadMedida("litro");
//    prodIngrLeche.setCantidadNecesariaProducto(1.0);
//    nuevaListaIngredientes.add(prodIngrLeche);
//
//    productosDAO.modificarProducto(productoModificado, nuevaListaIngredientes);
//
//
//    Producto productoActualizado = productosDAO.buscarProductoPorNombre("Pastel de Chocolate");
//    assertNotNull(productoActualizado, "El producto debe existir después de la modificación");
//    assertEquals(999.0, productoActualizado.getPrecio(), "El precio debe haber sido actualizado");
//    assertEquals(Tipo_Producto.SNACK, productoActualizado.getTipo(), "El tipo de producto debe haber cambiado");
//
//    List<ProductoOcupaIngrediente> relaciones = productoActualizado.getProductos();
//    assertNotNull(relaciones, "Las relaciones deben existir");
//    assertEquals(2, relaciones.size(), "Debe haber exactamente dos ingredientes después de la modificación");
//
//    boolean contieneHarina = relaciones.stream().anyMatch(rel ->
//            rel.getIngrediente().getNombre().equals("Harina") &&
//                    rel.getIngrediente().getUnidad_medida().equals("kg"));
//    boolean contieneLeche = relaciones.stream().anyMatch(rel ->
//            rel.getIngrediente().getNombre().equals("Leche") &&
//                    rel.getIngrediente().getUnidad_medida().equals("litro"));
//    boolean contieneAzucar = relaciones.stream().anyMatch(rel ->
//            rel.getIngrediente().getNombre().equals("Azúcar"));
//
//    assertTrue(contieneHarina, "El producto debe seguir teniendo Harina");
//    assertTrue(contieneLeche, "El producto debe haber agregado Leche");
//    assertFalse(contieneAzucar, "El producto ya no debe contener Azúcar");
//}
//
//}

