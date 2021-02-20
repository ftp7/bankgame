package E2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Account {
    private  double id;
    private String password;
    private double balance;
    private double annualInterestRate;
    private Date dateCreated;
    private String  playedDate;
    private int WPM;
    private int correctlyTyped;
    private int mistyped;

    public Account(double id, String password, double balance, double annualInterestRate ){
        this.id = id; this.password = password; this.balance = balance; this.annualInterestRate = annualInterestRate;
        this.dateCreated = new Date();
    }
    public boolean withdraw(double withdrawAmount){
        if (withdrawAmount > this.balance || withdrawAmount < 0)
            return false;
        else{
            this.balance -= withdrawAmount;
            return true;
        }
    }
    public void deposit(double depositAmount){
        double set = depositAmount > 0 ? depositAmount : 0;
        this.balance += set;
    }

    public double getID(){
        return this.id;
    }
    public double getBalance(){
        return this.balance;
    }
    public double getAnnualInterestRate(){
        return this.annualInterestRate;
    }
    public Date getDateCreated(){
        return this.dateCreated;
    }
    public String getPassword(){
        return this.password;
    }
    public void setAnnualInterestRate(double setAnnualInterestRate){
        double set = setAnnualInterestRate > 0 ? setAnnualInterestRate : this.annualInterestRate;
        this.annualInterestRate = set;
    }
    public void changePassword(String password){
        String set = (password.length() > 5 && password.isBlank() == false) ? password : this.password;
        this.password = set;
    }
    public void getAccountInfo(){
        System.out.println("\nHere is your account info:\n" + "\nAccount Id:\n\t" + this.id + "\nDate Created:\n\t" + this.dateCreated
        + "\nBalance=\n\t" + this.balance + "\nAnnual Interest Rate=\n\t" + this.annualInterestRate);
    }
    public void wordCountGame(ArrayList<String> storeIDGameStat) throws Exception{

        FileReader fileReader = new FileReader("listOfWords.txt");
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Scanner sc0 = new Scanner(bufferedReader);

        ArrayList<String> collectionOfWords = new ArrayList<>();
        Random rn = new Random();

        String[] randomWords = new String[10];
        ArrayList<String> wordsTypedAL = new ArrayList<>();
        for (int n = 1; n <= 100; n++) {
            wordsTypedAL.add(null);
        }


        String fromUser = null;
        Scanner sc1 = new Scanner(System.in);


        this.correctlyTyped = 0;
        this.mistyped = 0;

        while (sc0.hasNext()) {
            collectionOfWords.add(sc0.next());
        }


        for (int n = 0; n < randomWords.length; n++) {
            randomWords[n] = collectionOfWords.get(rn.nextInt(collectionOfWords.size()));
        }

        System.out.print("🏁Timer🏁: ");
        System.out.print(1 + "   ");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(2 + "   ");
        TimeUnit.SECONDS.sleep(1);
        System.out.print(3);
        TimeUnit.SECONDS.sleep(1);
        System.out.println();
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println("You should type these words(with spaces):");
        System.out.println();
        for (int n = 0; n < 10; n++) {
            System.out.print(randomWords[n] + " ");
        }
        System.out.println();
        System.out.println("★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★");
        System.out.println();
        System.out.println("Start Typing ↓");
        System.out.println();


        double start = LocalTime.now().toNanoOfDay();

        fromUser = sc1.nextLine();

        double end = LocalTime.now().toNanoOfDay();
        double timeElapsed = (end - start) * Math.pow(10, -9);
        int charLength = fromUser.length();
        this.WPM = (int) ((((double) charLength / 5.0) / timeElapsed) * 60);

        StringTokenizer stringTokenizer = new StringTokenizer(fromUser, " ");
        for (int n = 0; n < 10; n++) {
            if (stringTokenizer.hasMoreTokens())
                wordsTypedAL.add(n, stringTokenizer.nextToken());
        }
        for (int n = 0; n < randomWords.length; n++) {
            if (randomWords[n].equals(wordsTypedAL.get(n)))
                correctlyTyped++;
            else
                mistyped++;
        }
        this.playedDate = new Date().toString();
        System.out.println("\n♥︎Great Job♥︎\nPlayed date: " + playedDate + "\nWords per minute= " + WPM + "/WPM" + "\nCorrectly Typed= " + correctlyTyped + " word(s)" +
                "\nMistyped= " + mistyped + " word(s)");
        storeIDGameStat.add(Double.toString(this.id));
        storeIDGameStat.add("\nPlayed date: " + playedDate + "\nWords per minute= " + WPM + "/WPM" + "\nCorrectly Typed= " + correctlyTyped + " word(s)" +
                "\nMistyped= " + mistyped + " word(s)");
    }
    }

