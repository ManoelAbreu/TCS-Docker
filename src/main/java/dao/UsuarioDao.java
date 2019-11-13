package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import interfaces.DaoI;
import model.Usuario;
import util.JpaUtil;

public class UsuarioDao implements DaoI<Usuario> {
	
	private EntityManager manager;
	
	private EntityTransaction trx;
	
	private TypedQuery<Usuario> query;

	@Override
	public Usuario salvar(Usuario usuario) {
	
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			trx.begin();
			usuario = manager.merge(usuario);
			trx.commit();
			return usuario;
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
	public void deletar(Usuario usuario) {
		
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		
		try {
			trx.begin();
			manager.remove(manager.find(Usuario.class, usuario.getId()));
			trx.commit();
			
		}finally {
			if(trx.isActive()) {
				trx.rollback();
			}
			manager.close();
		}	
	}

	@Override
	public List<Usuario> listar() {
		
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		query = manager.createQuery("from Usuario" , Usuario.class);
		
		try {
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
		
	}
        
	public List<Usuario> listarGarcons() {
		
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		query = manager.createQuery("from Usuario where perfil = 'GARCOM'" , Usuario.class);
		
		try {
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
		
	}

	public List<Usuario> listarAtivo() {
		
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		query = manager.createQuery("from Usuario WHERE ativo = true " , Usuario.class);
		
		try {
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
		
	}

	@Override
	public List<Usuario> listarPorNome(String txt) {
		manager = JpaUtil.getEntityManager();
		trx = manager.getTransaction();
		query = manager.createQuery("from Usuario WHERE ativo = true AND nome LIKE :nome" , Usuario.class);
		query.setParameter("nome","%"+txt+"%");
		
		try {
			return query.getResultList();
		}catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}

	@Override
	public Usuario pesquisarPorID(Long id) {
		manager = JpaUtil.getEntityManager();
		
		try {
			return manager.find(Usuario.class, id);   
		}catch (Exception e) {
			return null;
		}finally {
			manager.close();
		}
	}
	
	public Usuario verificaLogin(String login , String senha) {
		EntityManager manager = JpaUtil.getEntityManager();
		TypedQuery<Usuario> query = manager.createQuery("from Usuario u where u.login = :email"
				+ " AND u.senha = :senha" , Usuario.class);
		query.setParameter("email", login);
		query.setParameter("senha",senha);
		
		try {
			return query.getSingleResult();
		}catch (NoResultException e) {
			return null;
		} finally {
			manager.close();
		}
		
	}
	
	
}
