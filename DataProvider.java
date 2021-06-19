/*
 * Student name: XXX
 * Student ID: YYY
 * LMS username: ZZZ
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DataProvider {
    /**
     * 
     * @param memberFile A path to the member file (e.g., members.csv)
     * @param billFile A path to the bill file (e.g., bills.csv)
     * @throws DataAccessException If a file cannot be opened/read
     * @throws DataFormatException If the format of the the content is incorrect
     */
    Member currentMembers;
    boolean perfectData;
    ArrayList<ArrayList<String>> memberIdList = new ArrayList<>();
    ArrayList<ArrayList<String>> billIdList = new ArrayList<>();



     public DataProvider(String memberFile, String billFile)
             throws DataAccessException, DataFormatException, IOException {

         File memberFileRoot = new File(memberFile);
         File billFileRoot = new File(billFile);

         if (!memberFileRoot.exists() || !billFileRoot.exists()) {
             throw new DataAccessException();
         } else if (!memberFileRoot.exists() || !billFileRoot.exists()) {
             throw new DataFormatException();
         } else {
             //members.csv
             BufferedReader br = new BufferedReader(new FileReader(memberFileRoot));
             String strLine = "";
             String[] tempArr;
             int memberCount = 0;
             while((strLine = br.readLine()) != null) {
                 memberIdList.add(new ArrayList<>());
                 memberCount ++;
                 tempArr = strLine.split(",");

                 for (String tempStr : tempArr) {
                     memberIdList.get(memberCount-1).add(tempStr);
                 }
             }
             br.close();

//             Bill newBill = new Bill();
//             newBill.billListCreate(billFileRoot);

             //bills.csv
             BufferedReader brb = new BufferedReader(new FileReader(billFileRoot));
             strLine = "";
             int billCount = 0;
             while((strLine = br.readLine()) != null) {
                 billIdList.add(new ArrayList<>());
                 billCount ++;
                 tempArr = strLine.split(",");
                 for (String tempStr : tempArr) {
                     billIdList.get(billCount-1).add(tempStr);
                 }
             }
             br.close();
         }
         //PRINTING AN ARRAYLIST
//         System.out.println("prints memberlist");
//         for(ArrayList obj: memberIdList){
//             ArrayList<String> temp = obj;
//             for(String num : temp){
//                 System.out.print(num + " ");
//             }
//             System.out.println();
//         }

         System.out.println("prints billlist");
         for(ArrayList obj: billIdList){
             ArrayList<String> temp = obj;
             for(String num : temp){
                 System.out.print(num + " ");
             }
             System.out.println();
         }
     }

}
