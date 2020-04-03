package edu.wpi.teamname;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class App extends Application {

  ViewModel viewModel;

  @Override
  public void init() {
    log.info("Starting Up");
    Model.getInstance().register();
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("views/LoanCalc.fxml"));
    final Parent root = loader.load();
    viewModel = loader.getController();
    primaryStage.setScene(new Scene(root));
    primaryStage.show();
  }

  @Override
  public void stop() {
    log.info("Shutting Down");
    Model.getInstance().unregister(); // Note the unregister call here
    viewModel.unregister(); // and here
  }
}
