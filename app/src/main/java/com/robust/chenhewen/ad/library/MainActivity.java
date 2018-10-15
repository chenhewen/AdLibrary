package com.robust.chenhewen.ad.library;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.robust.adsource.ModuleContext;
import com.robust.adsource.ad.loader.admob.AdmobBannerLoader;
import com.robust.adsource.adsource.AdType;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ModuleContext.init(this, true);
        ModuleContext.setAdSourceId(new AdSourceId());

        setBtn();
    }

    private void setBtn() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent entryIntent = AdActivity.getEntryIntent(MainActivity.this, AdActivity.ENTRY_MAIN);
                startActivity(entryIntent);
            }
        });
    }


}
