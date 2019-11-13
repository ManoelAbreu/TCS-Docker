package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import interfaces.DaoI;
import model.Produto;
import util.JpaUtil;

public class ProdutoDao implements DaoI<Produto>{

private EntityManager manager;
	
	private EntityTransaction trx;
	
	private TypedQuery<Produto> query;

	@Override
	public Produto salvar(Produto produto) {

		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			trx.begin();
			produto = manager.merge(produto);
			trx.commit();
			
			return produto;
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
	public void deletar(Produto produto) {

		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			trx.begin();
			manager.remove(manager.find(Produto.class, produto.getId()));
			trx.commit();
		} finally {
			if(trx.isActive()) {
				trx.rollback();
			}
			manager.close();
		}
	}

	@Override
	public List<Produto> listar() {
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Produto", Produto.class);
		
		try {
			return query.getResultList();
			
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
		
	}
        
	public List<Produto> listarParaPedidos () {
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Produto where ativo = 1", Produto.class);
		
		try {
			return query.getResultList();
			
		} catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
		
	}

	@Override
	public List<Produto> listarPorNome(String txt) {
		manager = JpaUtil.getEntityManager();
		query = manager.createQuery("from Produto where nome LIKE :nome ", Produto.class);
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
	public Produto pesquisarPorID(Long id) {
		
		manager = JpaUtil.getEntityManager();
		
		try {
			return manager.find(Produto.class, id);
			
		}catch (Exception e) {
			return null;
		} finally {
			manager.close();
		}
	}
	
}
