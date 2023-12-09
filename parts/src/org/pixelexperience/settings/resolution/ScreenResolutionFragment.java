/*
 * Copyright (C) 2020 Paranoid Android
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.pixelexperience.settings.resolution;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;

import org.pixelexperience.settings.R;

import java.io.IOException;

public class ScreenResolutionFragment extends PreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private static final String TAG = ScreenResolutionFragment.class.getSimpleName();

    private static final String PREF_SCREEN_RESOLUTION = "screen_resolution_pref";

    private ListPreference mChooseResolutionPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.screen_resolution_settings);

        mChooseResolutionPref = (ListPreference) findPreference(PREF_SCREEN_RESOLUTION);
        mChooseResolutionPref.setDefaultValue(
            Integer.toString(ScreenResolutionUtils.getResolutionMode(getActivity())));
        mChooseResolutionPref.setOnPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        if (preference == mChooseResolutionPref) {
            int value = Integer.parseInt(newValue.toString());
            return ScreenResolutionUtils.setResolutionMode(getActivity(), value);
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        mChooseResolutionPref.setValue(
            Integer.toString(ScreenResolutionUtils.getResolutionMode(getActivity())));
    }
}
