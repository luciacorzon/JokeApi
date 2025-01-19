package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class ChisteJPAManager {
    public static final String CHISTES_H2 = "chistesh2";

    private static final Map<String, EntityManagerFactory> instancies = new HashMap<>();

    private ChisteJPAManager() {
    }

    // Comproba se o EntityManagerFactory asociado á unidade de persistencia que se lle pasa
    // está pechado ou non e se existe
    private static boolean isEntityManagerFactoryClosed(String unidadPersistencia){
        return !instancies.containsKey(unidadPersistencia) || instancies.get(unidadPersistencia) == null
                || !instancies.get(unidadPersistencia).isOpen();
    }

    // Devolve o EntityManagerFactory asociado á unidade de persistencia
    public static EntityManagerFactory getEntityManagerFactory(String unidadPersistencia){
        if (isEntityManagerFactoryClosed(unidadPersistencia)) {
            synchronized (ChisteJPAManager.class) {
                if (isEntityManagerFactoryClosed(unidadPersistencia)){
                    try {
                        instancies.put(unidadPersistencia, Persistence.createEntityManagerFactory(unidadPersistencia));
                    } catch (Exception e){
                        System.err.println("Erro ó crear a unidade de persistencia " + unidadPersistencia + ": " + e.getMessage());
                    }

                }
            }
        }
        return instancies.get(unidadPersistencia);
    }

    // Devolve o entityManager asociado á unidade de persistencia
    public static EntityManager getEntityManager (String persistenceUnitName){
        return getEntityManagerFactory(persistenceUnitName).createEntityManager();
    }

    public static void close(String persistenceUnitName){
        if (instancies.containsKey(persistenceUnitName)){
            instancies.get(persistenceUnitName).close();
            instancies.remove(persistenceUnitName);
        }
    }
}
