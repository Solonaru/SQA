package sms.entities.logic;

import org.springframework.stereotype.Component;

@Component
public class LineFactory {

	public ILine createLine(ILineAbstractFactory laf) {
		return laf.createLine();
	}

}
