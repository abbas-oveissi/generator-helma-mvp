package <%= appPackage %>.services;

/**
 * Created by abbas on 8/12/16.
 */
//public class MyFirebaseMessagingService extends FirebaseMessagingService {
//
//    private static final String TAG = "MyFirebaseMsgService";
//
//
//    @Override
//    public void onMessageReceived(RemoteMessage remoteMessage) {
//        String data= remoteMessage.getData().get("data");
//        String type= remoteMessage.getData().get("type");
//        String link= remoteMessage.getData().get("link");
//        String title= remoteMessage.getData().get("title");
//        if(title==null)
//            title="ورزش ۳ - غیررسمی";
//
//
//        if(data==null||type==null||link==null)
//        {
//            Log.e("error","formate notif mohem nist");
//            return;
//        }
//
//        if(type.equals("update"))
//        {
//            sendUpdateNotification(title,data);
//        }
//        else if(type.equals("text"))
//        {
//            sendSimpleNotification(title,data);
//        }
//        else if(type.equals("video"))
//        {
//            String[] splitedText=data.split("#");
//            sendVideoNotification(title,splitedText[0],splitedText[1],splitedText[2]);
//        }
//    }
//
//
//    private void sendVideoNotification(String title,String messageBody,String video_name,String video_id) {
//        Intent intent = new Intent(this, CategoryDetailActivity.class);
//        intent.putExtra("video_id",Integer.valueOf(video_id));
//        intent.putExtra("video_name",Integer.valueOf(video_id));
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
//        stackBuilder.addParentStack(CategoryDetailActivity.class);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent resultPendingIntent =  stackBuilder.getPendingIntent((int)System.currentTimeMillis(), PendingIntent.FLAG_UPDATE_CURRENT);
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                .setContentTitle(title)
//                .setContentText(messageBody)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(resultPendingIntent);
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify((int)System.currentTimeMillis(),notificationBuilder.build());
//    }
//
//
//    private void sendUpdateNotification(String title,String messageBody) {
//        Intent notificationIntent = new <%= appPackage %>.utils.MarketIntentsImpl().getAppPageIntent("<%= appPackage %>");
//        PendingIntent pIntent = PendingIntent.getActivity(this, 0, notificationIntent,0);
//
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                .setContentTitle(title)
//                .setContentText(messageBody)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pIntent);
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify((int)System.currentTimeMillis(),notificationBuilder.build());
//    }
//
//
//    private void sendSimpleNotification(String title,String messageBody) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher))
//                .setContentTitle(title)
//                .setContentText(messageBody)
//                .setStyle(new NotificationCompat.BigTextStyle().bigText(messageBody))
//                .setAutoCancel(true)
//                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent);
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify((int)System.currentTimeMillis(),notificationBuilder.build());
//    }
//
//
//}
