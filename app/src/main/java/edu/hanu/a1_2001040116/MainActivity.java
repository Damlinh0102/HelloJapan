package edu.hanu.a1_2001040116;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private final String[] c = {"a", "i", "e", "o", "u", "ka", "ki", "ku", "ke", "ko",
    "sa", "shi", "su", "se", "so", "ta", "chi", "tsu", "te", "to",
    "na", "ni", "nu", "ne", "no", "ha", "hi", "fu", "he", "ho",
    "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo",
    "ra", "ri", "ru", "re", "ro", "wa", "wo", "n" };
    Button hiraganaBtn;
    Button katakanaBtn;
    TextView katakanaTitle;
    TextView hiraganaTitle;
    ScrollView hiragana;
    ScrollView katakana;
    LinearLayout linearLayout;
    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hiragana = findViewById(R.id.hiragana);
        katakana = findViewById(R.id.katakana);
        katakana.setAlpha(0f);
        hiragana.bringToFront();
        katakanaTitle = findViewById(R.id.katakanaTitle);
        katakanaTitle.setAlpha(0f);
        hiraganaTitle = findViewById(R.id.hiraganaTitle);
        hiraganaBtn = findViewById(R.id.hiraganaBtn);
        katakanaBtn = findViewById(R.id.katakanaBtn);
        hiraganaBtn.setBackgroundTintList(getColorStateList(R.color.purple_200));
        linearLayout = findViewById(R.id.layoutBtn);
        linearLayout.bringToFront();
        onClickDisplayKatakana();
        onClickDisplayHiragana();
        sound("h");
        sound("k");
    }

    public void onClickDisplayKatakana() {
        katakanaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                katakana.bringToFront();
                linearLayout.bringToFront();
                katakanaTitle.setAlpha(1f);
                katakanaTitle.animate().scaleXBy(0.5f).scaleYBy(0.5f).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        katakanaTitle.animate().scaleXBy(-0.5f).scaleYBy(-0.5f).setDuration(1000);
                    }
                });
                hiraganaTitle.setAlpha(0f);
                hiragana.setAlpha(0f);
                katakana.setAlpha(1f);
                katakanaBtn.setBackgroundTintList(getColorStateList(R.color.purple_200));
                hiraganaBtn.setBackgroundTintList(getColorStateList(R.color.purple_500));
            }
        });
    }

    public void onClickDisplayHiragana() {
        hiraganaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hiragana.bringToFront();
                linearLayout.bringToFront();
                hiraganaTitle.setAlpha(1f);
                katakanaTitle.setAlpha(0f);
                hiragana.setAlpha(1f);
                hiraganaTitle.animate().scaleXBy(0.5f).scaleYBy(0.5f).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        hiraganaTitle.animate().scaleXBy(-0.5f).scaleYBy(-0.5f).setDuration(1000);
                    }
                });
                katakana.setAlpha(0f);
                hiraganaBtn.setBackgroundTintList(getColorStateList(R.color.purple_200));
                katakanaBtn.setBackgroundTintList(getColorStateList(R.color.purple_500));
            }
        });
    }

    public void sound(String x) {
        for(int i = 0; i < c.length; i ++) {
            String s = c[i];
            String x_ =  x + "_" + s;
            int x_id = getResources().getIdentifier(x_, "id", getPackageName());
            int sound_ = getResources().getIdentifier(s, "raw", getPackageName());
            try {
                ImageButton k_imgBtn = findViewById(x_id);
                k_imgBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(mediaPlayer != null) {
                            if(mediaPlayer.isPlaying()) {
                                mediaPlayer.stop();
                            }
                        }
                        mediaPlayer = MediaPlayer.create(MainActivity.this, sound_);
                        mediaPlayer.start();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}