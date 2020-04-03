package edu.wpi.teamname;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class ViewModel {
    // This is for data-binding
    // There are "property" classes for everything you would need to display
    // LOOK AT THE ACTUAL FXML FILE (not in scene builder!) to see how this data-binding works.
    //   Specifically, the {controller.monthlyPayment} for the text attribute (controller refers to this controller)
    // Note how there is no reference to the Label anywhere in this class -> this is what we want: only values, no views
    // You do not have to use data-binding, but it would be best if you did in order to follow MVVM
    final DoubleProperty monthlyPayment = new SimpleDoubleProperty(0);

    // I experimented with data-binding for TextFields but it required too much boilerplate, so use
    // this standard method w/ injection done for you by JavaFX
    // Although this does break the MVVM architecture by holding a view in the ViewModel,
    //   with JavaFX this is the easiest way to go about it
    // to use data-binding with a textfield, see here: https://www.youtube.com/watch?v=HuMjNnFqpLE
    @FXML
    private TextField loanAmount, rate, numMonths;

    @FXML
    private void calculatePressed() {
        final double a = Double.parseDouble(loanAmount.getText()),
                r = Double.parseDouble(rate.getText()),
                m = Double.parseDouble(numMonths.getText());
        EventBus.getDefault().post(new Model.CalculateMonthlyPaymentEvent(a, r, m));
    }

  /*
  The following methods are for communication between this ViewModel and the Model
   */

    public ViewModel() {
        EventBus.getDefault().register(this);
    }

    @Subscribe
    @SuppressWarnings("unused") // doesn't affect the actual program; just silences "unused" warning in IntelliJ
    public void onMonthlyPaymentCalculatedEvent(Model.MonthlyPaymentCalculatedEvent event) {
        setMonthlyPayment(event.value);
    }

    public void unregister() {
        EventBus.getDefault().unregister(this);
    }

  /*
  The following methods are for data-binding and were created automatically by IntelliJ through:
   hitting Alt-Insert -> Getter and Setter
   Do not delete any, they should all remain to keep javafx with data-binding working properly
   */

    public double getMonthlyPayment() {
        return monthlyPayment.get();
    }

    @SuppressWarnings("unused") // this just suppresses the unused warning (doesn't affect the program at all)
    public DoubleProperty monthlyPaymentProperty() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment.set(monthlyPayment);
    }
}
