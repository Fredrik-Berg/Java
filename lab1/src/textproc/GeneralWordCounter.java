package textproc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class GeneralWordCounter implements TextProcessor {
	private Map<String, Integer> m;
	private Set<String> stop;
	
	public GeneralWordCounter(Set<String> s) 	{
		m = new TreeMap <String, Integer>();
		this.stop = s;
		
		
	}
	@Override
	public void process(String w){
		boolean check = false;
		if(stop.contains(w) && check == false){
			check = true;
		}
		if(!check){
			if(m.containsKey(w)){
				m.put(w, (m.get(w)+1));
			}
			else{
				m.put(w, 1);
			}
		}
	}

	@Override
	public void report() {
		
		Set<Map.Entry<String, Integer>> wordSet = m.entrySet();
		List<Map.Entry<String, Integer>> wordList = new ArrayList<>(wordSet);
		wordList.sort(new WordCountComparator());
		for(int i = 0; i < 20; i++){
			System.out.println(wordList.get(i).getKey()+ " : " + wordList.get(i).getValue());	
		}
	}
	
	public Set<Map.Entry<String, Integer>> getWords(){
		return m.entrySet();
		
	}
		
}
	


