package android.example.exercise1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText messageEdit = findViewById(R.id.editText);
        final Button previewBtn = findViewById(R.id.btnPreview);
        previewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreviewActivity(messageEdit.getText().toString());
            }
        });

    }
    private void openPreviewActivity(String text) {
        PreviewActivity.start(this, text);
    }
}
