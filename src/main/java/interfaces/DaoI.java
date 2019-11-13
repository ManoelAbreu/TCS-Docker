package interfaces;

import java.util.List;

public interface DaoI<T> {
	
	public T salvar(T obj);
	
	public void deletar(T obj);
	
	public List<T> listar();
	
	public List<T> listarPorNome(String txt);
	
	public T pesquisarPorID(Long id);
	
}