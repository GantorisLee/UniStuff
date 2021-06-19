/*
 * Student name: XXX
 * Student ID: YYY
 * LMS username: ZZZ
 */

import java.util.Random;
import java.util.Scanner;

public class RandomPickCompetition extends Competition {
    private final int FIRST_PRIZE = 50000;
    private final int SECOND_PRIZE = 5000;
    private final int THIRD_PRIZE = 1000;
    private final int[] prizes = {FIRST_PRIZE, SECOND_PRIZE, THIRD_PRIZE};
	
    private final int MAX_WINNING_ENTRIES = 3;

    public RandomPickCompetition(Scanner keyboard, boolean testingMode) {
        super(keyboard, testingMode);
    }

    @Override
    public void addEntries() {

    }

    @Override
    public void drawWinners() {

    }
}
