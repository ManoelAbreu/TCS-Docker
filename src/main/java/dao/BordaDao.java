package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import interfaces.DaoI;
import model.Borda;
import model.Pedido;
import util.JpaUtil;

public class BordaDao implements DaoI<Borda> {

	private EntityManager manager;

	private EntityTransaction trx;

	private TypedQuery<Borda> query;

	@Override
	public Borda salvar(Borda borda) {

		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();

		try {
			trx.begin();
			borda = manager.merge(borda);
			trx.commit();

			return borda;
		} catch (Exception e) {
			return null;
		} finally {
			if (trx.isActive()) {
				trx.rollback();
			}
			manager.close();
		}
	}

	@Override
	public void deletar(Borda borda) {

		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();

		try {
			trx.begin();
			manager.remove(manager.find(Borda.class, borda.getId()));
			trx.commit();

		} finally {
			if (trx.isActive()) {
				trx.rollback();
			}
			manager.close();
		}
	}

	@Override
	public List<Borda> listar() {
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Borda", Borda.class);
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}

	}

	@Override
	public List<Borda> listarPorNome(String txt) {
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Borda where nome LIKE :nome", Borda.class);
		query.setParameter("nome", "%"+txt+"%");
		
		try {
			return query.getResultList();
		} catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}

	}

	@Override
	public Borda pesquisarPorID(Long id) {
		
		manager = JpaUtil.getEntityManager();
		
		try {
			return manager.find(Borda.class, id);
			
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
		
		
	}

}
