package br.com.lucaslib.factory;

import java.lang.reflect.ParameterizedType;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.lucaslib.dao.DAO;

public class DAOFactory {

	@Inject
	private EntityManager em;

	@Produces
	public <T> DAO<T> factory(InjectionPoint point) {
		ParameterizedType type = (ParameterizedType) point.getType();
		@SuppressWarnings("unchecked")
		Class<T> classe = (Class<T>) type.getActualTypeArguments()[0];
		return new DAO<T>(classe, em);
	}
}
