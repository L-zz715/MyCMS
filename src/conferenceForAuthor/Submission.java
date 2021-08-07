package conferenceForAuthor;

import conferenceManageByChair.ConferenceDB;
import conferenceManageByChair.ConferenceMain;
import entity.CMS;
import entity.Conference;
import entity.Paper;
import reviewPaper.ReviewPaper;
import sign.SignIn;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static javax.swing.JOptionPane.YES_NO_OPTION;

public class Submission extends JFrame {
    private JPanel Submission;
    private JButton confButton;
    private JButton paperButton;
    private JButton reviewButton;
    private JPanel leftPanel;
    private JPanel upPanel;
    private JSplitPane firSplit;
    private JSplitPane secSplit;
    private JSplitPane thirSplit;
    private JPanel titleP;
    private JPanel otherP;
    private JLabel titleLabel;
    private JComboBox roleBox;
    private JLabel profileLabel;
    private JLabel logText;
    private JPanel subContPanel;
    private JLabel subTitle;
    private JPanel downPanel;
    private JPanel rightPanel;
    private JSplitPane fourSplit;
    private JPanel paperInfoPanel;
    private JPanel dropPanel;
    private JButton cancelButton;
    private JButton submitButton;
    private JLabel fileFoLabel;
    private JLabel titleAceLabel;
    private JLabel file2Label;
    private JSplitPane inforSplit1;
    private JSplitPane inforSplit2;
    private JTextField paperNameTextField;
    private JPanel paperNamePanel;
    private JPanel infoPanel2;
    private JPanel infoPanel3;
    private JLabel paperNameLabel;
    private JPanel infoPanel4;
    private JSplitPane inforSplit3;
    private JPanel topicPanel;
    private JLabel topicLabel;
    private JComboBox topicBox;
    private JTextField topictextField;
    private JPanel topicContPanel;
    private JButton browseButton;
    private JLabel showMessageLabel;
    private JLabel showPaperNameLabel;
    private JButton logOutButton;
    private JButton backButton;
    private Paper tempPaper;
    private ConferenceDB conferences;
    private String newArea;
    private boolean isSubmit;
    private String paperName;
    private String filepath;
    private File tempFile;
    private ArrayList<Conference> attendConferences;
    //private int confIndex = -1;
    private String conferenceName;
    private final CMS cms;

    public Submission(int conferenceIndex) throws IOException {
        setTitle("Submission");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        add(Submission);
        setVisible(true);
        setResizable(false);
        reviewButton.setEnabled(false);

        tempPaper = null;
        filepath = "";
        paperName = "";
        isSubmit = false;

        conferences = new ConferenceDB();
        attendConferences = new ArrayList<>();
        for (Conference temp : conferences.getConferenceList()) {
            int asChair = -1;
            String[] tempChairs = temp.getConferenceChairs().split(",");
            for (String tempChair : tempChairs)  {
                if (tempChair.equals(SignIn.signUser.getUserName())) {
                    asChair = 1;
                    break;
                }
            }
            if (asChair < 0) {
                attendConferences.add(temp);
            }
        }

        conferenceName = attendConferences.get(conferenceIndex).getConferenceName();
        cms = new CMS();
        //confIndex = Integer.parseInt(cms.readFile("tempIndex.txt").get(0));
        cms.readCurrentPaper(cms.readFile("PaperList.txt"));

        newArea = "";

        backButton.setVisible(false);

        for (Paper temp : cms.getListOfPapers()) {

            if (temp.getConferenceName().equals(conferenceName) && temp.getPaperAuthorName().equals(SignIn.signUser.getUserName())) {
                isSubmit = true;
                showPaperNameLabel.setText("Your submitted paper: " + temp.getPaperName());
                browseButton.setEnabled(false);
                cancelButton.setVisible(false);
                submitButton.setVisible(false);
                backButton.setVisible(true);

                break;
            }
        }


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConferenceForAuthor authorConference = null;
                try {
                    authorConference = new ConferenceForAuthor();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                authorConference.setVisible(true);
                dispose();
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
                    else if (roleBox.getSelectedItem().equals("Chair")) {
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
            }
        });


        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                String confPaperDueDate = attendConferences.get(conferenceIndex).getPaperDueDate();

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
                String submitDate = df.format(System.currentTimeMillis());
                System.out.println(df.format(System.currentTimeMillis()));
                String authorName = SignIn.signUser.getUserName();
                int index = -1;
                int isOnTime = -1;


                System.out.println(confPaperDueDate);
                try {
                    System.out.println((df.parse(confPaperDueDate)).compareTo(df.parse(submitDate)));
                    isOnTime = (df.parse(confPaperDueDate)).compareTo(df.parse(submitDate));
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }

