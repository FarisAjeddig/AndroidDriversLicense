package dim.uqac;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Switch;
import android.widget.Toast;
import android.view.KeyEvent;



public class MainActivity extends AppCompatActivity {

    protected Switch switchGender;
    protected TextView gender;
    protected CheckBox printPaiement;
    protected TextView paiement;
    protected Button notifButton;

    private Menu m = null;

    private static final String CHANNEL_ID = "channel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setHasOptionsMenu(true);
        createNotificationChannel();


        switchGender = findViewById(R.id.switch1);
        gender = findViewById(R.id.sexe_value);
        printPaiement = findViewById(R.id.CheckPrintPaiement);
        paiement = findViewById(R.id.paiement);
        notifButton = findViewById(R.id.sendNotification);

        switchGender.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    gender.setText(R.string.m);
                } else {
                    gender.setText(R.string.f);
                }
            }
        });

        printPaiement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    paiement.setText("");
                } else {
                    paiement.setText(R.string.texte_paiement);
                }
            }
        });

        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        notifButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an explicit intent for an Activity in your app
               // Intent intent = new Intent(this, MainActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                //PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                /*NotificationCompat.Builder n = new NotificationCompat.Builder(this, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notification toute simple !")
                        .setContentText("Viens voir le permis de conduire ;)")
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setVibrate(new long[]{0, 500, 110, 500, 110})
                        .setLights(0xff00ff00, 300, 100)
                        .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        //.setAutoCancel(true)
                        .build();
                try {
                    nm.notify(1, n);
                } catch(Exception e){
                    Log.e("Notification Error", e.getMessage());
                }*/
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_main_land);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
        }
    }

    public boolean onCreateOptionsMenu(ContextMenu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        m = menu;
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu1:
                Log.d("DIM", "Clic sur le menu 1!");
                return true;
            case R.id.menu2:
                Log.d("DIM", "Clic sur le menu 2!");
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    public void majNames(View view) {
        Button button = (Button)view;
        TextView prenom = (TextView)findViewById(R.id.prenom);
        TextView nom = (TextView)findViewById(R.id.nom);

        EditText newPrenom = (EditText)findViewById(R.id.nouveau_prenom);
        EditText newNom = (EditText)findViewById(R.id.nouveau_nom);

        prenom.setText(newPrenom.getText());
        nom.setText(newNom.getText());

        newPrenom.setText("");
        newNom.setText("");
    }

    public void sendToast(View view) {
        Context context = getApplicationContext();
        CharSequence text = "Et voici le toast !";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Nom du channel";
            String description = "Description du channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
