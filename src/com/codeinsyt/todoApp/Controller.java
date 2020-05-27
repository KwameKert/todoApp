package com.codeinsyt.todoApp;

import com.codeinsyt.todoApp.dataModel.TodoItem;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;
    @FXML
    private ListView todoListView;

    public void initialize(){
        TodoItem todoItem1 = new TodoItem("Work on skuulBa", "Need to finish building the skuulba application", LocalDate.of(2020,05,28));
        TodoItem todoItem2 = new TodoItem("Project work chapter 4", "Work on final year project chapter 4 documentation", LocalDate.of(2020,05,29));
        TodoItem todoItem3 = new TodoItem("Build compiler", "Build an ILOC compiler", LocalDate.of(2020,06,7));
        TodoItem todoItem4 = new TodoItem("Docker Setup", "Set skuulba on docker", LocalDate.of(2020,06,7));
        TodoItem todoItem5 = new TodoItem("Hopepress Deployment", "Deploy website to firebase server", LocalDate.of(2020,06,7));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(todoItem1);
        todoItems.add(todoItem2);
        todoItems.add(todoItem3);
        todoItems.add(todoItem4);
        todoItems.add(todoItem5);

        //populating listview
        this.todoListView.getItems().setAll(this.todoItems);
        this.todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }



}
