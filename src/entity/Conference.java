package entity;

import java.util.ArrayList;

public class Conference {
    private int conferenceId;
    private String conferenceName;
    private String conferenceTitle;
    private String conferenceTopic;
    private String conferenceChairs;
    private String conferenceDate;
    private String paperDueDate;
    private ArrayList<Paper> listOfPapers;

    public Conference(String conferenceName, String conferenceTitle, String conferenceTopic, String conferenceChairs, String conferenceDate, String paperDueDate) {
        this.conferenceName = conferenceName;
        this.conferenceTitle = conferenceTitle;
        this.conferenceTopic = conferenceTopic;
        this.conferenceChairs = conferenceChairs;
        this.conferenceDate = conferenceDate;
        this.paperDueDate = paperDueDate;
    }

//    public String getConferenceDate() {
//        return conferenceDate;
//    }
//
//    public void setConferenceDate(String conferenceDate) {
//        this.conferenceDate = conferenceDate;
//    }
//
//    public ArrayList<Paper> getListOfPapers() {
//        return listOfPapers;
//    }
//
//    public void setListOfPapers(ArrayList<Paper> listOfPapers) {
//        this.listOfPapers = listOfPapers;
//    }
//
//    public String getPaperDueDate() {
//        return paperDueDate;
//    }
//
//    public void setPaperDueDate(String paperDueDate) {
//        this.paperDueDate = paperDueDate;
//    }

    public void setConferenceId(int conferenceId) {
        this.conferenceId = conferenceId;
    }

    public int getConferenceId() {
        return conferenceId;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }

    public String getConferenceTitle() {
        return conferenceTitle;
    }

    public void setConferenceTopic(String conferenceTopic) {
        this.conferenceTopic = conferenceTopic;
    }

    public String getConferenceTopic() {
        return conferenceTopic;
    }

    public void setConferenceChairs(String conferenceChairs) {
        this.conferenceChairs = conferenceChairs;
    }

    public String getConferenceChairs() {
        return conferenceChairs;
    }

    public void setConferenceDate(String conferenceDate) {
        this.conferenceDate = conferenceDate;
    }

    public String getConferenceDate() {
        return conferenceDate;
    }

    public void setPaperDueDate(String paperDueDate) {
        this.paperDueDate = paperDueDate;
    }

    public String getPaperDueDate() {
        return paperDueDate;
    }

    @Override
    public String toString() {
        return conferenceName;
    }

    public String toWriter() {
        return "\n" + conferenceName + "#" +conferenceTitle + "#" +conferenceTopic + "#" +conferenceChairs
                + "#" +conferenceDate + "#" +paperDueDate;
    }
}
