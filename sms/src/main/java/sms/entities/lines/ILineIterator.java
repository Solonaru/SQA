package sms.entities.lines;

import java.util.Iterator;

public interface ILineIterator {

	public Iterator<? extends ILine> createLinesIterator();

}
