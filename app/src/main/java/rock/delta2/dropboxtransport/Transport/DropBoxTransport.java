package rock.delta2.dropboxtransport.Transport;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import rock.delta2.dropboxtransport.Helper;
import rock.delta2.dropboxtransport.Mediator.ITransport;
import rock.delta2.dropboxtransport.Preferences.PreferencesHelper;

public class DropBoxTransport implements ITransport {

    Context _context;

    public DropBoxTransport(Context c){
        _context = c;
    }

    public boolean checkConnection(){
        boolean result = tokenExists();
        if(result )
            retrieveAccessToken();

        return result;
    }

    public void init(Context context) {
        _context = context;
    }

    public void close() {
        _context = null;

    }

    private boolean tokenExists() {
        String token = PreferencesHelper.getToken();
        return  token!= null && !token.equals("");
    }

    private String retrieveAccessToken() {

        String accessToken = PreferencesHelper.getToken();
        if (accessToken == null && !accessToken.equals("")) {
            return null;
        } else {

            return accessToken;
        }
    }

    @Override
    public void sendTxt(String replMsgId, String msg) {

        if (!PreferencesHelper.getSendText())
            return;

        try {
            String filename = "msg_" + Helper.getNowDTFile() + ".txt";

            final File file = new File(Helper.getWorkDir(), filename);

            file.createNewFile();
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(msg);

            myOutWriter.close();

            fOut.flush();
            fOut.close();

            new UploadTask(DropboxClient.getClient(PreferencesHelper.getToken()), file, _context).execute();

        }
        catch (Exception e) {
            Helper.Ex2Log(e);
        }
    }

    @Override
    public void sendPhoto(String replMsgId, String file, String caption) {
        if (!PreferencesHelper.getSendPhoto())
            return;

        File f = new File(file);
        new UploadTask(DropboxClient.getClient(PreferencesHelper.getToken()), f, _context).execute();

    }

    @Override
    public void sendFile(String replMsgId, String file) {
        if (!PreferencesHelper.getSendFile())
            return;

        File f = new File(file);
        new UploadTask(DropboxClient.getClient(PreferencesHelper.getToken()), f, _context).execute();

    }

}
