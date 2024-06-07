import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
/**
 * The MexicanTrainGame class represents the Mexican Train game program.
 * It handles all the GUI.
 */
public class MexicanTrainGame extends Application{
    //a linked list of dominoes all players draw from.
    LinkedList drawpile = new LinkedList();
    //an arraylist of linked lists that stores the dominoes each player currently has in their hand.
    ArrayList<LinkedList> playerpiles = new ArrayList<LinkedList>();
    //an arraylist that stores the domino trains in the main stage/window of the game.
            ArrayList<DominoTrain> trainsinscene = new ArrayList<DominoTrain>();
    //an arraylist of the hboxes in the main stage/window (values gotten from trainsinscene arraylist)
    ArrayList<HBox> hboxesinscene = new ArrayList<HBox>();
    //an array of booleans representing which players' trains are can be played on by other players.
    boolean[] openedtrains;
    //an arraylist of hboxes representing which dominoes are in a different players
    hands.
            ArrayList<HBox> hboxforplayerhand = new ArrayList<HBox>();
    //the previously selected domino button.
    private Button prevbutton;
    //the currently selected domino button.
    private Button selectedbutton;
    //int storing which player's turn it is.
    private int playerturn = 1;
    //stores how many rounds of the game are left.
    private int startroundnumber = 9;
    //stores the first stage/window seen when the game launches.
    Stage firststage;
    /**
     * The start method initializes the first window of the game, asking how many
     players there are.
     *
     * @param primaryStage The primary stage for the application.
     */
    public void start(Stage primaryStage){
        firststage = primaryStage;
        Text text = new Text("Welcome to the Mexican Train Game!\nHow many people will be playing today?\n\n(Enter a numeric value from 2 - 9)");
                TextField field = new TextField();
        field.setMaxWidth(100);
        Button button = new Button("Okay!");
        button.setOnAction(e -> setPlayers(e,field));
        VBox vbox = new VBox(text,field,button);
        vbox.setSpacing(20);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox,500,500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Sets up the player names based on user input and moves to the next scene to
     start the game.
     *
     * @param event The ActionEvent triggered by clicking okay after specifying the
    number of players.
     * @param field The TextField where the user entered the number of players.
     * @throws IllegalArgumentException If the input is not a valid integer or is
    less than 2 or greater than 9.
     */
    private void setPlayers(ActionEvent event, TextField field) throws
            IllegalArgumentException {
// Validate the input for the number of players
        try {
            int numofplayers = Integer.parseInt(field.getText());
//game works for 2 through 9 players
            if (numofplayers < 2 || numofplayers > 9) {
                throw new IllegalArgumentException("Number of players must be between 2 and 9.");
            }
//storing an empty array of size number of player in openedtrains
            created earlier.
            openedtrains = new boolean[numofplayers];
//setting all elements of openedtrains array to false because each player's train starts off as closed.
            for (int i = 0; i < numofplayers; i++) {
                openedtrains[i] = false;
            }
//creating an array of text fields to collect player names.
            TextField[] playernamefield = new TextField[numofplayers];
            Button source = (Button) event.getSource();
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setSpacing(30);
//displaying the text fields on the screen so players can enter their names.
//storing the player names in an array, playernamefield
            for (int i = 0; i < numofplayers; i++) {
                TextField temptextfield = new TextField("Player " + (i + 1) + "'s name");
                        temptextfield.setMaxWidth(250);
                vbox.getChildren().add(temptextfield);
                playernamefield[i] = temptextfield;
            }
//button to start the game after names are collected.
            Button playbutton = new Button("Play!");
            playbutton.setOnAction(e -> startGame(e, numofplayers,
                    playernamefield));
            vbox.getChildren().add(playbutton);
//closing the previous window after game starts.
            Stage closestage = (Stage) source.getScene().getWindow();
            closestage.close();
            Scene scene = new Scene(vbox, 500, 500);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid input. Please enter a valid integer.");
        }
    }
    /**
     * Creates the game components and starts the game.
     *
     * @param event The ActionEvent triggered by clicking the play
    button.
     * @param numofplayers The number of players in the game.
     * @param playernamefield The TextField array storing the player names
     */
    private void startGame(ActionEvent event, int numofplayers,TextField[]
            playernamefield){
//an arraylist created to store all 55 dominoes being used.
        ArrayList<Domino> dominolist = new ArrayList<Domino>();
//loop to create the dominoes and add them to the arraylist.
        for(int i=0; i<=9; i++){
            for(int j=i; j<=9; j++){
                dominolist.add(new Domino(i,j,null));
            }
        }
//removing the last domino because that is the [9|9] domino, and it is reserved.
                dominolist.remove(54);
//shuffling the dominoes
        Collections.shuffle(dominolist);
        Button source = (Button)event.getSource();
//creating the main stage where the play happens.
        Stage mexicantrainstage = new Stage();
        mexicantrainstage.setTitle("The Mexican Train Game!");
//creating a VBox storing the HBoxes ("trains") in the main game.
        VBox vboxtrains = new VBox();
        vboxtrains.setSpacing(10);
        Label label1 = new Label("Mexican Train: ");
        Label label = new Label("["+startroundnumber+"|"+startroundnumber+"]");
//creating a new Domino Train to represent the Mexican train
        trainsinscene.add(new DominoTrain(startroundnumber));
        HBox hbox = new HBox(label1);
        hbox.getChildren().add(label);
//creating the add domino button at the end of the mexican train.
        Button adddomino = new Button("Add Domino");
//setting the add domino button's id as 0 for identification as the mexican train's add domino button.
        adddomino.setId("0");
        hbox.getChildren().add(adddomino);
        hboxesinscene.add(hbox);
//clicking add domino runs the addToTrain method.
        adddomino.setOnAction(e -> addToTrain(e,hboxesinscene,numofplayers));
        vboxtrains.getChildren().add(hbox);
//works on the GUI for each player train shown in the main game.
//does everything I did above for the Mexican train, but for each player.
//The id of the add domino button for each player differs by 1.
        for(int i=0; i<numofplayers; i++){
            Label labeltemp1 = new Label(playernamefield[i].getText()+"'s Train:
                    ");
                    Label labeltemp = new
                    Label("["+startroundnumber+"|"+startroundnumber+"]");
            HBox hboxtemp = new HBox(labeltemp1);
            hboxtemp.getChildren().add(labeltemp);
            trainsinscene.add(new DominoTrain(startroundnumber));
            Button adddominotemp = new Button("Add Domino");
            adddominotemp.setId(""+(i+1));
            hboxtemp.getChildren().add(adddominotemp);
            hboxesinscene.add(hboxtemp);
            adddominotemp.setOnAction(e ->
                    addToTrain(e,hboxesinscene,numofplayers));
            vboxtrains.getChildren().add(hboxtemp);
        }
//adding the vbox created above with all the trains to a scene and showing
        the scene.
        Scene mexicantrainscene = new Scene(vboxtrains,500,500);
        mexicantrainstage.setScene(mexicantrainscene);
        mexicantrainstage.show();//shuffling the dominoes
        Collections.shuffle(dominolist);
        Button source = (Button)event.getSource();
//creating the main stage where the play happens.
        Stage mexicantrainstage = new Stage();
        mexicantrainstage.setTitle("The Mexican Train Game!");
//creating a VBox storing the HBoxes ("trains") in the main game.
        VBox vboxtrains = new VBox();
        vboxtrains.setSpacing(10);
        Label label1 = new Label("Mexican Train: ");
        Label label = new Label("["+startroundnumber+"|"+startroundnumber+"]");
//creating a new Domino Train to represent the Mexican train
        trainsinscene.add(new DominoTrain(startroundnumber));
        HBox hbox = new HBox(label1);
        hbox.getChildren().add(label);
//creating the add domino button at the end of the mexican train.
        Button adddomino = new Button("Add Domino");
//setting the add domino button's id as 0 for identification as the mexican
        train's add domino button.
        adddomino.setId("0");
        hbox.getChildren().add(adddomino);
        hboxesinscene.add(hbox);
//clicking add domino runs the addToTrain method.
        adddomino.setOnAction(e -> addToTrain(e,hboxesinscene,numofplayers));
        vboxtrains.getChildren().add(hbox);
//works on the GUI for each player train shown in the main game.
//does everything I did above for the Mexican train, but for each player.
//The id of the add domino button for each player differs by 1.
        for(int i=0; i<numofplayers; i++){
            Label labeltemp1 = new Label(playernamefield[i].getText()+"'s Train:
                    ");
                    Label labeltemp = new
                    Label("["+startroundnumber+"|"+startroundnumber+"]");
            HBox hboxtemp = new HBox(labeltemp1);
            hboxtemp.getChildren().add(labeltemp);
            trainsinscene.add(new DominoTrain(startroundnumber));
            Button adddominotemp = new Button("Add Domino");
            adddominotemp.setId(""+(i+1));
            hboxtemp.getChildren().add(adddominotemp);
            hboxesinscene.add(hboxtemp);
            adddominotemp.setOnAction(e ->
                    addToTrain(e,hboxesinscene,numofplayers));
            vboxtrains.getChildren().add(hboxtemp);
        }
//adding the vbox created above with all the trains to a scene and showing
        the scene.
        Scene mexicantrainscene = new Scene(vboxtrains,500,500);
        mexicantrainstage.setScene(mexicantrainscene);
        mexicantrainstage.show();
//adding linked lists to the array list, playerpiles
//every player has a separate pile
        for(int i =0; i<numofplayers; i++){
            playerpiles.add(new LinkedList());
        }
//creating hboxes for each player
//this is the same as playerpiles, but for the GUI
        for(int j=0; j<numofplayers; j++){
            hboxforplayerhand.add(new HBox());
        }
//deals the dominoes from the dominolist arraylist to their hands
//adds to the linked lists and also adds to the hboxes, so we see the
        change visually.
        for(int i=0; i<numofplayers; i++){
            int count=0;
//every player gets 12 dominoes if there are 2 players
            if (numofplayers == 2) {
                for(int j=12*i; count<=11; j++){
                    playerpiles.get(i).addToFront(dominolist.get(j).getLeftValue(),dominolist.get(j).ge
                            tRightValue());
                    count++;
                    Button dominobutton = new
                            Button(playerpiles.get(i).getFirstDomino().toString());
                    dominobutton.setId(""+(i+1));
                    dominobutton.setOnAction(this::dominoSelect);
                    hboxforplayerhand.get(i).getChildren().add(dominobutton);
                }
            }
//if there are more than 2 players, the dominoes are shared equally
            between the players and the draw pile.
else if(numofplayers>2){
                for(int j=(55/(numofplayers+1))*i; count<=55/(numofplayers+1); j++)
                {
                    playerpiles.get(i).addToFront(dominolist.get(j).getLeftValue(),dominolist.get(j).getRightValue());
                    count++;
                    Button dominobutton = new Button(playerpiles.get(i).getFirstDomino().toString());
                    dominobutton.setId(""+(i+1));
                    dominobutton.setOnAction(this::dominoSelect);
                    hboxforplayerhand.get(i).getChildren().add(dominobutton);
                }
            }
//creating the GUI for each player's hand
            VBox vboxtemp = new VBox(hboxforplayerhand.get(i));
            Button drawButton = new Button("Draw");
//creating a draw button for each player that runs the drawDomino method.
                    drawButton.setOnAction(e->drawDomino(e,numofplayers));
//setting the id for each button to know which player is trying to
            draw.
                    drawButton.setId(""+(i+1));
            vboxtemp.getChildren().add(drawButton);
            Scene playerscene = new Scene(vboxtemp,750,400);
            vboxtemp.setSpacing(10);
            Stage playerstage = new Stage();
            playerstage.setScene(playerscene);
            playerstage.setTitle(playernamefield[i].getText());
            playerstage.show();
        }
//adding to the drawpile
//no GUI stuff because we don't need to see the drawpile in the game.
        if(numofplayers ==2){
//12 dominoes in the draw pile if 2 players are playing.
            for(int i=24; i<54; i++){
                drawpile.addToFront(dominolist.get(i).getLeftValue(),dominolist.get(i).getRightValue());
            }
        }
        else{
            int num = 0;
//adding to the draw pile from the domino list.
//equal sharing of dominoes since more than 2 people are playing.
            for(int j=53; num < (54/(numofplayers+1)); j--){
                drawpile.addToFront(dominolist.get(j).getLeftValue(),dominolist.get(j).getRightValue());
                num++;
            }
        }
        Stage closestage = (Stage)source.getScene().getWindow();
        closestage.close();
    }
    /**
     * Runs when a player clicks on a domino.
     *
     * @param event The ActionEvent triggered by selecting a domino button.
     */
    private void dominoSelect(ActionEvent event){
        Button source = (Button)event.getSource();
//printing to check if the button clicked has the right id.
        System.out.println(source.getId());
//setting selectedbutton to store the button clicked. (makes sense right?)
        selectedbutton = source;
        selectedbutton.setId(source.getId());
//changing the height of the button that is selected, so players know which
        button is selected.
                source.setPrefHeight(40);
        if(prevbutton != null){
            prevbutton.setPrefHeight(10);
        }
        prevbutton = source;
    }
    /**
     * Run when a player adds/tries to add a domino to a train in the main window.
     *
     * @param event The ActionEvent triggered by clicking the "Add Domino"
    button.
     * @param hboxes The ArrayList of HBoxes containing the trains in the main
    game scene.
     * @param numofplayers The number of players in the game.
     */
    private void addToTrain(ActionEvent event,ArrayList<HBox> hboxes, int
            numofplayers) {
        Button source = (Button) event.getSource();
//stores whichever player is trying to play
        int player= Integer.parseInt(source.getId());
        System.out.println(source.getId());
//creating a domino with the selected button's information
//takes the selected button's string and gets the characters 1, and 3, converts them to int and creates a domino with those ints.
        Domino selection = new Domino(Character.getNumericValue(selectedbutton.getText().charAt(1)),Character.getNumericValue(selectedbutton.getText().charAt(3)),null);
//check if person trying to add to train is authorized to play and is adding to owner's train
        if(playerturn==Integer.parseInt(selectedbutton.getId()) && player==playerturn){
//sets their train to be closed and updates the GUI if their train was open before.
            hboxesinscene.get(player).setBackground(null);
            openedtrains[player-1]=false;
        }
//check if its players turn and if they're adding to their own train, or if their adding to the mexican train, or if the train they're adding to is open
//note: selectedbutton is the button they selected before clicking add to train; it is different from source. source is the add to train button.
        if(playerturn==Integer.parseInt(selectedbutton.getId()) && (player==playerturn || player== 0 || openedtrains[player-1])){
//check if the domino train the player wants to add to has elements and if the player can add the selected button.
//makes use of isEmpty(), canAdd(), and addToFront() from domino train class
            if(!trainsinscene.get(player).isEmpty() &&
                    trainsinscene.get(player).canAdd(selection)){
                trainsinscene.get(player).addToFront(new
                        Domino(selection.getLeftValue(),selection.getRightValue(),null));
//updating the GUI reflect the changes just made to the domino train
                hboxes.get(player).getChildren().add(new
                        Label(trainsinscene.get(player).getFirstDomino().toString()));
//removing the button they just played
                hboxforplayerhand.get(Integer.parseInt(selectedbutton.getId())- 1).getChildren().remove(selectedbutton);
//ending the game if the player playing has no dominoes left in their hand
                if(hboxforplayerhand.get(Integer.parseInt(selectedbutton.getId())- 1).getChildren().isEmpty()){
                    gameOver(playerturn);
                    Stage closestage = (Stage)source.getScene().getWindow();
                    closestage.close();
                }
//after a player successfully adds to a train, its no longer their turn.
                        playerturn++;
//if player turn exceeds the number of players, the variable is reset (its player 1's turn again).
                if(playerturn>numofplayers){
                    playerturn=1;
                }
            }
//if the domino train the player wants to add to is empty,
//very similar to above
            else if(trainsinscene.get(player).isEmpty()){
                trainsinscene.get(player).addToFront(new
                        Domino(Character.getNumericValue(selectedbutton.getText().charAt(1)),Character.getNumericValue(selectedbutton.getText().charAt(3)),null));
                hboxes.get(player).getChildren().add(new Label(trainsinscene.get(player).getFirstDomino().toString()));
                hboxforplayerhand.get(Integer.parseInt(selectedbutton.getId())- 1).getChildren().remove(selectedbutton);
                if(hboxforplayerhand.get(Integer.parseInt(selectedbutton.getId())- 1).getChildren().isEmpty()){
                    gameOver(playerturn);
                    Stage closestage = (Stage)source.getScene().getWindow();
                    closestage.close();
                }
                playerturn++;
                if(playerturn>numofplayers){
                    playerturn=1;
                }
            }
//removing the add domino button because a domino was put after it
//and putting the add domino button at the end of the hboxes
//(Basically destroying the add domino button and re-creating it at the end of the hbox)
            hboxes.get(player).getChildren().remove(source);
            Button button = new Button("Add Domino");
            button.setOnAction(e -> addToTrain(e,hboxesinscene,numofplayers));
            hboxes.get(player).getChildren().add(button);
            button.setId(source.getId());
        }
    }
    /**
     * Handles the drawing of a domino from the draw pile.
     *
     * @param event The ActionEvent triggered by the clicking the "Draw"
    button.
     * @param numofplayers The number of players in the game.
     */
    private void drawDomino(ActionEvent event, int numofplayers) {
        Button source = (Button)event.getSource();
//only let the player play if its their turn
        if(playerturn==Integer.parseInt(source.getId())){
//set their train as open since they are drawing
            openedtrains[Integer.parseInt(source.getId())-1]=true;
//changing background color for the players train, to signal that their train is open
            hboxesinscene.get(Integer.parseInt(source.getId())).setBackground(new Background(new BackgroundFill(Color.AQUAMARINE,null,null)));
//don't mind me; just checking if it works
            System.out.println("Hi"+source.getId());
//adding a domino to the player's linked list since they drew.
//also making the GUI reflect this change.
            playerpiles.get(Integer.parseInt(source.getId())-
                    1).addToFront(drawpile.getFirstDomino().getLeftValue(),drawpile.getFirstDomino().ge
                    tRightValue());
            Button addedbutton = new Button(new
                    Domino(drawpile.getFirstDomino().getLeftValue(),drawpile.getFirstDomino().getRightV
                    alue(),null).toString());
//new button added (drew) should function as a domino, no?
            addedbutton.setOnAction(this::dominoSelect);
//setting id for identification
            addedbutton.setId(source.getId());
//putting the drawn button in the player's hand.
            hboxforplayerhand.get(Integer.parseInt(source.getId())- 1).getChildren().add(addedbutton);
//turn is over for whoever played.
            playerturn++;
            if(playerturn>numofplayers){
                playerturn=1;
            }
//removing the drawn domino from the drawpile.
//no GUI to update!
            drawpile.removeFromFront();
        }
    }Button button = new Button("Add Domino");
button.setOnAction(e -> addToTrain(e,hboxesinscene,numofplayers));
hboxes.get(player).getChildren().add(button);
button.setId(source.getId());
}
}
/**
 * Handles the drawing of a domino from the draw pile.
 *
 * @param event The ActionEvent triggered by the clicking the "Draw"
button.
 * @param numofplayers The number of players in the game.
 */
private void drawDomino(ActionEvent event, int numofplayers) {
    Button source = (Button)event.getSource();
//only let the player play if its their turn
    if(playerturn==Integer.parseInt(source.getId())){
//set their train as open since they are drawing
        openedtrains[Integer.parseInt(source.getId())-1]=true;
//changing background color for the players train, to signal that their
        train is open
        hboxesinscene.get(Integer.parseInt(source.getId())).setBackground(new
                Background(new BackgroundFill(Color.AQUAMARINE,null,null)));
//don't mind me; just checking if it works
        System.out.println("Hi"+source.getId());
//adding a domino to the player's linked list since they drew.
//also making the GUI reflect this change.
        playerpiles.get(Integer.parseInt(source.getId())-
                1).addToFront(drawpile.getFirstDomino().getLeftValue(),drawpile.getFirstDomino().ge
                tRightValue());
        Button addedbutton = new Button(new
                Domino(drawpile.getFirstDomino().getLeftValue(),drawpile.getFirstDomino().getRightV
                alue(),null).toString());
//new button added (drew) should function as a domino, no?
        addedbutton.setOnAction(this::dominoSelect);
//setting id for identification
        addedbutton.setId(source.getId());
//putting the drawn button in the player's hand.
        hboxforplayerhand.get(Integer.parseInt(source.getId())-
                1).getChildren().add(addedbutton);
//turn is over for whoever played.
        playerturn++;
        if(playerturn>numofplayers){
            playerturn=1;
        }
//removing the drawn domino from the drawpile.
//no GUI to update!
        drawpile.removeFromFront();
    }
}
/**
 * Displays the game-over screen and allows the option to play again.
 *
 * @param winner The player who won the game.
 */
private void gameOver(int winner){
    Stage gameover = new Stage();
    Button playagain = new Button("Play again?");
    playagain.setOnAction(e->start(firststage));
    VBox vbox = new VBox(new Text(""+winner+" won!\n"),playagain);
    Scene gameoverscene = new Scene(vbox);
    gameover.setScene(gameoverscene);
    gameover.show();
}
public static void main(String[] args){
    launch(args);
}
}
