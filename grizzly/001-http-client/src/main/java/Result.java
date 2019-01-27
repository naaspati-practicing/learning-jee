import java.nio.file.Path;

public class Result {
	final String filename;
	final long size;
	final Path savePath;
	
	public Result(Path savePath, String filename, long size) {
		this.savePath = savePath;
		this.filename = filename;
		this.size  = size;
	}

	@Override
	public String toString() {
		return "Result [filename=" + filename + ", size=" + size + ", savePath=" + savePath + "]";
	}
}
