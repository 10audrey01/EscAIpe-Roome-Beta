package nz.ac.auckland.se206.puzzles;

import java.util.Random;
import javafx.application.Platform;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.controllers.rooms.notes.ClassicalNoteController;
import nz.ac.auckland.se206.controllers.rooms.notes.RockNoteController;

public class RavePuzzle {
  // rave puzzle - Find the safe, unlock the code through finding respective object in each of rock
  // and classical rooms
  private GameState gamestate;
  private boolean isSafeFound;
  private boolean isSafeSolved;
  private String safeSolution;

  public RavePuzzle() {
    this.gamestate = GameState.getInstance();
    this.isSafeFound = false;
    this.isSafeSolved = false;

    String solution = "";
    // generate random solution
    Random random = new Random();
    for (int i = 0; i < 6; i++) {
      int currentNumber = random.nextInt(10);
      solution += Integer.toString(currentNumber);
    }

    System.out.println(solution);

    this.safeSolution = solution;
  }

  public String getSolution() {
    return this.safeSolution;
  }

  // sets the isSafeFound to true
  public void setFoundSafe() {
    this.isSafeFound = true;
  }

  // attempts to solve the safe
  public boolean attemptSolveSafe(String input) {
    if (input.equals(safeSolution)) {
      this.isSafeSolved = true;
      this.gamestate.taskManager.completeLargeTask();
      return true;
    }
    return false;
  }

  public void setHints(ClassicalNoteController classical, RockNoteController rock) {
    System.out.println("Solution-" + safeSolution);
    String firstHalfOfSolution = this.safeSolution.substring(0, 3);
    String secondHalfOfSolution = this.safeSolution.substring(3, 6);

    Random random = new Random();
    int randomNum = random.nextInt(2);

    if (randomNum == 1) {
      Platform.runLater(
          () -> {
            classical.setText(secondHalfOfSolution);
            rock.setText(firstHalfOfSolution);
          });
    } else {
      Platform.runLater(
          () -> {
            classical.setText(firstHalfOfSolution);
            rock.setText(secondHalfOfSolution);
          });
    }
  }
}
