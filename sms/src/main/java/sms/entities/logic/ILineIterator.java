package sms.entities.logic;

import java.util.Iterator;

public interface ILineIterator {

	public Iterator<? extends ILine> createLinesIterator();

}
