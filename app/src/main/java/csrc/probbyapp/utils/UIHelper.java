package csrc.probbyapp.utils;

import android.view.MotionEvent;
import android.view.View;

public class UIHelper {

    // UI helper to add touch effect to buttons

    public void applyTouchEffect(View view) {
        view.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {            case MotionEvent.ACTION_DOWN:
                v.animate().scaleX(0.95f).scaleY(0.95f).setDuration(100).start();
                break;
                case MotionEvent.ACTION_UP:
                    v.performClick();
                case MotionEvent.ACTION_CANCEL:
                    v.animate().scaleX(1f).scaleY(1f).setDuration(100).start();
                    break;
            }
            return true;
        });
    }
}
