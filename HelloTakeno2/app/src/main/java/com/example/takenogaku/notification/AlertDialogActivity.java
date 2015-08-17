package com.example.takenogaku.notification;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;


import static android.util.Log.v;


/**
 * Created by makitani on 2015/08/08.
 */
public class AlertDialogActivity extends FragmentActivity {
    String push_message = "";
    String push_title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        push_message = i.getStringExtra("message");
        push_title = i.getStringExtra("title");

        v("push_message", push_message + "//"+push_title);
        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.create();

//        shareDialog = new ShareDialog(this);
//        // this part is optional
//
//        if (ShareDialog.canShow(ShareLinkContent.class)) {
//            ShareLinkContent linkContent = new ShareLinkContent.Builder()
//                    .setContentTitle("Hello Facebook")
//                    .setContentDescription(
//                            "The 'Hello Facebook' sample  showcases simple Facebook integration")
//                    .setContentUrl(Uri.parse("http://developers.facebook.com/android"))
//                    .build();
//
//            shareDialog.show(linkContent);
//        }


//	 	ad.setNegativeButton(resource.getString(R.string.no), new DialogInterface.OnClickListener() {
//			public void onClick(DialogInterface dialog, int whichButton) {
//				/* Ç±Ç±Ç…NOÇÃèàóù */
//
//				finish();
//
//			}
//		});

        ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
				/* Ç±Ç±Ç…YESÇÃèàóù */
                finish();

            }
        });

        ad.setTitle(push_title);
        ad.setMessage(push_message);
        ad.show();

    }

}
