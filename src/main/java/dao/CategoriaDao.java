package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import interfaces.DaoI;
import model.Categoria;
import util.JpaUtil;

public class CategoriaDao implements DaoI<Categoria>{

	private EntityManager manager;
	
	private EntityTransaction trx;
	
	private TypedQuery<Categoria> query;

	@Override
	public Categoria salvar(Categoria categoria) {
		
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			
			trx.begin();
			categoria = manager.merge(categoria);
			trx.commit();
			return categoria;
	
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
	public void deletar(Categoria categoria) {
			
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			trx.begin();
			manager.remove(manager.find(Categoria.class, categoria.getId()));
			trx.commit();
		}finally {
			if(trx.isActive()) {
				trx.rollback();
			}
			manager.close();
		}
	}

	@Override
	public List<Categoria> listar() {
		
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Categoria" , Categoria.class);
		
		try {
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	
	}

	@Override
	public List<Categoria> listarPorNome(String txt) {
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Categoria where nome LIKE :nome" , Categoria.class);
		query.setParameter("nome", "%"+txt+"%");
		try {
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}

	@Override
	public Categoria pesquisarPorID(Long id) {
		
		manager = JpaUtil.getEntityManager();
		
		try {
			return manager.find(Categoria.class, id);
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}

}
