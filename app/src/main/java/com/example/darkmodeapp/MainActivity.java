/*
color.xml ve color.xml(night) dosyalarında 'backGroundColor' ve
'textColor' renk kodları girilir. (Tam tersi şekilde)
Ayrıca activity_main.xml dosyasında 'backGroundColor' ve 'textColor'
ifadeleri belirtilir.
  */

package com.example.darkmodeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
// gerekli library'ler import edilir
public class MainActivity
        extends AppCompatActivity {

    private Button button;

    /* Android'in yerelleştirilmiş kaynaklar mekanizması, kodunuzu değiştirmek
     zorunda kalmadan çeşitli yerel ayarları desteklemenize olanak tanır. */
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(
            Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);

        // Uygulamanın kaydetme işlemini
        // SharedPreferences ile gerçekleştiriyoruz
        SharedPreferences sharedPreferences
                = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        final SharedPreferences.Editor editor
                = sharedPreferences.edit();
        final boolean isDarkModeOn
                = sharedPreferences
                .getBoolean(
                        "isDarkModeOn", false);

        // Kullanıcı uygulamayı yeniden açtığında
        // Karanlık veya aydınlık mod işlemi devreye girecek
        if (isDarkModeOn) {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_YES);
            button.setText(
                    "Aydınlık Modu Aç");
        }
        else {
            AppCompatDelegate
                    .setDefaultNightMode(
                            AppCompatDelegate
                                    .MODE_NIGHT_NO);
            button.setText(
                            "Karanlık Modu Aç");
        }

        button.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View view)
                    {
                        // Kullanıcı butona bastığında
                        // Karanlık Mod Butonu
                        if (isDarkModeOn) {

                            // Karanlık mod açıksa onu kapatır
                            AppCompatDelegate
                                    .setDefaultNightMode(
                                            AppCompatDelegate
                                                    .MODE_NIGHT_NO);
                            // Mod durumu
                            // belirleniyor
                            editor.putBoolean(
                                    "isDarkModeOn", false);
                            editor.apply();

                            // Buton üzerindeki ifade değişir
                            button.setText(
                                    "Aydınlık modu aç");
                        }
                        else {

                            // Karanlık mod kapalı ise
                            // false durumu ile onu açacak
                            AppCompatDelegate
                                    .setDefaultNightMode(
                                            AppCompatDelegate
                                                    .MODE_NIGHT_YES);

                            // Karanlık mod açık ise
                            // true durumu ile onu açacak
                            editor.putBoolean(
                                    "isDarkModeOn", true);
                            editor.apply();

                            // Buton üzerindeki ifade değişir
                            button.setText(
                                    "Aydınlık modu aç");
                        }
                    }
                });
    }
}