                if (isSubmit) {

                    JOptionPane.showMessageDialog(null,
                            "Alert\nYou have submitted your paper.");
                    ConferenceForAuthor conferenceForAuthor = null;
                    try {
                        conferenceForAuthor = new ConferenceForAuthor();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    conferenceForAuthor.setVisible(true);
                    dispose();
                }
                if (isOnTime < 0) {
                    showPaperNameLabel.setText("Your submission overdue, you can not submit now.");
                    /*ConferenceForAuthor conferenceForAuthor = null;
                    try {
                        conferenceForAuthor = new ConferenceForAuthor();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    conferenceForAuthor.setVisible(true);
                    dispose();*/
                }
                else {

                    if (!filepath.isEmpty() && filepath.length() > 0) {
                        if (!paperNameTextField.getText().isEmpty() || paperNameTextField.getText().length() > 0)  {
                            paperName = paperNameTextField.getText();
                            showPaperNameLabel.setText(paperName);
                        }
                        else {
                            //paperName = filepath.substring(filepath.lastIndexOf("\\") + 1);
                            //paperName = paperName.substring(0, paperName.lastIndexOf("."));
                            showPaperNameLabel.setText(paperName);
                        }
                        System.out.println(paperName);

                        if (!topictextField.getText().isEmpty()){
                            newArea = topictextField.getText();
                        }
                        else if (newArea.equals("")){
                            newArea = "AI";
                        }
                        tempPaper = new Paper(paperName, conferenceName, authorName, newArea, tempFile, "Submitted");
                        showPaperNameLabel.setText(tempPaper.getPaperName());

                        int result = JOptionPane.showConfirmDialog(null, "Are you sure submit this paper?", "Confirm", YES_NO_OPTION);
                        System.out.println(result);
                        if (result == 0) {
                            for (Paper temp : cms.getListOfPapers()) {
                                if (temp.getPaperFile().equals(tempPaper.getPaperFile())) {
                                    index = cms.getListOfPapers().indexOf(temp);
                                    break;
                                }

                            }
                            if (index < 0) {
                                cms.saveNewPaper(tempPaper);
                                JOptionPane.showMessageDialog(null,
                                        "Congratulation\nYou have submitted your paper.");
                                cms.writePaperFile("PaperList.txt");
                                ConferenceForAuthor conferenceForAuthor = null;
                                try {
                                    conferenceForAuthor = new ConferenceForAuthor();
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                                conferenceForAuthor.setVisible(true);
                                dispose();


                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Sorry\nYou have submitted this paper, please select other paper.");

                            }
                        } else {

                            JOptionPane.showMessageDialog(null,
                                    "Cancel\nYour submission has been cancelled, please choose other files to submit.");
                        }
                        tempPaper = null;
                        showPaperNameLabel.setText("");

                    }
                    else {
                        showPaperNameLabel.setText("Please upload a paper");
                    }

                }


            }
        });

        topicBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    newArea = topicBox.getSelectedItem().toString();
                }

            }
        }
        );

        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                JFileChooser chooser = new JFileChooser();             //set chooser
                chooser.setMultiSelectionEnabled(false);             // set multi -> false
                // 过滤文件类型
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                //chooser.addChoosableFileFilter(new FileNameExtensionFilter("pdf,doc,docx", "doc", "docx","pdf"));
                chooser.setFileFilter(new FileNameExtensionFilter("pdf,doc,docx", "doc", "docx", "pdf"));

                int returnVal = chooser.showOpenDialog(browseButton);        //open browse dialog

                if (returnVal == JFileChooser.APPROVE_OPTION) {          // if approve
                    if (chooser.getSelectedFile().getAbsolutePath().substring(
                            chooser.getSelectedFile().getAbsolutePath().lastIndexOf(".") + 1).equals("pdf") ||
                            chooser.getSelectedFile().getAbsolutePath().substring(
                                    chooser.getSelectedFile().getAbsolutePath().lastIndexOf(".") + 1).equals("doc") ||
                            chooser.getSelectedFile().getAbsolutePath().substring(
                                    chooser.getSelectedFile().getAbsolutePath().lastIndexOf(".") + 1).equals("docx")

                    ) {
                        filepath = chooser.getSelectedFile().getAbsolutePath();     // get filepath
                        paperName = chooser.getSelectedFile().getName();
                        //String paperName = filepath.substring(filepath.lastIndexOf("\\") + 1);
                        //paperName = paperName.substring(0, paperName.lastIndexOf("."));
                        tempFile = chooser.getSelectedFile();
                        showPaperNameLabel.setText(paperName);

                    } else {
                        showPaperNameLabel.setText("Please select correct file type.");
                    }

                } else {
                    showPaperNameLabel.setText("Cancel select.");
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

        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Sorry\nThis function (check all your submitted papers) will be available in the next release");
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConferenceForAuthor conferenceForAuthor = null;
                try {
                    conferenceForAuthor = new ConferenceForAuthor();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                conferenceForAuthor.setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) throws IOException {
        //new Submission();
    }


}
