package edu.wpi.teamname;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ViewModel {
  // This is for data-binding
  // There are "property" classes for everything you would need to display
  // LOOK AT THE ACTUAL FXML FILE (not in scene builder!) to see how this data-binding works.
  //   Specifically, the {controller.monthlyPayment} for the text attribute (controller refers to
  // this controller)
  // Note how there is no reference to the Label anywhere in this class -> this is what we want:
  // only values, no views
  // You do not have to use data-binding, but it would be best if you did to follow MVVM.
  final DoubleProperty monthlyPayment = new SimpleDoubleProperty(0);

  // I experimented with data-binding for TextFields but it required too much boilerplate, so use
  // the standard method
  // Although this does break the MVVM architecture by holding a view in the ViewModel,
  //   with JavaFX this is the easiest way to go about it
  @FXML private TextField loanAmount, rate, numMonths;

  @FXML
  private void calculatePressed(ActionEvent actionEvent) {
    final double a = Double.parseDouble(loanAmount.getText()),
        r = Double.parseDouble(rate.getText()),
        m = Double.parseDouble(numMonths.getText());
    // I have no idea if this formula is right but that is not the point
    setMonthlyPayment(a * Math.pow(1 + r / m, 12)); // todo put in model
    // I will push this to model later so you can see how that works. For now, it is calculated
    // here,
    //  which is fine for an application this small, but imagine if that formula was a complex
    // operation.
  }

  /*
  The following methods are for data-binding and were created automatically by IntelliJ through:
   hitting Alt-Insert -> Getter and Setter
   Do not delete any, they should all remain to keep javafx with data-binding working properly
   */

  public double getMonthlyPayment() {
    return monthlyPayment.get();
  }

  public DoubleProperty monthlyPaymentProperty() {
    return monthlyPayment;
  }

  public void setMonthlyPayment(double monthlyPayment) {
    this.monthlyPayment.set(monthlyPayment);
  }
}
