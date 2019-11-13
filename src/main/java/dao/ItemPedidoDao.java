package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import model.ItemPedido;
import util.JpaUtil;

public class ItemPedidoDao {

    private EntityManager manager;

    private EntityTransaction trx;

    private TypedQuery<ItemPedido> query;

    public List<ItemPedido> listar() {

        this.manager = JpaUtil.getEntityManager();
        this.query = this.manager.createQuery("from ItemPedido", ItemPedido.class);

        try {
            return this.query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            manager.close();
        }
    }


    public ItemPedido pesquisarPorID(Long id) {

        manager = JpaUtil.getEntityManager();

        try {
            return manager.find(ItemPedido.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }

    }

    public ItemPedido salvar(ItemPedido itemPedido) {

        manager = JpaUtil.getEntityManager();
        trx = manager.getTransaction();

        try {
            trx.begin();
            itemPedido = manager.merge(itemPedido);
            trx.commit();

            return itemPedido;

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

}
