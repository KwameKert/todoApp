package com.codeinsyt.todoApp.dataModel;

import javafx.collections.FXCollections;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

public class TodoData {

    private static TodoData instance = new TodoData();
    private String filename = "TodoList.txt";
    private DateTimeFormatter df;
    private List<TodoItem> todoItems;

    private TodoData() {
        this.df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
    }


    public static TodoData getInstance() {
        return instance;
    }



    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public void loadTodoItems() throws IOException {
        todoItems = FXCollections.observableArrayList();
        Path path = Paths.get(filename);
        //reading file contents
        BufferedReader br = Files.newBufferedReader(path);
        String input;

        while((input = br.readLine()) != null){
            try{
                //splitting line into array items
                String[] itemPieces = input.split("\t");
                String shortDescription = itemPieces[0];
                String details = itemPieces[1];
                String dueDate = itemPieces[2];
                //convert string date to Date Format
                LocalDate localDate = LocalDate.parse(dueDate, df);
                TodoItem todoItem = new TodoItem(shortDescription, details, localDate);
                //pushing item to items list
                this.todoItems.add(todoItem);

            }finally {
                if( br != null){
                    br.close();
                }
            }
        }
    }

    public void saveTodoItems() throws IOException {
        Path path = Paths.get(filename);
        BufferedWriter bw = Files.newBufferedWriter(path);
        try{
            Iterator<TodoItem> iter = todoItems.iterator();
            while(iter.hasNext()){
                TodoItem todoItem = iter.next();
                bw.write(String.format("%s\t%s\t%s\t",
                        todoItem.getShortDescription(),
                        todoItem.getDetails(),
                        todoItem.getDueDate().format(this.df)));
                bw.newLine();
            }
        }finally {
            if(bw != null){
                bw.close();
            }
        }
    }


}
