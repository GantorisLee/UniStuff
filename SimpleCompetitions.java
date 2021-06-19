/*
 * Student name: Lee Guo Yi
 * Student ID: YYY
 * LMS username: ZZZ
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class SimpleCompetitions {
    Competition currentCompetition;
    private int id = 0;
    boolean competitionType;
    String competitionTypeString;
    ArrayList<String> competitionName = new ArrayList<>(1);
    ArrayList<Integer> competitionId = new ArrayList<>(1);
    ArrayList<String> finalReport = new ArrayList<>(1);
    ArrayList<ArrayList<String>> memberIdListMain = new ArrayList<>();
    ArrayList<String> billIdList2 = new ArrayList<>(1);

    public Competition addNewCompetition(Scanner keyboard, boolean testingMode) {
        String name;

        competitionTypeLoop:
        while (true) {
            System.out.println("Type of competition (L: LuckyNumbers, R:Random Pick)?:");
            String competitionOption = keyboard.next();
            keyboard.nextLine();
            competitionOption = competitionOption.toUpperCase();

            switch (competitionOption) {
                case "L":
                    competitionType = true;
                    competitionTypeString = "LuckyNumbersCompetition";
                    break competitionTypeLoop;
                case "R":
                    competitionType = false;
                    competitionTypeString = "RandomPickCompetition";
                    break competitionTypeLoop;
                default:
                    System.out.println("Unsupported option. Please try again!");
                    break;
            }
        }

        System.out.println("Competition name: ");
        keyboard.nextLine();
        name = keyboard.nextLine();
        id++;
        System.out.println("A new competition has been created!");
        System.out.println("Competition ID: " + id + ", Competition Name: " + name + ",Type: " +
                competitionTypeString);
        competitionId.add(id);
        competitionName.add(name);
        if (competitionType = true) {
            currentCompetition = new LuckyNumbersCompetition(keyboard, testingMode);
        } else if (competitionType = false) {
            currentCompetition = new RandomPickCompetition(keyboard, testingMode);
        }
        currentCompetition.addCompetitionEntry(name, id);
        return null;
    }

    public void checkMemberId(){

    }

    /*get a summary report, option 4*/
    public void report(boolean openCompetition) {
        int currentOpenComp = 0;
        if (openCompetition) {
            currentOpenComp = 1;
        }
        System.out.println("----SUMMARY REPORT----" + "\n"
                + "+Number of completed competitions: " + (id-currentOpenComp) + "\n"
                + "+Number of active competitions: " + currentOpenComp);

        if (finalReport.size() != 0) {
            String joined = String.join("\n", finalReport);
            System.out.println(joined);
        } else{
            System.out.println("/n");
        }

        if(openCompetition) {
            System.out.println("Competition ID : " + id + ", "
                    + "name: " + competitionName.get(competitionName.size()-1) +
                    ", active: yes" + "\n" +"Number of entries: " + (currentCompetition.entryId-1));
        }

    }



    public static void main(String[] args) throws IOException {

        boolean openCompetition = false;
        boolean testingMode = true;
        boolean loadFileMode = true;
        String memberFile;
        String billFile;
        Scanner keyboard = new Scanner(System.in);
        SimpleCompetitions sc = new SimpleCompetitions();




        System.out.println("----WELCOME TO SIMPLE COMPETITIONS APP----");

        loadFileLoop:
        while (true) {
            System.out.println("Load competitions from file? (Y/N)?");
            String option = keyboard.next();
            keyboard.nextLine();
            option = option.toUpperCase();

            switch (option) {
                case "Y":
                    loadFileMode = true;
                    break loadFileLoop;
                case "N":
                    loadFileMode = false;
                    break loadFileLoop;
                default:
                    System.out.println("Unsupported option. Please try again!");
                    break;
            }
        }

        //load file or not, testing or normal mode
        if (loadFileMode) {
            System.out.println("File name:");
            String fileName = keyboard.nextLine();
        } else {
            testingLoop:
            while (true) {
                System.out.println("Which mode would you like to run? (Type T for Testing, and N for Normal mode):");
                String option = keyboard.next();
                keyboard.nextLine();
                option = option.toUpperCase();

                switch (option) {
                    case "T":
//                    System.out.println("Testing mode");
                        testingMode = true;
                        break testingLoop;
                    case "N":
//                    System.out.println("Normal mode");
                        testingMode = false;
                        break testingLoop;
                    default:
                        System.out.println("Invalid mode! Please choose again.");
                        break;
                }
            }
        }

        //DataProvider.java
        boolean done = false;
        while (!done) {
            System.out.println("Member file:");
            memberFile = keyboard.nextLine();
            System.out.println("Bill file:");
            billFile = keyboard.nextLine();

            try {
                DataProvider dataGrab = new DataProvider(memberFile, billFile);
                ArrayList<ArrayList<String>> memberIdListMain =
                        (ArrayList<ArrayList<String>>)dataGrab.memberIdList.clone();

                System.out.println("prints MAIN LIST");
                for(ArrayList obj: memberIdListMain){
                    ArrayList<String> temp = obj;
                    for(String num : temp){
                        System.out.print(num + " ");
                    }
                    System.out.println();
                }
                done = true;
            } catch (DataAccessException e) {
                System.out.println(e.getMessage());
            } catch (DataFormatException e) {
                System.out.println(e.getMessage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }


        mainLoop:
        while (true) {
            System.out.println("Please select an option. Type 5 to exit.");
            System.out.println("1. Create a new competition");
            System.out.println("2. Add new entries");
            System.out.println("3. Draw winners");
            System.out.println("4. Get a summary report");
            System.out.println("5. Exit");
            int option = keyboard.nextInt();

            switch (option) {
                case 1:
                    if(openCompetition){
                        System.out.println("There is an active competition. SimpleCompetitions " +
                                "does not support " + "concurrent competitions!");
                    } else {
                        sc.addNewCompetition(keyboard, testingMode);
                        openCompetition = true;
                    }
                    break;
                case 2:
//                    System.out.println("adds new entries");
                    if(!openCompetition){
                        System.out.println("There is no active competition. Please create one!");
                    } else {
                        //sc.checkMemberId
                        sc.currentCompetition.addEntries();
                    }

                    break;
                case 3:
                    if(!openCompetition){
                        System.out.println("There is no active competition. Please create one!");
                    } else {
                        sc.currentCompetition.drawWinners();
                        sc.finalReport.add(sc.currentCompetition.report());
                    }
                    openCompetition = false;
                    break;
                case 4:
                    if(sc.id == 0){
                        System.out.println("No competition has been created yet!");
                    } else {
                        sc.report(openCompetition);
                    }
                    break;
                case 5:
                    break mainLoop;
                default:
                    System.out.println("Unsupported option. Please try again!");
                    break;
            }
        }

        System.out.println("Goodbye!");
        keyboard.close();


    }
}
