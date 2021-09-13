package cb13.project.domain;

public class Message {
	
	private String type;
	
	private int from;
	
	private String message;

	public Message(String type, int from, String message) {
		this.type = type;
		this.from = from;
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(int from) {
		this.from = from;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
