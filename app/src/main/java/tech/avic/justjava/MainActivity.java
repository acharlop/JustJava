package tech.avic.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private int calculatePrice() {
        return quantity * 5;
    }

    /**
     * Creates display message for order
     *
     * @param price of total order
     * @return text summary
     */
    private String createOrderSummary(int price) {
        String message = "Name: " + "Avi Charlop" + "\n";
        message += "Quantity: " + quantity + "\n";
        message += "Total: $" + price + "\n";
        message += "Thank you!";
        return message;
    }


    public void submitOrder(View view) {
        int price = calculatePrice();
        String message = createOrderSummary(price);
        displayMessage(message);
    }

    public void increment(View view) {
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    //    The method to display the price for the given quantity on screen
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        if (message != null) {
            orderSummaryTextView.setText(message);
        }
    }
}
