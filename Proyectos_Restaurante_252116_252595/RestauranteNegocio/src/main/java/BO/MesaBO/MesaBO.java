/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO.MesaBO;

import DAO.Mesas.MesasDAO;
import DTOS.Mesa.NuevaMesaDTO;
import Entidades.Mesa.EstadoMesa;
import Entidades.Mesa.Mesa;
import java.util.List;

/**
 *
 * @author PC Gamer
 */
public class MesaBO implements IMesaBO  {
    private MesasDAO mesaDAO;

    public MesaBO(MesasDAO mesaDAO) {
        this.mesaDAO = mesaDAO;
    }
    
    
    
    /*
Este método se encarga de crear una nueva mesa en el sistema, validando la información proporcionada en un objeto `NuevaMesaDTO` antes de proceder con la operación de creación.

1. **Método**: `crearMesaBO(NuevaMesaDTO nuevaMesaDTO)`
2. **Parámetro**: 
   - `nuevaMesaDTO`: Un objeto de tipo `NuevaMesaDTO` que contiene la información necesaria para crear una nueva mesa.
3. **Excepciones**: 
   - Lanza `IllegalArgumentException` si se detectan problemas con los datos proporcionados.

4. **Funcionalidad**:
   - Se verifica si `nuevaMesaDTO` es `null`. Si es así, se lanza una `IllegalArgumentException` con un mensaje que indica que el DTO no puede ser nulo.
   - Se valida que el número de mesa (`num_mesa`) sea mayor a cero. Si no lo es, se lanza una `IllegalArgumentException` correspondiente.
   - Se valida que el estado de la mesa no sea nulo. Si es nulo, se lanza una `IllegalArgumentException` correspondiente.
   - Se busca si ya existe una mesa con el número proporcionado utilizando el método `obtenerMesaPorNumMesa` del `mesaDAO`. Si se encuentra una mesa existente, se lanza una `IllegalArgumentException` indicando que ya existe una mesa con ese número.
   - Si todas las validaciones son satisfactorias y no existe una mesa con el mismo número, se llama al método `crearMesa` del `mesaDAO`, pasando el `nuevaMesaDTO` para realizar la creación en la base de datos.

Este método es útil para asegurar que solo se creen mesas válidas y que se manejen adecuadamente los errores, promoviendo la integridad de los datos en el sistema y permitiendo la adición de nuevas mesas de manera controlada.
*/
    @Override
    public Mesa crearMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }

        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }

        if (nuevaMesaDTO.getEstado() == null) {
            throw new IllegalArgumentException("El estado de la mesa no puede ser null");
        }

        Mesa existente = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        
        if (existente != null) {
            throw new IllegalArgumentException("Ya existe una mesa con el número proporcionado");
        }
        
        return mesaDAO.crearMesa(nuevaMesaDTO);
    }
    
    
    
    /*
Este método se encarga de eliminar una mesa del sistema, validando la información proporcionada en un objeto `NuevaMesaDTO` antes de proceder con la operación de eliminación.

1. **Método**: `eliminarMesaBO(NuevaMesaDTO nuevaMesaDTO)`
2. **Parámetro**: 
   - `nuevaMesaDTO`: Un objeto de tipo `NuevaMesaDTO` que contiene la información necesaria para identificar la mesa que se desea eliminar.
3. **Excepciones**: 
   - Lanza `IllegalArgumentException` si se detectan problemas con los datos proporcionados.

4. **Funcionalidad**:
   - Se verifica si `nuevaMesaDTO` es `null`. Si es así, se lanza una `IllegalArgumentException` con un mensaje que indica que el DTO no puede ser nulo.
   - Se valida que el número de mesa (`num_mesa`) sea mayor a cero. Si no lo es, se lanza una `IllegalArgumentException` correspondiente.
   - Se busca la mesa correspondiente al número proporcionado utilizando el método `obtenerMesaPorNumMesa` del `mesaDAO`. Si no se encuentra una mesa con ese número, se lanza una `IllegalArgumentException` indicando que no existe una mesa con ese número.
   - Si todas las validaciones son satisfactorias y se encuentra la mesa, se llama al método `eliminarMesa` del `mesaDAO`, pasando el `nuevaMesaDTO` para realizar la eliminación en la base de datos.

Este método es útil para asegurar que solo se eliminen mesas válidas y que se manejen adecuadamente los errores, promoviendo la integridad de los datos en el sistema y permitiendo la eliminación de mesas de manera controlada. Además, ayuda a evitar intentos de eliminación de mesas que no existen, lo que podría causar inconsistencias en la base de datos.
*/
    @Override
    public void eliminarMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }

        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }

        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número");
        }

        mesaDAO.eliminarMesa(nuevaMesaDTO);
    }    
    
    
    
    /*
Este método se encarga de recuperar una mesa específica del sistema utilizando su número como parámetro.

1. **Método**: `obtenerMesaPorNumMesaBO(int numeroMesa)`
2. **Parámetro**: 
   - `numeroMesa`: Un `int` que representa el número de la mesa que se desea obtener.
3. **Tipo de retorno**: `Mesa`
4. **Funcionalidad**:
   - Llama al método `obtenerMesaPorNumMesa` del `mesaDAO`, que se espera que devuelva un objeto `Mesa` correspondiente al número de mesa proporcionado.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `numeroMesa` es válido y que el `mesaDAO` está correctamente configurado para realizar la consulta.

Este método es útil para obtener información sobre una mesa específica en el sistema, permitiendo a otras partes de la aplicación acceder a los detalles de la mesa de manera sencilla. Es común que este tipo de métodos se utilicen en la lógica de negocio para facilitar la gestión de mesas en aplicaciones de restaurantes, bares o cualquier sistema que requiera la administración de espacios de servicio.
*/
    
    
    
    @Override
    public Mesa obtenerMesaPorNumMesaBO(int numeroMesa)   {
        return mesaDAO.obtenerMesaPorNumMesa(numeroMesa);
    }
    
    
    
    
    
    /*
Este método se encarga de marcar una mesa como ocupada en el sistema, validando la información proporcionada en un objeto `NuevaMesaDTO` antes de proceder con la operación de ocupación.

1. **Método**: `ocuparMesaBO(NuevaMesaDTO nuevaMesaDTO)`
2. **Parámetro**: 
   - `nuevaMesaDTO`: Un objeto de tipo `NuevaMesaDTO` que contiene la información necesaria para identificar la mesa que se desea ocupar.
3. **Excepciones**: 
   - Lanza `IllegalArgumentException` si se detectan problemas con los datos proporcionados.
   - Lanza `IllegalStateException` si la mesa ya está ocupada.

4. **Funcionalidad**:
   - Se verifica si `nuevaMesaDTO` es `null`. Si es así, se lanza una `IllegalArgumentException` con un mensaje que indica que el DTO no puede ser nulo.
   - Se valida que el número de mesa (`num_mesa`) sea mayor a cero. Si no lo es, se lanza una `IllegalArgumentException` correspondiente.
   - Se busca la mesa correspondiente al número proporcionado utilizando el método `obtenerMesaPorNumMesa` del `mesaDAO`. Si no se encuentra una mesa con ese número, se lanza una `IllegalArgumentException` indicando que no existe una mesa con ese número.
   - Se verifica el estado de la mesa. Si la mesa ya está ocupada (`EstadoMesa.OCUPADA`), se lanza una `IllegalStateException` indicando que la mesa ya se encuentra ocupada.
   - Si todas las validaciones son satisfactorias y la mesa está disponible, se llama al método `ocuparMesa` del `mesaDAO`, pasando el `nuevaMesaDTO` para realizar la ocupación en la base de datos.

Este método es útil para asegurar que solo se ocupen mesas válidas y disponibles, y que se manejen adecuadamente los errores, promoviendo la integridad de los datos en el sistema. Además, ayuda a evitar intentos de ocupar mesas que ya están en uso, lo que podría causar confusiones y problemas en la gestión del servicio.
*/
    
    @Override
    public Mesa ocuparMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }
    
        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }
    
        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número");
        }
    
        if (mesa.getEstado() == EstadoMesa.OCUPADA) {
            throw new IllegalStateException("La mesa ya se encuentra ocupada");
        }
    
        return mesaDAO.ocuparMesa(nuevaMesaDTO);
    }    
    
    



