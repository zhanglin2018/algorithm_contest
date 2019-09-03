import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ImprovedBC3 {

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
			if (o1.list.size() != o2.list.size()) {
				return o1.list.size() - o2.list.size();
			}
			return o2.list.get(o2.list.size() - 1).intValue() - o1.list.get(o1.list.size() - 1).intValue();
		}

	}

	public static void bin_completion(List<Bin> bin_list, List<Integer> items, int waste) {
		if (waste > max_waste || bin_list.size() >= optimal)
			return;
		if (items.isEmpty()) {
			if (bin_list.size() < optimal) {
				optimal = bin_list.size();
				optimal_bin_list = new ArrayList<>(bin_list);
				if (optimal == lower_limit) {
					found_optimal = true;
				}
			}
			return;
		}
		if (found_optimal)
			return;
		if (System.currentTimeMillis() - start_time > TIME_OUT)
			return;

		List<Bin> completions = new ArrayList<>();
		Integer largest_item = Result.max(items);
		items.remove(largest_item);
		Bin current_bin = new Bin(largest_item);
		List<Integer> excludes = new ArrayList<>();
		int upper_bound = current_bin.residual();
		int lower_bound = 0;
		generate_completions(completions, current_bin, excludes, items, lower_bound, upper_bound);
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
			List<Integer> remain_items, int lower_bound, int upper_bound) {
		if (remain_items.isEmpty() || upper_bound <= 0) {
			if (lower_bound <= 0) {
				if (test_completion(current_bin, excludes)) {
					completions.add(new Bin(current_bin.list));
				}
			}
			return;
		}
		if (System.currentTimeMillis() - start_time > TIME_OUT)
			return;
		Integer x = Result.max(remain_items);
		if (x.intValue() > upper_bound) {
			remain_items.remove(x);
			generate_completions(completions, current_bin, excludes, remain_items, lower_bound, upper_bound);
			remain_items.add(x);
		} else if (x.intValue() == upper_bound) {
			remain_items.remove(x);
			current_bin.add(x);
			generate_completions(completions, current_bin, excludes, remain_items, lower_bound - x.intValue(),
					upper_bound - x.intValue());
			remain_items.add(x);
			current_bin.remove(x);
		} else {
			remain_items.remove(x);
			current_bin.add(x);
			generate_completions(completions, current_bin, excludes, remain_items, lower_bound - x.intValue(),
					upper_bound - x.intValue());
			current_bin.remove(x);
			excludes.add(x);
			generate_completions(completions, current_bin, excludes, remain_items,
					(x.intValue() + 1 > lower_bound) ? (x.intValue() + 1) : lower_bound, upper_bound);
			excludes.remove(x);
			remain_items.add(x);
		}
	}

	public static boolean test_completion(Bin bin, List<Integer> excludes) {
		for (Integer x : excludes) {
			List<Integer> items = bin.list;
			int num = items.size();
			int capacity = x.intValue();
			int i, j;
			int table[][] = new int[num + 1][capacity + 1];
			for (i = 2; i <= num; ++i) {
				int weight = items.get(i - 1).intValue();
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
