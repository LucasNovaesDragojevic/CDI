package br.com.lucaslib.jsf.phaseListener;

import java.lang.annotation.Annotation;

import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.enterprise.util.AnnotationLiteral;
import javax.faces.event.PhaseEvent;

import br.com.lucaslib.jsf.phaseListener.annotation.After;
import br.com.lucaslib.jsf.phaseListener.annotation.Before;

public class PhaseListenerObserver {
	
	private BeanManager observer = CDI.current().getBeanManager();
	
	private Annotation momento;
	
	@SuppressWarnings("serial")
	public PhaseListenerObserver after() {
		this.momento = new AnnotationLiteral<After>() {};
		return this;
	}

	@SuppressWarnings("serial")
	public PhaseListenerObserver before() {
		this.momento = new AnnotationLiteral<Before>() {};
		return this;
	}
	
	public void fire(PhaseEvent phaseEvent) {
		observer.fireEvent(phaseEvent, momento, new PhaseLiteral(phaseEvent.getPhaseId()));
	}
}
