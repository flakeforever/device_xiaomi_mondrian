/*
 * Copyright (C) 2018,2020 The LineageOS Project
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
import android.content.Intent;
import android.os.Handler;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.KeyEvent;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import android.view.Display;
import android.view.IWindowManager;
import android.view.WindowManagerGlobal;

import static android.provider.Settings.System.SCREEN_RESOLUTION_MODE;
import static android.provider.Settings.System.SCREEN_OVERRIDE_WIDTH;
import static android.provider.Settings.System.SCREEN_OVERRIDE_HEIGHT;
import static android.provider.Settings.System.SCREEN_OVERRIDE_DENSITY;

public final class ScreenResolutionUtils {
    private static final int OVERRIDE_DEFAULT_WIDTH = 1080;
    private static final int OVERRIDE_DEFAULT_HEIGHT = 2400;
    private static final int OVERRIDE_DEFAULT_DENSITY = 420;
    
    private static boolean userPresent = false;

    private static void updateScreenSize(Context context) {
        int resolutionMode = Settings.System.getIntForUser(context.getContentResolver(),
            Settings.System.SCREEN_RESOLUTION_MODE, 0, UserHandle.USER_CURRENT);
        int overrideWidth = Settings.System.getIntForUser(context.getContentResolver(),
            Settings.System.SCREEN_OVERRIDE_WIDTH, OVERRIDE_DEFAULT_WIDTH, UserHandle.USER_CURRENT);
        int overrideHeight = Settings.System.getIntForUser(context.getContentResolver(),
            Settings.System.SCREEN_OVERRIDE_HEIGHT, OVERRIDE_DEFAULT_HEIGHT, UserHandle.USER_CURRENT);
        try {
            if (resolutionMode == 0) {
                final IWindowManager wms = WindowManagerGlobal.getWindowManagerService();
                int displayId = Display.DEFAULT_DISPLAY;
                wms.clearForcedDisplaySize(displayId);
            } else if (resolutionMode == 1) {
                final IWindowManager wms = WindowManagerGlobal.getWindowManagerService();
                int displayId = Display.DEFAULT_DISPLAY;
                wms.setForcedDisplaySize(displayId, overrideWidth, overrideHeight);
            }
        } catch (RemoteException e) {
            //Slog.e(TAG, "Remote execution", e);
        }
    }

    private static void updateScreenDensity(Context context) {
        int resolutionMode = Settings.System.getIntForUser(context.getContentResolver(),
            Settings.System.SCREEN_RESOLUTION_MODE, 0, UserHandle.USER_CURRENT);
        int overrideDensity = Settings.System.getIntForUser(context.getContentResolver(),
            Settings.System.SCREEN_OVERRIDE_DENSITY, OVERRIDE_DEFAULT_DENSITY, UserHandle.USER_CURRENT);
        try {
            if (resolutionMode == 0) {
                final IWindowManager wms = WindowManagerGlobal.getWindowManagerService();
                int displayId = Display.DEFAULT_DISPLAY;
                wms.clearForcedDisplayDensityForUser(displayId, UserHandle.USER_CURRENT);
            } else if (resolutionMode == 1) {
                final IWindowManager wms = WindowManagerGlobal.getWindowManagerService();
                int displayId = Display.DEFAULT_DISPLAY;
                wms.setForcedDisplayDensityForUser(displayId, overrideDensity, UserHandle.USER_CURRENT);
            }
        } catch (RemoteException e) {
            //Slog.e(TAG, "Remote execution", e);
        }
    }

    public static int getResolutionMode(Context context) {
        return Settings.System.getIntForUser(context.getContentResolver(),
            Settings.System.SCREEN_RESOLUTION_MODE, 0, UserHandle.USER_CURRENT);
    }

    public static boolean setResolutionMode(Context context, int mode) {
        Settings.System.putIntForUser(context.getContentResolver(),
                 SCREEN_OVERRIDE_WIDTH, OVERRIDE_DEFAULT_WIDTH, UserHandle.USER_CURRENT);
        Settings.System.putIntForUser(context.getContentResolver(),
                SCREEN_OVERRIDE_HEIGHT, OVERRIDE_DEFAULT_HEIGHT, UserHandle.USER_CURRENT);
        Settings.System.putIntForUser(context.getContentResolver(),
                SCREEN_OVERRIDE_DENSITY, OVERRIDE_DEFAULT_DENSITY, UserHandle.USER_CURRENT);
        Settings.System.putIntForUser(context.getContentResolver(),
                SCREEN_RESOLUTION_MODE, mode, UserHandle.USER_CURRENT);

        if (userPresent) {
            updateScreenSize(context);
            updateScreenDensity(context);
        }
        return true;
    }

    public static void onUserPresent(Context context) {
        userPresent = true;
        updateScreenSize(context);
        updateScreenDensity(context);
    }
}
