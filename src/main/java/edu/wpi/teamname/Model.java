package edu.wpi.teamname;

import lombok.Getter;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * A class representing the model In reality, for something as simple as this application, you would
 * not factor this out into a model But to show MVVM with event-driven design, here is a separate
 * model class
 */
public class Model {
  /** An event captured by the model that tells it to calculate a monthly payment */
  public static class CalculateMonthlyPaymentEvent {
    final double a, r, m;

    public CalculateMonthlyPaymentEvent(double a, double r, double m) {
      this.a = a;
      this.r = r;
      this.m = m;
    }
  }

  /** An event fired from model that signifies when a monthly payment has been calculated */
  public static class MonthlyPaymentCalculatedEvent {
    final double value;

    public MonthlyPaymentCalculatedEvent(double value) {
      this.value = value;
    }
  }

  @Subscribe
  @SuppressWarnings("unused") // suppresses the "unused" warning in IntelliJ
  public void onCalculateMonthlyPaymentEvent(CalculateMonthlyPaymentEvent event) {
    // I have no idea if this formula is right but that is not the point
    final double monthlyPayment = event.a * Math.pow(1 + event.r / event.m, 12);
    // Send the value back to view model via another event
    EventBus.getDefault().post(new MonthlyPaymentCalculatedEvent(monthlyPayment));
  }

  // We are using a singleton-esque model for ease of use:
  @Getter private static Model instance = new Model();

  public void register() {
    // Register this model before use (this is required). See App.java for its use
    EventBus.getDefault().register(this);
  }

  public void unregister() {
    // Unregister this model after it is no longer needed (this is required). See App.java for its use
    EventBus.getDefault().unregister(this);
  }
}
