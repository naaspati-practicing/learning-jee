package javaeetutorial.fileupload;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name = "FileUploadServlet", urlPatterns="/upload") 
@MultipartConfig
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 9134452041355076937L;
	private static final Logger LOGGER = Logger.getLogger(FileUploadServlet.class.getSimpleName());

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	private void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		resp.setContentType("text/html;charset=UTF-8");

		String path = req.getParameter("destination");
		Part filePart = req.getPart("file");
		String filename = parseFilename(filePart);
		
		try (PrintWriter writer = resp.getWriter()) {
			try(OutputStream out = Files.newOutputStream(Paths.get(path, filename));
					InputStream fileContent = filePart.getInputStream();) {
				
				int read;
	            final byte[] bytes = new byte[1024];

	            while ((read = fileContent.read(bytes)) != -1) 
	                out.write(bytes, 0, read);
				
	            writer.println("New file " + filename + " created at " + path);
	            LOGGER.info(() -> "file: "+filename+" is being uploaded to path: "+path);
			} catch (IOException e) {
				writer.println("You either did not specify a file to upload or are "
	                    + "trying to upload a file to a protected or nonexistent "
	                    + "location.");
				
	            writer.println("<br/> ERROR: " + e.getMessage());
	            e.printStackTrace(writer);
	            LOGGER.info(() -> "Problems during file upload. Error: "+e.getMessage());
			}	
		}
	}

	private static final Pattern PATTERN = Pattern.compile(";", Pattern.LITERAL);

	private String parseFilename(Part part) {
		String disposition = part.getHeader("content-disposition");
		LOGGER.fine(() -> "content-disposition="+disposition);

		return PATTERN.splitAsStream(disposition)
				.map(String::trim)
				.filter(s -> s.startsWith("filename"))
				.findFirst()
				.map(s -> s.substring(s.indexOf('=') + 1).replace('"', ' ').trim())
				.orElse(null);
	}

}
