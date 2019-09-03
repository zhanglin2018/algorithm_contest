import java.util.ArrayList;
import java.util.List;

public class DP {

	public static class Node {

		int value = 0;

		boolean chosen = false;
	}

	public static List<Integer> packing(int capacity, List<Integer> items) {
		List<Integer> answer = new ArrayList<>();
		int num = items.size();
		int i, j;
		Node table[][] = new Node[num + 1][capacity + 1];
		for(i=0;i<=num;++i) {
			for(j=0;j<=capacity;++j) {
				table[i][j] = new Node();
			}
		}

		for (i = 1; i <= num; ++i) {
			int weight = items.get(i - 1).intValue();
			for (j = 0; j <= capacity; ++j) {
				table[i][j].value = table[i - 1][j].value;
				table[i][j].chosen = false;
				if (weight <= j && weight + table[i - 1][j - weight].value <= capacity) {
					if (table[i - 1][j - weight].value + weight > table[i][j].value) {
						table[i][j].value = table[i - 1][j - weight].value + weight;
						table[i][j].chosen = true;
					}
				}
			}
		}
		
		int c = capacity;
		for(i = num; i > 0; --i) {
			if(table[i][c].chosen) {
				answer.add(items.get(i - 1));
				c -= items.get(i - 1).intValue();
			}
		}

		return answer;
	}

}
