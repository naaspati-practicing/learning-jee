package sam.hello.ejb;

import javax.ejb.Stateless;

@Stateless
public class HelloUserBean implements HelloUser {
	
	@Override
	public String sayHello(String to) {
		String s = to == null || to.isEmpty() ? "Hello Stranger!" : "Hello "+to+"!";
		debug(s);
		return s;
	}

	private void debug(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append(s)
		.append("\n  instance: ").append(getClass()).append('@').append(System.identityHashCode(this))
		.append("\n  stack: \n");
		
		StackTraceElement[] e = Thread.currentThread().getStackTrace();
		int max = Math.min(8, e.length);
		
		for (int i = 2; i < max; i++) 
			sb.append("    ").append(e[i]).append('\n');
		
		System.out.println(sb);
	}
}
