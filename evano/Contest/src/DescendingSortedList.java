import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@SuppressWarnings("serial")
public class DescendingSortedList extends ArrayList<Integer> {

	public DescendingSortedList(List<Integer> tasks) {
		super(tasks);
		sort(Comparator.reverseOrder());
	}

	public DescendingSortedList() {
		super();
	}

	@Override
	public boolean add(Integer e) {
		if (super.add(e)) {
			int i = size() - 1;
			Integer tmp;
			while (i > 0 && get(i).intValue() > get(i - 1).intValue()) {
				tmp = get(i);
				set(i, get(i - 1));
				set(i - 1, tmp);
				--i;
			}

			return true;
		}
		return false;
	}

	@Override
	public void add(int index, Integer element) {
		super.add(index, element);
		sort(Comparator.reverseOrder());
	}

	public Integer max() {
		return get(0);
	}

	public Integer min() {
		return get(size() - 1);
	}

	public List<Integer> min(int len) {
		return subList(size() - len, size());
	}

	public int sum() {
		int sum = 0;
		for (Integer i : this) {
			sum += i.intValue();
		}
		return sum;
	}
}
