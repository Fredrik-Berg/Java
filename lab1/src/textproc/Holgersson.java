package textproc;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Holgersson {

	public static final String[] REGIONS = { "blekinge", "bohuslän", "dalarna", "dalsland", "gotland", "gästrikland",
			"halland", "hälsingland", "härjedalen", "jämtland", "lappland", "medelpad", "närke", "skåne", "småland",
			"södermanland", "uppland", "värmland", "västerbotten", "västergötland", "västmanland", "ångermanland",
			"öland", "östergötland" };

	public static void main(String[] args) throws FileNotFoundException {
		
		long t0 = System.nanoTime();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while(scan.hasNext()){
			stopwords.add(scan.next());
		}
		
		scan.close();
		
		TextProcessor r = new GeneralWordCounter(stopwords);
		
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+"); 

	while (s.hasNext()) {
			String word = s.next().toLowerCase();
			r.process(word);
			}
	
		s.close();
		r.report();
		
		long t1 = System.nanoTime();
		System.out.println("tid: " + (t1 - t0) / 1000000.0 + " ms");
		}

		
	}

	