/*
Este método se encarga de marcar una mesa como disponible en el sistema, validando la información proporcionada en un objeto `NuevaMesaDTO` antes de proceder con la operación de disponibilización.

1. **Método**: `disponibilizarMesaBO(NuevaMesaDTO nuevaMesaDTO)`
2. **Parámetro**: 
   - `nuevaMesaDTO`: Un objeto de tipo `NuevaMesaDTO` que contiene la información necesaria para identificar la mesa que se desea disponibilizar.
3. **Excepciones**: 
   - Lanza `IllegalArgumentException` si se detectan problemas con los datos proporcionados.
   - Lanza `IllegalStateException` si la mesa ya está disponible.

4. **Funcionalidad**:
   - Se verifica si `nuevaMesaDTO` es `null`. Si es así, se lanza una `IllegalArgumentException` con un mensaje que indica que el DTO no puede ser nulo.
   - Se valida que el número de mesa (`num_mesa`) sea mayor a cero. Si no lo es, se lanza una `IllegalArgumentException` correspondiente.
   - Se busca la mesa correspondiente al número proporcionado utilizando el método `obtenerMesaPorNumMesa` del `mesaDAO`. Si no se encuentra una mesa con ese número, se lanza una `IllegalArgumentException` indicando que no existe una mesa con ese número.
   - Se verifica el estado de la mesa. Si la mesa ya está disponible (`EstadoMesa.DISPONIBLE`), se lanza una `IllegalStateException` indicando que la mesa ya está disponible.
   - Si todas las validaciones son satisfactorias y la mesa está ocupada, se llama al método `disponibilizarMesa` del `mesaDAO`, pasando el `nuevaMesaDTO` para realizar la disponibilización en la base de datos.

Este método es útil para asegurar que solo se disponibilicen mesas válidas y que están actualmente ocupadas, y que se manejen adecuadamente los errores, promoviendo la integridad de los datos en el sistema. Además, ayuda a evitar intentos de disponibilizar mesas que ya están libres, lo que podría causar confusiones en la gestión del servicio.
*/



    
    @Override
    public Mesa disponibilizarMesaBO(NuevaMesaDTO nuevaMesaDTO) {
        if (nuevaMesaDTO == null) {
            throw new IllegalArgumentException("El DTO de nueva mesa no puede ser null");
        }
    
        if (nuevaMesaDTO.getNum_mesa() <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero");
        }
    
        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(nuevaMesaDTO.getNum_mesa());
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número");
        }
    
        if (mesa.getEstado() == EstadoMesa.DISPONIBLE) {
            throw new IllegalStateException("La mesa ya está disponible");
        }
    
        return mesaDAO.disponibilizarMesa(nuevaMesaDTO);
    }
    
    
    
    
    /*
Este método se encarga de verificar las relaciones asociadas a una mesa específica en el sistema, utilizando su número como parámetro.

1. **Método**: `verRelacionesMesaBO(int num_mesa)`
2. **Parámetro**: 
   - `num_mesa`: Un `int` que representa el número de la mesa de la cual se desean verificar las relaciones.
3. **Tipo de retorno**: `boolean`
4. **Excepciones**: 
   - Lanza `IllegalArgumentException` si el número de mesa es menor o igual a cero o si no existe una mesa con ese número.

5. **Funcionalidad**:
   - Se valida que el número de mesa (`num_mesa`) sea mayor a cero. Si no lo es, se lanza una `IllegalArgumentException` con un mensaje correspondiente.
   - Se busca la mesa correspondiente al número proporcionado utilizando el método `obtenerMesaPorNumMesa` del `mesaDAO`. Si no se encuentra una mesa con ese número, se lanza una `IllegalArgumentException` indicando que no existe una mesa con ese número.
   - Si todas las validaciones son satisfactorias, se llama al método `verRelacionesMesa` del `mesaDAO`, pasando el `num_mesa` para verificar las relaciones asociadas a esa mesa.

Este método es útil para determinar si una mesa tiene relaciones activas o asociadas en el sistema, lo que puede ser importante para la gestión de reservas, asignaciones o cualquier otra funcionalidad que dependa del estado de la mesa. Además, al manejar adecuadamente las excepciones, se asegura que solo se realicen consultas válidas, promoviendo la integridad de los datos en el sistema.
*/
    
    @Override
    public boolean verRelacionesMesaBO(int num_mesa) {
        if (num_mesa <= 0) {
            throw new IllegalArgumentException("El número de mesa debe ser mayor a cero.");
        }
    
        Mesa mesa = mesaDAO.obtenerMesaPorNumMesa(num_mesa);
        if (mesa == null) {
            throw new IllegalArgumentException("No existe una mesa con ese número.");
        }
    
        return mesaDAO.verRelacionesMesa(num_mesa);
    }

    
    
    
    
    /*
Este método se encarga de recuperar una lista de mesas que están disponibles en el sistema.

1. **Método**: `obtenerListaMesasDisponiblesBO()`
2. **Tipo de retorno**: `List<Mesa>`
3. **Funcionalidad**:
   - Llama al método `obtenerListaMesasDisponibles` del `mesaDAO`, que se espera que devuelva una lista de objetos `Mesa` que están actualmente disponibles para ser ocupadas.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `mesaDAO` está correctamente configurado para realizar la consulta.

Este método es útil para obtener una lista de mesas que están libres y disponibles para ser ocupadas, lo que es esencial en aplicaciones de gestión de restaurantes, bares o cualquier sistema que requiera la administración de espacios de servicio. Facilita la toma de decisiones sobre la asignación de mesas a clientes y mejora la eficiencia en la gestión del servicio.
*/
    
    @Override
    public List<Mesa> obtenerListaMesasDisponiblesBO() {
        return mesaDAO.obtenerListaMesasDisponibles();
    }
    
    /*
Este método se encarga de recuperar una lista de mesas que están ocupadas en el sistema.

1. **Método**: `obtenerListaMesasOcupadasBO()`
2. **Tipo de retorno**: `List<Mesa>`
3. **Funcionalidad**:
   - Llama al método `obtenerListaMesasOcupadas` del `mesaDAO`, que se espera que devuelva una lista de objetos `Mesa` que están actualmente ocupadas.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `mesaDAO` está correctamente configurado para realizar la consulta.

Este método es útil para obtener una lista de mesas que están en uso, lo que es esencial en aplicaciones de gestión de restaurantes, bares o cualquier sistema que requiera la administración de espacios de servicio. Facilita la supervisión del estado de las mesas y ayuda en la planificación de la atención al cliente, permitiendo a los administradores y al personal del servicio tomar decisiones informadas sobre la gestión de las mesas.
*/
    
    
    
    @Override
    public List<Mesa> obtenerListaMesasOcupadasBO() {
        return mesaDAO.obtenerListaMesasOcupadas();
    }
    
    
    
    /*
Este método se encarga de recuperar una lista de todas las mesas en el sistema, independientemente de su estado (disponibles, ocupadas, etc.).

1. **Método**: `obtenerListaMesasTodasBO()`
2. **Tipo de retorno**: `List<Mesa>`
3. **Funcionalidad**:
   - Llama al método `obtenerListaMesasTodas` del `mesaDAO`, que se espera que devuelva una lista de objetos `Mesa` que incluye todas las mesas registradas en el sistema.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `mesaDAO` está correctamente configurado para realizar la consulta.

Este método es útil para obtener una visión completa de todas las mesas disponibles en el sistema, lo que puede ser esencial para la gestión y planificación en aplicaciones de restaurantes, bares o cualquier sistema que requiera la administración de espacios de servicio. Permite a los administradores y al personal del servicio tener un panorama general de la disponibilidad y el estado de las mesas, facilitando la toma de decisiones sobre la asignación y gestión de las mismas.
*/
    
    @Override
    public List<Mesa> obtenerListaMesasTodasBO() {
        return mesaDAO.obtenerListaMesasTodas();
    }
    
    
    /*
Este método se encarga de crear una nueva mesa en el sistema y devolver el objeto `Mesa` correspondiente.

1. **Método**: `crearMesaEnOrdenBO()`
2. **Tipo de retorno**: `Mesa`
3. **Funcionalidad**:
   - Llama al método `crearMesaEnOrden` del `mesaDAO`, que se espera que realice la operación de creación de una nueva mesa en la base de datos y devuelva el objeto `Mesa` que representa la mesa recién creada.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `mesaDAO` está correctamente configurado para realizar la operación de creación.

Este método es útil para agregar nuevas mesas al sistema, lo que es esencial en aplicaciones de gestión de restaurantes, bares o cualquier sistema que requiera la administración de espacios de servicio. Permite a los administradores y al personal del servicio expandir la capacidad del establecimiento al añadir nuevas mesas según sea necesario.
*/
    
    
    @Override
    public Mesa crearMesaEnOrdenBO(){
        return mesaDAO.crearMesaEnOrden();
        
    }
    
    
    /*
Este método se encarga de verificar si existen mesas registradas en el sistema.

1. **Método**: `existenMesasBO()`
2. **Tipo de retorno**: `boolean`
3. **Funcionalidad**:
   - Llama al método `existenMesas` del `mesaDAO`, que se espera que devuelva un valor booleano indicando si hay mesas registradas en la base de datos.
   - Este método no incluye validaciones ni manejo de excepciones, lo que implica que se asume que el `mesaDAO` está correctamente configurado para realizar la consulta.

Este método es útil para determinar si hay mesas disponibles en el sistema antes de realizar operaciones que dependan de la existencia de mesas, como la creación de nuevas mesas o la gestión de reservas. Ayuda a optimizar la lógica de negocio y a evitar errores al interactuar con el sistema de gestión de mesas.
*/
    
    public boolean existenMesasBO() {
    return mesaDAO.existenMesas();
}

    

    
    

           
    
    
}
