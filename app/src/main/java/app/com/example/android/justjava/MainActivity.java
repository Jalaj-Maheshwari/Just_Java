package app.com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends ActionBarActivity {

    int quantity = 1;
    Boolean hasWhippedCream, hasChocolate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View v) {
        int price = calculatePrice();

        String[] addresses = new String[2];
        addresses[0] = "jalaj.1702@gmail.com";
        addresses[1] = "mmanvi.44@gmail.com";

        String fullname = getName();

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));//So that only email apps can handle this intent and is compulsory along with SENDTO
        //intent.setType("*/*"); // No need to set it as there is no MIME thing to be sent as only mail needs to be sent but is needed compulsory for SEND,SEND_MULTIPLE .
        intent.putExtra(Intent.EXTRA_EMAIL, addresses); // for multiple addresses, for single address use 'mailto:' with 'SENDTO'
        //String subject = getString(R.string.Email_subject);
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.Email_subject,fullname));
        intent.putExtra(Intent.EXTRA_TEXT, getString(R.string.priceMessage,fullname,hasWhippedCream,hasChocolate,quantity,price));

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void increment(View v) {
        quantity++;
        if (quantity > 100) {
            Toast.makeText(this,getString(R.string.positive_toast), Toast.LENGTH_SHORT).show();
            quantity = 100;
        }
        displayQuantity(quantity);
        // displayPrice(quantity*5);
    }

    public void decrement(View v) {
        quantity--;
        if (quantity < 1) {
            Toast.makeText(this,getString(R.string.negative_toast), Toast.LENGTH_SHORT).show();
            quantity = 1;
        }

        displayQuantity(quantity);
        //displayPrice(quantity*5);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    private void cream() {
        CheckBox WhippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream);
        hasWhippedCream = WhippedCreamCheckBox.isChecked();

    }

    private void chocolate() {
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.chocolate);
        hasChocolate = ChocolateCheckBox.isChecked();

    }

    private String getName() {
        EditText Customer_name = (EditText) findViewById(R.id.name);
        return Customer_name.getText().toString();
    }


    public int calculatePrice() {
        int extra;

        cream();
        chocolate();

        if (hasWhippedCream && hasChocolate)
            extra = 3;
        else if (hasChocolate)
            extra = 2;
        else if (hasWhippedCream)
            extra = 1;
        else
            extra = 0;

        return (quantity) * (5 + extra);
    }


    /**
     * This method displays the given text on the screen.
     */
   /* private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }*/
}