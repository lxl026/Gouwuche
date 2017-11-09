package company.leon.gouwuche;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Leon on 2017/10/30.
 */

public class Reciver extends AppWidgetProvider {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("company.leon.gouwuche.DynamicActionWidget")) {
            goods g=(goods) intent.getExtras().get("goods");
            RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.m_widget);//实例化RemoteView
            Intent i = new Intent(context,MainActivity.class);
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            i.putExtras(intent.getExtras());
            PendingIntent pi = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
            updateViews.setTextViewText(R.id.appwidget_text,g.getName()+"已添加到购物车");
            updateViews.setImageViewResource(R.id.appwidget_image,g.getImageid());
            updateViews.setOnClickPendingIntent(R.id.widget,pi);
            ComponentName me = new ComponentName(context,mWidget.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(me,updateViews);
        }
    }
}
