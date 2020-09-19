package tech.abhinav.accessibilitytesting;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final AccessibilityManager manager = (AccessibilityManager)this.getSystemService(Context.ACCESSIBILITY_SERVICE);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                if (manager.isEnabled()) {
                    AccessibilityEvent e = AccessibilityEvent.obtain();
                    e.setEventType(AccessibilityEvent.TYPE_VIEW_LONG_CLICKED);
                    e.setClassName(getClass().getName());
                    e.setPackageName(getApplicationContext().getPackageName());
                    e.getText().add("magic text");
                    e.setSource(view);
                    manager.sendAccessibilityEvent(e);
                }


                if(manager.isEnabled()){
                    Log.i("TAG", "onClick: manager enabled");

                    /*AccessibilityEvent event = AccessibilityEvent.obtain();
                    event.setEventType(AccessibilityEvent.TYPE_ANNOUNCEMENT);
                    event.setClassName(getClass().getName());
                    event.getText().add("Peace text");
                    event.setSource(findViewById(R.id.button_first));
                    view.getParent().requestSendAccessibilityEvent(view, event);
                    manager.sendAccessibilityEvent(event);
                    Log.i("TAG", "onClick: event fired");*/
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}