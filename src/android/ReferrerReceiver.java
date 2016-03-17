package com.likemindnetworks.referral;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

public class ReferrerReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle extras = intent.getExtras();

		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor edit = sharedPreferences.edit();

		if (sharedPreferences.getBoolean("installed", false)) {
			Log.d("ReferrerReceiver", "Ignore duplicate install campaign");
			return;
		}

		Log.d("ReferrerReceiver", "Handling INSTALL_REFERRER intent");
		edit.putBoolean("installed", true);

		if (extras != null) {
			String referrerString = extras.getString("referrer");
			edit.putString("referrer", referrerString == null ? "null" : referrerString);
		} else {
			edit.putString("referrer", "null");
		}

		edit.commit();
	}
}
