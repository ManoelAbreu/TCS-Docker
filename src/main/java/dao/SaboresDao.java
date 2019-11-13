package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import interfaces.DaoI;
import model.Sabores;
import util.JpaUtil;

public class SaboresDao implements DaoI<Sabores>{

	private EntityManager manager;
	
	private EntityTransaction trx;
	
	private TypedQuery<Sabores> query;

	@Override
	public Sabores salvar(Sabores sabores) {
		
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			trx.begin();
			sabores = manager.merge(sabores);
			trx.commit();
			
			return sabores;
			
		} catch (Exception e) {
			return null;
		}finally {
			if(trx.isActive()) {
				trx.rollback();
			}
			manager.close();
		}
	}

	@Override
	public void deletar(Sabores sabores) {
		
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			
			trx.begin();
			manager.remove(manager.find(Sabores.class, sabores.getId()));
			trx.commit();
			
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}finally {
			if(trx.isActive()) {
				trx.rollback();
			}
			manager.close();
		}
		
	}

	@Override
	public List<Sabores> listar() {
			
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Sabores", Sabores.class);
		
		try {
			return query.getResultList();
			
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}
	public List<Sabores> listarParaPedido(Long idCat) {
			
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Sabores where categoria_id = :idcat", Sabores.class);
                query.setParameter("idcat", idCat);
		
		try {
			return query.getResultList();
			
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}

	@Override
	public List<Sabores> listarPorNome(String txt) {

		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Sabores where nome LIKE :nome", Sabores.class);
		query.setParameter("nome", "%"+txt+"%");
		
		try {
			return query.getResultList();
			
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}

	@Override
	public Sabores pesquisarPorID(Long id) {
		
		manager = JpaUtil.getEntityManager();
		
		try {
			return manager.find(Sabores.class, id);
			
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}
}
