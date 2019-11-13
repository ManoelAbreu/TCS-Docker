package dao;

import com.mysql.fabric.xmlrpc.base.Data;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import interfaces.DaoI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import model.Mesa;
import model.Pedido;
import util.JpaUtil;

public class PedidoDao implements DaoI<Pedido> {

    private EntityManager manager;

    private EntityTransaction trx;

    private TypedQuery<Pedido> query;

    @Override
    public Pedido salvar(Pedido pedido) {

        manager = JpaUtil.getEntityManager();
        trx = manager.getTransaction();

        try {
            trx.begin();
            pedido = manager.merge(pedido);
            trx.commit();

            return pedido;
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
    public void deletar(Pedido pedido) {

        manager = JpaUtil.getEntityManager();
        trx = manager.getTransaction();

        try {
            trx.begin();
            manager.remove(manager.find(Pedido.class, pedido));
            trx.commit();

        } finally {
            if (trx.isActive()) {
                trx.rollback();
            }
            manager.close();
        }
    }

    @Override
    public List<Pedido> listar() {

        this.manager = JpaUtil.getEntityManager();
        this.query = this.manager.createQuery("from Pedido order by dataPedido desc", Pedido.class);

        try {
            return this.query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            manager.close();
        }
    }
    public List<Pedido> listarCaixa() {
    	
    	this.manager = JpaUtil.getEntityManager();
    	this.query = this.manager.createQuery("from Pedido where statusPedido = 'ABERTO' order by dataPedido desc", Pedido.class);
    	
    	try {
    		return this.query.getResultList();
    	} catch (Exception e) {
    		System.out.println(e);
    		return null;
    	} finally {
    		manager.close();
    	}
    }
    
    
    public List<Pedido> listarCozinha() {

        this.manager = JpaUtil.getEntityManager();
        this.query = this.manager.createQuery("from Pedido where statusPedido = 'ABERTO' order by dataPedido asc", Pedido.class);

        try {
            return this.query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            manager.close();
        }
    }
    public List<Pedido> listarPorMesa(Long mesa) {

        this.manager = JpaUtil.getEntityManager();
        this.query = this.manager.createQuery("from Pedido where statusPedido = 'ABERTO' and mesa_id = "+mesa+ " order by dataPedido asc", Pedido.class);

        try {
            return this.query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            manager.close();
        }
    }

    public List<Pedido> listarQuery(Pedido pedidoQuery) throws ParseException {

        String hql = "from Pedido p where 1=1";
        if (pedidoQuery.getMesa() != null) {
            hql += " and p.mesa = " + pedidoQuery.getMesa().getId();
        }

        if (pedidoQuery.getNomeCliente() != null && pedidoQuery.getNomeCliente() != "") {
            hql += " and p.nomeCliente like " + "'%" + pedidoQuery.getNomeCliente() + "%'";
        }

        if (pedidoQuery.getStatusPedido() != null) {
            hql += " and p.statusPedido = '" + pedidoQuery.getStatusPedido() + "'";
        }

        if (pedidoQuery.getStatusPreparo() != null) {
            hql += " and p.statusPreparo = '" + pedidoQuery.getStatusPreparo() + "'";
        }

        if (pedidoQuery.getDataPedido() != null) {
             SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
             String data = sdf.format(pedidoQuery.getDataPedido());
            hql += " and p.dataPedido >= '" + data+"'" ;
        }
        hql += " order by dataPedido desc";

        this.manager = JpaUtil.getEntityManager();
        this.query = this.manager.createQuery(hql, Pedido.class);

        try {
            return this.query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public List<Pedido> listarPorNome(String txt) {

        manager = JpaUtil.getEntityManager();
        query = manager.createQuery("from Pedido where nome_cliente LIKE :nome", Pedido.class);
        query.setParameter("nome", "%" + txt + "%");

        try {
            return query.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public Pedido pesquisarPorID(Long id) {

        manager = JpaUtil.getEntityManager();

        try {
            return manager.find(Pedido.class, id);
        } catch (Exception e) {
            return null;
        } finally {
            manager.close();
        }

    }

}
