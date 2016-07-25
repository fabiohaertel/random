import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

/**
 * Created by fabio on 25/07/2016.
 * Mapeamento manual da API do Windows.
 */
public class JnBase {

    /**
     * Representação da User32.dll
     */
    public interface JnUser32 extends Library {
        public int MessageBox(WinDef.HWND hWnd,
                              WString lpText,
                              WString lpCaption,
                              int uType);
    }

    /**
     * Mensagem de alerta (MessageBox)
     */
    public void criaMessageBox(WinDef.HWND winHandle, String texto, String titulo, int tipo) {
        JnUser32 user32Lib = (JnUser32) Native.loadLibrary("user32", JnUser32.class, W32APIOptions.UNICODE_OPTIONS);
        user32Lib.MessageBox(winHandle, new WString(texto), new WString(titulo), tipo);
        Native.unregister(JnUser32.class);
    }



}
