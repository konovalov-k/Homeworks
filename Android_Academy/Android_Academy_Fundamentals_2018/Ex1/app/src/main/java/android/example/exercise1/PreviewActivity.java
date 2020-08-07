package android.example.exercise1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PreviewActivity extends AppCompatActivity {
    private static final String EXTRA_MESSAGE = "extra:message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);

        final String messageString = getIntent().getStringExtra(EXTRA_MESSAGE);
        final TextView messageText = findViewById(R.id.txtOutput);
        messageText.setText(messageString);

        final Button emailBtn = findViewById(R.id.email);
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmailApp(messageString);
            }
        });
    }

    public static void start(Activity activity, String message) {
        final Intent intent = new Intent(activity, PreviewActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        activity.startActivity(intent);

    }

    private void openEmailApp(String messageString) {
        final Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(Uri.parse(String.format("mailto:%s", getString(R.string.email_address))))
                .putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_subject))
                .putExtra(Intent.EXTRA_TEXT, messageString);

        // Check if the system can handle this type of intent or startActivity will crash otherwise.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.error_no_email_app, Toast.LENGTH_LONG).show();
        }
    }
}
