package br.com.lucaslib.dao.tx.annotation;

import java.io.Serializable;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

@Interceptor
@Transacional
public class GerenciadorTransacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager em;
	
	@Inject
	public GerenciadorTransacao(EntityManager em) {
		this.em = em;
	}

	@AroundInvoke
	public Object executaComTransacao(InvocationContext context) {
		em.getTransaction().begin();

		try {
			Object resultado = context.proceed();
			em.getTransaction().commit();
			return resultado;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}		
	}
}
