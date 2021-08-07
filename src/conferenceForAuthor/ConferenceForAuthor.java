package conferenceForAuthor;

import conferenceManageByChair.ConferenceDB;
import conferenceManageByChair.ConferenceMain;
import entity.CMS;
import entity.Conference;
import entity.Paper;
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
import java.io.PrintWriter;

public class ConferenceForAuthor extends JFrame {
    private JPanel AuthorConf;
    private JPanel upPanel;
    private JPanel downPanel;
    private JSplitPane firSplit;
    private JSplitPane secSplit;
    private JButton confButton;
    private JButton reviewButton;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton paperButton;
    private JSplitPane upSplit;
    private JPanel titleP;
    private JPanel otherP;
    private JLabel iconLabel;
    private JLabel logText;
    private JComboBox roleBox;
    private JLabel confTitle;
    private JPanel contentPanel;
    private JSplitPane thirSplit;
    private JPanel confInfoTitle;
    private JPanel confCont;
    private JSplitPane titleSplit1;
    private JPanel confNameP;
    private JPanel confOtherP;
    private JSplitPane titleSplit2;
    private JPanel confDateP;
    private JPanel confOtherP2;
    private JSplitPane tiitleSplit3;
    private JPanel paperDueP;
    private JPanel submitP;
    private JLabel confNameLabel;
    private JLabel confDateLabel;
    private JLabel paperDueLabel;
    private JLabel submitLabel;
    private JButton submitButton;
    private JLabel titleLabel;
    private JLabel naviLabel;
    private JSplitPane confContSplit;
    private JPanel confNameContPanel;
    private JPanel otherContPanel;
    private JList list1;
    private JPanel datSubContPanel;
    private JPanel datePanel;
    private JSplitPane dateSubSplit;
    private JSplitPane dueSubSplit;
    private JPanel dueContPanel;
    private JPanel subContPanel;
    private JList list2;
    private JList list3;
    private JButton logOutButton;
    private JButton button1;
    private JList list4;
    private DefaultListModel<String> listModel1;
    private DefaultListModel<String> listModel2;
    private DefaultListModel<String> listModel3;
    private DefaultListModel<String> listModel4;
    public int index;
    private ConferenceDB conferences;

    private CMS cms;

    public ConferenceForAuthor() throws IOException{
        setTitle("Conference");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        add(AuthorConf);
        setVisible(true);
        setResizable(false);
        reviewButton.setEnabled(false);

        index = -1;
        conferences = new ConferenceDB();

        listModel1 = new DefaultListModel<>();
        listModel2 = new DefaultListModel<>();
        listModel3 = new DefaultListModel<>();
        listModel4 = new DefaultListModel<>();

        DefaultListCellRenderer renderer2 = new DefaultListCellRenderer();
        renderer2.setHorizontalAlignment(SwingConstants.CENTER);
        list2.setCellRenderer(renderer2);

        DefaultListCellRenderer renderer3 = new DefaultListCellRenderer();
        renderer3.setHorizontalAlignment(SwingConstants.CENTER);
        list3.setCellRenderer(renderer3);

        DefaultListCellRenderer renderer4 = new DefaultListCellRenderer();
        renderer4.setHorizontalAlignment(SwingConstants.CENTER);
        list4.setCellRenderer(renderer4);

        cms = new CMS();
        cms.readCurrentPaper(cms.readFile("PaperList.txt"));


        for (Conference temp : conferences.getConferenceList()) {
            int submit = -1;
            int authorAttend = -1;
            String[] tempChairs = temp.getConferenceChairs().split(",");
            for (String tempChair : tempChairs)  {
                if (tempChair.equals(SignIn.signUser.getUserName())) {
                    authorAttend = -1;
                    break;
                }
                else  {
                    authorAttend = 1;
                }
            }

            if (authorAttend > 0) {
                listModel1.addElement(temp.getConferenceName());
                listModel2.addElement(temp.getConferenceDate());
                listModel3.addElement(temp.getPaperDueDate());

                for (Paper paper : cms.getListOfPapers()) {
                    if (paper.getConferenceName().equals(temp.getConferenceName()) && paper.getPaperAuthorName().equals(SignIn.signUser.getUserName())) {
                        listModel4.addElement("Submitted");
                        submit = 1;

                    }
                }

                if (submit < 0) {
                    listModel4.addElement("Unsubmittd");
                }
            }



            //listModel4.addElement("Submit");
        }


        list1.setModel(listModel1);
        list1.setVisibleRowCount(10);
        list2.setModel(listModel2);
        list2.setVisibleRowCount(10);
        list3.setModel(listModel3);
        list3.setVisibleRowCount(10);
        list4.setModel(listModel4);
        list4.setVisibleRowCount(10);

        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!list1.getValueIsAdjusting()) {    //设置只有释放鼠标时才触发
                    for (Conference temp : conferences.getConferenceList()) {
                        if (temp.getConferenceName().equals(list1.getSelectedValue().toString())) {
                            index = conferences.getConferenceList().indexOf(temp);
                            System.out.println(index);
                            cms.setIndex(index);
                            System.out.println(list1.getSelectedIndex());
                            System.out.println(list1.getSelectedValue().toString());
                            break;
                        }
                    }


                }
            }
        });

        list4.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if (!list4.getValueIsAdjusting()) {    //设置只有释放鼠标时才触发
                    index = list4.getSelectedIndex();
                    if (index >= 0) {
                        cms.setIndex(index);
                        writeFile("tempIndex.txt");

                        try {
                            Submission submission = new Submission(index);
                            submission.setVisible(true);
                            dispose();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

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
        paperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Sorry\nThis function (check all your submitted papers) will be available in the next release");
            }
        });
    }


    public void writeFile(String filename) {

        //FileWriter fw=null;

        try {
            //fw = new FileWriter("UserList.txt",true);
            PrintWriter outputFile = new PrintWriter(filename);
            outputFile.println(cms.getIndex());
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Problem found: " + e);
        }
    }


    public static void main(String[] args) throws IOException {
        new ConferenceForAuthor();
    }


}

/*
class MyListModel extends AbstractListModel {
    @Override
    public Object getElementAt(int index)  {
        return index;
    }

    @Override
    public int getSize() {
        return 100;
    }
}
*/
