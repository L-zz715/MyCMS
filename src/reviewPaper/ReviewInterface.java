package reviewPaper;

import conferenceForAuthor.ConferenceForAuthor;
import conferenceManageByChair.ConferenceMain;
import entity.CMS;
import entity.Paper;
import entity.Review;
import entity.User;
import sign.SignIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class ReviewInterface extends JFrame {
    private JPanel ReviewInterfacePanel;
    private JSplitPane firSplit;
    private JPanel upPanel;
    private JPanel downPanel;
    private JSplitPane upSplit;
    private JSplitPane downSplit;
    private JPanel titlePanel;
    private JPanel otherPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JComboBox roleComboBox;
    private JLabel titleLabel;
    private JLabel logTextLabel;
    private JLabel iconLabel;
    private JButton conferenceButton;
    private JButton paperButton;
    private JButton reviewButton;
    public JLabel subTitleLabel;
    private JPanel contPanel;
    public JTextArea paperContentTextArea;
    private JLabel reviewTitleLabel;
    private JButton cancelButton;
    private JButton submitButton;
    private JPanel reviewContPanel;
    private JTextArea reviewContentTextArea;
    private JButton logOutButton;
    private ArrayList<Paper> paperArrayList;
    private ArrayList<User> userArrayList;
    private CMS cms;
    ArrayList<Review> reviewArrayList;
    private Paper paper;


    public ReviewInterface() {
        setTitle("Review");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        add(ReviewInterfacePanel);
        setVisible(true);
        cms = new CMS();
        cms.readCurrentPaper(cms.readFile("PaperList.txt"));
//        paper = new Paper();
//        paper.readCurrentReview(cms.readFile("Review.txt"));
        paperArrayList = new ArrayList<>();
        paperArrayList = cms.getListOfPapers();
        reviewArrayList = new ArrayList<>();
//        reviewArrayList = paper.getListOfReviews();

        paperContentTextArea.setEnabled(false);
        reviewContentTextArea.setLineWrap(true);
        reviewContentTextArea.setWrapStyleWord(true);
        reviewContentTextArea.setEnabled(true);


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


        submitButton.addActionListener((e) -> {
            onButtonSubmit();
        });

        cancelButton.addActionListener((e) -> {
            onButtonCancel();
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

    public void onButtonSubmit() {
        int isFilled = 1;
        String content = reviewContentTextArea.getText();
        if (content.trim().equals("") || content.equals("null")) {
            Object[] options = {"OK", "CANCEL"};
            JOptionPane.showOptionDialog(null, "Please enter your review", "Warning",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
        } else {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure submit this review?", "Confirm", YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);
            System.out.println(result);
            if (result == 0) {
                Object[] option = {"OK"};
                JOptionPane.showOptionDialog(this, "Add review successfully", "Message",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
                for (Paper temp : paperArrayList) {
                    String name = SignIn.signUser.getUserName();
                    if (temp.getPaperName().equals(subTitleLabel.getText())) {
                        for (Review tempReview : temp.getListOfReviews()) {
                            if (tempReview.getReviewerName().equals(name)) {
                                tempReview.setReviewContent(content);
                            }
                            if (tempReview.getReviewContent().isEmpty() ||
                                    tempReview.getReviewContent().equals("") ||
                                    tempReview.getReviewContent().equals("null")) {
                                isFilled = -1;
                            }
                        }

                        if (isFilled > 0) {
                            temp.setPaperStatus("Reviewed");
                        }
                        break;
                        //Review review = new Review(name, content);

                        //temp.saveNewReview(review);
                        //temp.setPaperStatus("Reviewed");
//                    reviewArrayList.add(review);
                    }

                }
                cms.writePaper("PaperList.txt",paperArrayList);
//            paper.writeReview("Review.txt", reviewArrayList);
                reviewContentTextArea.setEnabled(false);
                submitButton.setEnabled(false);
            }


        }
    }

    public void onButtonCancel() {
        ReviewPaper reviewPaper = new ReviewPaper();
        reviewPaper.setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new ReviewInterface();
    }

}
