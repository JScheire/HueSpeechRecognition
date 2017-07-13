package com.philips.lighting.quickstart;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.philips.lighting.hue.listener.PHLightListener;
import com.philips.lighting.hue.sdk.PHHueSDK;
import com.philips.lighting.model.PHBridge;
import com.philips.lighting.model.PHBridgeResource;
import com.philips.lighting.model.PHHueError;
import com.philips.lighting.model.PHLight;
import com.philips.lighting.model.PHLightState;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * MyApplicationActivity - The starting point for creating your own Hue App.
 * Currently contains a simple view with a button to change your lights to random colours.  Remove this and add your own app implementation here! Have fun!
 *
 * @author SteveyO
 *
 */
public class MyApplicationActivity extends Activity {
    private Button openMic;
    private TextView showVoiceText;
    private final int REQ_CODE_SPEECH_OUTPUT = 143;
    private PHHueSDK phHueSDK;

    public static final String TAG = "QuickStart";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        phHueSDK = PHHueSDK.create();
        setTitle(R.string.app_name);
        openMic = (Button) findViewById(R.id.button);
        showVoiceText = (TextView) findViewById(R.id.showVoiceOutput);

        openMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnToOpenMic();
            }
        });
    }




            private void btnToOpenMic() {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak Now");

                try {
                    startActivityForResult(intent, REQ_CODE_SPEECH_OUTPUT);

                } catch (ActivityNotFoundException tim) {

                }
            }

            private void randomLights(){
                PHBridge bridge = phHueSDK.getSelectedBridge();

                List<PHLight> allLights = bridge.getResourceCache().getAllLights();

                for (PHLight light : allLights) {
                    PHLightState lightState = new PHLightState();
                    TextView jeff = (TextView) findViewById(R.id.showVoiceOutput);
                    lightState.setBrightness(100);
                    lightState.setSaturation(254);
                    if (jeff.getText().equals("red")) {
                        lightState.setHue(0);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("blue")) {
                        lightState.setHue(46920);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("green")) {
                        lightState.setHue(25500);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("pink")) {
                        lightState.setHue(56225);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("yellow")) {
                        lightState.setHue(12750);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("orange") || jeff.getText().equals("Orange")){
                        lightState.setHue(6375);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("white") || jeff.getText().equals("White")) {
                        lightState.setSaturation(0);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("purple")) {
                        lightState.setHue(50000);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("light blue")) {
                        lightState.setHue(40000);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("turn off") || jeff.getText().equals("off")){
                        lightState.setOn(false);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("turn on") || jeff.getText().equals("on")){
                        lightState.setOn(true);
                        bridge.updateLightState(light, lightState, listener);

                    }
                    if (jeff.getText().equals("dim")){
                        lightState.setBrightness(50);
                        bridge.updateLightState(light, lightState, listener);

                    }


                    }

                }

            // If you want to handle the response from the bridge, create a PHLightListener object.
            PHLightListener listener = new PHLightListener() {

                @Override
                public void onSuccess() {
                }

                @Override
                public void onStateUpdate(Map<String, String> arg0, List<PHHueError> arg1) {
                    Log.w(TAG, "Light has updated");
                }

                @Override
                public void onError(int arg0, String arg1) {
                }

                @Override
                public void onReceivingLightDetails(PHLight arg0) {
                }

                @Override
                public void onReceivingLights(List<PHBridgeResource> arg0) {
                }

                @Override
                public void onSearchComplete() {
                }
            };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TextView jeff = (TextView) findViewById(R.id.showVoiceOutput);

        switch (requestCode) {
            case REQ_CODE_SPEECH_OUTPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> voiceInText = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    jeff.setText(voiceInText.get(0));
                    randomLights();
                }
                break;
            }
        }
    }
}

