package sign;

import conferenceManageByChair.ConferenceMain;
import entity.CMS;
import entity.User;
import viewDetailAdmin.ViewUsersDetail;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignIn extends JFrame {
    private JPanel sign;
    private JTextField emailTextField;
    private JButton signInButton;
    private JButton signUpButton;
    private JPanel signContent;
    private JPanel upP;
    private JSplitPane mainSplit;
    private JSplitPane secondSplit;
    private JPanel downP;
    private JLabel signTitle;
    private JLabel userEm;
    private JPasswordField passwordField;
    private JLabel userPaw;
    private JLabel titleIn;
    private JLabel showVerify;
    private JLabel helpLabel;
    //private boolean isOpen = false;
    //static JFrame frame3 = new JFrame();
    private CMS cms;
    public static User signUser;


    public SignIn() {
        setTitle("Sign In");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        add(sign);
        setResizable(false);
        setVisible(true);

        cms = new CMS();
        cms.readCurrentUser(readFile("UserList.txt"));
        userEm.setToolTipText("Example email: abb123@gmail.com.");


        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SignUp signup = new SignUp();
                signup.setVisible(true);

                dispose();

            }
        });
        signInButton.addActionListener(new ActionListener() {

            String adm = "admin11@admin.com";
            String admPwd = "admin123456789";

            @Override
            public void actionPerformed(ActionEvent e) {
                if (emailTextField.getText().contains(" ") || String.valueOf(passwordField.getPassword()).contains(" ") ) {
                    showVerify.setText("Please enter correct information.");
                }
                else if (!isEmail(emailTextField.getText())) {
                    showVerify.setText("Please enter correct email.");
                } else if (emailTextField.getText().equals(adm) && String.valueOf(passwordField.getPassword()).equals(admPwd)) {
                    ViewUsersDetail viewUsersDetail = new ViewUsersDetail();
                    viewUsersDetail.setVisible(true);
                    dispose();
                } else if (findUser(emailTextField.getText(), String.valueOf(passwordField.getPassword())) < 0) {
                    showVerify.setText("Please check your email and password again.");
                } else {
                    showVerify.setText("correct email.");
                    for (User temp : cms.getListOfUsers()) {
                        if (temp.getUserEmail().equals(emailTextField.getText())) {
                            signUser = temp;
                        }
                    }
                    ConferenceMain conferenceMain = null;
                    try {
                        conferenceMain = new ConferenceMain();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    conferenceMain.setVisible(true);
                    dispose();
                }
            }
        });


        /*helpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                JOptionPane.showMessageDialog(null, "Example email: abb123@gmail.com");
            }
        });*/
    }


    public boolean isEmail(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);

        return m.matches();

    }

    public int findUser(String email, String password) {
        int index = -1;
        for (User currentUser : cms.getListOfUsers()) {
            if (currentUser.getUserEmail().equals(email) && currentUser.getUserPassword().equals(password)) {
                index = cms.getListOfUsers().indexOf(currentUser);
                break;
            }
        }
        return index;
    }


    private ArrayList<String> readFile(String filename) {
        ArrayList<String> fileContent = new ArrayList<>();

        try {
            FileReader file = new FileReader(filename);
            Scanner scanFile = new Scanner(file);

            while (scanFile.hasNextLine()) {
                String oneLine = scanFile.nextLine();
                fileContent.add(oneLine.trim());
            }
            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem found: " + e);
        } catch (IOException e) {
            System.out.println("Problem found: " + e);
        } finally {
            return fileContent;
        }
    }


    public JPanel getSignIn() {
        return sign;
    }


    public static void main(String[] args) {
        new SignIn();

    }


}
