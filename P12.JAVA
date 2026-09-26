VIEW.XML

<?xml version="1.0" encoding="utf-8"?>

<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:gravity="center">

    <VideoView
        android:id="@+id/videoView"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</LinearLayout>


VIEW ACTIVITY.JAVA

package com.example.videoviewactivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoViewActivity extends AppCompatActivity {

    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        videoView = findViewById(R.id.videoView);

        // Create MediaController
        MediaController controller = new MediaController(this);

        // Attach MediaController to VideoView
        videoView.setMediaController(controller);

        // Video file from res/raw
        Uri video = Uri.parse(
                "android.resource://" + getPackageName() + "/" + R.raw.sample
        );

        // Set video URI
        videoView.setVideoURI(video);

        // Start video
        videoView.start();
    }
}
