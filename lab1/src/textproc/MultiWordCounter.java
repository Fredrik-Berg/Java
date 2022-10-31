package textproc;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class MultiWordCounter implements TextProcessor{
	private Map<String, Integer> m;

	
	public MultiWordCounter(String [] L) {
	 m = new TreeMap <String, Integer>();
	 for(int i = 0; i < L.length; i++){
		 m.put(L[i], 0);
		}		
	}
	

	@Override
	public void process(String w) {
		 Integer value = m.get(w);
		 if(value != null){
			 m.put(w, value+1);
		 }
		 
		
	}

	@Override
	public void report() {
		for(String key : m.keySet()){
			System.out.println(key +" : " + m.get(key));
		}
	
		
	}

}
