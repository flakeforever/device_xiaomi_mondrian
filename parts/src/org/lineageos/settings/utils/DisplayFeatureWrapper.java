/*
 * Copyright (C) 2023 Paranoid Android
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.settings.utils;

import android.os.IHwBinder.DeathRecipient;
import android.util.Log;

import vendor.xiaomi.hardware.displayfeature.V1_0.IDisplayFeature;

public class DisplayFeatureWrapper {

    private static final String TAG = "DisplayFeatureWrapper";
    private static final boolean DEBUG = Log.isLoggable(TAG, Log.DEBUG);

    private static IDisplayFeature mDisplayFeature;

    private static DeathRecipient mDeathRecipient = (cookie) -> {
        if (DEBUG) Log.d(TAG, "serviceDied");
        mDisplayFeature = null;
    };

    public static IDisplayFeature getDisplayFeature() {
        if (mDisplayFeature == null) {
            if (DEBUG) Log.d(TAG, "getDisplayFeature: mDisplayFeature=null");
            try {
                mDisplayFeature = IDisplayFeature.getService();
                mDisplayFeature.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (Exception e) {
                Log.e(TAG, "getDisplayFeature failed!", e);
            }
        }
        return mDisplayFeature;
    }

    public static void setDisplayFeature(DfParams params) {
        final IDisplayFeature displayFeature = getDisplayFeature();
        if (displayFeature == null) {
            Log.e(TAG, "setDisplayFeatureParams: displayFeature is null!");
            return;
        }
        if (DEBUG) Log.d(TAG, "setDisplayFeatureParams: " + params);
        try {
            displayFeature.setFeature(0, params.mode, params.value, params.cookie);
        } catch (Exception e) {
            Log.e(TAG, "setDisplayFeatureParams failed!", e);
        }
    }

    public static class DfParams {
        /* displayfeature parameters */
        public final int mode, value, cookie;

        public DfParams(int mode, int value, int cookie) {
            this.mode = mode;
            this.value = value;
            this.cookie = cookie;
        }

        public String toString() {
            return "DisplayFeatureParams(" + mode + ", " + value + ", " + cookie + ")";
        }
    }
}