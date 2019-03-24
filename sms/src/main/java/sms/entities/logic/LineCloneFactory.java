package sms.entities.logic;

public class LineCloneFactory {

	public ILine getClone(ILine line) {
		return line.makeCopy();
	}
}
