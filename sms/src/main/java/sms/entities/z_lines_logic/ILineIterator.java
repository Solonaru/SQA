package sms.entities.z_lines_logic;

import java.util.Iterator;

public interface ILineIterator {

	public Iterator<? extends ILine> createLinesIterator();

}
