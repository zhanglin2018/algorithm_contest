import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Other {
	@SuppressWarnings("boxing")
	public static int get_people_num(int days, List<Integer> tasks) {
		// Write your code here
		int pepoleCount = 0;
		float max = 0;
		List<Float> taskDaysRemoveOthers = new ArrayList<>();
		for (int i = 0; i < tasks.size(); i++) {
			float DaysOfOneTask = tasks.get(i);

			if (DaysOfOneTask > days) {
				return -1;
			}
			if (DaysOfOneTask == days) {
				pepoleCount++;
			} else {
				taskDaysRemoveOthers.add(DaysOfOneTask);
			}
			// System.out.println(taskDaysRemoveOthers.get(i));
		}
		for (int j = 0; j < taskDaysRemoveOthers.size(); j++) {
			if (max < taskDaysRemoveOthers.get(j)) {
				max = taskDaysRemoveOthers.get(j);
			}
		}

		Collections.sort(taskDaysRemoveOthers, Collections.reverseOrder());
		taskDaysRemoveOthers.remove(max);
		float maxCountOfOnePerson = max;
		while (!taskDaysRemoveOthers.isEmpty()) {
			if (taskDaysRemoveOthers.size() == 1) {
				pepoleCount++;
				break;
			}
			Iterator<Float> it = taskDaysRemoveOthers.iterator();
			while (it.hasNext()) {
				if (taskDaysRemoveOthers.size() == 1) {
					break;
				}
				Float s = it.next();
				if (maxCountOfOnePerson >= days) {
					pepoleCount++;
					maxCountOfOnePerson = taskDaysRemoveOthers.get(taskDaysRemoveOthers.size() - 1);
					break;
				}
				maxCountOfOnePerson = maxCountOfOnePerson + s;
				it.remove();
			}
		}
		return pepoleCount;
	}

}
