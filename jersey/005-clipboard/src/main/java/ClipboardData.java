import java.io.Serializable;

public class ClipboardData implements Serializable {
	private static final long serialVersionUID = 5611020287643474215L;
	private static int IDS = 1;
	
	private int id;
	private String content;
	
	public ClipboardData() {}
	
	public ClipboardData(int id, String content) {
		this.id = id;
		this.content = content;
	}
	public ClipboardData(String content) {
		this.id = IDS++;
		this.content = content;
	}

	public int getId() {
		if(id < 1)
			id = IDS++;
		
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public boolean isEmpty() {
		return content == null || content.isEmpty();
	}
	public void append(ClipboardData data) {
		if(data.isEmpty())
			return;
		if(isEmpty())
			this.content = data.content;
	}
	@Override
	public String toString() {
		return "ClipboardData [id=" + id + ", content=" + content + "]";
	}
}
