package javafx_observablelist;

import java.util.ArrayList;

import generator.NameGenerator;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {
	
	private static ObservableList<Person> people;
	
	public static void main(String[] args) {
		generatePeople(2);
		Application.launch(args);
	}
	
	private static void generatePeople(int amount) {
		ArrayList<Person> generatedPeople = new ArrayList<>();
		for (int i = 0; i < amount; i++) {
			generatedPeople.add(generatePerson());
		}
		
		people = FXCollections.observableArrayList(generatedPeople);
	}
	
	private static Person generatePerson() {
		NameGenerator gen = NameGenerator.getInstance();
		Person p = new Person(gen.generateFirstName(), gen.generateLastName(), gen.generateAge(12));
		
		return p;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("JavaFX Grain");
		
		GridPane root = new GridPane();
		initRoot(root);
		
		Scene scene = new Scene(root);
//		scene.getStylesheets().add("style.css");
		
		primaryStage.setScene(scene);
		primaryStage.setWidth(1024);
		primaryStage.setHeight(576);
		primaryStage.show();
	}
	
	private void initRoot(GridPane pane) {
		pane.setPadding(new Insets(20));
		
		pane.setHgap(20);
		pane.setVgap(10);
		
		initContent(pane);
	}
	
	Button btnStreamline, btnAddPerson;
	ListView<Person> lvwPeople;
	
	private void initContent(GridPane pane) {
		lvwPeople = new ListView<>();
		lvwPeople.getItems().setAll(people);
		pane.add(lvwPeople, 0, 0, 2, 1);
		
		btnStreamline = new Button("Streamline");
		btnStreamline.setOnAction(e -> btnStreamlineAction());
		pane.add(btnStreamline, 0, 1);
		
		btnAddPerson = new Button("Add Person");
		btnAddPerson.setOnAction(e -> btnAddPersonAction());
		pane.add(btnAddPerson, 1, 1);
	}
	
	private void btnStreamlineAction() {
		for (Person p : people) {
			p.setLastName("Præstegaard");
		}
		lvwPeople.refresh();
	}
	
	private void btnAddPersonAction() {
		people.add(generatePerson());
		lvwPeople.refresh();
	}

}
