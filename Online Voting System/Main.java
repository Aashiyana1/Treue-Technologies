import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main{
    private Scanner sc=new Scanner( System.in);
    public static void main(String[] args) {
        new Main().start();
    }
    private void start()
    {
        System.out.println("\u001B[35m***** Welcome to VoteXpress *****\u001B[0m");
        System.out.println("1.Vote");
        System.out.println("2.Admin Login");
        System.out.println("3.Exit");
        System.out.print("Enter Your Choice : ");
        int ch=sc.nextInt();
        switch (ch) {
            case 1:{
                  vote();
                  break;
            }
            case 2: {
                admin();
                break;
            }
            case 3:{
                System.exit(0);
            }
            default:{
                System.out.println("\u001B[31mInvalid input. Please make a valid selection.\u001B[0m");
                start();
            }
               
        }

    }

    private void admin(){

        System.out.println("\u001B[33m\n\t***** Welcome to the Administration Panel *****\u001B[0m");
        System.out.print("Please provide your name: ");
        String name=sc.next();
        System.out.print("Please enter your password: ");
        String pass=sc.next();
        if(name.equals(Admin.GLOBAL_NAME) && pass.equals(Admin.GLOBAL_PASSWORD)){
            showAdminDashbord();
        }else{
            System.out.println("Invalid username or password. Please try again.");
            start();
        }

    }

    private void viewVoterList(){
        System.out.println("\u001B[32mList of Voters and Their Responses\u001B[0m");
        Map<String,String> map=Parties.voterList;
        List<String> keySet=new ArrayList<>(map.keySet());
        for(int i = 0 ;i<map.size();++i ){
            System.out.println("Name : "+map.get(keySet.get(i))+" Voter ID : "+keySet.get(i));
        }
        showAdminDashbord();
    }

    private void viewResults(){
        System.out.println("Election Results Summary: ");
        if(Parties.partyMap.isEmpty()){
            System.out.println("The election has not yet begun.");
            showAdminDashbord();
            return;
         }
         int Green_Party=Parties.partyMap.get(Parties.GREEN_PARTY);
         int Justice_party=Parties.partyMap.get(Parties.JUSTICE_PARTY);
         int new_party=Parties.partyMap.get(Parties.NEW_PARTY);
         int old_party=Parties.partyMap.get(Parties.OLD_PARTY);

         int result=max(Green_Party,Justice_party,new_party,old_party);

         System.out.println("\n\tGreen Party : "+Green_Party+"\n\t"+
         "Justice party : "+Justice_party+"\n\t"+"New Party : "+new_party+
         "\n\t"+"Old Party : "+old_party
         );

         if(result==Green_Party){
            System.out.println("\n\tThe winner party is Green Party with total votes :"+Green_Party);
         }
         if(result==Justice_party){
            System.out.println("\n\tThe winner party is Justice Party with total votes :" +Justice_party );
         }
         if(result==new_party){
            System.out.println("\n\tThe winner party is NEW Party with total votes:"+new_party);
         }
         if(result==old_party){
            System.out.println("\n\tThe winner party is OLD Party with total votes:" +old_party);
         }
         showAdminDashbord();
    }

    

    private int max(int... i){
        int prev=0;
        for(int a:i){
            if(a>prev){
                prev=a;
            }
        }
        return prev;
    }

    private void modifyBallots(){

        System.out.print("\n\n\t\tDo you want clear all data ? (y/n) : ");
        char ch=sc.next().charAt(0);
        if(ch=='y'){
            System.out.println("\n\n\t\t Current vote count : "+Parties.voterList.size());
            Parties.partyMap.clear();
            Parties.voterList.clear();
            System.out.println("\t\t All Data cleared !");
        }
            showAdminDashbord();
        

    }

    private void showAdminDashbord(){

        System.out.println("\n\n\t\t***** Welcome to admin portal *****");
        System.out.println("\n\t1.View Voters Data");
        System.out.println("\n\t2.View Results");
        System.out.println("\n\t3.Clear All Votes");
        System.out.println("\n\t4.Log Out");

        int ch=sc.nextInt();
        switch(ch){
            case 1:{
                viewVoterList();
                break;
            }
            case 2:{
                viewResults();
                break;
            }
            case 3:{
                modifyBallots();
                break;
            }
            case 4:{
                start();
                break;
            }
        }

    }

    private void vote(){
        System.out.println("\u001B[1;34m\n\n\t\t***** Welcome to Our VoteXpress *****\u001B[0m");
        System.out.print("\n\tPlease Enter Your Name : ");
        String name=sc.next();
        System.out.print("\n\tPlease Enter Your Voter Id Number : ");
        String id=sc.next();

        if(Parties.voterList.containsKey(id)){
            System.out.println("\n\tYou have already voted !");
            System.out.print("\n\tDo you want to enter details again ? (y/n) : ");
            char ch=sc.next().charAt(0);
            switch(ch){
                case 'y':vote();break;
                default : {
                    System.out.println("\n\t Thanks for using our System !");
                }
            }

        }else{
            System.out.println("\n\tPlease Choose Your Party : ");
            System.out.print("\n\t1. Green Party");
            System.out.print("\n\t2. Justice Party");
            System.out.print("\n\t3. New Party");
            System.out.print("\n\t4. Old Part");
            System.out.print("\n\tEnter your choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:{
                    
                    Map<Integer,Integer> map=Parties.partyMap;
                    int key=Parties.GREEN_PARTY;
                    Parties.partyMap.put(key, map.getOrDefault(key, 0) + 1);
                    Parties.voterList.put(id, name);
                    System.out.println("\u001B[38;5;208m\n\tDear " + name + ",\n You Voted Green Party ! Thanks for Voting\u001B[0m");
                    break;
                }
                case 2:{
                    Map<Integer,Integer> map=Parties.partyMap;
                    int key=Parties.JUSTICE_PARTY;
                    Parties.partyMap.put(key, map.getOrDefault(key, 0) + 1);
                    Parties.voterList.put(id, name);
                    System.out.println("\u001B[38;5;94m\n\tDear " + name + ",\n You Voted Justice Party ! Thanks for Voting\u001B[0m");
                    break;
                }
                case 3:{
                     Map<Integer,Integer> map=Parties.partyMap;
                    int key=Parties.NEW_PARTY;
                    Parties.partyMap.put(key, map.getOrDefault(key, 0) + 1);
                    Parties.voterList.put(id, name);
                    System.out.println("\u001B[38;5;205m\n\tDear " + name + ",\n You Voted New Party ! Thanks for Voting\u001B[0m");
                    break;
                }
                case 4:{
                     Map<Integer,Integer> map=Parties.partyMap;
                    int key=Parties.OLD_PARTY;
                    Parties.partyMap.put(key, map.getOrDefault(key, 0) + 1);
                    Parties.voterList.put(id, name);
                    System.out.println("\u001B[31m\n\tDear " + name + ",\n You Voted Old Party ! Thanks for Voting\u001B[0m");
                    break;
                }
                default:{
                    System.out.println("\n\tInvalid Choice !");
                }

            }
        }

        start();
    }
}