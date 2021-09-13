package cb13.project.enumeration;

public enum MessageType {
	
	MESSAGE(1),
	TYPING(2),
	JOINED(3),
	LEFT(4);
	
	private final int messageType;
	
	MessageType(int messageType) {
		this.messageType = messageType;
	}
	
	public int getType() {
		return messageType;
	}
}
