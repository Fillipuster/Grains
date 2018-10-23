package javafx_base;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	public static void main(String[] args) {
		Application.launch(args);
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
	
	private void initContent(GridPane pane) {
		Button btnHelloWorld = new Button("Hello World!");
		btnHelloWorld.setOnAction(e -> btnHelloWorldAction());
		pane.add(btnHelloWorld, 0, 0);
	}
	
	private void btnHelloWorldAction() {
		System.out.println("Hello World!");
	}

}
