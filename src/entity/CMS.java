package entity;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class CMS {
    private final ArrayList<User> listOfUsers;
    private final ArrayList<Paper> listOfPapers;
    private int tempIndex;
    private User signInUser;



    public CMS() {

        listOfUsers= new ArrayList<>();
        listOfPapers= new ArrayList<>();
        tempIndex = -1;
    }

    public void saveNewPaper(Paper newPaper) {
        if (listOfPapers == null && listOfPapers.isEmpty()) {
            newPaper.setPaperId(1);
            listOfPapers.add(newPaper);
        }
        else {
            newPaper.setPaperId(listOfPapers.size()+1);
            listOfPapers.add(newPaper);
        }
    }

    public void removeLastPaper() {
        listOfPapers.remove(getLengthOfPaperList()-1);
    }



    public void saveNewUser(User newUser) {
        if (listOfUsers == null || listOfUsers.isEmpty()) {
            newUser.setUserId(1);
            listOfUsers.add(newUser);
        }
        else {
            newUser.setUserId(listOfUsers.size()+1);
            listOfUsers.add(newUser);
        }
    }

    public void readCurrentUser(ArrayList<String> currentUsers) {
        if (currentUsers.size() != 0) {
            for (String userDetail : currentUsers) {
                String[] listOneLine = userDetail.trim().split(",");
                int id = Integer.parseInt(listOneLine[0]);
                String name = listOneLine[1];
                String email = listOneLine[2];
                String password = listOneLine[3];
                int mobile = Integer.parseInt(listOneLine[4]);
                String qualification = listOneLine[5];
                String occupation = listOneLine[6];
                String employerDetail = listOneLine[7];
                String interestArea = listOneLine[8];
                User user = new User(name, email, password, mobile, qualification, occupation, employerDetail, interestArea);
                user.setUserId(id);
                listOfUsers.add(user);
            }
        }
        else {
            System.out.println("There is no user,sign up first.");
        }
    }

    public void readCurrentPaper(ArrayList<String> currentPapers) {
        if (currentPapers.size() != 0) {
            for (String paperDetail : currentPapers) {
                String[] listOneLine = paperDetail.trim().split("#");
                int id = Integer.parseInt(listOneLine[0]);
                String paperName = listOneLine[1];
                String confName = listOneLine[2];
                String authName = listOneLine[3];
                String paperArea = listOneLine[4];
                File paperFile = new File(listOneLine[5]);
                String papStatus = listOneLine[6];
                Paper paper = new Paper(paperName,confName,authName,paperArea,paperFile,papStatus);
                paper.setPaperId(id);

                if (listOneLine.length == 8) {
                    String reviewerName = listOneLine[7];
                    Review review = new Review(reviewerName);
                    paper.saveNewReview(review);
                }
                else if (listOneLine.length == 9) {
                    String reviewerName = listOneLine[7];
                    String reviewerContent = listOneLine[8];
                    Review review = new Review(reviewerName,reviewerContent);
                    paper.saveNewReview(review);
                }
                else if (listOneLine.length == 10) {
                    String reviewerName = listOneLine[7];
                    String reviewerContent = listOneLine[8];
                    Review review = new Review(reviewerName,reviewerContent);
                    paper.saveNewReview(review);

                    String reviewerName2 = listOneLine[9];
                    Review review2 = new Review(reviewerName2);
                    paper.saveNewReview(review2);
                }
                else if (listOneLine.length == 11) {
                    String reviewerName1 = listOneLine[7];
                    String reviewerContent1 = listOneLine[8];
                    Review review1 = new Review(reviewerName1,reviewerContent1);
                    paper.saveNewReview(review1);

                    String reviewerName2 = listOneLine[9];
                    String reviewerContent2 = listOneLine[10];
                    Review review2 = new Review(reviewerName2,reviewerContent2);
                    paper.saveNewReview(review2);
                }
                else if (listOneLine.length == 12) {
                    String reviewerName1 = listOneLine[7];
                    String reviewerContent1 = listOneLine[8];
                    Review review1 = new Review(reviewerName1,reviewerContent1);
                    paper.saveNewReview(review1);

                    String reviewerName2 = listOneLine[9];
                    String reviewerContent2 = listOneLine[10];
                    Review review2 = new Review(reviewerName2,reviewerContent2);
                    paper.saveNewReview(review2);


                    String reviewerName3 = listOneLine[11];
                    //String reviewerContent = listOneLine[8];
                    Review review3 = new Review(reviewerName3);
                    paper.saveNewReview(review3);
                }
                else if (listOneLine.length == 13) {
                    String reviewerName1 = listOneLine[7];
                    String reviewerContent1 = listOneLine[8];
                    Review review1 = new Review(reviewerName1,reviewerContent1);
                    paper.saveNewReview(review1);

                    String reviewerName2 = listOneLine[9];
                    String reviewerContent2 = listOneLine[10];
                    Review review2 = new Review(reviewerName2,reviewerContent2);
                    paper.saveNewReview(review2);

                    String reviewerName3 = listOneLine[11];
                    String reviewerContent3 = listOneLine[12];
                    Review review3 = new Review(reviewerName3,reviewerContent3);
                    paper.saveNewReview(review3);
                }
                else if (listOneLine.length == 14) {
                    String reviewerName1 = listOneLine[7];
                    String reviewerContent1 = listOneLine[8];
                    Review review1 = new Review(reviewerName1,reviewerContent1);
                    paper.saveNewReview(review1);

                    String reviewerName2 = listOneLine[9];
                    String reviewerContent2 = listOneLine[10];
                    Review review2 = new Review(reviewerName2,reviewerContent2);
                    paper.saveNewReview(review2);

                    String reviewerName3 = listOneLine[11];
                    String reviewerContent3 = listOneLine[12];
                    Review review3 = new Review(reviewerName3,reviewerContent3);
                    paper.saveNewReview(review3);

                    String reviewerName4 = listOneLine[13];
                    //String reviewerContent = listOneLine[8];
                    Review review4 = new Review(reviewerName4);
                    paper.saveNewReview(review4);
                }
                else if (listOneLine.length == 15) {
                    String reviewerName1 = listOneLine[7];
                    String reviewerContent1 = listOneLine[8];
                    Review review1 = new Review(reviewerName1,reviewerContent1);
                    paper.saveNewReview(review1);

                    String reviewerName2 = listOneLine[9];
                    String reviewerContent2 = listOneLine[10];
                    Review review2 = new Review(reviewerName2,reviewerContent2);
                    paper.saveNewReview(review2);

                    String reviewerName3 = listOneLine[11];
                    String reviewerContent3 = listOneLine[12];
                    Review review3 = new Review(reviewerName3,reviewerContent3);
                    paper.saveNewReview(review3);

                    String reviewerName4 = listOneLine[13];
                    String reviewerContent4 = listOneLine[14];
                    Review review4 = new Review(reviewerName4,reviewerContent4);
                    paper.saveNewReview(review4);
                }

                listOfPapers.add(paper);
            }
        }
        else {
            System.out.println("There is no paper");
        }
    }

    public ArrayList<String> readFile(String filename) {
        ArrayList<String> fileContent = new ArrayList<>();

        try {
            FileReader file = new FileReader(filename);
            Scanner scanFile = new Scanner(file);

            while (scanFile.hasNextLine()) {

                String oneLine = scanFile.nextLine();
                fileContent.add(oneLine.trim());
            }

            file.close();
        } catch (FileNotFoundException e) {
            System.out.println("Problem found: " + e);
        } catch (IOException e) {
            System.out.println("Problem found: " + e);
        } finally {
            return fileContent;
        }
    }

    public void writePaperFile(String filename) {
        try {
            PrintWriter outputFile = new PrintWriter(filename);

            for (int i = 0; i < getLengthOfPaperList(); i++) {
                if (getListOfPapers().get(i).getListOfReviews().size()>0) {
                    outputFile.print(getListOfPapers().get(i).getPaperId() + "#" +
                            getListOfPapers().get(i).getPaperName() + "#" +
                            getListOfPapers().get(i).getConferenceName() + "#" +
                            getListOfPapers().get(i).getPaperAuthorName() + "#" +
                            getListOfPapers().get(i).getPaperTopicArea() + "#" +
                            getListOfPapers().get(i).getPaperFile().getAbsolutePath() + "#" +
                            getListOfPapers().get(i).getPaperStatus() + "#");

                    for (int j = 0; j < getListOfPapers().get(i).getListOfReviews().size(); j++)  {
                        if (j != getListOfPapers().get(i).getListOfReviews().size()-1) {
                            outputFile.print(getListOfPapers().get(i).getListOfReviews().get(j).getReviewerName() + "#"
                                    + getListOfPapers().get(i).getListOfReviews().get(j).getReviewContent() + "#");
                        } else {
                            outputFile.println(getListOfPapers().get(i).getListOfReviews().get(j).getReviewerName() + "#"
                                    + getListOfPapers().get(i).getListOfReviews().get(j).getReviewContent());
                        }

                    }
                } else {
                    outputFile.println(getListOfPapers().get(i).getPaperId() + "#" +
                            getListOfPapers().get(i).getPaperName() + "#" +
                            getListOfPapers().get(i).getConferenceName() + "#" +
                            getListOfPapers().get(i).getPaperAuthorName() + "#" +
                            getListOfPapers().get(i).getPaperTopicArea() + "#" +
                            getListOfPapers().get(i).getPaperFile().getAbsolutePath() + "#" +
                            getListOfPapers().get(i).getPaperStatus());
                }
            }
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Problem found: " + e);
        }
    }

    public void writePaper(String filename, ArrayList<Paper> paperArrayList) {
        try {
            PrintWriter outputFile = new PrintWriter(filename);

            for (int i = 0; i < paperArrayList.size(); i++) {
                if (paperArrayList.get(i).getListOfReviews().size()>0) {
                    outputFile.print(paperArrayList.get(i).getPaperId() + "#" +
                            paperArrayList.get(i).getPaperName() + "#" +
                            paperArrayList.get(i).getConferenceName() + "#" +
                            paperArrayList.get(i).getPaperAuthorName() + "#" +
                            paperArrayList.get(i).getPaperTopicArea() + "#" +
                            paperArrayList.get(i).getPaperFile().getAbsolutePath() + "#" +
                            paperArrayList.get(i).getPaperStatus() + "#");
                    for (int j = 0; j < paperArrayList.get(i).getListOfReviews().size(); j++)  {
                        if (j != paperArrayList.get(i).getListOfReviews().size()-1) {
                            outputFile.print(paperArrayList.get(i).getListOfReviews().get(j).getReviewerName() + "#" +
                                    paperArrayList.get(i).getListOfReviews().get(j).getReviewContent() + "#");
                        } else {
                            outputFile.println(paperArrayList.get(i).getListOfReviews().get(j).getReviewerName() + "#" +
                                    paperArrayList.get(i).getListOfReviews().get(j).getReviewContent());
                        }

                    }
                } else {
                    outputFile.println(paperArrayList.get(i).getPaperId() + "#" +
                            paperArrayList.get(i).getPaperName() + "#" +
                            paperArrayList.get(i).getConferenceName() + "#" +
                            paperArrayList.get(i).getPaperAuthorName() + "#" +
                            paperArrayList.get(i).getPaperTopicArea() + "#" +
                            paperArrayList.get(i).getPaperFile().getAbsolutePath() + "#" +
                            paperArrayList.get(i).getPaperStatus());
                }



            /*for (int i = 0; i < paperArrayList.size(); i++) {
                outputFile.println(paperArrayList.get(i).getPaperId() + "," +
                        paperArrayList.get(i).getPaperName() + "," +
                        paperArrayList.get(i).getConferenceName() + "," +
                        paperArrayList.get(i).getPaperAuthorName() + "," +
                        paperArrayList.get(i).getPaperTopicArea() + "," +
                        paperArrayList.get(i).getPaperFile().getAbsolutePath() + "," +
                        paperArrayList.get(i).getPaperStatus());*/
            }
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Problem found: " + e);
        }
    }

    public void writeUserFile(String filename) {

        //FileWriter fw=null;

        try {
            //fw = new FileWriter("UserList.txt",true);
            PrintWriter outputFile = new PrintWriter(filename);
            for (int i = 0; i < getLengthOfUserList(); i++) {
                outputFile.println(getListOfUsers().get(i).getUserId() + "," +
                        getListOfUsers().get(i).getUserName() + "," +
                        getListOfUsers().get(i).getUserEmail() + "," +
                        getListOfUsers().get(i).getUserPassword() + "," +
                        getListOfUsers().get(i).getUserMobileNumber() + "," +
                        getListOfUsers().get(i).getUserQualification() + "," +
                        getListOfUsers().get(i).getUserOccupation() + "," +
                        getListOfUsers().get(i).getUserEmployerDetail() + "," +
                        getListOfUsers().get(i).getUserInterestArea());
            }
            outputFile.close();
        } catch (IOException e) {
            System.out.println("Problem found: " + e);
        }
    }



    public int getLengthOfUserList() {
        return listOfUsers.size();
    }

    public ArrayList<User> getListOfUsers() {
        return listOfUsers;
    }

    public int getLengthOfPaperList() {
        return listOfPapers.size();
    }

    public ArrayList<Paper> getListOfPapers() {
        return listOfPapers;
    }

    public void setIndex(int index) {
        this.tempIndex = index;
    }

    public int getIndex() {
        return tempIndex;
    }

    public void setSignInUser(User singInUser) {
        this.signInUser = signInUser;
    }

    public User getSignInUser() {
        return signInUser;
    }



}