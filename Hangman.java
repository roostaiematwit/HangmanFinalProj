package application;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
 
public class Hangman extends Application implements Initializable {
	
	int amountOfGuesses = 6;
	Random random = new Random();
	String[] guesses = {"Pedro", "Marco", "Aden", "daTeam", "Computer", "Science", "Adam"};
	char[] randomWordToGuess = guesses[random.nextInt(guesses.length)].toLowerCase().toCharArray(); //picks a random word from guesses and splits its letters into an array (fruit -> f,r,u,i,t)
	ArrayList<Character> displayLetters = new ArrayList<>();
	char[] playerProgress = new char[randomWordToGuess.length];                             //creates a char array to save user input (_ _ _ _)
	
	
	@FXML
	Button enterButton;
	@FXML
	Label wrongGuess;
	@FXML
	AnchorPane anchorPane;
	@FXML
	TextArea letEnter;
	@FXML
	Label playerProg;
	@FXML
	Label tries;
	

    public static void main(String[] args) {
        launch(args);
    }
	    
	   @Override
	   public void start(Stage primaryStage) throws Exception {
		   
		   
	       //NECCESARY BELOW
		   Parent root = FXMLLoader.load(getClass().getResource("/Main.fxml"));
		   Scene scene = new Scene(root);
		   primaryStage.setTitle("HANGMAN");
		   primaryStage.setScene(scene);
		   primaryStage.show();
	   }
	   @Override
		public void initialize(URL location, ResourceBundle resources) {
		   tries.setText(""+ amountOfGuesses);
		 
		   
		   for (int i = 0; i < randomWordToGuess.length;i++){                              //saves _ in place of letters
				playerProgress[i] = '_';
			}
		   String string = new String(playerProgress);
		   playerProg.setText(string);
		   
			enterButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					char c = letEnter.getText().toLowerCase().charAt(0);
					boolean test = false;
					for (int i =0; i < randomWordToGuess.length; i++) {
						if (randomWordToGuess[i] == c) {
							test = true;
							
						}
					}
					for (int i = 0;  i < randomWordToGuess.length; i++){  				//changes the underscores to the letter if guessed right
						if(randomWordToGuess[i] == c){
							playerProgress[i] = c; 
						}
					}
					letEnter.setText("");
					if (!test) {
						amountOfGuesses--;
						displayLetters.add(c);
						update(amountOfGuesses, anchorPane);
						
					}
					String string = new String(playerProgress);
					playerProg.setText(string);
					tries.setText(""+ amountOfGuesses);
					String s = "";
					for (int i = 00; i < displayLetters.size(); i++) {
						s+= (displayLetters.get(i) + " ");
					}
					wrongGuess.setText(s);
					boolean check = true;
					for (int i = 0; i < playerProgress.length; i++) {
						if (playerProgress[i] == '_') {
							check = false;
						}
					}
					if (check) {
						enterButton.setDisable(true);
						playerProg.setText("YOU WIN!!");
					}
				}
				
			});
			
			
	   }
	   public class FXMLDocumentController {
		   @FXML
		    void aButton(ActionEvent event) {
			   System.out.print("a");
		    }
	   }
	   
	   private static final int bodyStartX = 200;
       private static final int bodyStartY = 70;
       private static final int bodyEndX = bodyStartX;
       private static final int bodyEndY = bodyStartY + 100;

    
       	public void makeHead(Pane p)
       	{
       		Circle head = new Circle(20);
	            head.setTranslateX(bodyStartX);
	            head.setTranslateY(bodyStartY-20);
	            p.getChildren().add(head);
       	}
	        	
           public void makeSpine(Pane p) {
           	Line spine = new Line();
               spine.setStartX(bodyStartX);
               spine.setStartY(bodyStartY);
               spine.setEndX(bodyEndX);
               spine.setEndY(bodyEndX);
               p.getChildren().add(spine);
           }
       	
           public void makeArmL(Pane p) {
               Line leftArm = new Line();
               leftArm.setStartX(bodyStartX);
               leftArm.setStartY(bodyStartY + 15);
               leftArm.setEndX(bodyStartX + 40);
               leftArm.setEndY(bodyStartY + 25);
               p.getChildren().add(leftArm);

           }
           
           public void makeArmR(Pane p) {
           	Line rightArm = new Line();
               rightArm.setStartX(bodyStartX);
               rightArm.setStartY(bodyStartY + 15);
               rightArm.setEndX(bodyStartX - 40);
               rightArm.setEndY(bodyStartY + 25);
               p.getChildren().add(rightArm);
           }
           
           public void makeLegL(Pane p) {
           	Line leftLeg = new Line();
               leftLeg.setStartX(bodyEndX);
               leftLeg.setStartY(bodyEndY + 30);
               leftLeg.setEndX(bodyEndX + 25);
               leftLeg.setEndY(bodyEndY + 70);
               p.getChildren().add(leftLeg);

           }
           
           public void makeLegR(Pane p) {
           	Line rightLeg = new Line();
               rightLeg.setStartX(bodyEndX);
               rightLeg.setStartY(bodyEndY + 30);
               rightLeg.setEndX(bodyEndX - 25);
               rightLeg.setEndY(bodyEndY + 70);
               p.getChildren().add(rightLeg);
           }
           
           public void update(int n, Pane p)
           {
           	if(n > 6 || n < 0)
           	{
           		System.out.print("no");
           		System.exit(0);
           	}
           	if(n == 6)
           	{     	}
           	else if(n == 5)
           	{
           		makeHead(p);
           	}
           	else if(n == 4)
           	{
           		makeSpine(p);
           	}
           	else if(n == 3)
           	{
           		makeArmL(p);
           	}
           	else if(n == 2)
           	{
           		makeArmR(p);
           	}
           	else if(n == 1)
           	{
           		makeLegL(p);
           	}
           	else if(n == 0)
           	{
           		makeLegR(p);
           	}
           }			
		}



