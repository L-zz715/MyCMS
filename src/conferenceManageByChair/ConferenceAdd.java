package conferenceManageByChair;

import conferenceForAuthor.ConferenceForAuthor;
import entity.Conference;
import reviewPaper.ReviewPaper;
import sign.SignIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class ConferenceAdd extends JFrame {
    private JPanel ConfEditPanel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JSplitPane firSplit;
    private JSplitPane upSplit;
    private JPanel titlePanel;
    private JPanel otherPanel;
    private JComboBox roleComboBox;
    private JLabel logText;
    private JLabel iconLabel;
    private JLabel titleLabel;
    private JSplitPane downSplit;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton conferenceButton;
    private JButton reviewButton;
    private JButton paperButton;
    private JLabel subTitle;
    private JTextField confNameTextField;
    private JTextField confTopicTextField;
    private JTextField confTitleTextField;
    private JTextField confChairTextField;
    private JTextField confDateTextField;
    private JTextField paperDueTextField;
    private JButton cancelButton;
    private JButton confirmButton;
    private JButton backButton;
    private JLabel confInfoTitleLabel;
    private JLabel COnfNameLabel;
    private JLabel confTitleLabel;
    private JLabel confDateLabel;
    private JLabel confTopicLabel;
    private JLabel confChairLabel;
    private JLabel confPaperDueLabel;
    private JButton logOutButton;
    private ConferenceDB conferenceDB;

    public ConferenceAdd() throws IOException {
        conferenceDB = new ConferenceDB();
        setTitle("Conference");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        add(ConfEditPanel);
        setVisible(true);
        paperButton.setEnabled(false);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConferenceMain conferenceMain = null;
                try {
                    conferenceMain = new ConferenceMain();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                conferenceMain.setVisible(true);
                dispose();
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


        roleComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (roleComboBox.getSelectedItem().equals("Reviewer")) {
                        ReviewPaper reviewPaper = new ReviewPaper();
                        reviewPaper.setVisible(true);
                        dispose();
                    }
                    else if (roleComboBox.getSelectedItem().equals("Author")) {
                        ConferenceForAuthor conferenceForAuthor = null;
                        try {
                            conferenceForAuthor = new ConferenceForAuthor();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        conferenceForAuthor.setVisible(true);
                        dispose();
                    }
                }
            }
        });


        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int result = JOptionPane.showConfirmDialog(null, "Are you sure adding this conference now?", "Confirm", YES_NO_OPTION);
                System.out.println(result);
                if (result == 0) {
                    try {
                        addConference();
                        dispose();
                        new ConferenceMain().setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
                else {
                    System.out.println("test");
                }

            }
        });
    }

    private void addConference() throws IOException {
        String name = confNameTextField.getText();
        String title = confTitleTextField.getText();
        String topic = confTopicTextField.getText();
        String chairs = confChairTextField.getText();
        String date = confDateTextField.getText();
        String dueDate = paperDueTextField.getText();
        Conference conference = new Conference(name, title, topic, chairs, date, dueDate);
        conferenceDB.addConference(conference);
    }

}
