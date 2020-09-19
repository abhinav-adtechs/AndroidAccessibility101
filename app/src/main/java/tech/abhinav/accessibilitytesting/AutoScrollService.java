package tech.abhinav.accessibilitytesting;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

public class AutoScrollService extends AccessibilityService {


    @Override
    protected void onServiceConnected() {

        Log.i("TAG", "onServiceConnected: ");
        Log.i("TAG", "All events: announcement: " + AccessibilityEvent.TYPE_ANNOUNCEMENT + " click: " + AccessibilityEvent.TYPE_VIEW_CLICKED);
        AccessibilityServiceInfo info = getServiceInfo();
        info.eventTypes  = AccessibilityEvent.TYPE_VIEW_LONG_CLICKED;
        info.packageNames = new String[]{"org.telegram.messenger", "com.android.chrome", "tech.abhinav.accessibilitytesting"};
        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_SPOKEN;
        info.flags = AccessibilityServiceInfo.DEFAULT | AccessibilityServiceInfo.CAPABILITY_CAN_RETRIEVE_WINDOW_CONTENT;
        info.notificationTimeout = 100;

        this.setServiceInfo(info);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.i("TAG", "onAccessibilityEvent: event.getSource() " + (event.getSource() != null));
        Log.i("TAG", "onAccessibilityEvent: event type " + event.getEventType());
        Log.i("TAG", "onAccessibilityEvent: packagename " + event.getPackageName());
        Log.i("TAG", "onAccessibilityEvent: packagename " + event.getText());

        AccessibilityNodeInfo nodeInfo = event.getSource().getParent().getChild(3);
        if (nodeInfo != null) {

            /*Log.i("TAG", "onAccessibilityEvent: viewid " + nodeInfo.getViewIdResourceName());
            Log.i("TAG", "onAccessibilityEvent: childcount " + nodeInfo.getChildCount());*/

            nodeInfo.performAction(AccessibilityNodeInfo.ACTION_CLICK);
            nodeInfo.recycle();

        }
    }

    @Override
    public void onInterrupt() {

    }
}
