import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.glassfish.grizzly.Buffer;
import org.glassfish.grizzly.filterchain.BaseFilter;
import org.glassfish.grizzly.filterchain.FilterChainContext;
import org.glassfish.grizzly.filterchain.NextAction;
import org.glassfish.grizzly.http.HttpContent;
import org.glassfish.grizzly.http.HttpRequestPacket;
import org.glassfish.grizzly.http.Method;
import org.glassfish.grizzly.http.Protocol;
import org.glassfish.grizzly.http.util.Header;
import org.glassfish.grizzly.impl.FutureImpl;

public class ClientDownloadFilter extends BaseFilter {
	private String url;
	private URI uri;
	
	private FutureImpl<Result> onComplete;
	private volatile long downloaded;
	
	private Path savePath;
	private FileChannel file;

	public void setTask(URI uri, String url, FutureImpl<Result> onComplete) {
		this.uri = uri;
		this.url = url;
		this.onComplete = onComplete;
	}
	@Override
	public NextAction handleConnect(FilterChainContext ctx) throws IOException {
		HttpRequestPacket req = HttpRequestPacket.builder()
				.method(Method.GET)
				.protocol(Protocol.HTTP_1_1)
				.uri(url)
				.header(Header.Host, uri.getHost())
				.header(Header.UserAgent, "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36")
				.build();

		System.out.println("send request for file");

		ctx.write(req);

		return ctx.getStopAction();
	}
	private static final Pattern pattern = Pattern.compile("filename\\s*=\\s*\"(.+)\"", Pattern.CASE_INSENSITIVE);

	@Override
	public NextAction handleRead(FilterChainContext ctx) throws IOException {
		try {
			final HttpContent content = ctx.getMessage();
			String filename = content.getHttpHeader().getHeader(Header.ContentDisposition);

			if(filename != null) {
				Matcher m = pattern.matcher(filename);
				filename = m.find() ? m.group(1) : null;
			}

			Buffer buffer = content.getContent();
			int size = buffer.remaining();
			System.out.println("content size: "+size);

			if(savePath == null) {
				savePath = Files.createTempFile(getClass().getName(), null);
				file = FileChannel.open(savePath, StandardOpenOption.WRITE);
			}
			downloaded += size;

			if(size > 0) {
				ByteBuffer bt = buffer.toByteBuffer();
				while(bt.hasRemaining()) file.write(bt);
			}
			buffer.dispose();

			if(content.isLast()) {
				System.out.println("download completed: ".concat(url));
				onComplete.result(new Result(savePath, filename, downloaded));
				close();
			}
		} catch (Exception e) {
			onComplete.failure(e);
			close();
		}
		return ctx.getStopAction();
	}
	@Override
	public NextAction handleClose(FilterChainContext ctx) throws IOException {
		close();
		return ctx.getStopAction();
	}
	private void close() throws IOException {
		if(file != null)
			file.close();
		file = null;
		savePath = null;
		url = null;
		uri = null;
		downloaded = 0;
		
		if(onComplete != null && !onComplete.isDone())
			onComplete.failure(new IOException("connection was closed"));
		onComplete = null;
	}
}
