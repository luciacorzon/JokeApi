package org.example;

import javax.persistence.EntityManagerFactory;

public class ChisteDownloader implements Runnable {

    private static final long tiempoEspera = 550;
    private static ChisteDownloader instance;
    public static final int MAX_CHISTES = 200;
    private ChisteDAO chisteDAO;
    private int numeroChistes;

    public ChisteDownloader() {
        chisteDAO = new ChisteDAO();
        numeroChistes = MAX_CHISTES;
    }

    public ChisteDownloader(int numeroChistes) {
        chisteDAO = new ChisteDAO();
        this.numeroChistes = numeroChistes;
    }

    public static ChisteDownloader getInstance() {
        if (instance == null) {
            synchronized (ChisteDownloader.class) {
                if (instance == null) {
                    instance = new ChisteDownloader();
                }
            }
        }
        return instance;
    }


    @Override
    public void run() {
        // Pasáselle a unidade de persistencia ao JPAManager para obter un EntityManagerFactory asociado a ela
        var emf = ChisteJPAManager.getEntityManagerFactory(ChisteJPAManager.CHISTES_H2);
        // Créase un EntityManager asociado ao factory
        var em = emf.createEntityManager();
        for (int i = 0; i < numeroChistes; i++) {
            // Obtéñense os chistes da API a través do DAO e convértense en un obxecto Chiste
            Chiste chiste = chisteDAO.getJokeById(i);
            if (chiste != null) {
                try {
                    // Persístese o chiste na BD co EntityManager (facendo unha transacción por se hai fallos)
                    em.getTransaction().begin();
                    em.persist(chiste);
                    em.getTransaction().commit();
                } catch (Exception e) {
                    em.getTransaction().rollback();
                }
                System.out.print("*");
                try {
                    Thread.sleep(tiempoEspera);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public int getNumeroChistes() {
        return numeroChistes;
    }

    public void setNumeroChistes(int numeroChistes) {
        this.numeroChistes = numeroChistes;
    }

    public ChisteDAO getChisteDAO() {
        return chisteDAO;
    }

    public void setChisteDAO(ChisteDAO chisteDAO) {
        this.chisteDAO = chisteDAO;
    }
}
