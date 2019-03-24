package sms.entities.z_lines_logic;

public class LineCloneFactory {

	public ILine getClone(ILine line) {
		return line.makeCopy();
	}
}
