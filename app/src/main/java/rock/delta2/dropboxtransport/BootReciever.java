package rock.delta2.dropboxtransport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import rock.delta2.dropboxtransport.Preferences.PreferencesHelper;

public class BootReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        PreferencesHelper.init(context);
        if(PreferencesHelper.getAutoStart() ) {
            context.startActivity(new Intent(context, StarterApp.class));
        }
    }
}