package gt.com.gtnote;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.emarsys.Emarsys;
import com.emarsys.config.EmarsysConfig;

import java.util.HashMap;

import gt.com.gtnote.dagger.ContextModule;
import gt.com.gtnote.dagger.DaggerNoteManagerComponent;
import gt.com.gtnote.dagger.NoteManagerComponent;

/**
 * Custom Application class, to instantiate things over the whole lifetime of the app.
 */
public class ApplicationClass extends Application
{
    private NoteManagerComponent m_NoteManagerComponent;

    @Override
    public void onCreate()
    {
        super.onCreate();

        EmarsysConfig config = new EmarsysConfig.Builder()
                .application(this)
                .contactFieldId(3)
                .mobileEngageApplicationCode("EMS02-C18FB")
            .build();
        Emarsys.setup(config);

        HashMap map = new HashMap<String, String>();
        map.put("product", "undies");
        Emarsys.trackCustomEvent("purchase", map);

        this.createNotificationChannel();


        m_NoteManagerComponent = DaggerNoteManagerComponent.builder()
                .contextModule(new ContextModule(this))
                .build();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "security updates";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel1 = new NotificationChannel("news", name, importance);
            channel1.setDescription("NEEEWZ");
            NotificationChannel channel2 = new NotificationChannel("alert", name, importance);
            channel2.setDescription("ALLLEEERT");
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel1);
            notificationManager.createNotificationChannel(channel2);
        }
    }


    public NoteManagerComponent getNoteManagerComponent()
    {
        return m_NoteManagerComponent;
    }
}
