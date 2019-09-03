import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ImprovedBC3_4 {

	public static int max_waste;

	public static int optimal;

	public static List<Bin> optimal_bin_list;

	public static int lower_limit;

	public static boolean found_optimal;

	public static long start_time;

	public static final int TIME_OUT = 1000; // ms

	public static class BinComparator implements Comparator<Bin> {

		public static BinComparator instance = new BinComparator();

		@Override
		public int compare(Bin o1, Bin o2) {
			if (o1.sum != o2.sum) {
				return o2.sum - o1.sum;
			}
			if (o1.num != o2.num) {
				return o1.num - o2.num;
			}
			return o2.list.get(o2.num - 1).intValue() - o1.list.get(o2.num - 1).intValue();
		}

	}

	public static void bin_completion(List<Bin> bin_list, PriorityQueue<Integer> items, int waste) {
		if (waste > max_waste || bin_list.size() >= optimal)
			return;
		if (items.isEmpty()) {
			if (bin_list.size() < optimal) {
				optimal = bin_list.size();
				optimal_bin_list = new ArrayList<>(bin_list);
				if (optimal == lower_limit) {
					found_optimal = true;
				}
				max_waste = (optimal - 1) * Bin.capacity - Result.task_sum;
			}
			return;
		}
		if (found_optimal)
			return;
		if (System.currentTimeMillis() - start_time > TIME_OUT)
			return;

		List<Bin> completions = new ArrayList<>();
		Integer largest_item = items.poll();
		Bin current_bin = new Bin(largest_item);
		List<Integer> excludes = new ArrayList<>();
		int upper_bound = current_bin.residual();
		int lower_bound = 0;
		generate_completions(completions, current_bin, excludes, (Integer[]) items.toArray(), 0, lower_bound,
				upper_bound);
		completions.sort(BinComparator.instance);
		items.add(largest_item);
		for (Bin bin : completions) {
			bin_list.add(bin);
			for (Integer item : bin.list) {
				items.remove(item);
			}
			bin_completion(bin_list, items, waste + bin.residual());
			for (Integer item : bin.list) {
				items.add(item);
			}
			bin_list.remove(bin);
		}
	}

	public static void generate_completions(List<Bin> completions, Bin current_bin, List<Integer> excludes,
			Integer remain_items[], int index, int lower_bound, int upper_bound) {
		if (index == remain_items.length || upper_bound <= 0) {
			if (lower_bound <= 0) {
				if (test_completion(current_bin, excludes)) {
					completions.add(new Bin(current_bin.list));
				}
			}
			return;
		}
		if (System.currentTimeMillis() - start_time > TIME_OUT)
			return;
		int sum = 0;
		for (int i = 0; i < remain_items.length; ++i) {
			sum += remain_items[i].intValue();
			if (sum >= lower_bound) {
				break;
			}
		}
		if (sum < lower_bound) {
			return;
		}
		Integer x = remain_items[index];
		if (x.intValue() > upper_bound) {
			generate_completions(completions, current_bin, excludes, remain_items, index + 1, lower_bound, upper_bound);
		} else if (x.intValue() == upper_bound) {
			current_bin.add(x);
			generate_completions(completions, current_bin, excludes, remain_items, index + 1,
					lower_bound - x.intValue(), upper_bound - x.intValue());
			current_bin.remove(x);
		} else {
			if (!excludes.contains(x)) {
				current_bin.add(Integer.valueOf(x.intValue()));
				generate_completions(completions, current_bin, excludes, remain_items, index + 1,
						lower_bound - x.intValue(), upper_bound - x.intValue());
				current_bin.remove(x);
			}
			excludes.add(x);
			generate_completions(completions, current_bin, excludes, remain_items, index + 1,
					(x.intValue() + 1 > lower_bound) ? (x.intValue() + 1) : lower_bound, upper_bound);
			excludes.remove(x);
		}
	}

	public static boolean test_completion(Bin bin, List<Integer> excludes) {
		for (Integer x : excludes) {
			Integer items[] = (Integer[]) bin.list.toArray();
			int num = items.length;
			int capacity = x.intValue();
			int i, j;
			int table[][] = new int[num + 1][capacity + 1];
			for (i = 2; i <= num; ++i) {
				int weight = items[i - 1].intValue();
				for (j = 0; j <= capacity; ++j) {
					table[i][j] = table[i - 1][j];
					if (weight <= j && weight + table[i - 1][j - weight] <= capacity) {
						if (table[i - 1][j - weight] + weight > table[i][j]) {
							table[i][j] = table[i - 1][j - weight] + weight;
						}
					}
				}
			}
			if (bin.sum - table[num][capacity] + x.intValue() <= Bin.capacity) {
				return false;
			}
		}
		return true;
	}

}
