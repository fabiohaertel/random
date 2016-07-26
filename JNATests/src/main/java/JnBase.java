import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.WString;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.W32APIOptions;

/**
 * Created by fabio on 25/07/2016.
 * Mapeamento manual da API do Windows.
 * https://msdn.microsoft.com/en-us/library/
 * WString = wrapper class para os char[] do C.
 */
public class JnBase
{

    JnUser32 user32Lib = null;
    JnKernel32 kernel32Lib = null;

    /**
     * Inicializa as Libs
     */
    public void initLibs()
    {

        // User32.dll
        user32Lib = (JnUser32) Native.loadLibrary("user32", JnUser32.class, W32APIOptions.UNICODE_OPTIONS);

        // Kernel32.dll
        kernel32Lib = (JnKernel32) Native.loadLibrary("kernel32", JnKernel32.class, W32APIOptions.UNICODE_OPTIONS);
    }

    /**
     * Finaliza as Libs
     */
    public void unloadLibs()
    {

        Native.unregister(JnUser32.class);

    }

    /**
     * Mapeamento das funções da lib User32.dll
     */
    public interface JnUser32 extends Library
    {
        public WinDef.UINT MessageBox(WinDef.HWND hWnd, WString lpText, WString lpCaption, int uType);

        public WinDef.HWND FindWindow(WString lpClassName, WString lpWindowName);

        public WinDef.HWND FindWindowEx(WinDef.HWND parent, WinDef.HWND childAfter, WString lpszClass,
                                        WString lpszWindow);

        public WinDef.LRESULT PostMessage(WinDef.HWND hWnd, int msg, WinDef.WPARAM wParam, WinDef.LPARAM lParam);

        public WinDef.BOOL SetForegroundWindow(WinDef.HWND hWnd);

        public WinDef.HWND CreateWindowEx(WinDef.DWORD dwExStyle, WString lpClassName, WString lpWindowName,
                                          WinDef.DWORD dwStyle, int x, int y,
                                          int nWidth, int nHeight, WinDef.HWND hWndParent, WinDef.HMENU hMenu,
                                          WinDef.HINSTANCE hInstance, WinDef.LPVOID lpParam);
    }

    public interface JnKernel32 extends Library
    {
        public WinDef.DWORD GetLastError();
    }

    /**
     * Mensagem de alerta (MessageBox)
     */
    public void jnMessageBox(WinDef.HWND winHandle, String texto, String titulo, int tipo)
    {
        user32Lib.MessageBox(winHandle, new WString(texto), new WString(titulo), tipo);
    }

    /**
     * Localiza uma janela pelo título e/ou className (FindWindow)
     */
    public WinDef.HWND jnFindWindow(String className, String titulo)
    {
        return user32Lib.FindWindow(className != null ? new WString(className) : null,
                titulo != null ? new WString(titulo) : null);
    }

    /**
     * Localiza uma janela filha dentro da janela pai
     */
    public WinDef.HWND jnFindWindowEx(WinDef.HWND pai, WinDef.HWND childAfter, String className, String titulo)
    {
        return user32Lib.FindWindowEx(pai, childAfter, className != null ? new WString(className) : null,
                titulo != null ? new WString(titulo) : null);
    }

    /**
     * Traz uma janela para frente, ativando o foco nela
     */
    public WinDef.BOOL jnSetForegroundWindow(WinDef.HWND hWnd)
    {
        return user32Lib.SetForegroundWindow(hWnd);
    }

    /**
     * Adiciona uma mensagem na fila de mensagens de uma janela.
     * Ex: clicar, pressionar uma tecla, etc
     */
    public WinDef.LRESULT jnPostMessage(WinDef.HWND windowHandle, int msgCode, WinDef.WPARAM wParam,
                                        WinDef.LPARAM lParam)
    {
        return user32Lib.PostMessage(windowHandle, msgCode, wParam, lParam);
    }

    /**
     * Obtém o último erro gerado pela API
     */
    public WinDef.DWORD jnGetLastError()
    {
        return kernel32Lib.GetLastError();
    }

    /**
     * Cria uma janela (dialog)
     * Não funciona ainda, falta definir a estrutura do lpClassName
     */
    public WinDef.HWND jnCreateWindowEx(WinDef.DWORD dwExStyle, String lpClassName, String lpWindowName,
                                        WinDef.DWORD dwStyle, int x, int y,
                                        int nWidth, int nHeight, WinDef.HWND hWndParent, WinDef.HMENU hMenu,
                                        WinDef.HINSTANCE hInstance, WinDef.LPVOID lpParam)
    {
        return user32Lib
                .CreateWindowEx(dwExStyle, lpClassName != null ? new WString(lpClassName) : null,
                        lpWindowName != null ? new WString(lpWindowName) : null, dwStyle, x, y, nWidth, nHeight,
                        hWndParent, hMenu, hInstance,
                        lpParam);
    }
}
