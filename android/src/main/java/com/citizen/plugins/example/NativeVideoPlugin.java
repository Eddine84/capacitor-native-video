package com.citizen.plugins.example;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

import com.getcapacitor.JSObject;
import com.getcapacitor.PermissionState;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import com.getcapacitor.annotation.Permission;
import com.getcapacitor.annotation.PermissionCallback;
import com.getcapacitor.annotation.ActivityCallback;

import androidx.activity.result.ActivityResult; // ✅ le bon import

@CapacitorPlugin(
    name = "NativeVideo",
    permissions = {
        @Permission(alias = "camera", strings = { "android.permission.CAMERA" }),
        @Permission(alias = "record_audio", strings = { "android.permission.RECORD_AUDIO" })
    }
)
public class NativeVideoPlugin extends Plugin {

    private PluginCall pendingCall;

    @PluginMethod
    public void recordVideo(PluginCall call) {
        this.pendingCall = call;

        if (getPermissionState("camera") != PermissionState.GRANTED ||
            getPermissionState("record_audio") != PermissionState.GRANTED) {
            requestAllPermissions(call, "permissionsCallback");
            return;
        }

        launchVideoIntent();
    }

    private void launchVideoIntent() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 30);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);

        startActivityForResult(pendingCall, intent, "handleVideoResult");
    }

    @PermissionCallback
    private void permissionsCallback(PluginCall call) {
        if (getPermissionState("camera") == PermissionState.GRANTED &&
            getPermissionState("record_audio") == PermissionState.GRANTED) {
            launchVideoIntent();
        } else {
            call.reject("Permissions refusées.");
        }
    }

    // ✅ Corrigé avec le bon type ActivityResult
    @ActivityCallback
    private void handleVideoResult(PluginCall call, ActivityResult result) {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            Uri videoUri = result.getData().getData();

            JSObject ret = new JSObject();
            ret.put("videoPath", videoUri.toString());
            call.resolve(ret);
        } else {
            call.reject("Enregistrement annulé ou échoué.");
        }
    }
}
