import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Limit {

	public static int lower_bounds(int capacity, List<Integer> items) {
		List<Integer> array = new LinkedList<>(items);
		array.sort(Comparator.reverseOrder());
		int waste = 0;
		int sum = 0;
		while (!array.isEmpty()) {
			Integer s1 = array.remove(0);
			int residual = capacity - s1.intValue();
			Iterator<Integer> iterator = array.iterator();
			while (iterator.hasNext()) {
				int i = iterator.next().intValue();
				if (i <= residual) {
					sum += i;
					iterator.remove();
				}
			}
			if (sum <= residual) {
				waste += residual - sum;
				sum = 0;
			} else {
				sum -= residual;
			}
		}

		sum = Result.task_sum + waste;

		return (int) Math.ceil(((double) sum) / capacity);
	}

}
