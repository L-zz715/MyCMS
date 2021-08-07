package checkPaperbyChair;

import conferenceForAuthor.ConferenceForAuthor;
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
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class PapersForConference extends JFrame {
    private JPanel ChairPaper;
    private JSplitPane firSplit;
    private JPanel upPanel;
    private JPanel downPanel;
    private JSplitPane upSplit;
    private JPanel titlePanel;
    private JPanel otherPanel;
    private JLabel titleLabel;
    private JComboBox roleComboBox;
    private JLabel logText;
    private JLabel iconLabel;
    private JSplitPane downSplit;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JButton conferenceButton;
    private JButton paperButton;
    private JButton reviewButton;
    private JLabel confTitle;
    private JSplitPane contSplit;
    private JPanel contUpPanel;
    private JPanel contDownPanel;
    private JPanel contPanel;
    private JSplitPane paperTitleSplit;
    private JPanel paperNamePanel;
    private JPanel statusPanel;
    private JLabel statusLabel;
    private JSplitPane paperInfoSplit;
    private JPanel paperNameNewPanel;
    private JPanel checkReviewPanel;
    public JLabel paperNameLabel;
    private JLabel checkReviewLabel;
    private JSplitPane contDownSplit;
    private JPanel paperContPanel;
    private JSplitPane paperNameSplit;
    private JPanel paperNameConPanel;
    private JPanel paperCheckPanel;
    private JPanel paperStatusPanel;
    private JLabel navLabel;
    private JButton logOutButton;
    private JButton reviewButton2;
    private JList list2;
    private JList list3;
    private JLabel navTwoLabel;
    private JList list1;
    private JButton checkReviewButton;
    private JTable table;
    public CMS cms;
    private ConferenceDB conferenceDB;
    private ArrayList<Conference> conferenceArrayList;
    private ArrayList<Conference> attendConferences = new ArrayList<>();
    private ArrayList<Paper> papersInConf = new ArrayList<>();
    private int selectIndex;



    public PapersForConference(int index) throws IOException {
        setTitle("Conference");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        add(ChairPaper);
        setVisible(true);
        setResizable(false);

        conferenceDB = new ConferenceDB();
        conferenceArrayList = conferenceDB.getConferenceList();
        Conference conference = conferenceArrayList.get(index);

        for (Conference temp : conferenceArrayList) {
            String[] chairs = temp.getConferenceChairs().split(",");
            for (String chairName : chairs) {
                if (chairName.equals(SignIn.signUser.getUserName())) {
                    attendConferences.add(temp);
                    System.out.println(attendConferences);
                    break;
                }
            }
        }

        //paperNameLabel.setText(conference.getConferenceName());


        DefaultListModel<String> model = new DefaultListModel<>();
        DefaultListModel<String> model1 = new DefaultListModel<>();
        DefaultListModel<String> model2 = new DefaultListModel<>();

        DefaultListCellRenderer renderer3 = new DefaultListCellRenderer();
        renderer3.setHorizontalAlignment(SwingConstants.CENTER);
        list3.setCellRenderer(renderer3);

        DefaultListCellRenderer renderer4 = new DefaultListCellRenderer();
        renderer4.setHorizontalAlignment(SwingConstants.CENTER);
        list1.setCellRenderer(renderer4);


        cms = new CMS();
        cms.readCurrentPaper(cms.readFile("PaperList.txt"));

        findPaper(paperNameLabel.getText());

        for (Paper paper : cms.getListOfPapers()) {
            if (attendConferences.get(index).getConferenceName().equals(paper.getConferenceName())) {
                model.addElement(paper.getPaperName());
                model1.addElement(paper.getPaperStatus());
                model2.addElement("Review");
                papersInConf.add(paper);
            }
        }
        list2.setModel(model);
        list3.setModel(model1);
        list1.setModel(model2);
        list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list1.setVisibleRowCount(10);

        list2.setVisibleRowCount(10);

        list3.setVisibleRowCount(10);


        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SignIn signIn = new SignIn();
                signIn.setVisible(true);
                dispose();
            }
        });


        list1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!list1.getValueIsAdjusting()) {    //设置只有释放鼠标时才触发
                    selectIndex = list1.getSelectedIndex();
                    if (selectIndex >= 0) {
                        String paperName = papersInConf.get(selectIndex).getPaperName();
                        //cms.setIndex(selectIndex);
                        //writeFile("tempIndex.txt");
                        ReviewCheck reviewCheck = new ReviewCheck(paperName);
                        reviewCheck.setVisible(true);
                        dispose();

                    }
                }

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


        navLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
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
        list1.addMouseListener(new MouseAdapter() {
        });
        firSplit.addComponentListener(new ComponentAdapter() {
        });
    }

    public ArrayList<Paper> findPaper(String conference) {
        ArrayList<Paper> paperList = new ArrayList<>();
        for (Paper currentPaper : cms.getListOfPapers()) {
            if (currentPaper.getConferenceName().equals(conference)) {
                paperList.add(currentPaper);
            }
        }
        return paperList;
    }






}
