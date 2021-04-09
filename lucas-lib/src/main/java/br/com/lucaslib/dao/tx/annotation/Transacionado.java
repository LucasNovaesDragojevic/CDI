package br.com.lucaslib.dao.tx.annotation;

import java.io.Serializable;

import javax.interceptor.InvocationContext;

public interface Transacionado extends Serializable {

	Object executaComTransacao(InvocationContext context);
}
