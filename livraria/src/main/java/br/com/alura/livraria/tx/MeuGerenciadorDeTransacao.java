package br.com.alura.livraria.tx;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Typed;
import javax.inject.Inject;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.lucaslib.dao.tx.annotation.Transacionado;

@Alternative
@Priority(Interceptor.Priority.APPLICATION)
@Typed(Transacionado.class)
public class MeuGerenciadorDeTransacao implements Transacionado {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EntityManager em;
	
	@Override
	public Object executaComTransacao(InvocationContext context) {
		em.getTransaction().begin();
		System.out.println("Transação aberta");
		
		try {
			Object resultado = context.proceed();
			em.getTransaction().commit();
			System.out.println("Transação comitada");
			return resultado;
			
		} catch (Exception e) {
			em.getTransaction().rollback();
			System.out.println("Transação sofreu rollback");
			throw new RuntimeException(e);
		}
	}
}
