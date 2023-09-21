package nz.ac.auckland.se206;

import java.io.IOException;
import nz.ac.auckland.se206.controllers.rooms.notes.ClassicalNoteController;
import nz.ac.auckland.se206.controllers.rooms.notes.RockNoteController;
import nz.ac.auckland.se206.controllers.rooms.rave.BodybuilderController;

/** Represents the state of the game. */
public class GameState {
  // Enum to represent game difficulty levels
  public enum Difficulty {
    EASY,
    MEDIUM,
    HARD
  }

  // Enum to represent game playtime options
  public enum PlayTime {
    TWO(2),
    FOUR(4),
    SIX(6);

    private final Integer time;

    PlayTime(Integer time) {
      this.time = time;
    }

    public Integer getTime() {
      return this.time;
    }
  }

  // Variables to keep track of various game states
  private static GameState instance; // Singleton instance of GameState
  public static Difficulty difficulty; // Current game difficulty
  public static PlayTime time; // Current game playtime
  public static boolean isRiddleResolved = false;
  public static boolean isRiddleObjectFound = false;
  public static boolean isNoteSequenceFound = false;
  public static boolean isKeyFound = false;
  public static boolean isMusicQuizCompleted = false;
  public static boolean isSafeOpened = false;
  public static boolean isPianoPlayed = false;
  public static boolean isHarpPlayed = false;
  public static boolean isEscaped = false;
  public static boolean isTrumpetPlayed = false;

  // Singleton pattern: Get an instance of the GameState
  public static GameState getInstance() {
    if (instance == null) {
      instance = new GameState();
      // Initialize various game managers and components
      instance.timeManager = new TimeManager();
      instance.taskManager = new TaskManager();
      instance.chatManager = new ChatManager();
      instance.rockBigTaskManager = new RockBigTaskManager();
      instance.ravePuzzle = new RavePuzzle();
      instance.objectiveListManager = new ObjectiveListManager();
      instance.hintManager = new HintManager();
    }
    return instance;
  }

  // Setter for the GameState instance
  public static void setInstance(GameState instance) {
    GameState.instance = instance;
  }

  // Reset various game variables
  public static void resetVariables() {
    isRiddleResolved = false;
    isRiddleObjectFound = false;
    isNoteSequenceFound = false;
    isKeyFound = false;
    isMusicQuizCompleted = false;
    isSafeOpened = false;
    isPianoPlayed = false;
    isHarpPlayed = false;
    isEscaped = false;
  }

  // Managers and controllers to handle game components
  public TimeManager timeManager;
  public HintManager hintManager;
  public TaskManager taskManager;
  public ChatManager chatManager;
  public RockBigTaskManager rockBigTaskManager;
  public RavePuzzle ravePuzzle;
  public ObjectiveListManager objectiveListManager;
  public BodybuilderController bodybuilderController;
  public ClassicalNoteController classicalNote;
  public RockNoteController rockNote;

  // Method to start the game and initialize various components
  public void startGame() throws IOException {
    this.taskManager.generateTasks(); // Generate game tasks
    this.timeManager.setTime(time.getTime() * 60); // Set game time
    this.timeManager.startCountdown(); // Start countdown timer
    this.hintManager.initialiseManager(difficulty); // Initialize hint manager based on difficulty
    this.ravePuzzle.setHints(classicalNote, rockNote); // Set hints for the Rave puzzle
    this.bodybuilderController.initialiseCode(); // Initialize code for the Bodybuilder puzzle
  }
}
