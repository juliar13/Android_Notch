package sec.co.jp.notchsample;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DisplayCutout;
import android.view.Window;

// window size
// device data
// status bar background color change
public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Return Statusbar size.
     * @param activity Activity
     * @return Statusbar size
     */
    public static int getStatusBarHeight(Activity activity){
        final Rect rect = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        Log.d("MainActivity","status bar : " + String.valueOf(rect.top));
        return rect.top;
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        getStatusBarHeight(this);
        getNotchSize();
    }

    public void getNotchSize() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Log.d(TAG,"---display cut---");
            DisplayCutout displayCutout = getWindow().getDecorView().getRootWindowInsets().getDisplayCutout();
            displayCutout.getBoundingRects();   //<List>
            int top = displayCutout.getSafeInsetTop();
            int bottom = displayCutout.getSafeInsetBottom();
            int left = displayCutout.getSafeInsetLeft();
            int right = displayCutout.getSafeInsetRight();
            Log.d(TAG,"top : " + top);
            Log.d(TAG,"bottom : " + bottom);
            Log.d(TAG,"left : " + left);
            Log.d(TAG,"right : " + right);

            if (getStatusBarHeight(this) < top) {
                Log.d(TAG,"notch is oversize!!");
            }
        }else {
            Log.d(TAG,"notch is not incompatible below Android P");
        }

    }

    public void getWindowSize(){

    }

}
