package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.alura.livraria.modelo.Autor;
import br.com.lucaslib.dao.DAO;
import br.com.lucaslib.dao.tx.annotation.Transacional;

@Model
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	private Integer autorId;
	
	private DAO<Autor, Integer> autorDao;

	@Inject
	public AutorBean(DAO<Autor, Integer> autorDao) {
		this.autorDao = autorDao;
	}
	
	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
	
	public void carregarAutorPelaId() {
		this.autor = autorDao.buscaPorId(autorId);
	}

	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());

		if(this.autor.getId() == null) {
			autorDao.adiciona(this.autor);
		} else {
			autorDao.atualiza(this.autor);
		}

		this.autor = new Autor();

		return "livro?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Autor autor) {
		System.out.println("Removendo autor " + autor.getNome());
		autorDao.remove(autor);
	}
	
	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
}
