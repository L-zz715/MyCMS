package reviewPaper;

import conferenceForAuthor.ConferenceForAuthor;
import conferenceManageByChair.ConferenceMain;
import entity.CMS;
import entity.Conference;
import entity.Paper;
import entity.Review;
import sign.SignIn;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

public class ReviewPaper extends JFrame {
    private JPanel reviewPaperPanel;
    private JPanel upPanel;
    private JPanel downPanel;
    private JSplitPane downSplit;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JSplitPane firSplit;
    private JSplitPane upSplit;
    private JPanel titlePanel;
    private JPanel otherPanel;
    private JComboBox roleComboBox;
    private JLabel titleLabel;
    private JLabel logLabel;
    private JLabel iconLabel;
    private JButton conferenceButton;
    private JButton paperButton;
    private JButton reviewButton;
    private JPanel contPanel;
    private JLabel subTitleLabel;
    private JLabel areaLabel;
    private JLabel taskLabel;
    private JSplitPane contSplit;
    private JPanel contUpPanel;
    private JPanel contDownPanel;
    private JSplitPane contUpSplit;
    private JLabel paperNameLabel;
    private JPanel paperNamePanel;
    private JLabel statusLabel;
    private JSplitPane contDownSplit;
    private JPanel otherTitlePanel;
    private JPanel paperContDownPanel;
    private JPanel otherContPanel;
    private JList paperList;
    private JList deadlineList;
    private JList statusList;
    private JButton logOutButton;
    ArrayList<String> paperName, deadline, paperStatus;
    DefaultListModel<String> nameModel = new DefaultListModel<>();
    DefaultListModel<String> deadlineModel = new DefaultListModel<>();
    DefaultListModel<String> statusModel = new DefaultListModel<>();
    ArrayList<Paper> paperArrayList;
    ArrayList<Conference> allConferenceArrayList;
    ArrayList<Conference> chairsConferenceArrayList;


    public ReviewPaper() {
        setTitle("Review");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        add(reviewPaperPanel);
        setVisible(true);
        setResizable(false);

        conferenceButton.setEnabled(false);
        paperButton.setEnabled(false);

        CMS cms = new CMS();
        allConferenceArrayList = new ArrayList<>();
        chairsConferenceArrayList = new ArrayList<>();
        cms.readCurrentPaper(cms.readFile("PaperList.txt"));



        paperArrayList = new ArrayList<>();
        paperArrayList = cms.getListOfPapers();
        paperName = new ArrayList<>();
        //deadline = new ArrayList<>();
        paperStatus = new ArrayList<>();

        paperName.add("There is no assigned paper.");

        for (Paper temp : paperArrayList) {
            if (!temp.getListOfReviews().isEmpty()) {
                for (Review tempReview : temp.getListOfReviews()) {
                    if (tempReview.getReviewerName().equals(SignIn.signUser.getUserName())) {
                        paperName.clear();

                        paperName.add(temp.getPaperName());

                        if (tempReview.getReviewContent().isEmpty() ||
                                tempReview.getReviewContent().equals("null") ||
                                tempReview.getReviewContent().equals("")) {
                            paperStatus.add("Unreviewed");
                        }
                        else {
                            paperStatus.add("Reviewed");
                        }
                    }

                }
            }

        }


        for (String temp : paperName) {
            nameModel.addElement(temp);
        }
        /*for (String temp : deadline) {
            deadlineModel.addElement(temp);
        }*/
        for (String temp : paperStatus) {
            statusModel.addElement(temp);
        }

        paperList.setModel(nameModel);
        paperList.setVisibleRowCount(10);
       /* deadlineList.setModel(deadlineModel);
        deadlineList.setVisibleRowCount(10);*/
        statusList.setModel(statusModel);
        statusList.setVisibleRowCount(10);

        DefaultListCellRenderer renderer2 = new DefaultListCellRenderer();
        renderer2.setHorizontalAlignment(SwingConstants.CENTER);
        statusList.setCellRenderer(renderer2);

        areaLabel.setText("My area: " + SignIn.signUser.getUserName());


        statusList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!statusList.getValueIsAdjusting()) {
                    String select = statusList.getModel().getElementAt(statusList.getSelectedIndex()).toString();
                    if (select.equals("Unreviewed")) {
                        String title = paperName.get(statusList.getSelectedIndex());
                        ReviewInterface reviewInterface = new ReviewInterface();
                        reviewInterface.subTitleLabel.setText(title);
                        reviewInterface.setVisible(true);
                        dispose();
                    }
                    else {
                        Object[] option = {"OK"};
                        JOptionPane.showOptionDialog(null, "Has already add review", "Message",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                    }
                }
            }
        });


        roleComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (roleComboBox.getSelectedItem().equals("Chair")) {
                        ConferenceMain conferenceMain = null;
                        try {
                            conferenceMain = new ConferenceMain();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        conferenceMain.setVisible(true);
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


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.setVisible(true);
                dispose();
            }
        });



    }

//    public void getPaper() {
//        CMS cms = new CMS();
//        ArrayList<Paper> paper = cms.getListOfPapers();
//        for (Paper temp : paper) {
//            paperName.add(temp.getPaperName());
////            deadline.add(temp.);
//            paperStatus.add(temp.getPaperStatus());
//        }
//    }


    public static void main(String[] args) {
        new ReviewPaper();
    }

}
