import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class MovieExcercise {

	private static Scanner s;

	public static void main(String[] args) {
		s = new Scanner(System.in);
		int num_mov = Integer.parseInt(s.nextLine());

		Map<String, Set<String>> act_mov = new HashMap<>();
		Map<String, Set<String>> dct_act = new HashMap<>();
		Map<String, Set<String>> act_act = new HashMap<>();

		for (int i = 1; i <= num_mov; i++) {
			String[] inp_line = s.nextLine().split(" ");
			String mov = inp_line[0];
			String dct = inp_line[1];

			List<String> actors = new ArrayList<>(Arrays.asList(inp_line[2], inp_line[3], inp_line[4]));
			Collections.sort(actors);

			String act1 = actors.get(0);
			String act2 = actors.get(1);
			String act3 = actors.get(2);
			
			String key = "";
			
			key = act1;
			add_act_mov(key,act_mov,mov);
			key = act2;
			add_act_mov(key,act_mov,mov);
			key = act3;
			add_act_mov(key,act_mov,mov);
			
			key = act1 + "$@" + act2;
			add_act_act(key,act_act,mov);
			key = act2 + "$@" + act3;
			add_act_act(key,act_act,mov);
			key = act1 + "$@" + act3;
			add_act_act(key,act_act,mov);
			
			key = dct + "$@" + act1;
			add_dct_act(key,dct_act,mov);
			key = dct + "$@" + act2;
			add_dct_act(key,dct_act,mov);
			key = dct + "$@" + act3;
			add_dct_act(key,dct_act,mov);
		}
		
		find_pop_act(act_mov);
		System.out.println();
		find_act_act(act_act);
		System.out.println();
		find_dct_act(dct_act);

	}

	public static void find_pop_act(Map<String, Set<String>> act_mov) {
		Set<String> mov_lst = null;
		List<String> mov_lst_sort = null;		
		int max = 0;
		String act = "";
		for (Entry<String, Set<String>> e : act_mov.entrySet()) {
			if (e.getValue().size() > max) {
				act = e.getKey();
				mov_lst = e.getValue();
				max = e.getValue().size();
			}
		}
		mov_lst_sort = new ArrayList<>(mov_lst);
		Collections.sort(mov_lst_sort);
		System.out.print(act+"-"+mov_lst_sort.toString().replace("[", "(").replace("]", ")").replace(", ", ","));
	}

	public static void find_act_act(Map<String, Set<String>> act_act) {
		Set<String> mov_lst = null;
		List<String> mov_lst_sort = null;
		int max = 0;
		String act = "";
		for (Entry<String, Set<String>> e : act_act.entrySet()) {
			if (e.getValue().size() > max) {
				act = e.getKey();
				mov_lst = e.getValue();
				max = e.getValue().size();
			}
		}
		mov_lst_sort = new ArrayList<>(mov_lst);
		Collections.sort(mov_lst_sort);
		act = act.replace("$@", "-");
		System.out.print(act+"-"+mov_lst_sort.toString().replace("[", "(").replace("]", ")").replace(", ", ","));
	}

	public static void find_dct_act(Map<String, Set<String>> dct_act) {
		Set<String> mov_lst = null;
		List<String> mov_lst_sort = null;
		int max = 0;
		String dct = "";
		for (Entry<String, Set<String>> e : dct_act.entrySet()) {
			if (e.getValue().size() > max) {
				dct = e.getKey();
				mov_lst = e.getValue();
				max = e.getValue().size();
			}
		}
		mov_lst_sort = new ArrayList<>(mov_lst);
		Collections.sort(mov_lst_sort);
		dct = dct.replace("$@", "-");
		System.out.print(dct+"-"+mov_lst_sort.toString().replace("[", "(").replace("]", ")").replace(", ", ","));
	}

	public static void add_act_mov(String key, Map<String, Set<String>> act_mov, String mov) {
		Set<String> mov_lst;
		if (act_mov.get(key) != null) {
			mov_lst = act_mov.get(key);
			mov_lst.add(mov);
			act_mov.put(key, mov_lst);
		} else {
			mov_lst = new HashSet<>();
			mov_lst.add(mov);
			act_mov.put(key, mov_lst);
		}		
	}
	
	public static void add_act_act(String key, Map<String, Set<String>> act_act, String mov) {
		Set<String> mov_lst;
		if (act_act.get(key) != null) {
			mov_lst = act_act.get(key);
			mov_lst.add(mov);
			act_act.put(key, mov_lst);
		} else {
			mov_lst = new HashSet<>();
			mov_lst.add(mov);
			act_act.put(key, mov_lst);
		}
	}
	
	public static void add_dct_act(String key, Map<String, Set<String>> dct_act, String mov) {
		Set<String> mov_lst;
		if (dct_act.get(key) != null) {
			mov_lst = dct_act.get(key);
			mov_lst.add(mov);
			dct_act.put(key, mov_lst);
		} else {
			mov_lst = new HashSet<>();
			mov_lst.add(mov);
			dct_act.put(key, mov_lst);
		}
	}

}
