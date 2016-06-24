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
        importHistory();
        rpsLogo();
        mainMenu();
        saveHistory();
    }
    static void rpsLogo() {
        System.out.println("=================================================================");
        System.out.println(".---.  .--.  .--. .-..-. .--.   .---.  .--. .---.  .--. .---.");
        System.out.println(": .; :: ,. :: .--': :' ;: .--'  : .; :: .; :: .; :: .--': .; :");
        System.out.println(":   .': :: :: :   :   ' `. `.   :  _.':    ::  _.': `;  :   .'");
        System.out.println(": :.`.: :; :: :__ : :.`. _`, :  : :   : :: :: :   : :__ : :.`.");
        System.out.println(":_;:_;`.__.'`.__.':_;:_;`.__.'  :_;   :_;:_;:_;   `.__.':_;:_;");
        System.out.println("        .--.  .--. .-. .--.  .--.  .--. .---.  .--. ");
        System.out.println("       : .--': .--': :: .--': .--': ,. :: .; :: .--'");
        System.out.println("       `. `. : :   : :`. `. `. `. : :: ::   .'`. `. ");
        System.out.println("        _`, :: :__ : : _`, : _`, :: :; :: :.`. _`, :");
        System.out.println("       `.__.'`.__.':_;`.__.'`.__.'`.__.':_;:_;`.__.'");
        System.out.println("=================================================================");
        try {
            Thread.sleep(3000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    static void mainMenu() {
        System.out.println("================================================================");
        System.out.println("                    M A I N  M E N U");
        System.out.println("================================================================");
        System.out.println("                     O P T I O N S");
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
                System.out.println("  GGGG   OOOOO   OOOOO  DDDDD      GGGG    AAA   MM    MM EEEEEEE");
                System.out.println(" GG  GG OO   OO OO   OO DD  DD    GG  GG  AAAAA  MMM  MMM EE     ");
                System.out.println("GG      OO   OO OO   OO DD   DD  GG      AA   AA MM MM MM EE     ");
                System.out.println("GG      OO   OO OO   OO DD   DD  GG      AA   AA MM MM MM EEEEE  ");
                System.out.println("GG   GG OO   OO OO   OO DD   DD  GG   GG AAAAAAA MM    MM EE     ");
                System.out.println("GG   GG OO   OO OO   OO DD   DD  GG   GG AAAAAAA MM    MM EE     ");
                System.out.println(" GGGGGG  OOOO0   OOOO0  DDDDDD    GGGGGG AA   AA MM    MM EEEEEEE");
                System.out.println("=================================================================");
                System.out.println();
                System.out.println("Thanks for playing!");
                break;
            default: System.out.println("Please select a valid option!");
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
            mainMenu();
        }

    }

    static void history() {
        System.out.println("========================================");
        System.out.println("        G A M E  H I S T O R Y");
        System.out.println("========================================");
        if (savedResults.size() == 0) {
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
        int aiChoice = randomGenerator.nextInt(2);
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

    /*static void syncHistory() {
        for (int i = gameHistory.size(); gameHistory.size() < savedResults.size(); i++) {
                savedResults.add(gameHistory.get(i) + ": Player: " + playerChoice.get(i) + " vs. AI Choice: " + compChoice.get(i));
        }
    }*/

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


}
