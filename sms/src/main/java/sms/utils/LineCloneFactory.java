package sms.utils;

import sms.entities.lines.ILine;

public class LineCloneFactory {

	public ILine getClone(ILine line) {
		return line.makeCopy();
	}
}
