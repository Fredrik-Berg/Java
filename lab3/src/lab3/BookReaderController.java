package lab3;

import java.io.File;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import textproc.GeneralWordCounter;
//import textproc.TextProcessor;

public class BookReaderController extends Application {
	
	ObservableList<Entry<String, Integer>> words;

	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		Scene scene = new Scene(root, 500, 500);
		primaryStage.setTitle("Bookreader");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		Scanner scan = new Scanner(new File("undantagsord.txt"));
		Set<String> stopwords = new HashSet<String>();
		while(scan.hasNext()){
			stopwords.add(scan.next());
		}
		
		scan.close();
		
		GeneralWordCounter r = new GeneralWordCounter(stopwords);
		
		Scanner s = new Scanner(new File("nilsholg.txt"));
		s.useDelimiter("(\\s|,|\\.|:|;|!|\\?|'|\\\")+");

	while (s.hasNext()) {
			String word = s.next().toLowerCase();
			r.process(word);
			}
	
		s.close();
	
	Set<Map.Entry<String, Integer>> w = new HashSet<Map.Entry<String, Integer>>(r.getWords());
	
	words = FXCollections.observableArrayList(w);
	
	ListView<Entry<String, Integer>> listview = new ListView<Entry<String, Integer>>(words);
	
	root.setCenter(listview);
	
	HBox hbox  = new HBox();
	Button button1 = new Button("Alphabetic");
	Button button2 = new Button("Frequency");
	Button button3 = new Button("Go");
	button3.setDefaultButton(true);
	TextField textfield = new TextField();
	Alert alert = new Alert(AlertType.INFORMATION, "Ordet du sökt efter finns inte");
	HBox.setHgrow(textfield, Priority.ALWAYS);
	button1.setOnAction(e -> words.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey())));
	button2.setOnAction(e -> words.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue())));
	button3.setOnAction(e -> { 
		for(Entry<String, Integer> entry : words){
			if(entry.getKey().equals(textfield.getText())){
				listview.scrollTo(entry);
				return;
			} 
			
		}
		alert.show();
				
	});
	hbox.getChildren().addAll(button1, button2, textfield, button3);
	
	root.setBottom(hbox);
	
	
	}



	public static void main(String[] args) {
		Application.launch(args);

	}

}
