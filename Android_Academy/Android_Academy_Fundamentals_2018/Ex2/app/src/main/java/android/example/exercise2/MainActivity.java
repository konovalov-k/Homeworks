package android.example.exercise2;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String EMAIL_ADDRESS = "";
    private static final String EMAIL_SUBJECT = "Buisness letter";
    private static final String INSTAGRAM_URL = "https://www.instagram.com";
    private static final String FACEBOOK_URL = "https://www.facebook.com/";
    private static final String VK_URL = "https://vk.com/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupDisclaimer();
        setupSocialNetworkButtons();
        setupSendButton();
    }

    private void setupDisclaimer() {
        RelativeLayout bottomLinearLayout = findViewById(R.id.footer);
        TextView disclaimerTextView = new TextView(this);
        disclaimerTextView.setText(R.string.disclaimer_text);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, R.id.vk);
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        disclaimerTextView.setLayoutParams(params);
        bottomLinearLayout.addView(disclaimerTextView);
    }
    private void startBrowserWithSocial(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
    private void setupSocialNetworkButtons() {

        ImageView instagramImageView = (ImageView) findViewById(R.id.empty1);
        ImageView facebookImageView = (ImageView)findViewById(R.id.vk);
        ImageView vkImageView = (ImageView)findViewById(R.id.empty2);
        instagramImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBrowserWithSocial(INSTAGRAM_URL);
            }
        });
        facebookImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBrowserWithSocial(FACEBOOK_URL);
            }
        });
        vkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBrowserWithSocial(VK_URL);
            }
        });
    }
    private void startEmailClient(String text) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_TEXT, text);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{EMAIL_ADDRESS});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, EMAIL_SUBJECT);
        if ((emailIntent.resolveActivity(getPackageManager())!=null)){
            startActivity(emailIntent);
        } else {
            Toast.makeText(this, R.string.btn_txt, Toast.LENGTH_LONG).show();
        }

    }
    private void setupSendButton() {
        Button sendMessageButton = (Button)findViewById(R.id.btn);
        final EditText editText = (EditText)findViewById(R.id.editText);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textToSend = editText.getText().toString();
                if (!textToSend.isEmpty()){
                    startEmailClient(textToSend);
                }
            }}
         );
    }
}
