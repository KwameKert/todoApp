package com.codeinsyt.todoApp;

import com.codeinsyt.todoApp.dataModel.TodoData;
import com.codeinsyt.todoApp.dataModel.TodoItem;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Controller {

    private List<TodoItem> todoItems;

    @FXML
    private ListView<TodoItem> todoListView;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    private TextArea todoItemDetails;

    @FXML
    private Label dueDateDetails;

    public void initialize(){

        this.addEventListener();

        //populating listview
        this.todoListView.getItems().setAll(TodoData.getInstance().getTodoItems());
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

    @FXML
    public void showNewItemDialog() throws Exception{
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(this.mainBorderPane.getScene().getWindow());

        try{
            Parent root = FXMLLoader.load(getClass().getResource("addtododialog.fxml"));
            dialog.getDialogPane().setContent(root);

        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            System.out.println("Add Was Pressed");
        }else{
            System.out.println("Cancel was pressed");
        }
    }


}
