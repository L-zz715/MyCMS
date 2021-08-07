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
import java.util.ArrayList;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class ConferenceEdit extends JFrame {
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
    public JLabel subTitle;
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
    private JButton editButton;
    private JLabel COnfNameLabel;
    private JLabel confTitleLabel;
    private JLabel confDateLabel;
    private JLabel confTopicLabel;
    private JLabel confChairLabel;
    private JLabel confPaperDueLabel;
    private JButton logOutButton;
    private JPanel contPanel;
    private ConferenceDB conferenceDB;
    private ArrayList<Conference> conferenceArrayList;
    private ArrayList<Conference> attendconferences;

    public ConferenceEdit(int index) throws IOException {
        conferenceDB = new ConferenceDB();
        setTitle("Conference");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        add(ConfEditPanel);
        setVisible(true);
        paperButton.setEnabled(false);
        attendconferences = new ArrayList<>();



        conferenceArrayList = conferenceDB.getConferenceList();

        for (Conference conference : conferenceArrayList) {
            String[] chairs = conference.getConferenceChairs().split(",");
            for (String chairName : chairs) {
                if (chairName.equals(SignIn.signUser.getUserName())) {
                    //System.out.println(conference);
                    attendconferences.add(conference);
                    break;
                }
            }
        }



        Conference conference = attendconferences.get(index);
        confNameTextField.setText(conference.getConferenceName());
        confTitleTextField.setText(conference.getConferenceTitle());
        confTopicTextField.setText(conference.getConferenceTopic());
        confChairTextField.setText(conference.getConferenceChairs());
        confDateTextField.setText(conference.getConferenceDate());
        paperDueTextField.setText(conference.getPaperDueDate());

        confNameTextField.setEnabled(false);
        confTitleTextField.setEnabled(false);
        confTopicTextField.setEnabled(false);
        confChairTextField.setEnabled(false);
        confDateTextField.setEnabled(false);
        paperDueTextField.setEnabled(false);
        cancelButton.setVisible(false);
        confirmButton.setVisible(false);


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConferenceMain conferenceMain = null;
                try {
                    conferenceMain = new ConferenceMain();
                } catch (Exception exception) {
                    exception.printStackTrace();
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
                conferenceDB.getConferenceList().get(index).setConferenceName(confNameTextField.getText());
                conferenceDB.getConferenceList().get(index).setConferenceTitle(confTitleTextField.getText());
                conferenceDB.getConferenceList().get(index).setConferenceTopic(confTopicTextField.getText());
                conferenceDB.getConferenceList().get(index).setConferenceChairs(confChairTextField.getText());
                conferenceDB.getConferenceList().get(index).setConferenceDate(confDateTextField.getText());
                conferenceDB.getConferenceList().get(index).setPaperDueDate(paperDueTextField.getText());

                int result = JOptionPane.showConfirmDialog(null, "Are you sure saving edit now?", "Confirm", YES_NO_OPTION);
                System.out.println(result);
                if (result == 0) {
                    confNameTextField.setEnabled(false);
                    confTitleTextField.setEnabled(false);
                    confTopicTextField.setEnabled(false);
                    confChairTextField.setEnabled(false);
                    confDateTextField.setEnabled(false);
                    paperDueTextField.setEnabled(false);
                    cancelButton.setVisible(false);
                    confirmButton.setVisible(false);
                    
                    try {
                        conferenceDB.clearDatabase();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    try {
                        conferenceDB.updateDB(conferenceArrayList);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    /*dispose();
                    try {
                        new ConferenceMain().setVisible(true);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }*/

                }
                else {
                    //no action
                }

            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confNameTextField.setEnabled(true);
                confTitleTextField.setEnabled(true);
                confTopicTextField.setEnabled(true);
                confChairTextField.setEnabled(true);
                confDateTextField.setEnabled(true);
                paperDueTextField.setEnabled(true);
                cancelButton.setVisible(true);
                confirmButton.setVisible(true);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure cancel edit?", "Cancel Confirm", YES_NO_OPTION);
                System.out.println(result);
                if (result == 0) {
                    conferenceArrayList = conferenceDB.getConferenceList();
                    Conference conference = conferenceArrayList.get(index);
                    confNameTextField.setText(conference.getConferenceName());
                    confTitleTextField.setText(conference.getConferenceTitle());
                    confTopicTextField.setText(conference.getConferenceTopic());
                    confChairTextField.setText(conference.getConferenceChairs());
                    confDateTextField.setText(conference.getConferenceDate());
                    paperDueTextField.setText(conference.getPaperDueDate());

                    confNameTextField.setEnabled(false);
                    confTitleTextField.setEnabled(false);
                    confTopicTextField.setEnabled(false);
                    confChairTextField.setEnabled(false);
                    confDateTextField.setEnabled(false);
                    paperDueTextField.setEnabled(false);
                    cancelButton.setVisible(false);
                    confirmButton.setVisible(false);

                } else {

                    //JOptionPane.showMessageDialog(null,
                    //        "Cancel\nYour submission has been cancelled, please choose other files to submit.");
                }
            }
        });
    }


}
