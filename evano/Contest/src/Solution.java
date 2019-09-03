import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("boxing")
public class Solution {

	public static void main(String[] args) throws IOException {

		List<String> samples = Arrays.asList("N2C3W2_S", "N3C2W1_B", "N3C3W2_B", "N3C3W2_F", "N3C3W2_H", "N3C3W2_K",
				"N3C3W4_A", "N3C3W4_F", "N3C3W4_M", "N3C3W4_N", "N3C3W4_P", "N4C2W1_E", "N4C3W2_F", "N4C3W4_A",
				"N4C3W4_R", "N4C3W4_S", "N4C3W4_T");
		FileReader all_inputs = new FileReader("input.txt");
		BufferedReader reader = new BufferedReader(all_inputs);
		String str;
		List<String> lines = new ArrayList<>();
		while ((str = reader.readLine()) != null) {
			lines.add(str);
		}
		reader.close();
		all_inputs.close();

		int passed = 0;
		int failed = 0;
		for (int i = 0; i < lines.size(); i += 2) {
			String file_name = lines.get(i);
			if (args.length > 0 && !file_name.equals(args[0])) {
				continue;
			}
			if (!samples.contains(file_name)) {
				continue;
			}
			int answer = Integer.parseInt(lines.get(i + 1));
			FileReader input = new FileReader("bin1data/" + file_name + ".BPP");
			reader = new BufferedReader(input);
			int task_count = Integer.parseInt(reader.readLine());
			int total_days = Integer.parseInt(reader.readLine());
			List<Integer> tasks = new ArrayList<>();
			for (int j = 0; j < task_count; ++j) {
				tasks.add(Integer.valueOf(reader.readLine()));
			}
			reader.close();
			input.close();
			long start_time = System.currentTimeMillis();
			int result = Result.get_people_num(total_days, tasks);
			long stop_time = System.currentTimeMillis();
			String message;
			if (result == answer) {
				message = String.format("PASSED, result: %d", result);
				++passed;
			} else {
				message = String.format("FAILED, result: %d, reference: %d", result, answer);
				++failed;
			}
			System.out.println(String.format("(%d/%d)%s - %s - %dms", passed, passed + failed, file_name, message,
					stop_time - start_time));
		}
	}

}