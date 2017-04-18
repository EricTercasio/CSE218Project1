import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Kitcatski on 4/12/2017.
 */
public class App extends Application {
    MasterLinkedList masterLinkedList = new MasterLinkedList();
    ArrayList<String> newLyrics = new ArrayList();
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser fileChooser = new FileChooser();
        Menu menu = new Menu("File");
        MenuItem menuItem = new MenuItem("Open");
        MenuItem menuItem2 = new MenuItem("Save");
        menu.getItems().addAll(menuItem,menuItem2);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menu);
        TextArea textArea = new TextArea();
        Button generateButton = new Button("Generate lyrics");
        TextField wordField = new TextField();
        wordField.setPromptText("Word");
        TextField sizeField = new TextField();
        sizeField.setPromptText("Number of words");
        HBox textFieldBox = new HBox(wordField, sizeField);
        textFieldBox.setAlignment(Pos.CENTER);
        textFieldBox.setSpacing(20);
        menuItem.setOnAction(e -> {
            File selection = fileChooser.showOpenDialog(null);
            if (selection != null) {
                try (Scanner scanner = new Scanner(selection)) {
                    String fileContents = scanner.nextLine();
                    DocumentHelper documentHelper = new DocumentHelper(fileContents);
                    List words = documentHelper.getTokens("[A-Za-z]\\w*");
                   // textArea.appendText(words.toString());
                    //textArea.appendText(Integer.toString(documentHelper.getTokens("[A-Za-z]\\w*").size()));
                    masterLinkedList = generateLinkList(words);
                    textArea.appendText( "Number of words: " + String.valueOf(documentHelper.getNumberOfWords(fileContents)));
                    textArea.appendText("\n Number of sentences: "+ String.valueOf(documentHelper.getNumberofSentences(fileContents)));
                    textArea.appendText("\n Syllable count: " + String.valueOf(documentHelper.countSyllables(fileContents)));
                    textArea.appendText("\n Flesch score: " + String.valueOf(documentHelper.getFleschScore(fileContents)));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        generateButton.setOnAction(e->{
            textArea.clear();
            textArea.appendText("\t\t\t\t");
            String selectedWord = wordField.getText();
            int amountOfWords = Integer.valueOf(sizeField.getText());
            newLyrics = generateLyrics(masterLinkedList,selectedWord,amountOfWords);
            for(int i = 0; i < newLyrics.size(); i++){
                if(i % 5 == 0){
                    textArea.appendText("\n\t\t\t\t");
                }
                textArea.appendText(newLyrics.get(i) + " ");
            }
        });
        menuItem2.setOnAction(e->{
            String newLyricsOut = "";
            for(int i = 0; i < newLyrics.size(); i++){
                newLyricsOut += newLyrics.get(i);
                newLyricsOut += " ";
            }
            String dir = System.getProperty("user.dir");
            File file = new File(dir + "\\save\\newLyrics.txt");
            try(PrintWriter out = new PrintWriter(file)){
                out.println(newLyricsOut);
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }
        });
        VBox vBox = new VBox();
        vBox.getChildren().addAll(menuBar, textFieldBox, generateButton, textArea);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(40);
        Scene scene = new Scene(vBox, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public MasterLinkedList generateLinkList(List song) {
        MasterLinkedList masterLinkedList = new MasterLinkedList();
        String word;
        int i = 0; // counter
        while (i <= song.size() - 1) {
            word = (String)song.get(i);

            if(masterLinkedList.isEmpty()){
                masterLinkedList.insertFirst(word);
                masterLinkedList.find(word).setBabyLinkedList(new BabyLinkedList());
                masterLinkedList.find(word).getBabyLinkedList().insertFirst(song.get(i + 1).toString());
                i++;
            }else if (masterLinkedList.find(word) == null) {
                masterLinkedList.insertFirst((String)song.get(i));
                if(!((i + 1) >= song.size())) {
                    masterLinkedList.find(song.get(i).toString()).setBabyLinkedList(new BabyLinkedList());
                    masterLinkedList.find(song.get(i).toString()).getBabyLinkedList().insertFirst(song.get(i + 1).toString());

                }
                i++;
            } else {
                if(!((i + 1) >= song.size())) {
                    masterLinkedList.find(song.get(i).toString()).getBabyLinkedList().insertFirst(song.get(i + 1).toString());
                }
                i++;
            }
        }
        masterLinkedList.displayList();
        return masterLinkedList;
    }
    public ArrayList generateLyrics(MasterLinkedList lyricList, String word, int amount){
        ArrayList newLyrics = new ArrayList<String>();
        MasterLink foundLink;
        newLyrics.add(word);
        for (int i = 0; i <= amount; i++){
            foundLink = lyricList.find(word);
            word = foundLink.getBabyLinkedList().getRandomWord();
            newLyrics.add(word);
        }
        return newLyrics;
    }
}

