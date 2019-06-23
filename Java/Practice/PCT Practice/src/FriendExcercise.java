import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class FriendExcercise {

	private static Scanner s;

	public static void main(String[] args) {

		s = new Scanner(System.in);

		int num_user = Integer.parseInt(s.nextLine());
		int num_msgs = Integer.parseInt(s.nextLine());

		Map<String, Integer> msg_map = new HashMap<>();
		Map<String, List<Integer>> in_out_count = new HashMap<>();

		List<String> bf = new ArrayList<>();
		List<String> f  = new ArrayList<>();
		List<String> aq = new ArrayList<>();
		
		for (int i = 1; i <= num_msgs; i++) {
			String[] inp_line = s.nextLine().split(" ");
			String key = "";
			if (inp_line[0].equals("+")) {
				key = inp_line[1] + "_" + inp_line[2];
				add_msg(key, msg_map);
				key = inp_line[1] + "_" + inp_line[3];
				add_msg(key, msg_map);
				add_in_out(inp_line[1], in_out_count, "out");
				add_in_out(inp_line[1], in_out_count, "out");
				add_in_out(inp_line[2], in_out_count, "in");
				add_in_out(inp_line[3], in_out_count, "in");
			} else if (inp_line[1].equals("-")) {
				key = inp_line[1] + "_" + inp_line[2];
				del_msg(key, msg_map);
				key = inp_line[1] + "_" + inp_line[3];
				del_msg(key, msg_map);
				del_in_out(inp_line[1], in_out_count, "out");
				del_in_out(inp_line[1], in_out_count, "out");
				del_in_out(inp_line[2], in_out_count, "in");
				del_in_out(inp_line[3], in_out_count, "in");
			}
		}
		
		find_best_friends(msg_map,bf,num_user);
		find_friends(msg_map,bf,f,num_user);
		find_aquaint(msg_map,bf,f,aq,num_user);
		
		System.out.print("Spammers:");
		String spammers = "";
		for (Entry<String, List<Integer>> e : in_out_count.entrySet()) {
			if (e.getValue().get(0) >= 3 && e.getValue().get(1) == 0) {
				spammers = spammers + e.getKey() + ","; 
			}
		}
		spammers = spammers.substring(0,spammers.length());
	}

	public static void find_best_friends(Map<String, Integer> msg_map, List<String> bf, int num_user) {
		for (int i = 0; i < num_user; i++) {
			for (Entry<String, Integer> e1 : msg_map.entrySet()) {
				String[] pair1 = e1.getKey().split("_");
				if (pair1[0].equals(Integer.toString(i)) && e1.getValue() >= 5 && msg_map.get(pair1[1]+"_"+pair1[0]) != null && msg_map.get(pair1[1]+"_"+pair1[0]) >= 5) {
					for (Entry<String, Integer> e2 : msg_map.entrySet()) {
						String[] pair2 = e2.getKey().split("_");
						if (pair2[0].equals(pair1[1]) && !pair2[1].equals(pair1[0]) && e2.getValue() >= 5 && msg_map.get(pair2[1]+"_"+pair2[0]) != null && msg_map.get(pair2[1]+"_"+pair2[0]) >= 5) {
							for (Entry<String, Integer> e3 : msg_map.entrySet()) {
								String[] pair3 = e3.getKey().split("_");
								if (pair3[0].equals(pair2[1]) && !pair3[1].equals(pair2[0]) && pair3[1].equals(pair1[0]) && e3.getValue() >= 5 
									&& msg_map.get(pair3[1]+"_"+pair3[0]) != null && msg_map.get(pair3[1]+"_"+pair3[0]) >= 5) {
									List<String> group = new ArrayList<>();
									group.add(pair1[0]);
									group.add(pair1[1]);
									group.add(pair2[1]);
									Collections.sort(group);			
									if (!bf.contains(group.get(0)+"_"+group.get(1)+"_"+group.get(2))) {
										bf.add(group.get(0)+"_"+group.get(1)+"_"+group.get(2));
									}
								}
							}															
						}
					}								
				}
			}			
		}
	}

	public static void find_friends(Map<String, Integer> msg_map, List<String> bf, List<String> f, int num_user) {
		for (int i = 0; i < num_user; i++) {
			for (Entry<String, Integer> e1 : msg_map.entrySet()) {
				String[] pair1 = e1.getKey().split("_");
				if (pair1[0].equals(Integer.toString(i)) && e1.getValue() >= 10 && msg_map.get(pair1[1]+"_"+pair1[0]) != null && msg_map.get(pair1[1]+"_"+pair1[0]) >= 10) {
					for (Entry<String, Integer> e2 : msg_map.entrySet()) {
						String[] pair2 = e2.getKey().split("_");
						if (pair2[0].equals(pair1[1]) && !pair2[1].equals(pair1[0]) && e2.getValue() >= 10 && msg_map.get(pair2[1]+"_"+pair2[0]) != null && msg_map.get(pair2[1]+"_"+pair2[0]) >= 10) {
							for (Entry<String, Integer> e3 : msg_map.entrySet()) {
								String[] pair3 = e3.getKey().split("_");
								if (pair3[0].equals(pair2[1]) && !pair3[1].equals(pair2[0]) && pair3[1].equals(pair1[0]) && e3.getValue() >= 10 
									&& msg_map.get(pair3[1]+"_"+pair3[0]) != null && msg_map.get(pair3[1]+"_"+pair3[0]) >= 10) {
									List<String> group = new ArrayList<>();
									group.add(pair1[0]);
									group.add(pair1[1]);
									group.add(pair2[1]);
									Collections.sort(group);			
									if (!f.contains(group.get(0)+"_"+group.get(1)+"_"+group.get(2)) && !bf.contains(group.get(0)+"_"+group.get(1)+"_"+group.get(2))) {
										f.add(group.get(0)+"_"+group.get(1)+"_"+group.get(2));
									}
								}
							}															
						}
					}								
				}
			}			
		}
	}

	public static void find_aquaint(Map<String, Integer> msg_map, List<String> bf, List<String> f, List<String> aq, int num_user) {
		for (int i = 0; i < num_user; i++) {
			for (Entry<String, Integer> e1 : msg_map.entrySet()) {
				String[] pair1 = e1.getKey().split("_");
				if (pair1[0].equals(Integer.toString(i)) && e1.getValue() >= 10 && msg_map.get(pair1[1]+"_"+pair1[0]) != null && msg_map.get(pair1[1]+"_"+pair1[0]) >= 2) {
					for (Entry<String, Integer> e2 : msg_map.entrySet()) {
						String[] pair2 = e2.getKey().split("_");
						if (pair2[0].equals(pair1[1]) && !pair2[1].equals(pair1[0]) && e2.getValue() >= 2 && msg_map.get(pair2[1]+"_"+pair2[0]) != null && msg_map.get(pair2[1]+"_"+pair2[0]) >= 2) {
							for (Entry<String, Integer> e3 : msg_map.entrySet()) {
								String[] pair3 = e3.getKey().split("_");
								if (pair3[0].equals(pair2[1]) && !pair3[1].equals(pair2[0]) && pair3[1].equals(pair1[0]) && e3.getValue() >= 2 
									&& msg_map.get(pair3[1]+"_"+pair3[0]) != null && msg_map.get(pair3[1]+"_"+pair3[0]) >= 2) {
									List<String> group = new ArrayList<>();
									group.add(pair1[0]);
									group.add(pair1[1]);
									group.add(pair2[1]);
									Collections.sort(group);			
									if (!aq.contains(group.get(0)+"_"+group.get(1)+"_"+group.get(2)) && !f.contains(group.get(0)+"_"+group.get(1)+"_"+group.get(2)) && !bf.contains(group.get(0)+"_"+group.get(1)+"_"+group.get(2))) {
										aq.add(group.get(0)+"_"+group.get(1)+"_"+group.get(2));
									}
								}
							}															
						}
					}								
				}
			}			
		}
	}
	
	public static void add_in_out(String user, Map<String, List<Integer>> in_out_count, String ind) {
		List<Integer> in_out_def;
		in_out_def = new ArrayList<>(Arrays.asList(new Integer[] { 0, 0 }));

		if (ind.equals("out")) {
			if (in_out_count.get(user) != null) {
				in_out_def = in_out_count.get(user);
				in_out_def.set(0, in_out_def.get(0) + 1);
				in_out_count.put(user, in_out_def);
			} else {
				in_out_def.set(0, 1);
				in_out_count.put(user, in_out_def);
			}
		}
		if (ind.equals("in")) {
			if (in_out_count.get(user) != null) {
				in_out_def = in_out_count.get(user);
				in_out_def.set(1, in_out_def.get(1) + 1);
				in_out_count.put(user, in_out_def);
			} else {
				in_out_def.set(1, 1);
				in_out_count.put(user, in_out_def);
			}
		}
	}

	public static void del_in_out(String user, Map<String, List<Integer>> in_out_count, String ind) {
		List<Integer> in_out_def;
		in_out_def = new ArrayList<>(Arrays.asList(new Integer[] { 0, 0 }));

		if (ind.equals("out")) {
			if (in_out_count.get(user) != null) {
				in_out_def = in_out_count.get(user);
				in_out_def.set(0, in_out_def.get(0) - 1);
				in_out_count.put(user, in_out_def);
			}
		}
		if (ind.equals("in")) {
			if (in_out_count.get(user) != null) {
				in_out_def = in_out_count.get(user);
				in_out_def.set(1, in_out_def.get(1) - 1);
				in_out_count.put(user, in_out_def);
			}
		}
	}

	public static void add_msg(String key, Map<String, Integer> msg_map) {
		int count = 0;
		if (msg_map.get(key) != null) {
			count = msg_map.get(key);
			count++;
			msg_map.put(key, count);
		} else {
			msg_map.put(key, 1);
		}
	}

	public static void del_msg(String key, Map<String, Integer> msg_map) {
		int count = 0;
		if (msg_map.get(key) != null) {
			count = msg_map.get(key);
			count--;
			msg_map.put(key, count);
		}
	}

}
