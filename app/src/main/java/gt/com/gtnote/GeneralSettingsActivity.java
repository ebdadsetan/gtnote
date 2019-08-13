package gt.com.gtnote;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class GeneralSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_settings);

        setTitle("General Settings");
    }
}
