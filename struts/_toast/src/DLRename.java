import java.io.File;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;

public class DLRename {

	public static void main(String[] args) {
		CharsetEncoder e =  StandardCharsets.UTF_8.newEncoder();
		
		File root = new File("D:\\Downloads\\youtube-dl");
		String[] file = root.list();
		
		StringBuilder sb = new StringBuilder();
		int count = 0;
		
		for (String s : file) {
			for (int i = 0; i < s.length(); i++) {
				if(!e.canEncode(s.charAt(i))) {
					sb.setLength(0);
					sb.append(s);
					sb.setCharAt(i, ' ');
					for (int j = i + 1; j < sb.length(); j++) {
						if(!e.canEncode(s.charAt(j)))
							sb.setCharAt(j, ' ');
					}
					new File(root, s).renameTo(new File(root, sb.toString()));
					System.out.println(s+"\n"+sb+"\n");
					count++;
					break;
				}
			}
		}
		System.out.println("\n"+count+"/"+file.length);
	}

}
