package com.example.qna;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;

import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    static String refreshedToken;


    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        // Log.e(TAG, message.getFrom());
        // 수신한 메시지 처리(알림)
        if (message.getNotification() != null) {
            sendNotification(message);
        }
    }

    // 받은 메시지로 알림 보내기
    private void sendNotification(RemoteMessage remoteMessage) {
        String CHANNEL_ID = getString(R.string.default_notification_channel_id);
        String CHANNEL_NAME = "CHANNEL_NOTIFICATION";

        // FCM으로부터 받은 Message에 Notification이 있을 때, 알림 생성
        if (remoteMessage.getNotification() != null) {
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

            NotificationCompat.Builder builder;
            // 오레오 버전 이상부터는 채널 생성
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (notificationManager.getNotificationChannel(CHANNEL_ID) == null) {
                    NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(channel);
                }
                builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            } else {
                builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
            }

            // 메시지 속 Notification으로부터 title과 body 받아옴
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            // 알림을 누를 시, 해당 화면으로 이동
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE);
            // 알림 관련 설정
            builder.setContentTitle(title)
                    .setContentText(body)
                    .setAutoCancel(true)
                    .setOngoing(true)
                    .setShowWhen(true)
                    .setContentIntent(pendingIntent);
            //s.setSmallIcon(R.drawable.ic_baseline_priority_high_24)
            // 알림 생성
            Notification notification = builder.build();
            // 알림 발생
            notificationManager.notify(1, notification);
        }
    }

    // 어플의 첫 실행 또는 특정 이벤트로 토큰이 갱신됐을 때, 실행
    @Override
    public void onNewToken (@NonNull String token){
        super.onNewToken(token);
        refreshedToken = FirebaseMessaging.getInstance().getToken().getResult();
    }
}
