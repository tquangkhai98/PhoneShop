package com.capstone.phoneshop.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.capstone.phoneshop.Model.Author;
import com.capstone.phoneshop.Model.Message;
import com.capstone.phoneshop.R;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import net.gotev.speech.GoogleVoiceTypingDisabledException;
import net.gotev.speech.Speech;
import net.gotev.speech.SpeechDelegate;
import net.gotev.speech.SpeechRecognitionNotAvailable;
import net.gotev.speech.ui.SpeechProgressView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.UUID;

import static android.speech.RecognizerIntent.RESULT_AUDIO_ERROR;
import static android.speech.RecognizerIntent.RESULT_CLIENT_ERROR;
import static android.speech.RecognizerIntent.RESULT_NETWORK_ERROR;
import static android.speech.RecognizerIntent.RESULT_NO_MATCH;
import static android.speech.RecognizerIntent.RESULT_SERVER_ERROR;

public class ChatboxActivity extends AppCompatActivity implements MessagesListAdapter.SelectionListener,
        MessagesListAdapter.OnLoadMoreListener, MessageInput.InputListener,
        MessageInput.AttachmentsListener,SpeechDelegate{

    private final int PERMISSIONS_REQUEST = 1;

    private MessagesListAdapter<Message> adapter;
    private ImageLoader imageLoader;
    private MessagesList messagesList;
    private MessageInput messageInput;
    private String TAG = "chatbox";
    private SpeechProgressView progress;
    private LinearLayout linearLayout;
    private TextView textSpeech;

    private boolean ready;
    private TextToSpeech t2s;

    public Author bot;
    public Author user;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Speech.getInstance().shutdown();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbox);
        setTitle(R.string.title_activity_chatbox);
        Speech.init(this, getPackageName());
        linearLayout = findViewById(R.id.linearLayout);
        progress = findViewById(R.id.progress);
        textSpeech = findViewById(R.id.textSpeech);
        int[] colors = {
                ContextCompat.getColor(this, android.R.color.black),
                ContextCompat.getColor(this, android.R.color.darker_gray),
                ContextCompat.getColor(this, android.R.color.black),
                ContextCompat.getColor(this, android.R.color.holo_orange_dark),
                ContextCompat.getColor(this, android.R.color.holo_red_dark)
        };
        progress.setColors(colors);
        /*mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //intent.putExtra("android.speech.extra.DICTATION_MODE", true);
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, new Long(100));
        intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS,new Long(100));
        //intent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS,new Long(1000));
        intent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS,true);
        //intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,new Integer(5));
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        SpeechRecognitionListener listener = new SpeechRecognitionListener();
        mSpeechRecognizer.setRecognitionListener(listener);*/


        imageLoader = new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {
                Picasso.get().load(url).into(imageView);
            }
        };

        adapter = new MessagesListAdapter<>("0",imageLoader);
        messagesList = findViewById(R.id.messagesList);
        messagesList.setAdapter(adapter);
        messageInput = findViewById(R.id.input);
        messageInput.setInputListener(this);
        messageInput.setAttachmentsListener(this);

        bot = new Author("1","User","https://pbs.twimg.com/media/C9NPAN8V0AAnI3F.png",true);
        user = new Author("0","Trần Quang Khải","https://scontent.fdad3-2.fna.fbcdn.net/v/t1.0-1/p240x240/65781335_1369044639936846_7825645702842679296_o.jpg?_nc_cat=105&_nc_ohc=IFPrRTl1l0YAQl7y1T-kXOXHlwZHO7lM4kkFLrRYvtDBCFlrjk2e8RS0A&_nc_ht=scontent.fdad3-2.fna&oh=12cd2c260d547c2a2a973324f022744e&oe=5E7D5D65",true);

        t2s = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                setTextToSpeechLanguage();
            }
        });
        promptSpeechInput();

    }





    private void promptSpeechInput() {

        if (Speech.getInstance().isListening()) {
            Speech.getInstance().stopListening();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                onRecordAudioPermissionGranted();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST);
            }
        }

        /*if (Speech.getInstance().isListening()) {
            Speech.getInstance().stopListening();
        } else {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED) {
                onRecordAudioPermissionGranted();
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, PERMISSIONS_REQUEST);
            }
        }*/

        /*SpeechRecognizer speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.startListening(intent);
        new CountDownTimer(5000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(getApplicationContext(),millisUntilFinished/1000+"",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                Toast.makeText(getApplicationContext(),"Finished",Toast.LENGTH_SHORT).show();
                //finishActivity(REQ_CODE_SPEECH_INPUT);
                speechRecognizer.stopListening();
            }

        }.start();*/

        /*try {

            //startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);

        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != PERMISSIONS_REQUEST) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        } else {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay!
                onRecordAudioPermissionGranted();
            } else {
                // permission denied, boo!
                Toast.makeText(ChatboxActivity.this, "Yều cầu quyền ghi âm", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void onRecordAudioPermissionGranted() {
        linearLayout.setVisibility(View.VISIBLE);

        try {
            Speech.getInstance().stopTextToSpeech();
            Speech.getInstance().startListening(progress, ChatboxActivity.this);

        } catch (SpeechRecognitionNotAvailable exc) {
            //showSpeechNotSupportedDialog();

        } catch (GoogleVoiceTypingDisabledException exc) {
            //showEnableGoogleVoiceTyping();
        }
    }

    /**
     * Receiving speech input
     * */
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    //txtSpeechInput.setText(result.get(0));
                    //textFromSpeech = result.get(0).toString();
                    Message newMessage = new Message("0",user,result.get(0),new Date());
                    adapter.addToStart(newMessage,true);
                }

                break;
            }


        }
    }*/



    @Override
    public void onLoadMore(int page, int totalItemsCount) {
        new Handler().postDelayed(new Runnable() { //imitation of internet connection
            @Override
            public void run() {
                ArrayList<Message> messages = new ArrayList<>();
                //lastLoadedDate = messages.get(messages.size() - 1).getCreatedAt();
                //messagesAdapter.addToEnd(messages, false);
            }
        }, 1000);
    }

    @Override
    public void onSelectionChanged(int count) {
        //this.selectionCount = count;
    }

    @Override
    public void onAddAttachments() {
        promptSpeechInput();
        //Log.i("Text",textFromSpeech);
        // Toast.makeText(getApplicationContext(),textFromSpeech,Toast.LENGTH_LONG).show();
      /*  Message newMessage;
        if(!textFromSpeech.equals("")){
            newMessage = new Message("",bot,textFromSpeech,new Date());
            adapter.addToStart(newMessage, true);
        }
        adapter.notifyDataSetChanged();*/

    }


    @Override
    public boolean onSubmit(CharSequence input) {
        String text = messageInput.getInputEditText().getText().toString();
        Message newMessage = new Message("1",bot,text,new Date());
        speakOut(text);
        adapter.addToStart(newMessage, true );
        return true;
    }

    private void setTextToSpeechLanguage() {
        Locale language = new Locale("vi","VN");
        if (language == null) {
            this.ready = false;
            Toast.makeText(this, "Not language selected", Toast.LENGTH_SHORT).show();
            return;
        }
        int result = t2s.setLanguage(language);
        if (result == TextToSpeech.LANG_MISSING_DATA) {
            this.ready = false;
            Toast.makeText(this, "Missing language data", Toast.LENGTH_SHORT).show();
            return;
        } else if (result == TextToSpeech.LANG_NOT_SUPPORTED) {
            this.ready = false;
            Toast.makeText(this, "Language not supported", Toast.LENGTH_SHORT).show();
            return;
        } else {
            this.ready = true;
            Locale currentLanguage = t2s.getVoice().getLocale();
            Toast.makeText(this, "Language " + currentLanguage, Toast.LENGTH_SHORT).show();
        }
    }

    private void speakOut(String toSpeak) {
        if (!ready) {
            Toast.makeText(this, "Text to Speech not ready", Toast.LENGTH_LONG).show();
            return;
        }

        // Văn bản cần đọc.
        //Toast.makeText(this, toSpeak, Toast.LENGTH_SHORT).show();
        String utteranceId = UUID.randomUUID().toString();
        t2s.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, utteranceId);
    }


    @Override
    public void onStartOfSpeech() {

    }

    @Override
    public void onSpeechRmsChanged(float value) {

    }

    @Override
    public void onSpeechPartialResults(List<String> results) {
        /*String text ="";
        for (String partial : results) {
            text += partial+" ";
        }
        Message newMessage = new Message("",bot,text,new Date());
        adapter.addToStart(newMessage, true);*/
    }

    @Override
    public void onSpeechResult(String result) {
        linearLayout.setVisibility(View.GONE);
        if (result.isEmpty()) {
            Toast.makeText(getApplicationContext(),"Không nghe được",Toast.LENGTH_SHORT).show();
        } else {
            Message newMessage = new Message("",user,result,new Date());
            adapter.addToStart(newMessage, true);
            //textSpeech.setText(result);
        }
    }
}
