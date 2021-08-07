package entity;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Paper {
    private int paperId;
    private String conferenceName;
    private String paperName;
    private String paperAuthorName;
    private String paperTopicArea;
    private String paperSubmitDate;
    private File paperFile;
    private ArrayList<Review> listOfReviews;
    private String paperStatus;

    public Paper() {

    }

    public Paper(String paperName, String conferenceName,String paperAuthorName, String paperTopicArea, File paperFile, String paperStatus) {
        this.paperName = paperName;
        this.conferenceName = conferenceName;
        this.paperAuthorName = paperAuthorName;
        this.paperTopicArea = paperTopicArea;
        this.paperFile = paperFile;
        this.listOfReviews= new ArrayList<Review>();
        this.paperStatus = paperStatus;
    }

    public ArrayList<Review> getListOfReviews() {
        return listOfReviews;
    }

    public void setListOfReviews(ArrayList<Review> listOfReviews) {
        this.listOfReviews = listOfReviews;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setPaperFile(File paperFile) {
        this.paperFile = paperFile;
    }

    public File getPaperFile() {
        return paperFile;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperTopicArea(String paperTopicArea) {
        this.paperTopicArea = paperTopicArea;
    }

    public String getPaperTopicArea() {
        return paperTopicArea;
    }

    public void setPaperAuthorName(String paperAuthorName) {
        this.paperAuthorName = paperAuthorName;
    }

    public String getPaperAuthorName() {
        return paperAuthorName;
    }

    public void setPaperStatus(String paperStatus)  { this.paperStatus = paperStatus;}

    public String getPaperStatus() { return paperStatus;}

    public void setPaperSubmitDate(String submitDate) {
        this.paperSubmitDate = paperSubmitDate;
    }

    public String getPaperSubmitDate() {
        return  paperSubmitDate;
    }

    public void saveNewReview(Review review) {

        if (listOfReviews.isEmpty() || listOfReviews == null) {
            review.setReviewId(getPaperId()+1);
            listOfReviews.add(review);
        }
        else if (listOfReviews.size() < 4) {
            review.setReviewId(getPaperId() + listOfReviews.size()+1);
            listOfReviews.add(review);
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Alert\nFour reviewer already submitted their review.");
        }
    }

    public void readCurrentReview(ArrayList<String> currentReviews) {
        if (listOfReviews.size() != 0) {
            for (String reviewDetail : currentReviews) {
                String[] listOneLine = reviewDetail.trim().split("#");
                int id = Integer.parseInt(listOneLine[0]);
                String reviewerName = listOneLine[1];
                String reviewContent = listOneLine[2];
                Review review = new Review(reviewerName, reviewContent);
                review.setReviewId(id);
                listOfReviews.add(review);
                System.out.println(listOfReviews);
            }
        }
        else {
            System.out.println("There is no Review");
        }
    }

    public void writeReview(String filename, ArrayList<Review> reviewArrayList) {
        try {
            PrintWriter outputFile = new PrintWriter(filename);
            for (int i = 0; i < reviewArrayList.size(); i++) {
                outputFile.println(reviewArrayList.get(i).getReviewId() + "#" +
                        reviewArrayList.get(i).getReviewerName() + "#" +
                        reviewArrayList.get(i).getReviewContent());
            }
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Problem found: " + e);
        }
    }


    public String display() {
        String content = paperId + "," + paperName + "," + conferenceName + "," + paperAuthorName + "," + paperTopicArea;
        return content;
    }

}


