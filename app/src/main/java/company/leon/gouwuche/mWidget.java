package company.leon.gouwuche;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class mWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.add_widget);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.m_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);
        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId);
//        }
        super.onUpdate(context,appWidgetManager,appWidgetIds);
        RemoteViews updateView = new RemoteViews(context.getPackageName(),R.layout.m_widget);//实例化RemoteView
        Intent i = new Intent(context,MainActivity.class);
        PendingIntent pi =PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
        updateView.setOnClickPendingIntent(R.id.widget,pi);//设置点击事件
        ComponentName me = new ComponentName(context,mWidget.class);
        appWidgetManager.updateAppWidget(me,updateView);
    }

    @Override
    public void onReceive(Context context,Intent intent)
    {
        super.onReceive(context,intent);
        if(intent.getAction().equals("company.leon.gouwuche.StaticActionWidget")) {
            goods g=(goods) intent.getExtras().get("goods");//取得推荐的商品
            RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.m_widget);//实例化RemoteView
            Intent i = new Intent(context,Detail.class);
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            i.putExtras(intent.getExtras());
            PendingIntent pi = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
            updateViews.setTextViewText(R.id.appwidget_text,g.getName()+"仅售"+g.getPrice()+"!");
            updateViews.setImageViewResource(R.id.appwidget_image,g.getImageid());//设置文字和图片
            updateViews.setOnClickPendingIntent(R.id.widget,pi);//设置点击事件
            ComponentName me = new ComponentName(context,mWidget.class);
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(me,updateViews);//更新widget
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
//        RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.m_widget);//实例化RemoteView
//        Intent i = new Intent(context,MainActivity.class);
//        i.addCategory(Intent.CATEGORY_LAUNCHER);
//        PendingIntent pi = PendingIntent.getActivity(context,0,i,PendingIntent.FLAG_UPDATE_CURRENT);
//        updateViews.setTextViewText(R.id.appwidget_text,"当前没有任何信息");
//        updateViews.setOnClickPendingIntent(R.id.widget,pi);
//        ComponentName me = new ComponentName(context,mWidget.class);
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//        appWidgetManager.updateAppWidget(me,updateViews);
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

