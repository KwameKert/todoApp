package com.codeinsyt.todoApp;

import com.codeinsyt.todoApp.dataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private TextArea todoItemDetails;

    @FXML
    private Label dueDateDetails;

    public void initialize(){
        TodoItem todoItem1 = new TodoItem("Work on skuulBa", "Need to finish building the skuulba application", LocalDate.of(2020,05,28));
        TodoItem todoItem2 = new TodoItem("Project work chapter 4", "Work on final year project chapter 4 documentation", LocalDate.of(2020,05,29));
        TodoItem todoItem3 = new TodoItem("Build compiler", "Build an ILOC compiler", LocalDate.of(2020,06,7));
        TodoItem todoItem4 = new TodoItem("Docker Setup", "Set skuulba on docker", LocalDate.of(2020,05,27));
        TodoItem todoItem5 = new TodoItem("Hopepress Deployment", "Deploy website to firebase server", LocalDate.of(2020,04,7));

        todoItems = new ArrayList<TodoItem>();
        todoItems.add(todoItem1);
        todoItems.add(todoItem2);
        todoItems.add(todoItem3);
        todoItems.add(todoItem4);
        todoItems.add(todoItem5);

        this.addEventListener();

        //populating listview
        this.todoListView.getItems().setAll(this.todoItems);
        this.todoListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.todoListView.getSelectionModel().selectFirst();
    }

    //on adding listener on todoItem change
    public void addEventListener(){
        DateTimeFormatter df = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        this.todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TodoItem>() {
            @Override
            public void changed(ObservableValue<? extends TodoItem> observableValue, TodoItem todoItem, TodoItem t1) {
                if(t1 != null){
                    todoItemDetails.setText(t1.getDetails());
                    dueDateDetails.setText(df.format(t1.getDueDate()));
                }
            }
        });
    }

//    @FXML
//    public void handleItemSelected(){
//        TodoItem todoItem = this.todoListView.getSelectionModel().getSelectedItem();
//        StringBuilder sb = new StringBuilder(todoItem.getDetails());
//        sb.append("\n\n\n\n");
//        sb.append("Date Due: ");
//        sb.append(todoItem.getDueDate());
//
//        this.todoItemDetails.setText(sb.toString());
//    }


}
