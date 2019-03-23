package sms.entities.z_lines_logic;

import org.springframework.stereotype.Component;

@Component
public class LineFactory {

	public ILine createLine(ILineAbstractFactory laf) {
		return laf.createLine();
	}

}
