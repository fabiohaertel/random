import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

/**
 * Created by fabio on 25/07/2016.
 * Testes JNA (Java Native API)
 */

public class JnTests {

    public static void main(String[] args) {

        JnBase base = new JnBase();
        base.initLibs();

        // MessageBox (tipo 0x40 = ícone de exclamação)
        base.jnMessageBox(null, "Testando MessageBox JNA, Teste...", "Título da mensagem", 0x40);

        // Tenta localizar a janela do Bloco de Notas pelo className
        WinDef.HWND winHandle = base.jnFindWindow("Notepad", null);
        if (winHandle != null) {
            base.jnMessageBox(null, "Achou um Bloco de notas aberto!", "Sucesso!", 0x40);
        } else {
            base.jnMessageBox(null, "NÃO achou Bloco de notas aberto!", "Erro", 0x10);
        }

        // Traz a janela do bloco de notas para frente (caso encontrada)
        if (winHandle != null) {
            base.jnSetForegroundWindow(winHandle);
        }

        // Envia uma mensagem ao Bloco de Notas (caso aberto)
        // O conteúdo da mensagem é a letra 'a', que será digitada automaticamente 6 vezes.
        if (winHandle != null) {

            // A mensagem deve ser enviada ao componente de texto (Edit), que é uma janela interna.
            // O Edit será localizado via FindWindowEx
            WinDef.HWND childEdit = base.jnFindWindowEx(winHandle, null, "Edit", null);

            // 0x0100 = WM_KEYDOWN - pressionar tecla
            // 0x41 = letra A
            // 6 = seis repetições
            base.jnPostMessage(childEdit, 0x0100, new WinDef.WPARAM(0x41), new WinDef.LPARAM(6));
        }

        base.unloadLibs();

    }

}
