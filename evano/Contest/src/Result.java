import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class Result {

	public static enum SORT_ORDER {
		NONE, INCREASING, DECREASING
	}

	public static enum INSERT_ORDER {
		FIRST_FIT, BEST_FIT, WORST_FIT
	}

	public static int sum(List<Integer> items) {
		int sum = 0;
		for (Integer i : items) {
			sum += i.intValue();
		}
		return sum;
	}

	public static Integer max(List<Integer> items) {
		int max = Integer.MIN_VALUE;
		for (Integer i : items) {
			if (i.intValue() > max) {
				max = i.intValue();
			}
		}
		return Integer.valueOf(max);
	}

	public static Integer min(List<Integer> items) {
		int min = Integer.MAX_VALUE;
		for (Integer i : items) {
			if (i.intValue() < min) {
				min = i.intValue();
			}
		}
		return Integer.valueOf(min);
	}

	public static int task_sum;

	/*
	 * Complete the 'get_people_num' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts following
	 * parameters: 1. INTEGER total_days 2. INTEGER_ARRAY tasks
	 */

	public static int get_people_num(int total_days, List<Integer> tasks) {

		for (Integer task : tasks) {
			if (task.intValue() > total_days || task.intValue() < 0) {
				return -1;
			}
		}

		int answer = tasks.size();
		task_sum = sum(tasks);
		Bin.capacity = total_days;

		SORT_ORDER[] sort_orders = { SORT_ORDER.NONE, SORT_ORDER.INCREASING, SORT_ORDER.DECREASING };
		INSERT_ORDER[] insert_orders = { INSERT_ORDER.FIRST_FIT, INSERT_ORDER.BEST_FIT, INSERT_ORDER.WORST_FIT };
		for (SORT_ORDER sort_order : sort_orders) {
			for (INSERT_ORDER insert_order : insert_orders) {
				List<Bin> allocation = Greedy.greedy_insert(total_days, tasks, sort_order, insert_order);
				if (answer > allocation.size()) {
					answer = allocation.size();
					ImprovedBC3_3.optimal_bin_list = new ArrayList<>(allocation);
				}
			}
		}

		List<Integer> items = new ArrayList<>(tasks);
		items.sort(Comparator.reverseOrder());
		List<Bin> allocation = new ArrayList<>();
		while (!items.isEmpty()) {
			List<Integer> pack = DP.packing(total_days, items);
			Bin bin = new Bin(pack);
			allocation.add(bin);
			for (Integer i : pack) {
				items.remove(i);
			}
		}
		if (answer > allocation.size()) {
			answer = allocation.size();
			ImprovedBC3_3.optimal_bin_list = new ArrayList<>(allocation);
		}

		int lower_bound = Limit.lower_bounds(total_days, tasks);
		if (answer == lower_bound) {
			return answer;
		}

		PriorityQueue<Integer> search_items = new PriorityQueue<>(tasks.size(), new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2.intValue() - o1.intValue();
			}
		});
		for (Integer i : tasks) {
			search_items.add(i);
		}
		List<Bin> bin_list = new ArrayList<>();
		int max_waste = (answer - 1) * total_days - task_sum;
		ImprovedBC3_3.max_waste = max_waste;
		ImprovedBC3_3.optimal = answer;
		ImprovedBC3_3.lower_limit = lower_bound;
		ImprovedBC3_3.found_optimal = false;
		ImprovedBC3_3.start_time = System.currentTimeMillis();
		ImprovedBC3_3.bin_completion(bin_list, search_items, 0);

		return ImprovedBC3_3.optimal;
	}

}