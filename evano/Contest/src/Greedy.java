import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Greedy {

	public static List<Bin> greedy_insert(int total_days, List<Integer> tasks, Result.SORT_ORDER sort_order,
			Result.INSERT_ORDER insert_order) {
		if (Result.SORT_ORDER.INCREASING.equals(sort_order)) {
			tasks.sort(null);
		} else if (Result.SORT_ORDER.DECREASING.equals(sort_order)) {
			tasks.sort(Comparator.reverseOrder());
		}

		List<Bin> bin_list = new ArrayList<>();
		for (Integer task : tasks) {
			int position;
			for (position = 0; position < bin_list.size(); ++position) {
				if (bin_list.get(position).feasible(task)) {
					break;
				}
			}
			if (position >= bin_list.size()) {
				bin_list.add(new Bin());
			}
			Bin current_bin = bin_list.get(position);
			current_bin.add(task);
			if (Result.INSERT_ORDER.BEST_FIT.equals(insert_order)) {
				while (position > 0 && bin_list.get(position).sum > bin_list.get(position - 1).sum) {
					Bin temp = bin_list.get(position);
					bin_list.set(position, bin_list.get(position - 1));
					bin_list.set(position - 1, temp);
				}
			} else if (Result.INSERT_ORDER.WORST_FIT.equals(insert_order)) {
				if (position == bin_list.size() - 1) {
					while (position > 0 && bin_list.get(position).sum < bin_list.get(position - 1).sum) {
						Bin temp = bin_list.get(position);
						bin_list.set(position, bin_list.get(position - 1));
						bin_list.set(position - 1, temp);
					}
				} else {
					while (position < bin_list.size() - 1
							&& bin_list.get(position).sum > bin_list.get(position + 1).sum) {
						Bin temp = bin_list.get(position);
						bin_list.set(position, bin_list.get(position + 1));
						bin_list.set(position + 1, temp);
					}
				}
			}
		}
		return bin_list;

	}

}
