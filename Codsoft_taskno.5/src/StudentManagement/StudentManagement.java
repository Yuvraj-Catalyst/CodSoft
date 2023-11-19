package StudentManagement;
//Student Management System
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.sql.*;
/*
 DataBase Name -> StudentManagementSystem
 tableName -> loginDetails,studentmanagementtable
 username->yuvraj and password -> Yuvi@ for login and this is stored in login details table
 */
class Student{
    int studentMarks,id;
    String name,mobileNumber,gender,address,course,image,email;
}
public class StudentManagement extends Student{
    Database db = new Database(this);
    int storeId = 0;
    boolean frame3Status = false;
    String imagePath = "";
    boolean validateId = false;
    JFrame frame1,frame2,frame3,frame4,frame5;
    JLabel label1,label2,imageLabel,image1Label,label3,label4,label5,label6,label7,displayHeading,label8,label9,label10;
    JTextField t1,name,studentMarks,email,id,mobileNumber,gender,course;
    JPasswordField pass;
    JButton login,close,add,remove,update,search,display,logout,back,read,browse,refresh,btn,showImage,clear;
    ImageIcon image,image2;
    JTable table;
    JRadioButton male,female,other;
    JTextArea address,images;
    JFileChooser imageChooser;
    JPanel panel,panel2;
    DefaultTableModel model;
    public void loginPage(){
        frame1 = new JFrame("Login Page");
        frame1.setResizable(false);
        frame1.setSize(500,300);
        frame1.setLocation(400,200);
        Container con = frame1.getContentPane();
        con.setBackground(Color.black);
        frame1.setLayout(null);
        frame1.setDefaultCloseOperation(frame1.EXIT_ON_CLOSE);
        label1 = new JLabel("USERNAME");
        label1.setBounds(50,50,180,50);
        label1.setFont(new Font("Arial",Font.BOLD,25));
        frame1.add(label1);
        label1 = new JLabel("USERNAME");
        label1.setBounds(50,50,180,50);
        label1.setFont(new Font("Arial",Font.BOLD,25));
        frame1.add(label1);
        t1 = new JTextField();
        t1.setBounds(250,55,150,40);
        t1.setFont(new Font("Arial",Font.BOLD,20));
        frame1.add(t1);
        label2 = new JLabel("PASSWORD");
        label2.setBounds(50,120,180,50);
        label2.setFont(new Font("Arial",Font.BOLD,25));
        frame1.add(label2);
        pass = new JPasswordField();
        pass.setBounds(250,125,100,40);
        pass.setFont(new Font("Arial",Font.BOLD,20));
        pass.setEchoChar('*');
        image = new ImageIcon("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Task 5\\Codsoft_taskno.5\\src\\images.jpg");
        image2 = new ImageIcon("C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Task 5\\Codsoft_taskno.5\\src\\images1.jpg");
        imageLabel = new JLabel(image);
        imageLabel.setBounds(350,125,image.getIconWidth(),image.getIconHeight());
        imageLabel.setBackground(Color.yellow);
        frame1.add(imageLabel);
        image1Label = new JLabel(image2);
        image1Label.setBounds(350,125,image.getIconWidth(),image.getIconHeight());
        frame1.add(image1Label);
        imageLabel.setVisible(false);
        frame1.add(pass);
        image1Label.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                image1Label.setVisible(false);
                imageLabel.setVisible(true);
                pass.setEchoChar((char)0);
            }
        });
        imageLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e){
                imageLabel.setVisible(false);
                image1Label.setVisible(true);
                pass.setEchoChar('*');
            }
        });
        login = new JButton("LOGIN");
        login.setFont(new Font("Arial",Font.BOLD,20));
        login.setBounds(50,200,120,50);
        frame1.add(login);
        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String str = t1.getText().toString();
                char [] str1 = pass.getPassword();
                if(str.length() == 0 || str1.length == 0){
                    JOptionPane.showMessageDialog(frame1,"You doesn't Enter all Credentials", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    db.validateLogin();
                }
            }
        });
        close = new JButton("CLOSE");
        close.setFont(new Font("Arial",Font.BOLD,20));
        close.setBounds(330,200,120,50);
        frame1.add(close);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int selection = JOptionPane.showConfirmDialog(frame1,"Are you sure to close the application","Confirm",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
                if(selection == JOptionPane.YES_OPTION){
                    frame1.dispose();
                }
            }
        });
        frame1.setVisible(true);
    }
    public void mainPageDesign(){
        frame1.dispose();
        frame2 = new JFrame("Student Option Select Page");
        frame2.setResizable(false);
        Container con = frame2.getContentPane();
        frame2.setBounds(50,200,700,450);
        con.setBackground(Color.black);
        frame2.setDefaultCloseOperation(frame2.EXIT_ON_CLOSE);;
        frame2.setLayout(null);
        // Container con = frame2.getContentPane();
        con.setBackground(Color.black);
        frame2.setLayout(null);
        label1 = new JLabel("Option 1:- ADD");
        label1.setBounds(100,25,300,50);
        label1.setFont(new Font("Arial",Font.PLAIN,25));
        frame2.add(label1);
        add = new JButton("ADD");
        add.setBounds(450,25,100,50);
        add.setFont(new Font("Arial",Font.PLAIN,18));
        frame2.add(add);
        label2 = new JLabel("Option 2:- Remove");
        label2.setBounds(100,85,300,50);
        label2.setFont(new Font("Arial",Font.PLAIN,25));
        frame2.add(label2);
        remove = new JButton("REMOVE");
        remove.setBounds(450,85,130,50);
        remove.setFont(new Font("Arial",Font.PLAIN,18));
        frame2.add(remove);
        label3 = new JLabel("Option 3:- Update");
        label3.setBounds(100,145,300,50);
        label3.setFont(new Font("Arial",Font.PLAIN,25));
        frame2.add(label3);
        update = new JButton("UPDATE");
        update.setBounds(450,145,130,50);
        update.setFont(new Font("Arial",Font.PLAIN,18));
        frame2.add(update);
        label4 = new JLabel("Option 4:- Search");
        label4.setBounds(100,205,300,50);
        label4.setFont(new Font("Arial",Font.PLAIN,25));
        frame2.add(label4);
        search = new JButton("SEARCH");
        search.setBounds(450,205,130,50);
        search.setFont(new Font("Arial",Font.PLAIN,18));
        frame2.add(search);
        label5 = new JLabel("Option 5:- LogOut");
        label5.setBounds(100,265,300,50);
        label5.setFont(new Font("Arial",Font.PLAIN,20));
        frame2.add(label5);
        logout = new JButton("LOGOUT");
        logout.setBounds(450,265,130,50);
        logout.setFont(new Font("Arial",Font.PLAIN,18));
        frame2.add(logout);
        label6 = new JLabel("Option 6:- Display All Students");
        label6.setBounds(100,325,300,50);
        label6.setFont(new Font("Arial",Font.PLAIN,20));
        frame2.add(label6);
        display = new JButton("DISPLAY");
        display.setBounds(450,325,130,50);
        display.setFont(new Font("Arial",Font.PLAIN,18));
        frame2.add(display);
        add.addActionListener(new SwingCommonOperation());
        remove.addActionListener(new SwingCommonOperation());
        search.addActionListener(new SwingCommonOperation());
        update.addActionListener(new SwingCommonOperation());
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame2.dispose();
                frame1.setVisible(true);
            }
        });
        display.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(!frame3Status){
                    displayAllStudent();
                    frame3Status = true;
                }
            }
        });
        // add.addActionListener();
        frame2.setVisible(true);
    }
    public void designStudentForm(){
        frame4 = new JFrame("Student Management System");
        frame4.setResizable(false);
        frame4.setBounds(80,80,550,670);
        frame4.setDefaultCloseOperation(frame4.EXIT_ON_CLOSE);
        frame4.setUndecorated(true);
        frame4.setLayout(null);
        Container cont = frame4.getContentPane();
        label1 = new JLabel("Student Data Field");
        label1.setBounds(170,0,350,50);
        label1.setFont(new Font("Arial",Font.BOLD,25));
        frame4.add(label1);
        label2 = new JLabel("Student Name");
        label2.setBounds(50,50,250,50);
        label2.setFont(new Font("Arial",Font.PLAIN,20));
        frame4.add(label2);
        name = new JTextField();
        name.setFont(new Font("Arial",Font.PLAIN,18));
        name.setBounds(270,60,180,30);
        frame4.add(name);
        label3 = new JLabel("Student Id");
        label3.setBounds(50,90,250,50);
        label3.setFont(new Font("Arial",Font.PLAIN,20));
        frame4.add(label3);
        id = new JTextField();
        id.setFont(new Font("Arial",Font.PLAIN,18));
        id.setBounds(270,100,180,30);
        frame4.add(id);
        label4 = new JLabel("Student Email");
        label4.setBounds(50,130,250,50);
        label4.setFont(new Font("Arial",Font.PLAIN,20));
        frame4.add(label4);
        email = new JTextField();
        email.setFont(new Font("Arial",Font.PLAIN,16));
        email.setBounds(270,140,180,30);
        frame4.add(email);
        label5 = new JLabel("Contact Number");
        label5.setBounds(50,170,250,50);
        label5.setFont(new Font("Arial",Font.PLAIN,20));
        frame4.add(label5);
        mobileNumber = new JTextField();
        mobileNumber.setFont(new Font("Arial",Font.PLAIN,18));
        mobileNumber.setBounds(270,180,180,30);
        frame4.add(mobileNumber);
        label6 = new JLabel("Select Gender");
        label6.setBounds(50,210,250,50);
        label6.setFont(new Font("Arial",Font.PLAIN,20));
        frame4.add(label6);
        ButtonGroup btngroup = new ButtonGroup();
        // frame4.add(btngroup);
        // btngroup.setFont(new Font("Arial",Font.PLAIN
        male = new JRadioButton("Male");
        male.setFont(new Font("Arial",Font.PLAIN,18));
        male.setBounds(230,220,80,30);
        female = new JRadioButton("Female");
        female.setFont(new Font("Arial",Font.PLAIN,18));
        female.setBounds(310,220,90,30);
        other = new JRadioButton("Other");
        other.setFont(new Font("Arial",Font.PLAIN,18));
        other.setBounds(400,220,90,30);
        male.setSelected(true);
        Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
        male.setCursor(cursor);
        female.setCursor(cursor);
        other.setCursor(cursor);
        cont.add(male);
        cont.add(female);
        cont.add(other);
        btngroup.add(male);
        btngroup.add(female);
        btngroup.add(other);
        label7 = new JLabel("Current Course");
        label7.setFont(new Font("Arial",Font.PLAIN,20));
        label7.setBounds(50,260,250,30);
        frame4.add(label7);
        course = new JTextField();
        course.setFont(new Font("Arial",Font.PLAIN,18));
        course.setBounds(270,262,180,30);
        frame4.add(course);
        label8 = new JLabel("Address");
        label8.setFont(new Font("Arial",Font.PLAIN,20));
        label8.setBounds(50,330,250,30);
        frame4.add(label8);
        address = new JTextArea();
        address.setFont(new Font("Arial",Font.PLAIN,18));
        address.setBounds(270,302,180,90);
        address.setBackground(Color.white);
        // address.setForeground(Color.white);
        address.setBorder(BorderFactory.createLineBorder(Color.black));
        name.setBorder(BorderFactory.createLineBorder(Color.black));
        id.setBorder(BorderFactory.createLineBorder(Color.black));
        email.setBorder(BorderFactory.createLineBorder(Color.black));
        course.setBorder(BorderFactory.createLineBorder(Color.black));
        mobileNumber.setBorder(BorderFactory.createLineBorder(Color.black));
        address.setLineWrap(true);
        frame4.add(address);
        label9 = new JLabel("Student Marks");
        label9.setFont(new Font("Arial",Font.PLAIN,20));
        label9.setBounds(50,410,250,30);
        frame4.add(label9);
        studentMarks = new JTextField("");
        studentMarks.setFont(new Font("Arial",Font.PLAIN,18));
        studentMarks.setBounds(270,410,180,30);
        frame4.add(studentMarks);

        studentMarks.setBorder(BorderFactory.createLineBorder(Color.black));
        panel = new JPanel();
        panel.setBounds(50,450,400,150);
        panel.setBorder(BorderFactory.createLineBorder(Color.black));
        frame4.add(panel);
        panel.setLayout(null);
        label10 = new JLabel("Image");
        label10.setFont(new Font("Arial",Font.BOLD,20));
        label10.setBounds(20,10,60,30);
        panel.add(label10);
        browse = new JButton("Browse");
        browse.setFont(new Font("Arial",Font.BOLD,20));
        browse.setBounds(10,70,120,30);
        browse.addActionListener(new SwingCommonOperation());
        panel.add(browse);
        images = new JTextArea("");
        // images.setFont()
        images.setFont(new Font("Arial",Font.BOLD,20));
        images.setBounds(135,10,250,50);
        images.setBorder(BorderFactory.createLineBorder(Color.red));
        images.setLineWrap(true);
        panel.add(images);
        images.setEditable(false);
        showImage = new JButton("Show Image");
        showImage.setFont(new Font("Arial",Font.BOLD,18));
        showImage.setBounds(185,80,140,50);
        showImage.setBorder(BorderFactory.createLineBorder(Color.red));
        panel.add(showImage);
        showImage.addActionListener(new SwingCommonOperation());
        back = new JButton("BACK");
        back.setFont(new Font("Arial",Font.BOLD,18));
        back.setBounds(10,5,120,35);
        frame4.add(back);
        clear = new JButton("Clear");
        clear.setFont(new Font("Arial",Font.BOLD,18));
        clear.setBounds(10,615,120,35);
        frame4.add(clear);
        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                id.setText("");
                name.setText("");
                email.setText("");
                mobileNumber.setText("");
                studentMarks.setText("");
                male.setSelected(true);
                course.setText("");
                images.setText("");
                address.setText("");
            }
        });
        display = new JButton("DISPLAY");
        display.setFont(new Font("Arial",Font.BOLD,18));
        display.setBounds(145,615,120,35);
        frame4.add(display);
        logout = new JButton("LOGOUT");
        logout.setFont(new Font("Arial",Font.BOLD,18));
        logout.setBounds(405,615,120,35);
        frame4.add(logout);
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame4.dispose();
                // frame3.setVisible(false);
                frame2.setVisible(true);


            }
        });
        logout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame4.dispose();
                // frame3.setVisible(false);
                frame1.setVisible(true);
            }
        });
        display.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                if(!frame3Status){
                    displayAllStudent();
                    frame3Status = true;
                }
            }
        });
        frame4.setVisible(true);
    }
    public void fetchTable(){
        Object [][] data = {};
        String[] columnName= {"Id","name","Email","ContactNumber","Gender","Course","Address","Marks","Path"};
        model = new DefaultTableModel(data,columnName);
        db.convertDbToTable();
        table = new JTable(model);
        table.setBounds(10,50,620,650);
        table.setBackground(Color.black);
        table.setForeground(Color.white);
        frame3.add(table);
    }
    public void displayAllStudent(){
        frame3 = new JFrame("Student Management System");
        frame3.setResizable(false);
        frame3.setBounds(750,50,650,770);
        frame3.setDefaultCloseOperation(frame3.EXIT_ON_CLOSE);
        frame3.setLayout(null);
        frame3.setUndecorated(true);
        displayHeading = new JLabel("ALL STUDENT RECORD");
        displayHeading.setFont(new Font("Arial",Font.BOLD,25));
        displayHeading.setBounds(40,5,400,40);
        displayHeading.setBorder(BorderFactory.createLineBorder(Color.red));
        displayHeading.setHorizontalAlignment(SwingConstants.CENTER);
        displayHeading.setForeground(Color.white);
        displayHeading.setBackground(Color.blue);
        displayHeading.setOpaque(true);

        frame3.add(displayHeading);
        fetchTable();
        close = new JButton("Close");
        close.setFont(new Font("Arial",Font.BOLD,18));
        close.setBounds(520,5,100,35);
        close.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                frame3Status = false;
                frame3.dispose();
            }
        });
        JButton clear1 = new JButton("Clear");
        clear1.setFont(new Font("Arial",Font.BOLD,18));
        clear1.setBounds(180,5,100,35);
        clear1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int selectedRow = table.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(frame3,"Please Select a row", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int selection = JOptionPane.showConfirmDialog(frame3,"Are you sure to clear that row from table","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(JOptionPane.YES_OPTION==selection){
                        model.removeRow(selectedRow);
                    }
                }
            }
        });
        panel = new JPanel();
        panel.setBounds(10,710,630,50);
        panel.setLayout(null);
        frame3.add(panel);
        refresh = new JButton("Refresh");
        refresh.setFont(new Font("Aria",Font.BOLD,18));
        refresh.setBounds(500,5,120,40);
        frame3.add(refresh);
        refresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(frame3,"Refresh the Table","Refresh",JOptionPane.INFORMATION_MESSAGE);
                frame3.dispose();
                displayAllStudent();
            }
        });
        read = new JButton("Read");
        read.setFont(new Font("Arial",Font.BOLD,18));
        read.setBounds(20,5,100,35);
        read.addActionListener(new SwingCommonOperation());

        JButton clearAll = new JButton("Clear All");
        clearAll.setFont(new Font("Arial",Font.BOLD,18));
        clearAll.setBounds(340,5,120,35);
        clearAll.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println(model);
                // if(model.setRowCount(); == null){
                //     JOptionPane.showMessageDialog(frame3,"Table is Already Empty","Warning",JOptionPane.WARNING_MESSAGE);
                // }
                int selection = JOptionPane.showConfirmDialog(frame3,"Are you sure to clear that row from table","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(JOptionPane.YES_OPTION==selection){
                    // model.setRowCount(0);
                    model.getDataVector().removeAllElements();
                    model.fireTableDataChanged();
                }

            }
        });
        panel.add(read);
        panel.add(clear1);
        panel.add(clearAll);
        panel.add(close);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        frame3.setVisible(true);
    }
    public void add(){
        int sId = Integer.parseInt(id.getText());
        String sName = name.getText();
        String sEmail = email.getText();
        String sNumber = mobileNumber.getText();
        String sCourse = course.getText();
        int sMarks = Integer.parseInt(studentMarks.getText());
        String sAddress = address.getText();
        String sGender = "male";
        if(male.isSelected()){
            sGender = "male";
        }
        else if(female.isSelected()){
            sGender = "female";
        }
        else if(other.isSelected()){
            sGender = "other";
        }
        try{
            Connection con = db.getConnection();
            String query = "insert into studentmanagementtable values ('"+sId+"','"+sName+"','"+sEmail+"','"+sNumber+"','"+sGender+"','"+sCourse+"','"+sAddress+"','"+sMarks+"','"+imagePath+"')";
            PreparedStatement pstmt = con.prepareStatement(query);
            int i = pstmt.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(frame4,"Your data Successfully Added", "Message", JOptionPane.PLAIN_MESSAGE);
                con.close();
                id.setText("");
                name.setText("");
                email.setText("");
                mobileNumber.setText("");
                studentMarks.setText("Marks out of 700");
                male.setSelected(true);
                course.setText("");
                images.setText("");
                address.setText("");
                frame4.dispose();
                frame2.setVisible(true);
            }
            else{
                JOptionPane.showMessageDialog(frame4,"Your data not Added", "Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame4,"Add error is: "+e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void update(){
        id.setEnabled(false);
        String sName = name.getText();
        String sEmail = email.getText();
        String sNumber = mobileNumber.getText();
        String sCourse = course.getText();
        int sMarks = Integer.parseInt(studentMarks.getText());
        String sAddress = address.getText();
        String path = imagePath;
        String sGender = "male";
        if(male.isSelected()){
            sGender = "male";
        }
        else if(female.isSelected()){
            sGender = "female";
        }
        else if(other.isSelected()){
            sGender = "other";
        }
        try{
            Connection con = db.getConnection();
            String query = "update studentmanagementtable set name='"+sName+"',email='"+sEmail+"',course='"+sCourse+"',address='"+sAddress+"',contactNumber='"+sNumber+"',gender='"+sGender+"',marks="+sMarks+",path=\""+path+"\" where id="+storeId;
            PreparedStatement pstmt = con.prepareStatement(query);
            int i = pstmt.executeUpdate();
            if(i>0){
                JOptionPane.showMessageDialog(frame4,"Your data Successfully Updated", "Message", JOptionPane.PLAIN_MESSAGE);
                con.close();
                name.setText("");
                email.setText("");
                mobileNumber.setText("");
                studentMarks.setText("Marks out of 700");
                male.setSelected(true);
                course.setText("");
                images.setText("");
                address.setText("");
            }
            else{
                JOptionPane.showMessageDialog(frame4,"Your data not Updated", "Message", JOptionPane.PLAIN_MESSAGE);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame4,"Update error is: "+e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void search(){
        try{
            Connection con = db.getConnection();
            String query = "Select * from studentmanagementtable where id= "+storeId;
            Statement pstmt = con.createStatement();
            ResultSet set = pstmt.executeQuery(query);
            while(set.next()){
                int sId = set.getInt("id");
                String sName = set.getString("name");
                String sEmail = set.getString("email");
                String sNumber = set.getString("contactNumber");
                String sGender = set.getString("gender");
                String sCourse = set.getString("course");
                String sAddress = set.getString("address");
                int sMarks = set.getInt("marks");
                String path = set.getString("path");
                id.setText(String.valueOf(sId));
                name.setText(sName);
                email.setText(sEmail);
                mobileNumber.setText(sNumber);
                if(sGender.equalsIgnoreCase("male")){
                    male.setSelected(true);
                }
                else if(sGender.equalsIgnoreCase("female")){
                    female.setSelected(true);
                }
                else if(sGender.equalsIgnoreCase("other")){
                    other.setSelected(true);
                }
                course.setText(sCourse);
                address.setText(sAddress);
                studentMarks.setText(String.valueOf(sMarks));
                images.setText(path);
            }
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame4,"Search error is: "+e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void remove(){
        try{
            Connection con = db.getConnection();
            String query = "Delete from studentmanagementtable where id= "+storeId;
            Statement pstmt = con.createStatement();
            pstmt.executeUpdate(query);
            con.close();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame4,"Remove error is: "+e, "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void checkId(){
        String str = JOptionPane.showInputDialog("Enter Student Id");
        if(str == null) {
            return;
        }
        if(str.isEmpty()){
             JOptionPane.showMessageDialog(frame1,"Enter Your Id","Error", JOptionPane.ERROR_MESSAGE);
             return;
        }
        else if(!(str.matches("^[0-9]+$"))){
            return;
        }
        else{
            storeId = Integer.parseInt(str);
            validateId = db.validateStudentId(storeId);
        }
    }
    public void setNonEditable(){
        id.setEditable(false);
        name.setEditable(false);
        course.setEditable(false);
        email.setEditable(false);
        mobileNumber.setEditable(false);
        studentMarks.setEditable(false);
        address.setEditable(false);
        male.setEnabled(false);
        female.setEnabled(false);
        other.setEnabled(false);
        browse.setEnabled(false);
    }
    public boolean validateComponent(){
        String sId = (String)id.getText();
        String sName = (String)name.getText();
        String sEmail = (String)email.getText();
        String sCourse = (String)course.getText();
        String sNumber = (String)mobileNumber.getText();
        String sAddress = address.getText();
        String sMarks = (String)studentMarks.getText();
        if(sName.trim().equals("")){
            JOptionPane.showMessageDialog(frame3,"Enter Student Name","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(sName.trim().matches("^[a-zA-Z' ']+[a-zA-Z]+$"))){
            JOptionPane.showMessageDialog(frame3,"Invalid Name","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sId.trim().equals("")){
            JOptionPane.showMessageDialog(frame3,"Enter Student Id","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(sId.trim().matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(frame4,"Incorrect Id","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sEmail.trim().equals("")){
            JOptionPane.showMessageDialog(frame3,"Enter Student Email","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(sEmail.trim().matches("^[a-zA-Z0-9_.-]+[a-zA-Z0-9]+@[a-zA-Z0-9.]+$"))){
            JOptionPane.showMessageDialog(frame3,"Invalid Email","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sNumber.trim().equals("")){
            JOptionPane.showMessageDialog(frame3,"Enter Student Mobile Number","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(!(sNumber.trim().matches("^[0-9]+$"))){
            JOptionPane.showMessageDialog(frame3,"Invalid Mobile Number","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if((sNumber.trim().length()<10 || sNumber.trim().length()>12)){
            JOptionPane.showMessageDialog(frame3,"Incorrect Mobile Number","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sCourse.trim().equals("")){
            JOptionPane.showMessageDialog(frame3,"Enter Student Course","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sAddress.trim().isEmpty()){
            JOptionPane.showMessageDialog(frame3,"Enter Student Address","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(sMarks.trim().equals("")){
            JOptionPane.showMessageDialog(frame3,"Enter Student Marks","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(!(sMarks.trim().matches(("^[0-9]+$")))){
            JOptionPane.showMessageDialog(frame3,"Invalid Marks","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }

        if(Integer.parseInt(sMarks)>700){
            JOptionPane.showMessageDialog(frame3,"Enter Marks out of 700","Warning",JOptionPane.WARNING_MESSAGE);
            return false;
        }
        try{
            Connection con = db.getConnection();
            Statement stmt = con.createStatement();
            if(!(btn.getText().equalsIgnoreCase("Update"))){
                int studentId = Integer.parseInt(sId);
                String query = "Select * from studentmanagementtable where id= "+studentId;
                System.out.println(query);
                ResultSet set = stmt.executeQuery(query);
                if(set.next()){
                    JOptionPane.showMessageDialog(frame3,"Please Enter Unique Id","Erorr",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
                ResultSet set1 = stmt.executeQuery("select * from studentmanagementtable where email = '"+sEmail+"'");
                if(set1.next()){
                    JOptionPane.showMessageDialog(frame3,"Please Enter Unique Email Id","Erorr",JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
            con.close();
            return true;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(frame3,"Validation error: "+e,"Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    class SwingCommonOperation implements ActionListener{
        public void actionPerformed(ActionEvent e){
            if(e.getSource()==add){
                frame2.dispose();
                designStudentForm();
                btn = new JButton("add");
                btn.setFont(new Font("Arial",Font.BOLD,18));
                btn.setBounds(275,615,120,35);
                frame4.add(btn);
                btn.addActionListener(new StudentFormOperation());
                frame4.validate();
            }
            if(e.getSource()==remove){
                checkId();
                if(validateId){
                    frame2.dispose();
                    designStudentForm();
                    btn = new JButton("Remove");
                    btn.setFont(new Font("Arial",Font.BOLD,18));
                    btn.setBounds(275,615,120,35);
                    frame4.add(btn);
                    btn.addActionListener(new StudentFormOperation());
                    search();
                    setNonEditable();
                    frame4.validate();
                }
                else{
                    JOptionPane.showMessageDialog(frame3,"Id Not Matched","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(e.getSource()==update){
                checkId();
                if(validateId){
                    frame2.dispose();
                    designStudentForm();
                    id.setEditable(false);
                    btn = new JButton("Update");
                    btn.setFont(new Font("Arial",Font.BOLD,18));
                    btn.setBounds(275,615,120,35);
                    frame4.add(btn);
                    btn.addActionListener(new StudentFormOperation());
                    search();
                    frame4.validate();
                }
                else{
                    JOptionPane.showMessageDialog(frame3,"Id Not Matched","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(e.getSource()==search){
                checkId();
                if(validateId){
                    frame2.dispose();
                    designStudentForm();
                    btn = new JButton("Search");
                    btn.setFont(new Font("Arial",Font.BOLD,18));
                    btn.setBounds(275,615,120,35);
                    frame4.add(btn);
                    btn.addActionListener(new StudentFormOperation());
                    search();
                    setNonEditable();
                    // clear.setEditable(false);
                    frame4.validate();
                }
                else{
                    JOptionPane.showMessageDialog(frame3,"Id Not Matched","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            if(e.getSource() == read){
                int selectedRow = table.getSelectedRow();
                if(selectedRow == -1){
                    JOptionPane.showMessageDialog(frame3,"Please Select a row", "Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    // frame2.dispose();
                    // frame4.setVisible(true);
                    designStudentForm();
                    int sId = (int)model.getValueAt(selectedRow, 0);
                    String sName = (String)model.getValueAt(selectedRow,1);
                    String sEmail = (String)model.getValueAt(selectedRow,2);
                    String sContactNumber = (String)model.getValueAt(selectedRow, 3);
                    String sGender = (String)model.getValueAt(selectedRow,4);
                    String sCourse = (String)model.getValueAt(selectedRow,5);
                    String sAddress = (String) model.getValueAt(selectedRow,6);
                    int sMarks = (int)model.getValueAt(selectedRow, 7);
                    String path = (String)model.getValueAt(selectedRow,8);
                    id.setText(String.valueOf(sId));
                    name.setText(sName);
                    email.setText(sEmail);
                    mobileNumber.setText(sContactNumber);
                    if(sGender.equalsIgnoreCase("male")){
                        male.setSelected(true);
                    }
                    else if(sGender.equalsIgnoreCase("female")){
                        female.setSelected(true);
                    }
                    else if(sGender.equalsIgnoreCase("other")){
                        other.setSelected(true);
                    }
                    course.setText(sCourse);
                    address.setText(sAddress);
                    studentMarks.setText(String.valueOf(sMarks));
                    images.setText(path);
                    setNonEditable();
                    btn = new JButton("Close");
                    btn.setFont(new Font("Arial",Font.BOLD,18));
                    btn.setBounds(275,615,120,35);
                    frame4.add(btn);
                    btn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            frame4.dispose();
                        }
                    });
                    back.setEnabled(false);
                    clear.setEnabled(false);
                    logout.setEnabled(false);
                    frame4.validate();
                }
            }
            if(e.getSource() == browse){
                images.setText("");
                imageChooser = new JFileChooser();
                imageChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image","jpg","gif","png");
                imageChooser.addChoosableFileFilter(filter);
                int selection = imageChooser.showDialog(null,"Open");
                if(selection == JFileChooser.APPROVE_OPTION){
                    File selectFile = imageChooser.getSelectedFile();
                    String path = selectFile.getAbsolutePath();
                    imagePath = path.replace("\\","\\\\");
                    images.setText(imagePath);
                    // images.setIcon(imageAdjust(path,null));
                    // imagePath = "C:\\Users\\TARKESHWAR PRASAD\\Desktop\\program\\Task 5\\images.jpg";
                }
                else{
                    JOptionPane.showMessageDialog(frame4,"NO Image Selected");
                }
            }
            if(e.getSource()==showImage){
                String str = images.getText();
                if(str.isEmpty()){
                    JOptionPane.showMessageDialog(frame3,"Image Is Not Selected", "Error",JOptionPane.WARNING_MESSAGE);
                    return;
                }
                frame4.dispose();
                frame5 = new JFrame();
                frame5.setUndecorated(true);
                frame5.setLayout(new BorderLayout());
                frame5.setBounds(80,80,600,500);
                JLabel labelImage = new JLabel();
                String path = (String)images.getText();
                // ImageIcon img = imageAdjust(path,null);
                labelImage.setSize(frame5.getWidth(),frame5.getHeight());
                ImageIcon img = imageAdjust(path,null,labelImage);
                labelImage.setIcon(img);
                labelImage.setHorizontalAlignment(SwingConstants.CENTER);
                close = new JButton("Close");
                close.setHorizontalAlignment(SwingConstants.CENTER);
                close.setFont(new Font("Arial",Font.BOLD,20));
                close.setSize(200,200);
                frame5.add(close,BorderLayout.PAGE_END);
                frame5.add(labelImage,BorderLayout.CENTER);
                close.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        frame5.dispose();
                        frame4.setVisible(true);
                    }
                });
                if(labelImage.getText().toString().isEmpty()){
                    labelImage.setText("Image Not Found");
                    labelImage.setFont(new Font("Arial",Font.ITALIC,30));
                }
                frame5.setVisible(true);

            }
        }
        private ImageIcon imageAdjust(String path,byte[] pic,JLabel labelImage){
            ImageIcon myImage = null;
            if(imagePath != null){
                myImage = new ImageIcon(imagePath);
            }
            else{
                myImage = new ImageIcon(pic);
            }
            Image img = myImage.getImage();
            Image newImage = img.getScaledInstance(labelImage.getWidth(),labelImage.getHeight(),Image.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(newImage);
            return icon;
        }

    }
    class StudentFormOperation implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String str = (String)btn.getText();
            if(str.equalsIgnoreCase("add")){
                if(validateComponent()){
                    add();
                }
            }
            if(str.equalsIgnoreCase("update")){
                int selection = JOptionPane.showConfirmDialog(frame4,"you sure to update this student record","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(selection == JOptionPane.YES_OPTION){
                    if(validateComponent()){
                        update();
                    }
                    frame4.dispose();
                    frame2.setVisible(true);
                }
            }
            if(str.equalsIgnoreCase("Remove")){
                int selection = JOptionPane.showConfirmDialog(frame4,"you sure to delete this student record","Confirmation",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if(selection == JOptionPane.YES_OPTION){
                    remove();
                    frame4.dispose();
                    frame2.setVisible(true);
                }
            }
            if(str.equalsIgnoreCase("search")){
                frame4.dispose();
                frame2.setVisible(true);
            }
        }
    }
}
class Database{
    StudentManagement sms;
    Database(StudentManagement sms){
        this.sms = sms;
    }
    public Connection getConnection(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/StudentManagementSystem","root","root");
            return con;
        }
        catch(Exception e){
            System.out.println("Error in getconnection "+e);
        }
        return null;
    }
    public void validateLogin(){
        try{
            String username = sms.t1.getText().toString();
            char [] password = sms.pass.getPassword();
            // System.out.println("password is "+sms.pass.getText().toString());
            boolean check = false;
            String checkPassword = "";
            Connection con = getConnection();
            Statement stmt = con.createStatement();
            String query = "select * from loginDetails";
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                if(username.equalsIgnoreCase(set.getString(2)) /*&& password.equals(set.getString(3))*/){
                    checkPassword = set.getString(3);
                    check = true;
                    break;
                }
            }
            if(check){
                for(int i=0;i<password.length;i++){
                    if(password[i] != checkPassword.charAt(i)){
                        check = false;
                        break;
                    }
                }
                if(check){
                    JOptionPane.showMessageDialog(sms.frame1,"Login Successfull","Information",JOptionPane.PLAIN_MESSAGE);
                    sms.mainPageDesign();
                }
                else{
                    JOptionPane.showMessageDialog(sms.frame1,"Incorrect Password","Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(sms.frame1,"Incorrect Username","Warning",JOptionPane.WARNING_MESSAGE);
            }
            sms.t1.setText("");
            sms.pass.setText("");
            con.close();
        }
        catch(Exception es){
            es.printStackTrace();
        }
    }
    public boolean validateStudentId(int id){
        try{
            Connection con = getConnection();
            String query = "Select id from studentmanagementtable";
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                if(set.getInt(1)==id){
                    con.close();
                    return true;
                }
            }
            con.close();
            return false;
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public void convertDbToTable(){
        try{
            Connection con = getConnection();
            String query = "Select * from studentmanagementtable";
            Statement stmt = con.createStatement();
            ResultSet set = stmt.executeQuery(query);
            while(set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                String email = set.getString("email");
                String number = set.getString("contactNumber");
                String gender = set.getString("gender");
                String course = set.getString("course");
                String address = set.getString("address");
                int marks = set.getInt("marks");
                String path = set.getString("path");
                Object row[] = {id,name,email,number,gender,course,address,marks,path};
                sms.model.addRow(row);
            }
            con.close();
        }
        catch(Exception e){
            System.out.println("Error in display table\t"+e);;
        }
    }
}

