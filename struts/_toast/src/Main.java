import java.util.HashMap;

public class Main {

	public static void main(String[] args) {
		HashMap<Integer, Integer> map = new HashMap<>();
		
		map.compute(11, (i,j) -> {
			System.out.println(i);
			System.out.println(j);
			return 10;
		});
		
		System.out.println(map);

	}

}
