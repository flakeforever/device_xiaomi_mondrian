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
import android.os.UserHandle;
import android.os.SystemClock;
import android.provider.Settings;
import android.view.KeyEvent;
import java.util.List;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.provider.Settings.System.SCREEN_RESOLUTION_MODE;

public final class ScreenResolutionUtils {

    public static boolean setResolutionMode(Context context, int mode) {
        return Settings.System.putIntForUser(context.getContentResolver(),
                SCREEN_RESOLUTION_MODE, mode, UserHandle.USER_CURRENT);
    }
}
