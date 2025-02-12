package com.sdrtuner;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

	private static final String LOGTAG = "SettingsActivity";
	public static final int PERMISSION_REQUEST_LOGGING_WRITE_FILES = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		SettingsFragment settingsFragment = new SettingsFragment();
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.replace(android.R.id.content, settingsFragment);
		fragmentTransaction.commit();
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == PERMISSION_REQUEST_LOGGING_WRITE_FILES) {// If request is cancelled, the result arrays are empty.
			if (grantResults.length <= 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
				Log.i(LOGTAG, "onRequestPermissionResult: User denied to write files for logging. deactivate setting..");
				SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
				preferences.edit().putBoolean(getString(R.string.pref_logging), false).apply();
			}
		}
	}
}
