package br.com.lucaslib.jsf.phaseListener;

import javax.enterprise.util.AnnotationLiteral;
import javax.faces.event.PhaseId;

import br.com.lucaslib.jsf.phaseListener.annotation.Phase;

public class PhaseLiteral extends AnnotationLiteral<Phase> implements Phase {

	private static final long serialVersionUID = 1L;
	
	private Phases phases;

	public PhaseLiteral(PhaseId phaseId) {
		phases = Phase.Phases.valueOf(phaseId.getName());
	}

	@Override
	public Phases value() {
		return phases;
	}

}
