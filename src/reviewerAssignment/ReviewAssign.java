package reviewerAssignment;


import conferenceForAuthor.ConferenceForAuthor;
import conferenceManageByChair.ConferenceDB;
import conferenceManageByChair.ConferenceMain;
import entity.*;
import reviewPaper.ReviewPaper;
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


public class ReviewAssign extends JFrame {
    private JPanel mainPanel;
    private JSplitPane firSplit;
    private JPanel upPanel;
    private JPanel downPanel;
    private JSplitPane upSplit;
    private JPanel titlePanel;
    private JPanel otherPanel;
    private JLabel titleLabel;
    private JLabel logText;
    private JComboBox roleBox;
    private JLabel iconLabel;
    private JSplitPane secSplit;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton confButton;
    private JButton paperButton;
    private JButton ReviewButton;
    private JLabel ReviewTitle;
    private JPanel contentPanel;
    private JLabel paperTopicLabel;
    private JSplitPane thrSplit;
    private JPanel secLeftPanel;
    private JPanel secRightPanel;
    private JButton cancelButton;
    private JButton confirmButton;
    private JComboBox topicBox;
    private JSplitPane forSplit;
    private JSplitPane fifSplit;
    private JPanel paperNameP;
    private JPanel ReviewerNameP;
    private JPanel paperConP;
    private JPanel reviewerConP;
    private JLabel reviewerNameLabel;
    private JLabel paperNameLabel;
    private final JTable paperTable = new JTable();
    private JButton logOutButton;
    private JList paperList;
    private JList reviewList;
//    private final DefaultTableModel tableModel;
    ArrayList<String> paperName, reviewerName;
    ArrayList<Paper> paperArrayList;
    ArrayList<User> userArrayList;
    ArrayList<Review> reviewArrayList;
    private String topic;
    DefaultListModel<String> paperModel = new DefaultListModel<>();
    DefaultListModel<String> reviewerNameModel = new DefaultListModel<>();
    private String paperSelect;
    private String reviewSelect;
    private ConferenceDB allConferences;
    private ArrayList<Conference> attendConferences;

    public ReviewAssign() throws IOException{


        setTitle("Review");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        add(mainPanel);
        setVisible(true);
        paperButton.setEnabled(false);

//        tableModel = new DefaultTableModel();
//        Object[] paper = {"Name 1", "3/4"};
//        String[] name = {"dddname", "fffname"};

//        tableModel.addRow(paper);
//        paperTable.setModel(tableModel);

        CMS cms = new CMS();
        Paper paper = new Paper();
        allConferences = new ConferenceDB();
        attendConferences = new ArrayList<>();

        cms.readCurrentPaper(cms.readFile("PaperList.txt"));
        cms.readCurrentUser(cms.readFile("UserList.txt"));
        allConferences.getConferenceList();
//        paper.readCurrentReview(cms.readFile("Review.txt"));
        paperName = new ArrayList<>();
        reviewerName = new ArrayList<>();

        paperArrayList = new ArrayList<>();
        paperArrayList = cms.getListOfPapers();


        userArrayList = new ArrayList<>();
        userArrayList = cms.getListOfUsers();
        //reviewArrayList = new ArrayList<>();
        //reviewArrayList = paper.getListOfReviews();
        //topic = topicBox.getSelectedItem().toString();

        System.out.println(SignIn.signUser.getUserName());
        for (Conference temp : allConferences.getConferenceList()) {
            String[] tempChairs = temp.getConferenceChairs().split(",");
            for (String chairsName : tempChairs) {
                System.out.println(chairsName);
                if (chairsName.equals(SignIn.signUser.getUserName())) {
                    attendConferences.add(temp);
                    break;
                }
            }
        }


        for (Paper temp : paperArrayList) {
            for (Conference tempConfs : attendConferences) {
                if (temp.getListOfReviews().size() < 4)  {
                    if (temp.getConferenceName().equals(tempConfs.getConferenceName()) &&
                            (temp.getPaperStatus().equals("Submitted") || temp.getPaperStatus().equals("Unreviewed"))) {
                        paperName.add(temp.getPaperName());
                    }
                }

            }

        }


        for (String temp : paperName) {
            paperModel.addElement(temp);
        }

        paperList.setModel(paperModel);
        paperList.setVisibleRowCount(10);



       /* for (Paper temp : paperArrayList) {
            if (temp.getPaperTopicArea().equals(topic)) {
                paperName.add(temp.getPaperName());
            }
        }*/




        for (User temp : userArrayList) {
            if (!temp.getUserName().equals(SignIn.signUser.getUserName())) {
                reviewerName.add(temp.getUserName());
            }
        }

        for (String temp: reviewerName) {
            reviewerNameModel.addElement(temp);
        }

        reviewList.setModel(reviewerNameModel);
        reviewList.setVisibleRowCount(10);

        DefaultListCellRenderer renderer4 = new DefaultListCellRenderer();
        renderer4.setHorizontalAlignment(SwingConstants.CENTER);
        reviewList.setCellRenderer(renderer4);


        topicBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                //DefaultListModel tempReviewModel = new DefaultListModel();
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    topic = topicBox.getSelectedItem().toString();
                    System.out.println(topic);

                    if (!topic.equals("") && topic != null) {
                        reviewerNameModel.removeAllElements();
                        paperModel.removeAllElements();

                        for (User tempUser : userArrayList) {
                            for (String temp : reviewerName) {
                                if ( temp.equals(tempUser.getUserName()) && tempUser.getUserInterestArea().equals(topic)) {
                                    reviewerNameModel.addElement(temp);
                                    //tempReviewModel.addElement(temp);

                                }
                            }
                        }

                        for (Paper tempPaper : paperArrayList) {
                            for (String temp : paperName) {
                                if (temp.equals(tempPaper.getPaperName()) && tempPaper.getPaperTopicArea().equals(topic)) {
                                    paperModel.addElement(temp);
                                }
                            }
                        }

                    }
                    else {

                    }


                }
                else {

                }

