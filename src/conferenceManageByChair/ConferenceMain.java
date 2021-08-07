package conferenceManageByChair;

import checkPaperbyChair.PapersForConference;
import conferenceForAuthor.ConferenceForAuthor;
import entity.Conference;
import reviewPaper.ReviewPaper;
import reviewerAssignment.ReviewAssign;
import sign.SignIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class ConferenceMain extends JFrame {
    private JPanel ConferenceMainPanel;
    private JSplitPane firSplit;
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel otherPanel;
    private JPanel titlePanel;
    private JSplitPane upSplit;
    private JComboBox roleComboBox;
    private JLabel titleLabel;
    private JLabel logText;
    private JLabel iconLabel;
    private JSplitPane downSplit;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton confButton;
    private JButton paperButton;
    private JButton reviewButton;
    private JPanel contPanel;
    private JLabel subTitleLabel;
    private JSplitPane contSplit;
    private JPanel confInfoPanel;
    private JPanel functionPanel;
    private JLabel navLabel;
    private JSplitPane confInfoSplit;
    private JSplitPane funcSplit;
    private JPanel confNamePanel;
    private JPanel confContPanel;
    private JPanel funcNamePanel;
    private JPanel funcContPanel;
    private JLabel confNameLabel;
    private JLabel funcLabel;
    private JList list1;
    private JLabel editLabel;
    private JLabel paperCheckLabel;
    private JLabel mailLabel;
    private JLabel plusIconLabel;
    private JButton logOutButton;
    DefaultListModel<Conference> model = new DefaultListModel<>();
    ArrayList<Conference> conferenceArrayList;


    public ConferenceMain() throws IOException {

        setTitle("Conference");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        setResizable(false);
        paperButton.setEnabled(false);

        add(ConferenceMainPanel);
        ConferenceDB conferenceDB = new ConferenceDB();
        conferenceArrayList = conferenceDB.getConferenceList();

        for (Conference conference : conferenceArrayList) {
            String[] chairs = conference.getConferenceChairs().split(",");
            for (String chairName : chairs) {
                if (chairName.equals(SignIn.signUser.getUserName())) {
                    //System.out.println(conference);
                    model.addElement(conference);
                    break;
                }
            }
        }

        list1.setModel(model);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setVisible(true);

        editLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ConferenceEdit conferenceEdit = null;
                try {
                    int index = list1.getSelectedIndex();
                    if (index>-1){
                        conferenceEdit = new ConferenceEdit(index);
                    }
                    else {
                        Frame a = new Frame();
                        a.setTitle("Warning");
                        JOptionPane.showMessageDialog(a, "Please select a conference before editing.");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                conferenceEdit.setVisible(true);
                dispose();
            }
        });

        plusIconLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ConferenceAdd conferenceAdd = null;
                try {
                    conferenceAdd = new ConferenceAdd();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                conferenceAdd.setVisible(true);
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
        paperCheckLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                PapersForConference papersforconference = null;
                try {
                    int index = list1.getSelectedIndex();
                    if (index>-1) {
                        papersforconference = new PapersForConference(index);
                        papersforconference.setVisible(true);
                        dispose();
                    }
                    else {
                        Frame a = new Frame();
                        a.setTitle("Warning");
                        JOptionPane.showMessageDialog(a, "Please select a conference before editing.");
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        });
        reviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReviewAssign reviewAssign = null;
                try {
                    reviewAssign = new ReviewAssign();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                reviewAssign.setVisible(true);
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

        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Alert\nYou may not have permission to use this section, please change your role!");
            }
        });
        mailLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                JOptionPane.showMessageDialog(null,
                        "Sorry\nThis function (check all your submitted papers) will be available in the next release.");
            }
        });
    }


    public static void main(String[] args) throws IOException {
        new ConferenceMain();
    }

}
