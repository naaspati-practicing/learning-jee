package com.tad.arquillian.chap1;

public interface Utils {
	static void printStack(String msg) {
		StackTraceElement[] els = Thread.currentThread().getStackTrace(); 

		int size = Math.min(6, els.length);
		StringBuilder sb = new StringBuilder("-------------------------\n");
		if(msg != null)
			sb.append(msg).append('\n');

		for (int i = 2; i < size; i++)
			sb.append(els[i]).append("\n");

		System.out.println(sb);
	}
}
