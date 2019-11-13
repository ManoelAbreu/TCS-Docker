package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import interfaces.DaoI;
import java.util.concurrent.ExecutionException;
import javax.persistence.Query;
import model.Mesa;
import util.JpaUtil;

public class MesaDao implements DaoI<Mesa> {

    private EntityManager manager;

    private EntityTransaction trx;

    private TypedQuery<Mesa> query;

    @Override
    public Mesa salvar(Mesa mesa) {

        manager = JpaUtil.getEntityManager();
        trx = manager.getTransaction();

        try {
            trx.begin();
            mesa = manager.merge(mesa);
            trx.commit();

            return mesa;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            if (trx.isActive()) {
                trx.rollback();
            }
            manager.close();
        }
    }

    @Override
    public void deletar(Mesa mesa) {

        manager = JpaUtil.getEntityManager();
        trx = manager.getTransaction();

        try {
            trx.begin();
            manager.remove(manager.find(Mesa.class, mesa.getId()));
            trx.commit();
        } finally {
            if (trx.isActive()) {
                trx.rollback();
            }
            manager.close();
        }
    }

    @Override
    public List<Mesa> listar() {

        manager = JpaUtil.getEntityManager();
        query = manager.createQuery("from Mesa where situacao = 1", Mesa.class);
        try {

            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }

    }

    public List<Mesa> listarParaPedido() {

        manager = JpaUtil.getEntityManager();
        query = manager.createQuery("from Mesa where situacao = 1 and ocupacao= 'DISPONIVEL' or ocupacao = 'OCUPADA' ", Mesa.class);
        try {

            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }

    }

    public List<Mesa> listarParaGarcom() {

        manager = JpaUtil.getEntityManager();
        query = manager.createQuery("from Mesa where ocupacao = 'OCUPADA' ", Mesa.class);
        try {

            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }

    }

    public List<Mesa> listarDesativados() {

        manager = JpaUtil.getEntityManager();
        query = manager.createQuery("from Mesa where situacao = 0", Mesa.class);
        try {

            return query.getResultList();

        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }

    }

    @Override
    public List<Mesa> listarPorNome(String txt) {

        manager = JpaUtil.getEntityManager();
        query = manager.createQuery("from Mesa where numero LIKE :numero", Mesa.class);
        query.setParameter("numero", "%" + txt + "%");

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }
    }

    public long numeroMesa() {
        EntityManager manager2 = JpaUtil.getEntityManager();

        Query query2 = manager2.createQuery("select max (numero)+1 from Mesa");
        try {
            return (long) query2.getSingleResult();
        } catch (Exception e) {
            return 1;
        } finally {
            manager2.close();
        }
    }

    @Override
    public Mesa pesquisarPorID(Long id) {

        manager = JpaUtil.getEntityManager();

        try {
            return manager.find(Mesa.class, id);
        } finally {
            manager.close();
        }
    }

    public Mesa pesquisarPorNumero(Long numero) {

        manager = JpaUtil.getEntityManager();
        try {
            return manager.find(Mesa.class, numero);
        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }
    }

    public Mesa pesquisarPorQRCode(String qrCode) {

        manager = JpaUtil.getEntityManager();
        query = manager.createQuery("from Mesa where qrCode LIKE :qrCode", Mesa.class);
        query.setParameter("qrCode", qrCode);

        try {
            return query.getSingleResult();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            manager.close();
        }
    }

    public List<Mesa> listarPorNumero(Long numero) {

        manager = JpaUtil.getEntityManager();
        query
                = manager.createQuery("from Mesa where numero LIKE :numero", Mesa.class
                );
        query.setParameter("numero", numero);

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }
    }
}
