/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ManejadorConexiones;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Este bloque de código define una clase que gestiona la creación de un
 * `EntityManagerFactory`, que es fundamental para la interacción con la base de
 * datos en JPA.
 *
 * 1. Se declara una constante estática `emFactory` que se inicializa utilizando
 * `Persistence.createEntityManagerFactory`, pasando como argumento el nombre de
 * la unidad de persistencia definida en el archivo de configuración
 * (persistence.xml). Este objeto es responsable de crear instancias de
 * `EntityManager`, que se utilizan para realizar operaciones de persistencia.
 * 2. El método `getEntityManager` es un método estático que devuelve una nueva
 * instancia de `EntityManager` a partir del `EntityManagerFactory`. Cada vez
 * que se llama a este método, se crea un nuevo `EntityManager`, lo que permite
 * realizar operaciones de base de datos de manera independiente y segura en
 * diferentes hilos o contextos. Este enfoque centraliza la gestión de la
 * conexión a la base de datos y facilita la creación de `EntityManager`,
 * promoviendo una buena práctica en la arquitectura de la aplicación.
 *
 * @author Ariel Eduardo Borbon Izaguirre 252116
 * @author Alberto Jimenez Garcia 252595
 */

public class ManejadorConexiones {

    private static final EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("com.mycompany_RestauranteDominio_jar_1.0-SNAPSHOTPU");

    /*
Este método estático se encarga de proporcionar una instancia de `EntityManager`, que es un componente fundamental en la gestión de la persistencia de datos en aplicaciones que utilizan Java Persistence API (JPA).

1. **Método**: `getEntityManager()`
2. **Tipo de retorno**: `EntityManager`
3. **Funcionalidad**:
   - Llama al método `createEntityManager()` del objeto `emFactory`, que es una instancia de `EntityManagerFactory`.
   - `EntityManagerFactory` es responsable de crear instancias de `EntityManager`, que se utilizan para interactuar con la base de datos, realizar operaciones de consulta, actualización, eliminación y gestión de transacciones.

Este método es útil para obtener un nuevo `EntityManager` cada vez que se necesita interactuar con la base de datos, asegurando que cada operación de persistencia se realice en un contexto de persistencia adecuado. Al utilizar este enfoque, se facilita la gestión de conexiones a la base de datos y se promueve un diseño más limpio y modular en la aplicación.
     */
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }

}
