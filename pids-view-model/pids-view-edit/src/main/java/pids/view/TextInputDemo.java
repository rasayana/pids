package pids.view;

import java.util.Optional;

import javafx.application.Application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;

import javafx.stage.Stage;

public class TextInputDemo extends Application
{
   @Override
   public void start(Stage primaryStage)
   {
      TextInputDialog textInput = new TextInputDialog("Enter some text");
      textInput.setTitle("Enter some text");
      textInput.setHeaderText("Text Input");
      textInput.setContentText("Please enter something:");
      Optional<String> result = textInput.showAndWait();
      if (result.isPresent())
      {
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setContentText("You entered: " + result.get());
         alert.showAndWait();
      }
   }
}