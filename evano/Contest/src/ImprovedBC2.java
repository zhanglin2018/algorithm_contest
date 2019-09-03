import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class ImprovedBC2 {

	public static int max_waste;

	public static int optimal;

	public static List<Bin> optimal_bin_list;

	public static int lower_limit;

	public static boolean found_optimal;

	public static long start_time;

	public static final int TIME_OUT = 1000; // ms

	public static List<Bin> bin_list;

	public static int[] items;

	public static class Bin {

		public static int capacity;

		public int sum = 0;

		public List<Integer> list = new LinkedList<>();

		public void add(Integer item) {
			list.add(item);
			sum += items[item.intValue()];
		}

		public boolean remove(Integer item) {
			if (list.remove(item)) {
				sum -= items[item.intValue()];
				return true;
			}
			return false;
		}

		public Bin() {

		}

		public Bin(Integer item) {
			add(item);
		}

		public Bin(List<Integer> list) {
			for (Integer item : list) {
				add(item);
			}
		}

		public boolean feasible(Integer item) {
			return sum + items[item.intValue()] <= capacity;
		}

		public int residual() {
			return capacity - sum;
		}

	}

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
			return items[o2.list.get(o2.list.size() - 1).intValue()]
					- items[o1.list.get(o1.list.size() - 1).intValue()];
		}

	}

	public static void bin_completion(BitSet chosen, int waste) {
		if (System.currentTimeMillis() - start_time > TIME_OUT)
			return;
		if (waste > max_waste)
			return;
		if (chosen.cardinality() == items.length) {
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

		List<Bin> completions = new ArrayList<>();
		int max_item_id = chosen.nextClearBit(0);
		chosen.set(max_item_id);
		Bin current_bin = new Bin(Integer.valueOf(max_item_id));
		List<Integer> excludes = new ArrayList<>();
		int upper_bound = current_bin.residual();
		int lower_bound = 0;
		generate_completions(completions, current_bin, excludes, chosen, lower_bound, upper_bound);
		completions.sort(BinComparator.instance);
		chosen.clear(max_item_id);
		for (Bin bin : completions) {
			bin_list.add(bin);
			for (Integer itemID : bin.list) {
				chosen.set(itemID.intValue());
			}
			bin_completion(chosen, waste + bin.residual());
			for (Integer itemID : bin.list) {
				chosen.clear(itemID.intValue());
			}
			bin_list.remove(bin_list.size() - 1);
		}
	}

	public static void generate_completions(List<Bin> completions, Bin current_bin, List<Integer> excludes,
			BitSet dealt, int lower_bound, int upper_bound) {
		if (System.currentTimeMillis() - start_time > TIME_OUT)
			return;
		if (dealt.cardinality() == items.length || upper_bound <= 0) {
			if (lower_bound <= 0) {
				completions.add(new Bin(current_bin.list));
			}
			return;
		}
		int x = dealt.nextClearBit(0);
		if (items[x] > upper_bound) {
			dealt.set(x);
			generate_completions(completions, current_bin, excludes, dealt, lower_bound, upper_bound);
			dealt.clear(x);
		} else if (items[x] == upper_bound) {
			dealt.set(x);
			current_bin.add(Integer.valueOf(x));
			generate_completions(completions, current_bin, excludes, dealt, lower_bound - items[x],
					upper_bound - items[x]);
			dealt.clear(x);
			current_bin.remove(Integer.valueOf(x));
		} else {
			dealt.set(x);
			current_bin.add(Integer.valueOf(x));
			generate_completions(completions, current_bin, excludes, dealt, lower_bound - items[x],
					upper_bound - items[x]);
			current_bin.remove(Integer.valueOf(x));
			excludes.add(Integer.valueOf(x));
			generate_completions(completions, current_bin, excludes, dealt,
					(items[x] + 1 > lower_bound) ? (items[x] + 1) : lower_bound, upper_bound);
			excludes.remove(Integer.valueOf(x));
			dealt.clear(x);
		}
	}

}
