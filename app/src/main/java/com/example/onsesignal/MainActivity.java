package com.example.onsesignal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button button;

    JSONObject object;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.tv);
        button=findViewById(R.id.button);

        String playerId=OneSignal.getDeviceState().getUserId();
        tv.setText(playerId);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    object=new JSONObject("{ " +
                            "'include_player_ids' : ['d429a92d-bc01-4cdb-86a8-0ba7b2d2f0ba'] ," +
                            "'contents':{'en': 'this is notification body' } , "+
                            "'headers':{ 'en':'this is notification title' }"
                            +
                            " }");

                    OneSignal.postNotification(object, new OneSignal.PostNotificationResponseHandler() {
                        @Override
                        public void onSuccess(JSONObject jsonObject) {
                            Toast.makeText(MainActivity.this,"Notification Sent",Toast.LENGTH_LONG).show();
                        }

                        @Override
                        public void onFailure(JSONObject jsonObject) {
                            Toast.makeText(MainActivity.this,"Notification Not Sent",Toast.LENGTH_LONG).show();

                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this,"JSON Error",Toast.LENGTH_LONG).show();

                }
            }
        });




    }
}