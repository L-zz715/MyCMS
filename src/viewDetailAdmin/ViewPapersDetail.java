package viewDetailAdmin;

import entity.CMS;
import entity.Paper;
import sign.SignIn;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ViewPapersDetail extends JFrame {
    private JPanel adminPaperMainPanel;
    private JSplitPane firSplit;
    private JPanel upPanel;
    private JPanel downPanel;
    private JPanel titlePanel;
    private JPanel otherPanel;
    private JSplitPane upSplit;
    private JComboBox roleComboBox;
    private JLabel titleLabel;
    private JLabel lagText;
    private JLabel iconLabel;
    private JSplitPane downSplit;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton userButton;
    private JButton conferenceButton;
    private JButton paperButton;
    private JPanel contPanel;
    private JLabel subTitle;
    private JSplitPane contSplit;
    private JPanel paperSearchPanel;
    private JPanel paperContPanel;
    private JSplitPane paperSearchSplit;
    private JPanel searchCombPanel;
    private JPanel searchTextPanel;
    private JComboBox paperComboBox;
    private JButton searchButton;
    private JTextField searchTextField;
    private JButton logOutButton;
    private JTextArea paperInfoTextArea;
    public CMS cms;
    private Paper paper;
    private int searchWay;

    public ViewPapersDetail() {

        cms = new CMS();
        cms.readCurrentPaper(cms.readFile("PaperList.txt"));

        setTitle("Administration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //profileLabel.setToolTipText("Other topic areas");  // show  气泡hint
        setSize(1000, 700);
        add(adminPaperMainPanel);
        setVisible(true);
        searchWay = 0;
        String paperInfo = "";

        for (Paper temp : cms.getListOfPapers()) {
            if (cms.getListOfPapers().indexOf(temp) != cms.getListOfPapers().size()-1) {
                paperInfo = paperInfo + "Paper ID: " + temp.getPaperId() + "\n Paper Name: " + temp.getPaperName() +
                        "\n Paper's Conference: " + temp.getConferenceName() + "\n Paper's Author Name: " +
                        temp.getPaperAuthorName() + "\n Paper's Topic: " + temp.getPaperTopicArea() +
                        "\n Paper's filepath: " + temp.getPaperFile() +"\n Paper Status: " + temp.getPaperStatus();
            } else {
                paperInfo = paperInfo + "Paper ID: " + temp.getPaperId() + "\n Paper Name: " + temp.getPaperName() +
                        "\n Paper's Conference: " + temp.getConferenceName() + "\n Paper's Author Name: " +
                        temp.getPaperAuthorName() + "\n Paper's Topic: " + temp.getPaperTopicArea() +
                        "\n Paper's filepath: " + temp.getPaperFile() +"\n Paper Status: " + temp.getPaperStatus();
            }

        }
        paperInfoTextArea.setText(paperInfo);

        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewUsersDetail viewUsersDetail = new ViewUsersDetail();
                viewUsersDetail.setVisible(true);
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


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paperInfoTextArea.setText("");
                if (searchWay > 0) {
                    returnPaperByName(searchTextField.getText());
                } else if (searchWay < 0) {
                    int index = findPaper(searchTextField.getText());
                    if (index < 0) {
                        paperInfoTextArea.append("There is no this user");
                    }
                    else {
                        returnPaper(findPaper(searchTextField.getText()));
                    }
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Alert\nPlease select search way first.");
                }



                findPaper(searchTextField.getText());

            }
        });


        paperComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    if (paperComboBox.getSelectedItem().equals("Paper Name")) {
                        searchWay = 1;
                    }
                    else if (paperComboBox.getSelectedItem().equals("Paper Id")) {
                        searchWay = -1;
                    }
                }
                else {
                    searchWay = -1;
                }
            }
        });
    }

    public int findPaper(String PaperInfo) {
        int index = -1;
        for (Paper currentPaper : cms.getListOfPapers()) {
            if (currentPaper.getPaperName().equals(PaperInfo) || String.valueOf(currentPaper.getPaperId()).equals(PaperInfo)) {
                index = cms.getListOfPapers().indexOf(currentPaper);
                break;
            }
        }
        return index;
    }

    public void returnPaper(int index) {
        Paper paper = cms.getListOfPapers().get(index);
        String paperInfo = "Paper ID: " + paper.getPaperId() + "\n Paper Name: " + paper.getPaperName() +
                  "\n Paper's Conference: " + paper.getConferenceName() + "\n Paper's Author Name: " + paper.getPaperAuthorName() +
                "\n Paper's Topic: " + paper.getPaperTopicArea() + "\n Paper's filepath: " + paper.getPaperFile() +"\n Paper Status: " + paper.getPaperStatus();
        paperInfoTextArea.setLineWrap(true);
        paperInfoTextArea.setWrapStyleWord(true);
        paperInfoTextArea.append(paperInfo);
    }

    public void returnPaperByName(String name) {
        int index = -1;
        for (Paper temp : cms.getListOfPapers()) {
            if (temp.getPaperName().equals(name)) {
                String paperInfo = "Paper ID: " + temp.getPaperId() + "\n Paper Name: " + temp.getPaperName() +
                        "\n Paper's Conference: " + temp.getConferenceName() + "\n Paper's Author Name: " +
                        temp.getPaperAuthorName() + "\n Paper's Topic: " + temp.getPaperTopicArea() +
                        "\n Paper's filepath: " + temp.getPaperFile() +"\n Paper Status: " + temp.getPaperStatus();
                paperInfoTextArea.setLineWrap(true);
                paperInfoTextArea.setWrapStyleWord(true);
                paperInfoTextArea.append(paperInfo);
                index = 1;
                break;
            }
        }
        if (index < 0) {
            paperInfoTextArea.append("There is no this user");
        }
    }




    public static void main(String[] args) {
        new ViewPapersDetail();

    }

}
