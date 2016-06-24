package com.company;

import java.io.*;
import java.security.SecureRandom;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static java.io.FileDescriptor.out;

public class Main {

    static List<String> gameHistory = new ArrayList<>();
    static List<String> playerChoice = new ArrayList<>();
    static List<String> compChoice = new ArrayList<>();
    static List<String> savedResults = new ArrayList<>();


    public static void main(String[] args) {
        File history = new File("history.txt");
        if (history.exists() && !history.isDirectory()) {
            importHistory();
        }
        rpsLogo();
        mainMenu();
        saveHistory();
    }
    static void rpsLogo() {
        System.out.println("=================================================================");
        System.out.println(ANSI_BLUE + ".---.  .--.  .--. .-..-. .--.   .---.  .--. .---.  .--. .---." + ANSI_RESET);
        System.out.println(ANSI_BLUE + ": .; :: ,. :: .--': :' ;: .--'  : .; :: .; :: .; :: .--': .; :" + ANSI_RESET);
        System.out.println(ANSI_BLUE + ":   .': :: :: :   :   ' `. `.   :  _.':    ::  _.': `;  :   .'" + ANSI_RESET);
        System.out.println(ANSI_BLUE + ": :.`.: :; :: :__ : :.`. _`, :  : :   : :: :: :   : :__ : :.`." + ANSI_RESET);
        System.out.println(ANSI_BLUE + ":_;:_;`.__.'`.__.':_;:_;`.__.'  :_;   :_;:_;:_;   `.__.':_;:_;" + ANSI_RESET);
        System.out.println(ANSI_RED + "        .--.  .--. .-. .--.  .--.  .--. .---.  .--. " + ANSI_RESET);
        System.out.println(ANSI_RED + "       : .--': .--': :: .--': .--': ,. :: .; :: .--'" + ANSI_RESET);
        System.out.println(ANSI_RED + "       `. `. : :   : :`. `. `. `. : :: ::   .'`. `. " + ANSI_RESET);
        System.out.println(ANSI_RED + "        _`, :: :__ : : _`, : _`, :: :; :: :.`. _`, :" + ANSI_RESET);
        System.out.println(ANSI_RED + "       `.__.'`.__.':_;`.__.'`.__.'`.__.':_;:_;`.__.'" + ANSI_RESET);
        System.out.println("=================================================================");
        delay(1000);
        System.out.println(ANSI_BLUE + "              B Y   J A M E S R O N D I N A" + ANSI_RESET);
        delay(1000);
    }

    static void mainMenu() {
        System.out.println("================================================================");
        System.out.println(ANSI_RED + "                    M A I N  M E N U" + ANSI_RESET);
        System.out.println("================================================================");
        System.out.println(ANSI_BLUE + "                     O P T I O N S" + ANSI_RESET);
        System.out.println("================================================================");
        System.out.println("1. Type 'play' to play");
        System.out.println("2. Type 'history' to view game history");
        System.out.println("3. Type 'quit' to quit playing");
        Scanner mainMenu = new Scanner(System.in);
        String choice = (mainMenu.nextLine());
        choice = choice.toLowerCase();
        switch (choice) {
            case "play": playGame();
                break;
            case "history": history();
                break;
            case "quit":
                System.out.println("=================================================================");
                System.out.println(ANSI_GREEN + "  GGGG   OOOOO   OOOOO  DDDDD      GGGG    AAA   MM    MM EEEEEEE" + ANSI_RESET);
                System.out.println(ANSI_GREEN + " GG  GG OO   OO OO   OO DD  DD    GG  GG  AAAAA  MMM  MMM EE     " + ANSI_RESET);
                System.out.println(ANSI_GREEN + "GG      OO   OO OO   OO DD   DD  GG      AA   AA MM MM MM EE     " + ANSI_RESET);
                System.out.println(ANSI_GREEN + "GG      OO   OO OO   OO DD   DD  GG      AA   AA MM MM MM EEEEE  " + ANSI_RESET);
                System.out.println(ANSI_GREEN + "GG   GG OO   OO OO   OO DD   DD  GG   GG AAAAAAA MM    MM EE     " + ANSI_RESET);
                System.out.println(ANSI_GREEN + "GG   GG OO   OO OO   OO DD   DD  GG   GG AAAAAAA MM    MM EE     " + ANSI_RESET);
                System.out.println(ANSI_GREEN + " GGGGGG  OOOO0   OOOO0  DDDDDD    GGGGGG AA   AA MM    MM EEEEEEE" + ANSI_RESET);
                System.out.println("=================================================================");
                System.out.println();
                System.out.println(ANSI_BLUE + "Thanks for playing!" + ANSI_RESET);
                break;
            default: System.out.println("Please select a valid option!");
                delay(1500);
                mainMenu();
        }
    }
    static void playGame() {
        Scanner play = new Scanner(System.in);
        System.out.println("======================================================");
        System.out.println("Type 'rock', 'paper', or 'scissors' to make your choice");
        System.out.println("Type 'quit' to go back to the main menu");
        String choice = (play.nextLine());
        choice = choice.toLowerCase();
        if (choice.equals("rock")) {
            playerChoice.add("Rock");
            rock(oppAI());
        }
        else if (choice.equals("paper")) {
            playerChoice.add("Paper");
            paper(oppAI());

        }
        else if (choice.equals("scissors")) {
            playerChoice.add("Scissors");
            scissors(oppAI());

        }
        else if (choice.equals("quit")) {
            mainMenu();
        }
        else {
            System.out.println("Please type a valid option!");
            delay(1500);
            mainMenu();
        }

    }

