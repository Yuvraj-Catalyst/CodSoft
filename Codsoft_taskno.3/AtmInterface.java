//Program for Atm machine
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AtmInterface{
    public static void main(String args[]){
        // Scanner input = new Scanner(System.in);
        // System.out.println("Enter your Name");
        // String name = input.nextLine();
        // System.out.println("Enter your Current Balace");
        // int accountBalance = input.nextInt();
        UserAccount holder1 = new UserAccount("Yuvraj",40000,"4002 0200 1002");
        holder1.setPin(325);
        Atm atm = new Atm("ATM",holder1);
        atm.firstPage();
    }
}
class UserAccount{
    String name;
    int accountBalance;
    private int pin;
    String cardNumber;
    UserAccount(String name,int accountBalance,String cardNumber){
        this.name = name;
        this.accountBalance = accountBalance;
        this.cardNumber = cardNumber;
    }
    public void setPin(int pin){
        this.pin = pin;
    }
    public int getPin(){
        return pin;
    }
}
class Atm extends JFrame{
    int option;
    UserAccount accountHolder;
    JLabel label1,label2,label3,label4,label5,label6,label7,label8;
    JTextField amount,pin,cardNumber;
    JButton withdrawing,depositing,check,btn,btn1;
    Atm(String msg,UserAccount accountHold){
        super(msg);
        this.accountHolder = accountHold;
        setLocationRelativeTo(null);
        setResizable(false);
    }
    public void firstPage(){
        label1 = new JLabel("Option 1:- Withdraw Or Deposit");
        label2 = new JLabel("Option 2:- Check Balance");
        btn = new JButton("Enter Amount");
        check = new JButton("Check");
        setSize(600,200);
        setLayout(null);
        label1.setFont(new Font("Arial",Font.PLAIN,20));
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        btn.setFont(new Font("Arial",Font.PLAIN,18));
        check.setFont(new Font("Arial",Font.PLAIN,18));
        label1.setBounds(30,50,300,30);
        btn.setBounds(340,50,200,30);
        label2.setBounds(30,100,250,30);
        check.setBounds(340,100,120,30);
        btn.addActionListener(new AmountInput());
        check.addActionListener(new CheckBalance());
        add(label1);
        add(btn);
        add(label2);
        add(check);
        display();
    }
    public void removeFirstPage(){
        remove(label1);
        remove(label2);
        remove(btn);
        remove(check);
    }
    public void removeInput(){
        remove(label4);
        remove(amount);
        remove(withdrawing);
        remove(depositing);
    }
    public void removeWithdraw(){
        remove(label5);
        remove(label6);
        remove(cardNumber);
        remove(pin);
        remove(btn);
    }
    class RemoveWithdraw implements ActionListener{
        public void actionPerformed(ActionEvent e){
            removeWithdraw();
            int pinNumber = Integer.parseInt(pin.getText());
            int amt = Integer.parseInt(amount.getText());
            if(pinNumber != accountHolder.getPin()){
                label7 = new JLabel("Pin is Incorrect");
                // display();
            }
            else if(accountHolder.accountBalance < amt){
                label7 = new JLabel("Insufficient Balance");
            }
            else{
                // int amt = Integer.parseInt(amount.getText());
                accountHolder.accountBalance -= amt;
                label7 = new JLabel("SuccessFull Withdraw");
            }
            label7.setBounds(50,80,300,30);
            label7.setFont(new Font("Arial",Font.BOLD,20));
            add(label7);
            checkBalance();
        }
    }
    class AmountInput implements ActionListener{
        public void actionPerformed(ActionEvent e){
            removeFirstPage();
            inputAmount();
        }
    }
    class Withdraw implements ActionListener{
        public void actionPerformed(ActionEvent e){
            removeInput();
            int amt = Integer.parseInt(amount.getText());
            withdraw(amt);
        }
    }
    class Deposit implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int amt = Integer.parseInt(amount.getText());
            removeInput();
            deposit(amt);
        }
    }
    class CheckBalance implements ActionListener{
        public void actionPerformed(ActionEvent e){
            removeFirstPage();
            checkBalance();
        }
    }
    public void inputAmount(){
        label4 = new JLabel("Enter the amount");
        amount = new JTextField();
        withdrawing = new JButton("Withdraw");
        depositing = new JButton("Deposit");
        setSize(400,200);
        label4.setFont(new Font("Arial",Font.PLAIN,24));
        amount.setFont(new Font("Arial",Font.BOLD,20));
        withdrawing.setFont(new Font("Arial",Font.BOLD,16));
        depositing.setFont(new Font("Arial",Font.BOLD,16));
        setLayout(null);
        label4.setBounds(100,50,200,30);
        amount.setBounds(135,80,90,20);
        depositing.setBounds(250,120,120,30);
        withdrawing.setBounds(20,120,120,30);
        withdrawing.addActionListener(new Withdraw());
        depositing.addActionListener(new Deposit());
        // btn.addActionListener(new RemoveInput());
        add(label4);
        add(amount);
        add(withdrawing);
        add(depositing);
        // display();
    }
    public void withdraw(int amount){
        label5 = new JLabel("Card Number");
        label6 = new JLabel("Enter your Pin");
        cardNumber = new JTextField();
        pin = new JTextField(4);
        btn = new JButton("Next");
        setSize(500,300);
        setLayout(null);
        cardNumber.setText(accountHolder.cardNumber);
        cardNumber.setEditable(false);
        label5.setFont(new Font("Arial",Font.PLAIN,24));
        label6.setFont(new Font("Arial",Font.PLAIN,24));
        btn.setFont(new Font("Arial",Font.BOLD,20));
        cardNumber.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        pin.setFont(new Font("Arial",Font.CENTER_BASELINE,18));
        label5.setBounds(70,70,150,30);
        cardNumber.setBounds(300,70,150,30);
        label6.setBounds(70,120,180,30);
        pin.setBounds(300,120,150,30);
        btn.setBounds(330,200,100,30);
        btn.addActionListener(new RemoveWithdraw());
        add(label5);
        add(cardNumber);
        add(label6);
        add(pin);
        add(btn);

    }
    public void deposit(int amount){
        accountHolder.accountBalance += amount; 
        setSize(500,300);
        label7 = new JLabel("Deposit Successfull!!!");
        btn = new JButton("Close");
        label8 = new JLabel("Current Balance = "+accountHolder.accountBalance);
        label7.setFont(new Font("Arial",Font.BOLD,24));
        label8.setFont(new Font("Arial",Font.BOLD,24));
        btn.setFont(new Font("Arial",Font.BOLD,20));
        setLayout(null);
        label7.setBounds(100,100,250,30);
        label8.setBounds(100,130,300,30);
        btn.setBounds(350,200,100,40);
        btn.addActionListener(new Close());
        add(label7);
        add(label8);
        add(btn);
        // display();
    }
    public void checkBalance(){
        setSize(400,200);
        label8 = new JLabel("Current Balance = "+accountHolder.accountBalance);
        btn = new JButton("Close");
        label8.setFont(new Font("Arial",Font.BOLD,22));
        btn.setFont(new Font("Arial",Font.ITALIC,20));
        setLayout(null);
        label8.setBounds(50,50,300,30);
        btn.setBounds(250,120,120,30);
        add(label8);
        add(btn);
        // display();
        btn.addActionListener(new Close());
    }
    public void display(){
        Container con;
        con = getContentPane();
        con.setBackground(Color.black);
        setVisible(true);
    }
    class Close implements ActionListener{
        public void actionPerformed(ActionEvent e){
            dispose();
        }
    }
}