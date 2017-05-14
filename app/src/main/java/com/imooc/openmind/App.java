package com.imooc.openmind;

import android.app.Application;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

/**
 * Created by Yat3s on 14/05/2017.
 * Email: hawkoyates@gmail.com
 * GitHub: https://github.com/yat3s
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Iconify.with(new FontAwesomeModule());
    }
}
