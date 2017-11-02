import com.sun.javafx.tk.FontLoader;
import com.sun.javafx.tk.Toolkit;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
    //Image variables
    private Image title = new Image("resources/Photos/SNAKE.png", 300, 78, false, false);
    private Image SnakePic = new Image("resources/Photos/SnakeSnakeSnake.png", 256, 256, false, false);
    private Image back = new Image("resources/Photos/BACK.png", 178, 62, false, false);
    private Image backH = new Image("resources/Photos/BACK (1).png", 178, 62, false, false);
    private Image rules = new Image("resources/Photos/RULES.png", 178, 50, false, false);
    private Image rulesH = new Image("resources/Photos/RULES (1).png", 178, 50, false, false);
    private Image slug = new Image("resources/Photos/SLUG.png", 178, 62, false, false);
    private Image slugH = new Image("resources/Photos/SLUG (1).png", 178, 62, false, false);
    private Image worm = new Image("resources/Photos/WORM.png", 178, 62, false, false);
    private Image wormH = new Image("resources/Photos/WORM (1).png", 178, 62, false, false);
    private Image snakeButton = new Image("resources/Photos/SNAKEN.png", 220, 62, false, false);
    private Image snakeButtonH = new Image("resources/Photos/SNAKE (1).png", 220, 62, false, false);
    private Image difficulty = new Image("resources/Photos/DIFFICULTY.png", 406, 62, false, false);
    private Image musicPNG = new Image("resources/Photos/MUSIC.png", 222, 62, false, false);
    private Image on = new Image("resources/Photos/ON.png", 91, 62, false, false);
    private Image onH = new Image("resources/Photos/ON (1).png", 91, 62, false, false);
    private Image off = new Image("resources/Photos/OFF.png", 134, 62, false, false);
    private Image offH = new Image("resources/Photos/OFF (1).png", 134, 62, false, false);
    private Image options = new Image("resources/Photos/OPTIONS.png", 234, 50, false, false);
    private Image optionsH = new Image("resources/Photos/OPTIONS (1).png", 234, 50, false, false);
    private Image quit = new Image("resources/Photos/QUIT.png", 82, 31, false, false);
    private Image quitH = new Image("resources/Photos/QUIT (1).png", 82, 31, false, false);
    private Image CW = new Image("resources/Photos/CW (1).png", 93, 62, false, false);
    private Image CWH = new Image("resources/Photos/CW (2).png", 93, 62, false, false);
    private Image SnakeSnakeIcon = new Image("resources/Photos/SnakeSnake icon.png", 241, 251, false, false);
    private Image deadBox = new Image("resources/Photos/deadBox.jpg", 25, 25, false, false);
    private Image box = new Image("resources/Photos/box.jpg", 25, 25, false, false);
    private Image gameOver = new Image("resources/Photos/GAME-OVER.png", 481, 78, false, false);
    private Image mainMenu = new Image("resources/Photos/MENU (1).png", 89, 31, false, false);
    private Image mainMenuH = new Image("resources/Photos/MENU.png", 89, 31, false, false);
    private Image Orange = new Image("resources/Photos/Orange.png", 25, 25, false, false);
    private Image Banana = new Image("resources/Photos/Banana.png", 25, 25, false, false);
    private Image Cherry = new Image("resources/Photos/Cherry.png", 25, 25, false, false);
    private Image Turnip = new Image("resources/Photos/Turnip.png", 25, 25, false, false);
    private Image Pear = new Image("resources/Photos/Pear.png", 25, 25, false, false);
    private Image Apple = new Image("resources/Photos/Apple.png", 25, 25, false, false);
    private Image play = new Image("resources/Photos/PLAY.png", 178, 62, false, false);
    private Image playHover = new Image("resources/Photos/PLAYH.png", 178, 62, false, false);
    private Image playAgain = new Image("resources/Photos/PLAY-AGAIN.png", 414, 62, false, false);
    private Image playAgainH = new Image("resources/Photos/PLAY-AGAIN (1).png", 414, 62, false, false);

    //declares global sound files
    private Media CWA = new Media(Main.class.getResource("CW.mp3").toExternalForm());
    private Media ambience = new Media(Main.class.getResource("Ambience.mp3").toExternalForm());
    private AudioClip ring = new AudioClip(Main.class.getResource("resources/Sounds/Ring.wav").toString());
    private AudioClip death = new AudioClip(Main.class.getResource("resources/Sounds/Death.wav").toString());
    private AudioClip click = new AudioClip(Main.class.getResource("resources/Sounds/Click.wav").toString());

    //Variables for the Options screen
    private double dblDifficulty = 0.1;
    private static int music = 1;
    private MediaPlayer mediaPlayer = new MediaPlayer(ambience);

    //Variables for the Game Screen
    private int X = 0, Y = 0;
    private boolean hasMoved = false;
    private ObservableList<Node> snake;
    private int Direction = 1;
    private boolean pausedCondition = false;
    private boolean bigEnough;
    private static int width = 850, height = 550;
    private Timeline timeline = new Timeline();
    private int CandyX, CandyY;
    private int Score = 1;
    private int fruitPhoto = (int) (Math.random() * 6 + 1);
    private boolean gameOverCondition = false;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    
    /*
        The following is the navigation menu that the program begins on
     */
    
    public void start(Stage primaryStage) {
        //starts game music
        if (music == 1 || music == 2)
            mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        //declares variables
        Group root = new Group();
        GridPane gridpane = new GridPane();
        Canvas canvas = new Canvas(width, height);
        Scene scene = new Scene(root, width, height);
        root.getChildren().addAll(gridpane, canvas);

        //calls method to draw backdrop
        drawBackDrop(canvas.getGraphicsContext2D());

        //generates play button
        Button playButton = new Button(width / 2 - 90, 435, 178, 62, playHover, play, root);
        playButton.button.setOnMouseClicked(event -> Game(primaryStage));

        //generates rules button
        Button rulesButton = new Button(width / 5 - 110, 275, 178, 50, rulesH, rules, root);
        rulesButton.button.setOnMouseClicked(event -> Rules(primaryStage));

        //generates options button
        Button optionsButton = new Button(width * 4 / 5 - 120, 275,234,50,optionsH, options,root);
        optionsButton.button.setOnMouseClicked(event -> Options(primaryStage));

        //generates quit button
        Button quitButton = new Button(width - 85, height - 34, 82, 31, quitH, quit, root);
        quitButton.button.setOnMouseClicked(event -> Platform.exit());

        //start game when enter is clicked
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.ENTER))
            Game(primaryStage);
        });

        scene.setOnMouseClicked(event -> {
            if (music == 1 || music == 2)
                click.play();
        });

        //initialize stage
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.setTitle("Snake");
        primaryStage.getIcons().add(SnakeSnakeIcon);
        primaryStage.show();
    }

    //draws background
    private void drawBackDrop(GraphicsContext gc) {
        //draws black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        //draws menu title
        gc.setFill(new ImagePattern(title));
        gc.fillRect(width / 2 - 160, 35, 300, 78);
        //draws menu image
        gc.setFill(new ImagePattern(SnakePic));
        gc.fillRect(width / 2 - 140, 140, 256, 256);
    }



    /*
        The following is the options screen
     */

    private void Options(Stage primaryStage){
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        Scene scene = new Scene(root, width, height);
        root.getChildren().add(canvas);
        canvas.getGraphicsContext2D().setFill(Color.BLACK);
        canvas.getGraphicsContext2D().fillRect(0,0,width,height);

        //generates difficulty button
        Rectangle Difficulty = new Rectangle(width / 2 - 302, 75, 406, 62);
        Difficulty.setFill(new ImagePattern(difficulty));
        //generates difficulty button fill based on current difficulty
        Rectangle difficultyButton = new Rectangle(width / 2 + 114, 75, 178, 62);
        if (dblDifficulty == 0.2) {
            difficultyButton.setFill(new ImagePattern(slug));
        } else if (dblDifficulty == 0.1) {
            difficultyButton.setFill(new ImagePattern(worm));
        } else {
            difficultyButton.setFill(new ImagePattern(snakeButton));
        }
        //Changes button colour on mouse hover
        difficultyButton.setOnMouseEntered(event -> {
            if (dblDifficulty == 0.2) {
                difficultyButton.setFill(new ImagePattern(slugH));
            } else if (dblDifficulty == 0.1) {
                difficultyButton.setFill(new ImagePattern(wormH));
            } else if (dblDifficulty == 0.06) {
                difficultyButton.setFill(new ImagePattern(snakeButtonH));
            }
        });
        difficultyButton.setOnMouseExited(event -> {
            if (dblDifficulty == 0.2) {
                difficultyButton.setFill(new ImagePattern(slug));
            } else if (dblDifficulty == 0.1) {
                difficultyButton.setFill(new ImagePattern(worm));
            } else if (dblDifficulty == 0.06) {
                difficultyButton.setFill(new ImagePattern(snakeButton));
            }
        });
        //cycles through difficulty options when difficulty button is clicked
        difficultyButton.setOnMouseClicked(event -> {
            if (dblDifficulty == 0.2) {
                dblDifficulty = 0.1;
                difficultyButton.setFill(new ImagePattern(wormH));
                System.out.println("medium");
            } else if (dblDifficulty == 0.1) {
                dblDifficulty = 0.06;
                difficultyButton.setFill(new ImagePattern(snakeButtonH));
                System.out.println("hard");
            } else if (dblDifficulty == 0.06) {
                dblDifficulty = 0.2;
                difficultyButton.setFill(new ImagePattern(slugH));
                System.out.println("easy");
            }
        });

        //generates music button
        Rectangle Music = new Rectangle(width / 2 - 156, 225, 207, 62);
        Music.setFill(new ImagePattern(musicPNG));
        //sets button fill based on current music setting
        Rectangle musicButton = new Rectangle(width / 2 + 65, 225, 91, 62);
        if (music == 1)
            musicButton.setFill(new ImagePattern(on));
        if (music == 2)
            musicButton.setFill(new ImagePattern(CW));
        if (music == 0)
            musicButton.setFill(new ImagePattern(off));
        //changes button colour on mouse hover
        musicButton.setOnMouseEntered(event -> {
            if (music == 1) {
                musicButton.setFill(new ImagePattern(onH));
            } else if (music == 0) {
                musicButton.setFill(new ImagePattern(offH));
            } else {
                musicButton.setFill(new ImagePattern(CWH));
            }
        });
        musicButton.setOnMouseExited(event -> {
            if (music == 1) {
                musicButton.setFill(new ImagePattern(on));
            } else if (music == 0) {
                musicButton.setFill(new ImagePattern(off));
            } else {
                musicButton.setFill(new ImagePattern(CW));
            }
        });
        //toggles sound on or off when music button is clicked
        musicButton.setOnMouseClicked(event -> {
            if (music == 0) {
                music = 2;
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(CWA);
                mediaPlayer.play();
                musicButton.setFill(new ImagePattern(CWH));
                System.out.println("careless whisper");
            } else if (music == 1){
                music = 0;
                mediaPlayer.pause();
                musicButton.setFill(new ImagePattern(offH));
                System.out.println("no sound");
            } else {
                music = 1;
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(ambience);
                mediaPlayer.play();
                musicButton.setFill(new ImagePattern(onH));
            }
        });

        //generates back button
        Button backButton = new Button(width / 2 - 89, 375, 178, 62, backH, back, root);
        backButton.button.setOnMouseClicked(event -> start(primaryStage));

        //generates quit button
        Button quitButton = new Button(width - 85, height - 34, 82, 31, quitH, quit, root);
        quitButton.button.setOnMouseClicked(event -> Platform.exit());

        //adds buttons to scene
        root.getChildren().addAll(difficultyButton, Difficulty, musicButton, Music);

        //accepts user keyboard input
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                start(primaryStage);
            }
        });

        scene.setOnMouseClicked(event -> {
            if (music == 1 || music == 2)
                click.play();
        });

        //initialize stage
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Options");
    }

    /*
        Rules
     */

    private void Rules(Stage primaryStage) {
        //declares variables
        Group root = new Group();
        Canvas canvas = new Canvas(width, height);
        root.getChildren().add(canvas);
        Font.loadFont(Main.class.getResource("resources/Minecraftia-Regular.ttf").toExternalForm(), 24);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        //outputs rules of the game
        Text rules = new Text("IN SNAKE, THE GOAL OF THE GAME IS TO \n" +
                "GROW TO AS LARGE A SIZE AS POSSIBLE.\n" +
                "EAT FRUITS TO GROW ONE UNIT IN SIZE. \n" +
                "BUT, IF YOU EAT YOURSELF OR HIT A WALL \n" +
                "YOU DIE. USE THE ARROW KEYS OR 'WASD' TO \n" +
                "MOVE AND 'ESCAPE' TO PAUSE.");
        rules.setFont(Font.font("Minecraftia", 32));
        rules.setX(35);
        rules.setY(70);
        rules.setTextAlignment(TextAlignment.CENTER);
        rules.setFill(Color.WHITE);
        root.getChildren().add(rules);
        gc.fillRect(0, 0, width, height);
        Scene scene = new Scene(root, width, height);

        //generate back button
        Button backButton = new Button(width / 2 - 89, 400, 178, 62, backH, back, root);
        backButton.button.setOnMouseClicked(event -> start(primaryStage));

        //generates quit button
        Button quitButton = new Button(width - 85, height - 34, 82, 31, quitH, quit, root);
        quitButton.button.setOnMouseClicked(event -> Platform.exit());

        //add buttons to scene

        //accepts user keyboard input
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (music == 1 || music == 2)
                    click.play();
                start(primaryStage);
            }
        });

        scene.setOnMouseClicked(event -> {
            if (music == 1 || music == 2)
                click.play();
        });

        //initializes stage
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rules");
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /*
        game
     */
    
    private void Game(Stage primaryStage){
            //initializes variables
            Direction = 1;
            X = 0;
            Y = 0;
            timeline = new Timeline();
            Score = 1;
            gameOverCondition = false;

            //loads custom font
            Font.loadFont(Main.class.getResource("resources/Minecraftia-Regular.ttf").toExternalForm(), 24);
            //declares variables
            Group root = new Group();
            snake = root.getChildren();
            Canvas canvas = new Canvas(width, height);
            root.getChildren().add(canvas);
            Scene scene = new Scene(root, width, height);

            //Event handler that is responsible for interpreting user keyboard input
            EventHandler<KeyEvent> DirectionShift = new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if (!hasMoved) /*if user hasn't already moved in the current tick*/ {
                        if ((event.getCode() == KeyCode.A || event.getCode() == KeyCode.LEFT) && Direction != 1 && !pausedCondition) {
                            //changes direction to left
                            Direction = 3;
                            hasMoved = true;
                        } else if ((event.getCode() == KeyCode.D || event.getCode() == KeyCode.RIGHT) && Direction != 3 && !pausedCondition) {
                            //changes direction to right
                            Direction = 1;
                            hasMoved = true;
                        } else if ((event.getCode() == KeyCode.W || event.getCode() == KeyCode.UP) && Direction != 2 && !pausedCondition) {
                            //changes direction to up
                            Direction = 4;
                            hasMoved = true;
                        } else if ((event.getCode() == KeyCode.S || event.getCode() == KeyCode.DOWN) && Direction != 4 && !pausedCondition) {
                            //changes direction to down
                            Direction = 2;
                            hasMoved = true;
                        } else if (event.getCode() == KeyCode.ESCAPE && !gameOverCondition) {
                            if (pausedCondition) {
                                //unpauses game
                                if (music == 1 || music == 2)
                                    click.play();
                                unPaused();
                            } else {
                                //pauses game
                                if (music == 1 || music == 2)
                                    click.play();
                                Paused(root, primaryStage);
                            }
                        }
                    }
                }
            };

            //initializes the snake as 1 unit long
            GrowSnake(25, 25);
            //draws game background
            DrawBackDrop(canvas.getGraphicsContext2D());
            //randomly generates candy position and draws it on screen
            PlaceCandy(canvas.getGraphicsContext2D());
            //each cycle of the keyframe is one game tick
            KeyFrame keyframe = new KeyFrame(Duration.seconds(dblDifficulty) /* difficulty represented by double*/, (event -> {
                if (!pausedCondition) /*if game is not paused*/ {
                    hasMoved = false; //ensures user only changes direction once per tick
                    scene.setOnKeyPressed(DirectionShift); //calls direction change event handler when user presses a key

                    //checks if snake is larger than 1 unit
                    bigEnough = snake.size() - 1 > 1;

                    //retrieves snake segment for movement
                    Node tail = bigEnough ? snake.remove(snake.size() - 1) : snake.get(1);

                    //determines current direction of movement and shifts accordingly
                    switch (Direction) {
                        case 1:
                            X = (int) (snake.get(1).getLayoutX() + 25);
                            Y = (int) (snake.get(1).getLayoutY());
                            break;
                        case 2:
                            X = (int) (snake.get(1).getLayoutX());
                            Y = (int) (snake.get(1).getLayoutY() + 25);
                            break;
                        case 3:
                            X = (int) (snake.get(1).getLayoutX() - 25);
                            Y = (int) (snake.get(1).getLayoutY());
                            break;
                        case 4:
                            X = (int) (snake.get(1).getLayoutX());
                            Y = (int) (snake.get(1).getLayoutY() - 25);
                            break;
                    }

                    //wall collision
                    if (X < 0 || X >= width || Y < 0 || Y >= height)
                    {
                        if (snake.size() - 1 > 0)
                            GrowSnake((int) tail.getLayoutX(), (int) tail.getLayoutY());
                        //calls game over method
                        endGame(canvas.getGraphicsContext2D(), 1 , scene, root, primaryStage);
                    }

                    //snake collision
                    for (int i = 2; i < snake.size(); i++) {
                        if (X == snake.get(i).getLayoutX() && Y == snake.get(i).getLayoutY()) {
                            GrowSnake((int) tail.getLayoutX(), (int) tail.getLayoutY());
                            //calls game over method
                            endGame(canvas.getGraphicsContext2D(), i, scene, root, primaryStage);
                            break;
                        }
                    }

                    //calls method to move snake forward
                    moveSnake(tail, bigEnough);

                    //if Snake eats candy
                    if (tail.getLayoutX() == CandyX * 25 && tail.getLayoutY() == CandyY * 25) {
                        //calls method to grow snake
                        GrowSnake((int) tail.getLayoutX(), (int) tail.getLayoutY());
                        //increases user score
                        Score += 1;
                        //randomly determines which fruit to display
                        fruitPhoto = (int) (Math.random() * 6 + 1);
                        //if music is enabled, play achievement sound
                        if (music == 1 || music == 2)
                            ring.play();
                        //calls method to draw background
                        DrawBackDrop(canvas.getGraphicsContext2D());
                        //calls method to draw fruit
                        PlaceCandy(canvas.getGraphicsContext2D());
                    }
                }
            }));

            //adds frame to main timeline animation and starts animation
            timeline.getKeyFrames().add(keyframe);
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();

            scene.setOnMouseClicked(event -> {
                if (music == 1 || music == 2)
                    click.play();
            });

            //initialize stage
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Snake");
            primaryStage.sizeToScene();
            primaryStage.show();
        }

        //Draws background of the game, including the score keeper
    private void DrawBackDrop(GraphicsContext gc) {
        //draws black background
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, width, height);
        //draws user score in top right corner
        gc.setFont(Font.font("Minecraftia", 16));
        gc.setFill(Color.WHITE);
        gc.setTextAlign(TextAlignment.RIGHT);
        if (!gameOverCondition)
            gc.fillText(Integer.toString(Score), 845, 30);
    }

    //increases size of the snake by one unit
    private void GrowSnake(int tailX, int tailY) {
        Rectangle rect = new Rectangle(25, 25, new ImagePattern(box));
        rect.setLayoutX(tailX);
        rect.setLayoutY(tailY);
        snake.add(rect);
    }

    //moves the snake forward one unit
    private void moveSnake(Node tail, boolean bigEnough) {
        if (!pausedCondition) {
            tail.setLayoutY(Y);
            tail.setLayoutX(X);
            if (bigEnough) {
                snake.add(1, tail);
            }
        }
    }

    //pauses the game
    private void Paused(Group root, Stage primaryStage) {
        //generates temporary quit button
        Button quitButton = new Button (width - 85, height - 34, 82, 31, quitH, quit, root);
        quitButton.button.setOnMouseClicked(event -> Platform.exit());

        //generates temporary menu button
        Button menuButton = new Button(3, height - 34, 89, 31, mainMenuH, mainMenu, root);
        menuButton.button.setOnMouseClicked(event -> {
            start(primaryStage);
            timeline.stop();
            pausedCondition = false;
        });

        //generates temporary pause button
        Button pauseButton = new Button(width / 2 - 45, height - 34, 89, 31, playHover, play, root);
        pauseButton.button.setOnMouseClicked(event -> unPaused());

        pausedCondition = true; //sets  paused boolean to true
        mediaPlayer.pause(); //pauses music
        System.out.println("Paused");
    }

    //unpauses the game
    private void unPaused() {
        //removes three buttons from the scene
        snake.remove(snake.size() - 1);
        snake.remove(snake.size() - 1);
        snake.remove(snake.size() - 1);
        pausedCondition = false;
        if (music == 1 || music == 2)
            //plays music
            mediaPlayer.play();
        System.out.println("unPaused");
    }

    //randomly generates and draws candy
    private void PlaceCandy(GraphicsContext gc) {
        //covers up previous candy
        gc.setFill(Color.BLACK);
        gc.fillRect(CandyX * 25, CandyY * 25, 25, 25);
        //randomly generates position for new candy
        CandyX = (int) (Math.random() * 34);
        CandyY = (int) (Math.random() * 22);
        //if candy isn't spawned on a snake segment, displays the candy
        for (int i = 2; i < snake.size(); i++) {
            if (CandyX * 25 == snake.get(i).getLayoutX() && CandyY * 25 == snake.get(i).getLayoutY()) {
                PlaceCandy(gc);
            }
        }
        if (fruitPhoto == 1)
            gc.setFill(new ImagePattern(Orange));
        if (fruitPhoto == 2)
            gc.setFill(new ImagePattern(Cherry));
        if (fruitPhoto == 3)
            gc.setFill(new ImagePattern(Banana));
        if (fruitPhoto == 4)
            gc.setFill(new ImagePattern(Apple));
        if (fruitPhoto == 5)
            gc.setFill(new ImagePattern(Turnip));
        if (fruitPhoto == 6)
            gc.setFill(new ImagePattern(Pear));
        gc.fillRect(CandyX * 25, CandyY * 25, 25, 25);
    }

    //called if game is lost
    private void endGame(GraphicsContext gc, int i, Scene scene, Group root, Stage primaryStage) {
        //stops animation
        timeline.stop();
        gameOverCondition = true;
        DrawBackDrop(gc);
        //generates red rectangle and places it where the user died
        Rectangle deadHead = new Rectangle(25, 25);
        deadHead.setFill(new ImagePattern(deadBox));

        deadHead.setLayoutX(snake.get(i).getLayoutX());
        deadHead.setLayoutY(snake.get(i).getLayoutY());

        snake.remove(i);
        snake.add(deadHead);
        System.out.println("you lose");
        //plays game over sound
        if (music == 1 || music == 2)
            death.play();

        //Displays score obtained by the user
        DropShadow ds = new DropShadow();
        ds.setRadius(7);
        ds.setOffsetX(3);
        ds.setOffsetY(3);
        ds.setSpread(0.4);
        ds.setBlurType(BlurType.ONE_PASS_BOX);
        ds.setColor(Color.BLACK);
        Label score = new Label("SCORE: " + Integer.toString(Score));
        score.setFont(Font.font("Minecraftia", 60));
        score.setTextFill(Color.WHITE);
        FontLoader fontLoader = Toolkit.getToolkit().getFontLoader();
        score.setLayoutX(850/2 - fontLoader.computeStringWidth(score.getText(), score.getFont())/2);
        score.setTranslateY(220);
        score.setEffect(ds);

        //displays 'game over'
        Rectangle GameOver = new Rectangle(width / 2 - 240.5, 100, 481, 78);
        GameOver.setFill(new ImagePattern(gameOver));

        //add game over elements to scene
        root.getChildren().addAll(score, GameOver);

        //generates play again button
        Button playAgainButton = new Button(width / 2 - 212, 350, 414, 62, playAgainH, playAgain, root);
        playAgainButton.button.setOnMouseClicked(event -> Game(primaryStage));

        //generates quit button
        Button quitButton = new Button(width - 85, height - 34, 82, 31, quitH, quit, root);
        quitButton.button.setOnMouseClicked(event -> Platform.exit());

        //generates temporary menu button
        Button menuButton = new Button(3, height - 34, 89, 31, mainMenuH, mainMenu, root);
        menuButton.button.setOnMouseClicked(event -> start(primaryStage));

        scene.setOnMouseClicked(event -> {
            if (music == 1 || music == 2)
                click.play();
        });

        //returns to main menu if user hits enter
        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                if (music == 1 || music == 2)
                    click.play();
                Game(primaryStage);
            }
        });
    }
}
