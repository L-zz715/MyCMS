package sign;


import entity.CMS;
import entity.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends JFrame {
    private JPanel SignUp;
    private JPanel upPanel;
    private JLabel titleUp;
    private JPanel downPanel;
    private JSplitPane seSplit;
    private JSplitPane firSplit;
    private JPanel subTitleP;
    private JLabel subTitle;
    private JPanel subDownP;
    private JButton backButton;
    private JButton signUpButton;
    private JTextField userNameField;
    private JTextField qualiField;
    private JTextField emailField;
    private JTextField employerField;
    private JTextField mobilField;
    private JPasswordField passwordField;
    private JTextField occupField;
    private JTextField interestField;
    private JLabel nameLa;
    private JLabel emailLa;
    private JLabel paswdLa;
    private JLabel mobilenoLa;
    private JLabel quaLa;
    private JLabel occuLa;
    private JLabel inareaLa;
    private JLabel empLa;
    private JLabel confirmPwdLabel;
    private JPasswordField confPasswordField;

    static JFrame frame = new JFrame();

    private final CMS cms;


    public SignUp() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        add(SignUp);
        setVisible(true);
        setResizable(false);

        cms = new CMS();
        cms.readCurrentUser(cms.readFile("UserList.txt"));

        emailLa.setToolTipText("Example email: abb123@gmail.com.");
        paswdLa.setToolTipText("Please enter 8 or more characters except space.");
        mobilenoLa.setToolTipText("Only number can be accepted");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                SignIn signInNew = new SignIn();
                signInNew.setVisible(true);
                dispose();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            final String pwd = new String(passwordField.getPassword());
            final String confPwd = new String(confPasswordField.getPassword());

            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameField.getText().isEmpty() || emailField.getText().isEmpty() ||
                        String.valueOf(passwordField.getPassword()).isEmpty() ||
                        mobilField.getText().isEmpty() || qualiField.getText().isEmpty() ||
                        occupField.getText().isEmpty() || employerField.getText().isEmpty() ||
                        interestField.getText().isEmpty() ||
                        userNameField.getText().contains(" ") || emailField.getText().contains(" ") ||
                        String.valueOf(passwordField.getPassword()).contains(" ") ||
                        mobilField.getText().contains(" ") || qualiField.getText().contains(" ") ||
                        occupField.getText().contains(" ") || employerField.getText().contains(" ") ||
                        interestField.getText().contains(" ")) {

                    Object[] option = {"OK"};
                    JOptionPane.showOptionDialog(null, "Alert\nYour information is unqualified, please fill in the required fields.", "Message",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

                } else if (!isEmail(emailField.getText()) || !isNumber(mobilField.getText()) || mobilField.getText().length() > 15) {
                    Object[] option = {"OK"};
                    JOptionPane.showOptionDialog(null, "Alert\nYour information is unqualified, please go back and have a double check.", "Message",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

                } else if (findUser(emailField.getText()) >= 0 || emailField.getText().equals("admin11@admin.com")) {
                    Object[] option = {"OK"};
                    JOptionPane.showOptionDialog(null, "Alert\nYour email has been signed up, please enter other email.", "Message",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

                } else if (passwordField.getPassword().length < 8 || confPasswordField.getPassword().length < 8) {
                    Object[] option = {"OK"};
                    JOptionPane.showOptionDialog(null, "Alert\nPlease enter 8 or more characters.", "Message",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

                } else if (!pwd.equals(confPwd)) {
                    Object[] option = {"OK"};
                    JOptionPane.showOptionDialog(null, "Alert\nPlease enter the same password.", "Message",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);

                } else {
                    Object[] option = {"OK"};
                    JOptionPane.showOptionDialog(null, "Congratulations\nYou have successfully signed up, we have sent a confirmation email to you.", "Message",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);


                    User newUser = new User(userNameField.getText(), emailField.getText(), String.valueOf(passwordField.getPassword()),
                            Integer.parseInt(mobilField.getText()), qualiField.getText(), occupField.getText(),
                            employerField.getText(), interestField.getText());
                    cms.saveNewUser(newUser);
                    cms.writeUserFile("UserList.txt");

                    SignIn signInNew = new SignIn();
                    signInNew.setVisible(true);
                    dispose();

                }
            }
        });
    }

    private boolean isNumber(String newLine) {
        for (int i = 0; i < newLine.length(); i++)
            if (!Character.isDigit(newLine.charAt(i)))
                return false;
        return true;
    }



    public boolean isEmail(String email) {
        String pattern = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(email);

        return m.matches();

    }

    public int findUser(String email) {
        int index = -1;
        for (User currentUser : cms.getListOfUsers()) {
            if (currentUser.getUserEmail().equals(email)) {
                index = cms.getListOfUsers().indexOf(currentUser);
                break;
            }
        }
        return index;
    }

    public JPanel getSignUp() {
        return SignUp;
    }

}
