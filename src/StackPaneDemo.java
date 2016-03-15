import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by tomas on 3/11/16.
 */
public class StackPaneDemo extends Application {
	public static void main(String[] args){ launch(args);}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Det er ikke muligt at indsætte en Circle, Rectangle eller Label i to forskellige StackPane, derfor to af hver.
		Circle cirkel = new Circle(100, Color.RED);
		Circle btfCirkel = new Circle(100, Color.RED);
		Rectangle rektangel = new Rectangle(200, 200, Color.BLUE);
		Rectangle btfRektangel = new Rectangle(200, 200, Color.BLUE);
		Label tekst = new Label ("In front");
		Label btfTekst = new Label ("In back");

		//StackPane stabler sine elementer. Den sidste på listen ligger forrest.
		StackPane ftbPane = new StackPane(rektangel, cirkel, tekst);
		StackPane btfPane = new StackPane(btfTekst, btfCirkel, btfRektangel);	//Kun btfRektangel er synlig, da den dækker for de andre.

		//En Scene til hver StackPane.
		Scene ftbScene = new Scene(ftbPane);
		Scene btfScene = new Scene(btfPane);

		//En Stage til hver Scene.
		Stage ftbWindow = new Stage();
		ftbWindow.setTitle("Front to back");
		ftbWindow.setScene(ftbScene);
		ftbWindow.setResizable(false);
		ftbWindow.setX(200);
		ftbWindow.setY(200);
		ftbWindow.show();

		Stage btfWindow = new Stage();
		btfWindow.setTitle("Back to front");
		btfWindow.setScene(btfScene);
		btfWindow.setResizable(true);
		btfWindow.setX(500);
		btfWindow.setY(200);
		btfWindow.show();

		/**
		 * Luk det andet vindue, hvis det er åbent. Ellers luk dette.
		 * Her bruges SimpleBooleanProperty i stedet for boolean for at undgå fejlmeddelelsen:
		 * local variables referenced from a lambda expression must be final or effectively final
		 */
		SimpleBooleanProperty otherClosed = new SimpleBooleanProperty(false);
		ftbWindow.setOnCloseRequest(event -> {
			if(!otherClosed.get()) event.consume();
			btfWindow.close();
			otherClosed.set(true);
		});
		btfWindow.setOnCloseRequest(event -> {
			if(!otherClosed.get()) event.consume();
			ftbWindow.close();
			otherClosed.set(true);
		});
	}
}
