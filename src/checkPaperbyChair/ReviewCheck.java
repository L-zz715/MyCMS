package checkPaperbyChair;

import conferenceForAuthor.ConferenceForAuthor;
import conferenceManageByChair.ConferenceMain;
import entity.CMS;
import entity.Paper;
import reviewPaper.ReviewPaper;
import sign.SignIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class ReviewCheck extends JFrame {

    private JPanel mainPanel;
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
    private JButton conferenceButton;
    private JButton paperButton;
    private JButton reviewButton;
    private JLabel reviewNameLabel;
    private JPanel contPanel;
    private JSplitPane reviewContSplit;
    private JPanel reviewContPanel;
    private JPanel reviewerPanel;
    private JButton backButton;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JTextArea textArea3;
    private JTextArea textArea4;
    private JTextArea reviewerTextArea1;
    private JTextArea reviewerTextArea2;
    private JTextArea reviewerTextArea3;
    private JTextArea reviewerTextArea4;
    private JButton rejectButton;
    private JButton acceptButton;
    private JButton logOutButton;
    private CMS cms;
    private String reviewerName1;
    private String reviewerCont;

    public ReviewCheck(String paperName) {
        setTitle("Conference");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        add(mainPanel);
        setVisible(true);

        cms = new CMS();
        cms.readCurrentPaper(cms.readFile("PaperList.txt"));
        reviewNameLabel.setText(paperName);



        for (Paper paper: cms.getListOfPapers()) {
            if (paper.getPaperName().equals(paperName)) {

                if (paper.getPaperStatus().equals("Rejected") || paper.getPaperStatus().equals("Accepted")) {
                    acceptButton.setVisible(false);
                    rejectButton.setVisible(false);
                }


                if (paper.getListOfReviews().size()>0) {
                    int length = paper.getListOfReviews().size();
                    switch (length) {
                        case 1:
                            reviewerTextArea1.setText("Reviewer Name: " + paper.getListOfReviews().get(0).getReviewerName());
                            textArea1.setText(paper.getListOfReviews().get(0).getReviewContent());
                            break;
                        case 2:
                            reviewerTextArea1.setText("Reviewer Name: " + paper.getListOfReviews().get(0).getReviewerName());
                            textArea1.setText(paper.getListOfReviews().get(0).getReviewContent());
                            reviewerTextArea2.setText("Reviewer Name: " + paper.getListOfReviews().get(1).getReviewerName());
                            textArea2.setText(paper.getListOfReviews().get(1).getReviewContent());
                            break;
                        case 3:
                            reviewerTextArea1.setText("Reviewer Name: " + paper.getListOfReviews().get(0).getReviewerName());
                            textArea1.setText(paper.getListOfReviews().get(0).getReviewContent());
                            reviewerTextArea2.setText("Reviewer Name: " + paper.getListOfReviews().get(1).getReviewerName());
                            textArea2.setText(paper.getListOfReviews().get(1).getReviewContent());
                            reviewerTextArea3.setText("Reviewer Name: " + paper.getListOfReviews().get(2).getReviewerName());
                            textArea3.setText(paper.getListOfReviews().get(2).getReviewContent());
                            break;
                        case 4:
                            reviewerTextArea1.setText("Reviewer Name: " + paper.getListOfReviews().get(0).getReviewerName());
                            textArea1.setText(paper.getListOfReviews().get(0).getReviewContent());
                            reviewerTextArea2.setText("Reviewer Name: " + paper.getListOfReviews().get(1).getReviewerName());
                            textArea2.setText(paper.getListOfReviews().get(1).getReviewContent());
                            reviewerTextArea3.setText("Reviewer Name: " + paper.getListOfReviews().get(2).getReviewerName());
                            textArea3.setText(paper.getListOfReviews().get(2).getReviewContent());
                            reviewerTextArea4.setText("Reviewer Name: " + paper.getListOfReviews().get(3).getReviewerName());
                            textArea4.setText(paper.getListOfReviews().get(3).getReviewContent());
                            break;
                        default:break;
                    }
                        reviewerTextArea1.setText(paper.getListOfReviews().get(0).getReviewerName());
                        textArea1.setText(paper.getListOfReviews().get(0).getReviewContent());


                }
                else {
                    textArea1.setText("there is no review.");
                }

                break;
            }

        }

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
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Reject Succeeded.");
                rejectStatus();

            }
        });
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //JOptionPane.showMessageDialog(null, "Accept Succeeded.");
                acceptStatus();
            }
        });

    }

    public void rejectStatus(){
        int result = JOptionPane.showConfirmDialog(null, "Are you sure rejecting this paper?", "Confirm", YES_NO_OPTION);
        System.out.println(result);
        if (result == 0) {
            for (Paper currentPaper : cms.getListOfPapers()) {
                if (currentPaper.getPaperName().equals(reviewNameLabel.getText())) {
                    currentPaper.setPaperStatus("Rejected");
                    cms.writePaperFile("PaperList.txt");
                    // System.out.println(currentPaper.getPaperStatus());
                }
            }
            JOptionPane.showMessageDialog(null, "Reject Succeeded.");
        } else {
            ///
        }
    }

    public void acceptStatus(){
        int result = JOptionPane.showConfirmDialog(null, "Are you sure accepting this paper?", "Confirm", YES_NO_OPTION);
        System.out.println(result);
        if (result == 0) {
            for (Paper currentPaper : cms.getListOfPapers()){
                if(currentPaper.getPaperName().equals(reviewNameLabel.getText())){
                    currentPaper.setPaperStatus("Accepted");
                    cms.writePaperFile("PaperList.txt");
                    // System.out.println(currentPaper.getPaperStatus());
                }
            }
            JOptionPane.showMessageDialog(null, "Accept Succeeded.");

        } else {
            ///
        }

    }

}
