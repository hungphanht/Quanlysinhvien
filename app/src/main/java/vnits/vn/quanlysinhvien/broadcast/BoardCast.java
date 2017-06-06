package vnits.vn.quanlysinhvien.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Phong on 9/5/2017.
 */

public class BoardCast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intentMain = new Intent(context, BoardCast.class);
        intentMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentMain);
    }
}
