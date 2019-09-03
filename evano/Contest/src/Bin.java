import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bin {

	public static int capacity;

	public int sum = 0;

	public int num = 0;

	public List<Integer> list = new ArrayList<>();

	public void add(Integer item) {
		list.add(item);
		++num;
		sum += item.intValue();
	}

	public boolean remove(Integer item) {
		for (int i = num - 1; i >= 0; --i) {
			if (list.get(i).equals(item)) {
				list.remove(i);
				sum -= item.intValue();
				--num;
				return true;
			}
		}
		return false;
	}

	public Bin() {

	}

	public Bin(Integer item) {
		add(item);
	}

	public Bin(Collection<Integer> list) {
		for (Integer item : list) {
			add(item);
		}
	}

	public boolean feasible(Integer item) {
		return sum + item.intValue() <= capacity;
	}

	public int residual() {
		return capacity - sum;
	}

	@Override
	public String toString() {
		return list.toString() + " -> " + sum;
	}

}