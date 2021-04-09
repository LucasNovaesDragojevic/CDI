package br.com.lucaslib.factory;

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.lucaslib.configuration.annotation.Configuration;

@ApplicationScoped
public class JPAFactory {

	private EntityManagerFactory emf;
	
	@Inject @Configuration
	private Properties properties;

	@Produces
	@RequestScoped
	public EntityManager getEntityManager() throws Exception {
		return emf.createEntityManager();
	}

	public void close(@Disposes EntityManager em) {
		em.close();
	}
	
	@PostConstruct
	public void loadEMF() {
		emf = Persistence.createEntityManagerFactory(properties.getProperty("lucas.lib.persistenceUnit"));		
	}
	
	@PreDestroy
	public void preDestroy() {
		emf.close();
	}
}
