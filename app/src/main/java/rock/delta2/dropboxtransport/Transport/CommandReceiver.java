package rock.delta2.dropboxtransport.Transport;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import rock.delta2.dropboxtransport.Mediator.MediatorMD;

public class CommandReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("rock.delta2.send.txt"))
        {
            String msgId = intent.getStringExtra("replMsgId");
            String msg = intent.getStringExtra("msg");
            MediatorMD.sendText(msgId, msg);
        }
        else if (intent.getAction().equals("rock.delta2.send.photo"))
        {
            String msgId = intent.getStringExtra("replMsgId");
            String msg = intent.getStringExtra("msg");
            String file = intent.getStringExtra("file");
            String caption = intent.getStringExtra("caption");
            MediatorMD.sendPhoto(msgId, file, caption);
        }
        else if (intent.getAction().equals("rock.delta2.send.file"))
        {
            String msgId = intent.getStringExtra("replMsgId");
            String file = intent.getStringExtra("file");
            MediatorMD.sendFile(msgId, file);
        }
    }
}
