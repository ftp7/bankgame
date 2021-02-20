package E2;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        ArrayList<String> storeUsernamePass = new ArrayList<>();
        ArrayList<Double> storeIDBalance = new ArrayList<>();
        ArrayList<Double> storeIDAnnualInterestRate = new ArrayList<>();
        ArrayList<String> storeIDDateCreated  = new ArrayList<>();
        ArrayList<Double> storeIdOnly = new ArrayList<>();
        ArrayList<String> storeIDGameStat = new ArrayList<>();

        //read data (create txt file with following names)
        readDataString("storeUsernamePass.txt",storeUsernamePass);
        readDataDouble("storeIDBalance.txt", storeIDBalance);
        readDataDouble("storeIDAnnualInterestRate.txt", storeIDAnnualInterestRate);
        readDataString("storeIDDateCreated.txt", storeIDDateCreated);
        readDataDouble("storeIdOnly.txt", storeIdOnly);
        readDataString("storeIDGameStat.txt",storeIDGameStat);

//       //maintenance
//       System.out.println(storeUsernamePass.toString() + " s: " + storeUsernamePass.size() + storeUsernamePass.get(0) + storeUsernamePass.get(1)); // x
//       System.out.println(storeIDBalance.toString() + "s: " + storeIDBalance.size()); //ok
//       System.out.println(storeIDAnnualInterestRate.toString() + "s: " + + storeIDAnnualInterestRate.size());
//       System.out.println(storeIDDateCreated.toString() +"s: " +storeIDDateCreated.size() );
//       System.out.println(storeIdOnly.toString()+ "s: " + storeIdOnly.size());


        char charAssign;
        String storePass;
        double storeID;
        double idBalance;
        double idAnnualInterestRate;
        double idDate;
        String doubleToString;
        Boolean flag;
        Account user;
        int tempInt;
        double tempDouble;

            while (true){
                System.out.println("\n\n💲Welcome to The Bank!💲");
                TimeUnit.SECONDS.sleep(1);
            System.out.println("\nWhat do you want to do?\n1. Login \n2. Register\nx. Exit");

            charAssign = scanner.nextLine().charAt(0);

                if(charAssign == '1'){
                    charAssign = '\u0000';
                    System.out.println("Enter Your ID(double): ");
                    storeID = scanner.nextDouble();
                    scanner.nextLine();
                    doubleToString = Double.toString(storeID);
                    if(storeUsernamePass.contains(doubleToString)){
                        System.out.println("Enter your password(string): ");
                        storePass = scanner.nextLine();
                        flag = storeUsernamePass.get(storeUsernamePass.indexOf(doubleToString) + 1).equals(storePass);
                        if(flag) {
                            idBalance = storeIDBalance.get(storeIDBalance.indexOf(new Double(storeID))+1);
                            idAnnualInterestRate = storeIDAnnualInterestRate.get(storeIDAnnualInterestRate.indexOf(new Double(storeID))+1);
                            user = new Account(storeID, storePass,idBalance,idAnnualInterestRate);
                            System.out.println("\nWelcome back " + user.getID() + "!\tLocal time and date: " + new Date().toString());

                            while (true) {
                                System.out.println("\nWhat do you want to do?\n1. Withdraw\n2. Deposit " +
                                        "\n3. Get ID \n4. Get Balance & " +
                                        "Get Annual Interest Rate" +
                                        "\n5. Get Date Account is Created\n6. Set new Annual Interest Rate" +
                                        "\n7. Change Password\n8. Get Account Info\n9. Log Out\nf. Play Typing Speed Game");
                                charAssign = scanner.nextLine().charAt(0);
                                if (charAssign == '1'){
                                    System.out.println("Enter amount that you want to withdraw(double):\n\t" +"Current balance " +
                                            "= " + user.getBalance());
                                    double withdrawAmount = scanner.nextDouble();
                                    scanner.nextLine();
                                    if(user.withdraw(withdrawAmount)) {
                                        tempInt = storeIDBalance.indexOf(storeID) + 1;
                                        storeIDBalance.remove(tempInt);
                                        storeIDBalance.remove(tempInt - 1);
                                        storeIDBalance.add(storeID);
                                        storeIDBalance.add(user.getBalance());
                                        System.out.println("\tSuccessfully Withdrawn...");
                                        System.out.println("\n\tCurrent Balance= " + user.getBalance());
                                    } else {
                                        System.out.println("\n\tYou have insufficient amount or you've entered a negative double...");
                                        System.out.println("\n\tCurrent Balance= " + user.getBalance());
                                    }
                                }
                                else if (charAssign == '2'){
                                    System.out.println("Enter amount(double) that you want to deposit(if you enter < 0, 0" +
                                            " will be the deposit):"+"\n\tCurrent Balance= " + user.getBalance() );
                                    tempDouble = scanner.nextDouble();
                                    scanner.nextLine();
                                    user.deposit(tempDouble);
                                    tempInt = storeIDBalance.indexOf(storeID) + 1;
                                    storeIDBalance.remove(tempInt);
                                    storeIDBalance.remove(tempInt - 1);
                                    storeIDBalance.add(storeID);
                                    storeIDBalance.add(user.getBalance());
                                    System.out.println("\n\tSuccessfully deposited... ");
                                    System.out.println("\n\tCurrent Balance= " + user.getBalance());
                                }
                                else if (charAssign == '3'){
                                    System.out.println("Account ID: \n\t" + user.getID() );
                                }
                                else if (charAssign == '4'){
                                    System.out.println("Your balance=\n\t" + user.getBalance() + "\nYour Annual" +
                                            " Interest Rate=\n\t" + user.getAnnualInterestRate());

                                }
                                else if (charAssign == '5'){
                                    System.out.println("Account ID:" +"\n\t" + user.getID() + "\nDate Created:" +"\n\t"+user.getDateCreated());
                                }
                                else if (charAssign == '6'){
                                    System.out.println("Enter Annual Interest rate(if you enter " +
                                            "< 0, 0 will be applied):"+"\n\tCurrent Annual Interest " +
                                            "Rate= " + user.getAnnualInterestRate());
                                    tempDouble = scanner.nextDouble();
                                    scanner.nextLine();
                                    user.setAnnualInterestRate(tempDouble);
                                    tempInt = storeIDAnnualInterestRate.indexOf(storeID) + 1;
                                    storeIDAnnualInterestRate.remove(tempInt);
                                    storeIDAnnualInterestRate.remove(tempInt - 1);
                                    storeIDAnnualInterestRate.add(storeID);
                                    storeIDAnnualInterestRate.add(user.getAnnualInterestRate());
                                    System.out.println("\n\tSuccessfully updated Annual Interest Rate... ");
                                    System.out.println("\n\tCurrent Annual Interest Rate= " + user.getAnnualInterestRate());

                                }
                                else if (charAssign == '7'){
                                    System.out.println("Enter new password(must be >5 characters, else current password" +
                                            " will still be attached):");
                                    storePass = scanner.nextLine();
                                    user.changePassword(storePass);
                                    tempInt = storeUsernamePass.indexOf(doubleToString) + 1;
                                    storeUsernamePass.remove(tempInt);
                                    storeUsernamePass.remove(tempInt - 1);
                                    storeUsernamePass.add(doubleToString);
                                    storeUsernamePass.add(user.getPassword());
                                    System.out.println("\n\tPassword successfully changed...");
                                    System.out.println("\"Log in to re-enter.\"");
                                    break;
                                }
                                else if (charAssign == '8'){
                                    user.getAccountInfo();
                                }
                                else if (charAssign == '9'){
                                    System.out.print("\tYou'll be logged out");
                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.print("▫︎");
                                    TimeUnit.SECONDS.sleep(1);
                                    System.out.print("▪︎");
                                    TimeUnit.SECONDS.sleep(1);
                                    break;
                                }
                                else if(charAssign == 'f'){
                                    while (true) {
                                        System.out.println("\nWelcome to typing speed game! Press 1 to play, " +
                                                "press 2 to get your history, 3 to remove history, or press x " +
                                                "to exit: ");
                                        charAssign = scanner.nextLine().charAt(0);
                                        if(charAssign == '1') {
                                            user.wordCountGame(storeIDGameStat);
                                            System.out.println("We hope that the game was enjoyable!");
                                        }
                                        else if(charAssign == '2'){
                                            System.out.println("Here Are your Game Stats:");
                                            for (int n=0; n<storeIDGameStat.size(); n+=2){
                                                if (Double.toString(user.getID()).equals(storeIDGameStat.get(n)))
                                                    System.out.println(storeIDGameStat.get(n+1));
                                            }
                                        }
                                        else if(charAssign == '3'){
                                            for(int n=0; n<storeIDGameStat.size(); n+=2){
                                                if(Double.toString(user.getID()).equals(storeIDGameStat.get(n))) {
                                                    storeIDGameStat.remove(n + 1);
                                                    storeIDGameStat.remove(n);
                                                    n-=2;
                                                }
                                            }
                                            System.out.print("Removing your histories");
                                            TimeUnit.SECONDS.sleep(1);
                                            System.out.print("▫︎");
                                            TimeUnit.SECONDS.sleep(1);
                                            System.out.print("▪︎");
                                        }
                                        else{
                                            System.out.print("You're exiting the game");
                                            TimeUnit.SECONDS.sleep(1);
                                            System.out.print("▫︎");
                                            TimeUnit.SECONDS.sleep(1);
                                            System.out.print("▪︎");
                                            break;
                                        }

                                    }
                                }

                            }

                        }else
                            System.out.println("\n\tWrong password...");
                    }else
                        System.out.println("\n\tID doesn't exist...");
                }

                else if(charAssign == '2'){
                    charAssign = '\u0000';
                    boolean f = true;
                    String pass;
                    String date;
                    double id;
                    double balance;
                    double interestRate;
                    Account a;

                    while (true) {
                        System.out.println("Enter an ID that you prefer(double): ");
                        id = scanner.nextDouble();
                        scanner.nextLine();

                        for (double d : storeIdOnly)
                            if(d == id)
                                f = false;

                            if (f){
                                System.out.println("\n\tThe ID that you've selected was stored...");
                                break;
                            }
                            else
                                System.out.println("\n\tSuch ID existed. Try again.");

                            f = true;
                    }

                    System.out.println("\nEnter Password(String): ");
                    pass = scanner.nextLine();
                    System.out.println("\n\tPassword was stored...");

                    System.out.println("\nEnter balance(double): ");
                    balance = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("\n\tBalance was stored...");

                    System.out.println("\nEnter Annual Interest Rate(double):");
                    interestRate = scanner.nextDouble();
                    System.out.println("\n\tAnnual Interest Rate was stored...");
                    scanner.nextLine();

                    storeUsernamePass.add(Double.toString(id));
                    storeUsernamePass.add(pass);

                    storeIDBalance.add(id);
                    storeIDBalance.add(balance);

                    storeIDAnnualInterestRate.add(id);
                    storeIDAnnualInterestRate.add(interestRate);

                    storeIdOnly.add(id);


                    a = new Account(id,pass, balance, interestRate);

                    date = a.getDateCreated().toString();

                    storeIDDateCreated.add(Double.toString(id));
                    storeIDDateCreated.add(date);
                }
                else if (charAssign == 'x'){
                    //write data
                    writeDataString("storeUsernamePass.txt",storeUsernamePass);
                    writeDataDouble("storeIDBalance.txt", storeIDBalance);
                    writeDataDouble("storeIDAnnualInterestRate.txt", storeIDAnnualInterestRate);
                    writeDataString("storeIDDateCreated.txt", storeIDDateCreated);
                    writeDataDouble("storeIdOnly.txt", storeIdOnly);
                    writeDataString("storeIDGameStat.txt",storeIDGameStat);

                    System.out.print("\n\n💲You are exiting The Bank!💲");
                    System.out.print("▫︎");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.print("▪︎");
                    TimeUnit.SECONDS.sleep(1);
                    break;
                }
            }

    }
    private static void readDataString(String fileName, ArrayList<String> stringArrayList)throws IOException{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner scanner = new Scanner(bufferedReader);
        scanner.useDelimiter("←");
        while (scanner.hasNext()){
            stringArrayList.add(scanner.next());
        }

    }
    private static void readDataDouble(String fileName, ArrayList<Double> doubleArrayList)throws IOException{
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner scanner = new Scanner(bufferedReader);
        scanner.useDelimiter("←");
        while (scanner.hasNext()){
            doubleArrayList.add(Double.parseDouble(scanner.next()));
        }
    }

    private static void writeDataString(String fileName, ArrayList<String> stringArrayList)throws  IOException{
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(int n = 0; n < stringArrayList.size(); n++){
            bufferedWriter.write(stringArrayList.get(n) + "←");
            bufferedWriter.flush();
        }
    }
    private static void writeDataDouble(String fileName, ArrayList<Double> doubleArrayList)throws  IOException{
        FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        for(int n = 0; n < doubleArrayList.size(); n++){
            bufferedWriter.write(doubleArrayList.get(n).toString()+"←");
            bufferedWriter.flush();
        }
    }
}