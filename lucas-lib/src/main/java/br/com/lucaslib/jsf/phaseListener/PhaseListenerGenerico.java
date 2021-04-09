package br.com.lucaslib.jsf.phaseListener;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class PhaseListenerGenerico implements PhaseListener {

	private static final long serialVersionUID = 1L;
	
	private PhaseListenerObserver observer = new PhaseListenerObserver();

	@Override
	public void afterPhase(PhaseEvent event) {
		observer.after().fire(event);
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		observer.before().fire(event);
	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE; 
	}

}
