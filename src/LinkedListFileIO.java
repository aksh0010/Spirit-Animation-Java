import java.io.*;
import java.util.LinkedList;

public class LinkedListFileIO {
    
    // Save the LinkedList to a file
    public static void saveLinkedListToFile(LinkedList<GameObject> list, String filename) {
        try {
        	 File file = new File(filename);
             if (!file.exists()) {
                 // If the file doesn't exist, create a new file
                 file.createNewFile();
                 System.out.println("Creating new file now ");
             }
            FileOutputStream fileout = new FileOutputStream(filename);
            ObjectOutputStream oos = new ObjectOutputStream(fileout);
            oos.writeObject(list);
            oos.close();
            fileout.close();
            System.out.println("Saved game state to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load the LinkedList from a file
    public static LinkedList<GameObject> loadLinkedListFromFile(String filename) {
        LinkedList<GameObject> loadedList = new LinkedList<>();
        try {
            FileInputStream filein = new FileInputStream(filename);
            ObjectInputStream ois= new ObjectInputStream(filein);
            loadedList=(LinkedList<GameObject>) ois.readObject();       
            ois.close();
            filein.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("File not found or error reading file. Loading new game.");
            // Create and return a new LinkedList if file is not found
            saveLinkedListToFile(new LinkedList<>(), filename);
        }
        return loadedList;
    }
}
