package com.rambostudio.zojoz.realmdatabase.util;

import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by rambo on 10/4/2560.
 */

public class ToastUtil {
    public static void Toast(String text, int lenght) {
        Toast.makeText(Contextor.getInstance().getContext(),
                text,
                lenght == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG)
                .show();
    }
}
