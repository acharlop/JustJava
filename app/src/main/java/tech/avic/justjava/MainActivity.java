package tech.avic.justjava;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int quantity = 2;
    boolean hasWhippedCream = false;
    boolean hasChocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setHasWhippedCream(View v) {
        hasWhippedCream = ((CheckBox) v).isChecked();
    }

    public void setHasChocolate(View v) {
        hasChocolate = ((CheckBox) v).isChecked();
    }

    private String getName() {
        EditText edit = (EditText) findViewById(R.id.name_text);
        return edit.getText().toString();
    }
    private int calculatePrice() {
        int price = 5;
        if (hasWhippedCream) {
            price += 1;
        }
        if (hasChocolate) {
            price += 2;
        }
        return price * quantity;
    }

    /**
     * Creates display message for order
     *
     * @param price of total order
     * @return text summary
     */
    private String createOrderSummary(int price) {
        String message = "Name: " + getName() + "\n";
        if (hasWhippedCream) {
            message += "Add whipped cream\n";
        }
        if (hasChocolate) {
            message += "Add Chocolate\n";
        }
        message += "Quantity: " + quantity + "\n";
        message += "Total: $" + price + "\n";
        message += "Thank you!";
        return message;
    }

    public void submitOrder(View view) {
        int price = calculatePrice();
        String message = createOrderSummary(price);
        emailMessage(message);
    }

    private void emailMessage(String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, "test@test.co");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order");
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

}
