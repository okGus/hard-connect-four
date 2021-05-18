import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.text.Font;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.shape.Shape;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ConnecFourMain extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) {
        // Set tile
        stage.setTitle("Connect Four");

        Player user = new RealPlayer("Red");
        Player user2 = new RealPlayer("Yellow");

        // Dimension of screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        // Intro Scene - label
        Label intro_label = new Label("Welcome to Connect Four");
        intro_label.setFont(new Font("Arial", 30));
        intro_label.relocate((width / 2 - 180), 150);

        // Intro Scene - button
        Button one_player_button = new Button("1 Player");
        one_player_button.setFont(new Font("Times New Roman", 20));
        one_player_button.setMinHeight(30);
        one_player_button.setMaxHeight(40);
        one_player_button.setMinWidth(200);
        one_player_button.setMaxWidth(400);
        one_player_button.setPrefHeight(250);
        one_player_button.setPrefWidth(35);

        Button two_players_button = new Button("2 Players");
        two_players_button.setFont(new Font("Times New Roman", 20));
        two_players_button.setMinHeight(30);
        two_players_button.setMaxHeight(40);
        two_players_button.setMinWidth(200);
        two_players_button.setMaxWidth(400);
        two_players_button.setPrefHeight(250);
        two_players_button.setPrefWidth(35);

        // Intro Scene - one player settings
        Label one_player_label = new Label("Choose your color");
        one_player_label.setFont(new Font("Arial", 20));

        RadioButton yellow_button = new RadioButton("Yellow Coin");
        yellow_button.setFont(new Font("Times New Roman", 15));

        RadioButton red_button = new RadioButton("Red Coin");
        red_button.setFont(new Font("Times New Roman", 15));

        ToggleGroup coin_buttons = new ToggleGroup();
        yellow_button.setToggleGroup(coin_buttons);
        red_button.setToggleGroup(coin_buttons);

        VBox emptyOne = new VBox();
        VBox coin_options = new VBox(5, one_player_label, yellow_button, red_button);
        VBox one_player_settings = new VBox(one_player_button, coin_options);

        // Intro Scene - two players settings
        VBox two_players_settings = new VBox(two_players_button);

        // Intro Scene - AI and play
        Label ai_label = new Label("Choose AI level");
        ai_label.setFont(new Font("Arial", 20));

        RadioButton ai_button_one = new RadioButton("Level 1");
        ai_button_one.setFont(new Font("Times New Roman", 15));

        RadioButton ai_button_two = new RadioButton("Level 2");
        ai_button_two.setFont(new Font("Times New Roman", 15));

        ToggleGroup ai_buttons = new ToggleGroup();
        ai_button_one.setToggleGroup(ai_buttons);
        ai_button_two.setToggleGroup(ai_buttons);

        VBox ai_setting = new VBox(5, ai_label, ai_button_one, ai_button_two);

        // Intro Scene - play button
        Button play_button = new Button("Play");
        play_button.setFont(new Font("Times New Roman", 20));
        play_button.setMinHeight(30);
        play_button.setMaxHeight(40);
        play_button.setMinWidth(200);
        play_button.setMaxWidth(400);
        play_button.setPrefHeight(250);
        play_button.setPrefWidth(35);

        // Intro Scene - all settings combined
        HBox settings = new HBox(20, one_player_settings, two_players_settings);
        settings.relocate((width / 2 - 200), (height / 2 - 100));

        // Button actions
        two_players_button.setOnAction(e -> {
            one_player_settings.getChildren().setAll(one_player_button, emptyOne);
            settings.getChildren().setAll(one_player_settings, two_players_settings);
            ai_setting.getChildren().setAll(ai_label, ai_button_one, ai_button_two);
            yellow_button.setSelected(false);
            red_button.setSelected(false);
            ai_button_one.setSelected(false);
            ai_button_two.setSelected(false);
        });

        one_player_button.setOnAction(e -> {
            one_player_settings.getChildren().setAll(one_player_button, coin_options);
            settings.getChildren().setAll(one_player_settings, two_players_settings);
            ai_setting.getChildren().setAll(ai_label, ai_button_one, ai_button_two);
            yellow_button.setSelected(false);
            red_button.setSelected(false);
            ai_button_one.setSelected(false);
            ai_button_two.setSelected(false);
        });

        yellow_button.setOnAction(e -> {
            // instantiate player with yellow coin
            settings.getChildren().setAll(one_player_settings, two_players_settings, ai_setting);
            user.setColor("Yellow");
        });

        red_button.setOnAction(e -> {
            // instantiate player with red coin
            settings.getChildren().setAll(one_player_settings, two_players_settings, ai_setting);
            user.setColor("Red");
        });

        ai_button_one.setOnAction(e -> {
            // instantiate simple ai
            ai_setting.getChildren().setAll(ai_label, ai_button_one, ai_button_two, play_button);
            if (user.getColor().equals("Red")) {
                Player ai = new FoolAI("Yellow");
            } else {
                Player ai = new FoolAI("Red");
            }
        });

        ai_button_two.setOnAction(e -> {
            // instantiate complex ai
            ai_setting.getChildren().setAll(ai_label, ai_button_one, ai_button_two, play_button);
            if (user.getColor().equals("Yellow")) {
                Player ai = new FoolAI("Red");
            } else {
                Player ai = new FoolAI("Yellow");
            }
        });

        play_button.setOnAction(e -> {
            // Switch to game scene
            game(stage);
        });

        Pane intro_pane = new Pane(intro_label, settings);
        Scene intro_scene = new Scene(intro_pane, width, height);

        stage.setScene(intro_scene);
        stage.setResizable(true);
        stage.show();
    }

    public void game(Stage secondary_stage) {
        Board connectFourBoard = new Board();

        // creating the circle objects
        Circle[][] circle = new Circle[8][8];

        // storing the gameboard in an Image variable
        Image image = new Image("file:Connect4GameBoard.png");
        ImageView imageView = new ImageView(image);

        // setting the width and height of the image
        imageView.setFitWidth(600);
        imageView.setFitHeight(600);
        imageView.setPreserveRatio(true);

        // SETTNG THE COORDINATES FOR THE FIRST CIRCLE
        float xCoord = 70.0f;
        float yCoord = 38.0f;
        float radius = 30.0f;

        // for loop for placing the circles on the board
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {

                // initializing circle objects
                circle[i][j] = new Circle();
                // Circle
                circle[i][j].setCenterX((float) xCoord);
                circle[i][j].setCenterY((float) yCoord);
                circle[i][j].setRadius((float) radius);

                // Setting color to the circle
                circle[i][j].setFill(Color.WHITE);

                // Setting the stroke width
                circle[i][j].setStrokeWidth(3);

                // Setting color to the stroke
                circle[i][j].setStroke(Color.DARKBLUE);

                // CHANGING THE COLOR OF THE CIRCLE WHEN CLICKED
                circle[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        ((Shape) e.getSource()).setFill(Color.BLUE);
                    }
                });

                xCoord += 70;
                // checking if i is the 8th item in the row and then placing the circles in the
                // next row
                // removing the "drifting" of the circles away from the game board
                if (j == 7) {
                    yCoord += 65;
                    xCoord = 70.0f;
                }

                if (i == 1 && j == 0) {
                    yCoord += 5;

                }
                if (i == 2 && j == 0) {
                    yCoord += 10;

                }
                if (i == 4 && j == 0) {
                    yCoord += 10;

                }
                if (i == 6 && j == 0) {
                    yCoord += 5;

                }

            }
        } // end of for loop

        // Create an gridPane container and add the Labels.
        Group labels = new Group();
        labels.getChildren().add(imageView);
        // adding each one of the circle objects
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                labels.getChildren().add(circle[i][j]);
            }
        }

        // Setting the back ground color
        labels.setStyle("-fx-background-color: BLUE;");

        // Create a Scene with the HBox as its root node.
        Scene game_scene = new Scene(labels, 600, 600);

        secondary_stage.setScene(game_scene);
        secondary_stage.show();
    }
}