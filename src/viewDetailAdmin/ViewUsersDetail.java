package viewDetailAdmin;

import entity.CMS;
import entity.User;
import sign.SignIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ViewUsersDetail extends JFrame {
    private JPanel adminMainPanel;
    private JSplitPane firSplit;
    private JPanel upPanel;
    private JPanel downPanel;
    private JSplitPane upSplit;
    private JPanel titlePanel;
    private JPanel otherPanel;
    private JComboBox roleComboBox;
    private JLabel titleLabel;
    private JLabel logText;
    private JLabel iconLabel;
    private JSplitPane downSplit;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton userButton;
    private JButton conferenceButton;
    private JButton paperButton;
    private JSplitPane contSplit;
    private JPanel searchPanel;
    private JPanel resultPanel;
    private JPanel contPanel;
    private JList userInfoList;
    private JSplitPane searchSplit;
    private JPanel selectPanel;
    private JPanel typePanel;
    private JComboBox selectComboBox;
    private JTextField searchTextField;
    private JButton searchButton;
    private JLabel subTitleLabel;
    private JTextArea userInfoTextArea;
    private JButton logOutButton;
    private User user;
    private CMS cms;
    private int searchWay;

    public ViewUsersDetail() {

        cms = new CMS();
        cms.readCurrentUser(cms.readFile("UserList.txt"));

        setTitle("Administration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        add(adminMainPanel);
        setVisible(true);
        searchWay = 0;
        String userInfo = "";

        for (User temp : cms.getListOfUsers()) {
            if (cms.getListOfUsers().indexOf(temp) != cms.getListOfUsers().size()-1) {
                userInfo = userInfo + "User Name: " + temp.getUserName() + "\nUser Email: " + temp.getUserEmail()
                        + "\nUser ID: " + temp.getUserId() + "\nUser Occupation: " + temp.getUserOccupation()
                        + "\nUser Interest Area: " + temp.getUserInterestArea() + "\nUser Qualification: " + temp.getUserQualification()
                        + "\nUser Employment: " + temp.getUserEmployerDetail() + " .\n";
            } else {
                userInfo = userInfo + "User Name: " + temp.getUserName() + "\nUser Email: " + temp.getUserEmail()
                        + "\nUser ID: " + temp.getUserId() + "\nUser Occupation: " + temp.getUserOccupation()
                        + "\nUser Interest Area: " + temp.getUserInterestArea() + "\nUser Qualification: " + temp.getUserQualification()
                        + "\nUser Employment: " + temp.getUserEmployerDetail() + " .";
            }

        }
        userInfoTextArea.setText(userInfo);



        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewPapersDetail viewPapersDetail = new ViewPapersDetail();
                viewPapersDetail.setVisible(true);
                dispose();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userInfoTextArea.setText("");
                if (searchWay > 0) {
                    returnUserByName(searchTextField.getText());
                } else if (searchWay < 0) {
                    int index = findUser(searchTextField.getText());
                    if (index < 0) {
                        userInfoTextArea.append("There is no this user");
                    }
                    else {
                        returnUser(findUser(searchTextField.getText()));
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Alert\nPlease select search way first.");
                }

            }
        });


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.setVisible(true);
                dispose();

            }
        });


        conferenceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Sorry\nThis function will be available in the next release ");
            }
        });


        selectComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (selectComboBox.getSelectedItem().equals("User Name")) {
                        searchWay = 1;
                    }
                    else if (selectComboBox.getSelectedItem().equals("User Id")) {
                        searchWay = -1;
                    }
                }
                else {
                    searchWay = -1;
                }
            }
        });

    }

    public int findUser(String userInfo) {
        int index = -1;
        for (User currentUser : cms.getListOfUsers()) {
            if (currentUser.getUserEmail().equals(userInfo) || String.valueOf(currentUser.getUserId()).equals(userInfo)) {
                index = cms.getListOfUsers().indexOf(currentUser);
                break;
            }
        }
        return index;
    }

    public void returnUser(int index) {
        User user = cms.getListOfUsers().get(index);
        String userInfo = "User Name: " + user.getUserName() + "\nUser Email: " + user.getUserEmail()
                + "\nUser ID: " + user.getUserId() + "\nUser Occupation: " + user.getUserOccupation()
                + "\nUser Interest Area: " + user.getUserInterestArea() + "\nUser Qualification: " + user.getUserQualification()
                + "\nUser Employment: " + user.getUserEmployerDetail() + " .";
        userInfoTextArea.setLineWrap(true);
        userInfoTextArea.setWrapStyleWord(true);
        userInfoTextArea.append(userInfo);
    }

    public void returnUserByName(String name) {
        int index = -1;
        for (User temp : cms.getListOfUsers()) {
            if (temp.getUserName().equals(name)) {
                String userInfo = "User Name: " + temp.getUserName() + "\nUser Email: " + temp.getUserEmail()
                        + "\nUser ID: " + temp.getUserId() + "\nUser Occupation: " + temp.getUserOccupation()
                        + "\nUser Interest Area: " + temp.getUserInterestArea() + "\nUser Qualification: " + temp.getUserQualification()
                        + "\nUser Employment: " + temp.getUserEmployerDetail() + " .";
                userInfoTextArea.setLineWrap(true);
                userInfoTextArea.setWrapStyleWord(true);
                userInfoTextArea.append(userInfo);
                index = 1;
                break;
            }
        }
        if (index < 0) {
            userInfoTextArea.append("There is no this user");
        }
    }


//    private ArrayList<String> readFile() {
//        ArrayList<String> fileContent = new ArrayList<>();
//        URL url = getClass().getResource("UserList.txt");
//        File filename = new File(url.getPath());
//        try {
//            FileReader file = new FileReader(filename);
//            Scanner scanFile = new Scanner(file);
//
//            while (scanFile.hasNextLine()) {
//                String oneLine = scanFile.nextLine();
//                fileContent.add(oneLine.trim());
//            }
//            file.close();
//        } catch (FileNotFoundException e) {
//            System.out.println("Problem found: " + e);
//        } catch (IOException e) {
//            System.out.println("Problem found: " + e);
//        } finally {
//            return fileContent;
//        }
//    }
//

    public static void main(String[] args) {
        new ViewUsersDetail();

    }


}