    static void history() {
        System.out.println("========================================");
        System.out.println(ANSI_RED + "        G A M E  H I S T O R Y" + ANSI_RESET);
        System.out.println("========================================");

        if ((gameHistory.size() == 0) && (savedResults.size() == 0)) {
            System.out.println("No Games Played!");
        }
        else if (gameHistory.size() == 0) {
            for (int i = 0; i < savedResults.size(); i++) {
                System.out.println(savedResults.get(i));

            }
        }
        else {
            for (int i = 0; i < gameHistory.size(); i++) {
                savedResults.add(gameHistory.get(i) + ": Player: " + playerChoice.get(i) + " vs. AI Choice: " + compChoice.get(i));
            }
            for (int i = 0; i < savedResults.size(); i++) {
                System.out.println(savedResults.get(i));

            }
        }
        mainMenu();
    }

    static void rock(int aiChoice) {
        if (aiChoice == 0) {
            tie();
        }
        else if (aiChoice == 1) {
            lose();
        }
        else {
            win();
        }

    }

    static void paper(int aiChoice) {
        if (aiChoice == 0) {
            win();
        }
        else if (aiChoice == 1) {
            tie();
        }
        else {
            lose();
        }
    }

    static void scissors(int aiChoice) {
        if (aiChoice == 0) {
            lose();
        }
        else if (aiChoice == 1) {
            win();
        }
        else {
            tie();
        }
    }

    static int oppAI(){
        Random randomGenerator = new SecureRandom();
        int aiChoice = randomGenerator.nextInt(3);
        if (aiChoice == 0) {
            compChoice.add("Rock");
            System.out.println("AI chose rock");
        }
        else if (aiChoice == 1) {
            compChoice.add("Paper");
            System.out.println("AI chose paper.");
        }
        else if (aiChoice == 2) {
            compChoice.add("Scissors");
            System.out.println("AI chose scissors.");
        }
        else {
            System.out.println("AI choice error" + aiChoice);
        }
        return aiChoice;


    }

    static void win() {
        System.out.println("You Win!");
        gameHistory.add("Win");
        playGame();

    }

    static void lose() {
        System.out.println("You lose!");
        gameHistory.add("Loss");
        playGame();

    }

    static void tie() {
        System.out.println("You tied.");
        gameHistory.add("Tie");
        playGame();

    }

    static void importHistory() {

        try (BufferedReader read = new BufferedReader(new FileReader("history.txt"))) {
            String line = null;
            while ((line = read.readLine()) != null) {
                    savedResults.add(line);

            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    static void saveHistory() {
        try {
            FileWriter history = new FileWriter("history.txt", true);
            for (int i = 0; i < gameHistory.size(); i++) {
                history.write(savedResults.get(i) + "\n");
            }

            history.close();
        }
        catch (IOException e) {
            e.fillInStackTrace();
        }

    }
    static void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";


}