package company.leon.gouwuche;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Leon on 2017/10/30.
 */

public class Reciver extends BroadcastReceiver{
    private static final String STATICACTION="company.leon.gouwuche.MyStaticFliter";
    private static final String DYNAMIACTION="company.leon.gouwuche.MyDynamicFliter";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(STATICACTION)){
            goods gd=(goods) intent.getExtras().get("goods");
            assert gd!=null;
            int image=gd.getImageid();
            Bitmap bm= BitmapFactory.decodeResource(context.getResources(),image);
            //获取状态通知栏管理
            NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //实例化通知栏构造器
            Notification.Builder builder=new Notification.Builder(context);
            //对builder进行配置
            builder.setContentTitle("新商品热卖")
                    .setContentText(gd.getName()+"仅售"+gd.getPrice()+"!")
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bm)
                    .setSmallIcon(image)
                    .setAutoCancel(true);
            //绑定intent，点击图标时能够进入其activity
            Intent mIntent = new Intent(context,Detail.class);
            mIntent.putExtras(intent.getExtras());
            PendingIntent mPendingIntent=PendingIntent.getActivity(context,0,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);
            Notification notify=builder.build();
            manager.notify(0,notify);
            int tmp;
            if(gd.isStar())tmp=gd.getId();
            else  tmp=-2;
            EventBus.getDefault().post(tmp);
        }
        else if(intent.getAction().equals(DYNAMIACTION)){
            goods gd=(goods) intent.getExtras().get("goods");
            assert gd!=null;
            int image=gd.getImageid();
            Bitmap bm= BitmapFactory.decodeResource(context.getResources(),image);
            //获取状态通知栏管理
            NotificationManager manager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            //实例化通知栏构造器
            Notification.Builder builder=new Notification.Builder(context);
            //对builder进行配置
            builder.setContentTitle("马上下单")
                    .setContentText(gd.getName()+"已添加到购物车")
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bm)
                    .setSmallIcon(image)
                    .setAutoCancel(true);
            //绑定intent，点击图标时能够进入其activity
            Intent mIntent = new Intent(context,MainActivity.class);
//            mIntent.putExtras(intent.getExtras());
            PendingIntent mPendingIntent=PendingIntent.getActivity(context,0,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);
            Notification notify=builder.build();
            manager.notify(0,notify);

            EventBus.getDefault().post(gd);
        }
    }
}
