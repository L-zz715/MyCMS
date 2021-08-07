package conferenceManageByChair;

import entity.Conference;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;


public class ConferenceDB {
    private ArrayList<Conference> conferenceArrayList;
    private final String database ="ConferenceDB.txt";
    URL path = ConferenceDB.class.getResource(database);

    public ConferenceDB() throws IOException {

        conferenceArrayList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(path.getFile()))) {
            String line = reader.readLine();
            while (line != null){
                String[] confString = line.trim().split("#");
                String name = confString[0];
                String title = confString[1];
                String topic = confString[2];
                String chairs = confString[3];
                String date = confString[4];
                String dueDate = confString[5];
                Conference conference = new Conference(name, title, topic, chairs, date, dueDate);
                conferenceArrayList.add(conference);
                line = reader.readLine();
            }
        }
    }

    public ArrayList<Conference> getConferenceList() {
        return conferenceArrayList;
    }

    public void addConference(Conference conference) throws IOException {
        try(FileWriter fw = new FileWriter(path.getFile(), true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw))
        {
            out.print(conference.toWriter());
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }

    public void clearDatabase() throws IOException {
        PrintWriter pw = new PrintWriter(path.getFile());
        pw.print("");
        pw.close();
    }

    public void updateDB (ArrayList<Conference> conferenceArrayList) throws IOException {
        for (Conference conference : conferenceArrayList) {
            try(FileWriter fw = new FileWriter(path.getFile(), true);
                BufferedWriter bw = new BufferedWriter(fw))
            {
                bw.append(conference.toWriter());
            } catch (IOException e) {
                //exception handling left as an exercise for the reader
            }
        }
        removeFirstLine();
    }

    public void removeFirstLine() throws IOException {
        RandomAccessFile raf = new RandomAccessFile(path.getFile(), "rw");
        //Initial write position
        long writePosition = raf.getFilePointer();
        raf.readLine();
        // Shift the next lines upwards.
        long readPosition = raf.getFilePointer();

        byte[] buff = new byte[1024];
        int n;
        while (-1 != (n = raf.read(buff))) {
            raf.seek(writePosition);
            raf.write(buff, 0, n);
            readPosition += n;
            writePosition += n;
            raf.seek(readPosition);
        }
        raf.setLength(writePosition);
        raf.close();
    }

    public static void main(String[] args) throws IOException {
        new ConferenceDB();
    }
}