                /*if (reviewerNameModel.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "Sorry\nThere is no reviewer.");
                }*/
                //reviewList.removeAll();
                //reviewList.setModel(tempReviewModel);


            }
        });



        roleBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (roleBox.getSelectedItem().equals("Reviewer")) {
                        ReviewPaper reviewPaper = new ReviewPaper();
                        reviewPaper.setVisible(true);
                        dispose();
                    }
                    else if (roleBox.getSelectedItem().equals("Author")) {
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



        paperList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!paperList.getValueIsAdjusting()) {
                    paperSelect = paperList.getModel().getElementAt(paperList.getSelectedIndex()).toString();
                }
            }
        });


        reviewList.addListSelectionListener(new ListSelectionListener() {
           @Override
           public void valueChanged(ListSelectionEvent e) {
               if (!reviewList.getValueIsAdjusting()) {
                   reviewSelect = reviewList.getModel().getElementAt(reviewList.getSelectedIndex()).toString();
               }
           }
       });

        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int hasAssigned = -1;
                if (paperSelect != null && reviewSelect != null) {
                    for (Paper temp : paperArrayList) {
                        if (temp.getPaperName().equals(paperSelect) && !temp.getListOfReviews().isEmpty()) {
                            for (Review tempReview : temp.getListOfReviews()) {
                                if (tempReview.getReviewerName().equals(reviewSelect)) {
                                    hasAssigned = 1;
                                    break;
                                }
                            }

                            if (hasAssigned > 0) {
                                JOptionPane.showMessageDialog(null,
                                        "Sorry\nThe reviewer has been assigned this paper.");
                            }
                            else if (hasAssigned < 0 && !reviewSelect.equals(temp.getPaperAuthorName())){
                                Review review = new Review(reviewSelect);
                                temp.saveNewReview(review);

                                cms.writePaperFile("PaperList.txt");
                                JOptionPane.showMessageDialog(null,
                                        "Congratulation\nThe reviewer has been assigned this paper.");
                            }
                            else if (reviewSelect.equals(temp.getPaperAuthorName())) {
                                JOptionPane.showMessageDialog(null,
                                        "Sorry\nYou cannot assign this reviewer.");
                            }

                        }
                        else if (temp.getPaperName().equals(paperSelect) && temp.getListOfReviews().isEmpty()) {
                            if (!reviewSelect.equals(temp.getPaperAuthorName())) {
                                Review review = new Review(reviewSelect,"null");
                                temp.saveNewReview(review);
                                temp.setPaperStatus("Unreviewed");
                                //temp.setPaperStatus("Unreviewed");
                                cms.writePaperFile("PaperList.txt");
                                JOptionPane.showMessageDialog(null,
                                        "Congratulation\nThe reviewer has been assigned this paper.");
                            }
                            else {
                                JOptionPane.showMessageDialog(null,
                                        "Sorry\nYou cannot assign this reviewer.");
                            }
                        }

                        if (temp.getListOfReviews().size() == 4) {
                            temp.setPaperStatus("Unreviewed");
                            dispose();
                            ReviewAssign reviewAssign = null;
                            try {
                                reviewAssign = new ReviewAssign();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            reviewAssign.setVisible(true);

                        }


                    }


                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "Sorry\nPlease select paper and reviewer first.");
                }



                /*if (paperSelect != null && reviewSelect != null) {
                    for (Paper temp : paperArrayList) {
                        if (temp.getPaperName().equals(paperSelect)) {
                            if (temp.getListOfReviews().size()<5 && temp.getListOfReviews().size() != 0) {
                                for (Review item : temp.getListOfReviews()) {
                                    if (!item.getReviewerName().equals(reviewSelect)){
                                        Review review = new Review(reviewSelect, "");
                                        temp.saveNewReview(review);
//                                        reviewArrayList.add(review);
                                    }
                                    else {
                                        Object[] option = {"OK"};
                                        JOptionPane.showOptionDialog(null, "This man has already been assigned", "Message",
                                                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                                    }
                                }
                            }
                            temp.setPaperStatus("Unreviewed");
                            Object[] option = {"OK"};
                            JOptionPane.showOptionDialog(null, "Assign review successfully", "Message",
                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                        }
                    }
                    cms.writePaper("PaperList.txt",paperArrayList);
//                    paper.writeReview("Review.txt", reviewArrayList);
                }
                else {
                    Object[] options = {"OK", "CANCEL"};
                    JOptionPane.showOptionDialog(null, "Please select paper and reviewer", "Warning",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                }*/
            }
        });


//        confirmButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showConfirmDialog(null, "Are you sure assign papers and reviewers like this?", "Confirm", YES_NO_OPTION);
//                int result = JOptionPane.showConfirmDialog(null, "Are you sure assign papers and reviewers like this?", "Confirm", YES_NO_OPTION);
//                if (result == 0) {
//                    Submission submission = null;
//                    try {
//                        submission = new Submission();
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//                    submission.setVisible(true);
//                    dispose();
//                }
//            }
//        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.setVisible(true);
                dispose();
            }
        });
        confButton.addActionListener(new ActionListener() {
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
        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Alert\nYou may not have permission to use this section, please change your role!");
            }
        });
    }

    public static void main(String[] args) throws IOException{
        new ReviewAssign() ;
    }


}