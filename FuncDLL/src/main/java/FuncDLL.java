import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * Created by fabio on 25/07/2016.
 * Chama função de uma DLL via JNA!
 */
public class FuncDLL
{
    /**
     * Interface para mapear a função da DLL
     */
    public interface BaseDLL extends Library
    {
        // Essa função é exportada e existe dentro da DLL.
        int SomaValores(int a, int b);
    }

    public static void main(String[] args)
    {
        // Deixar o arquivo DLL (base.dll) no diretório root do projeto
        BaseDLL baseDll = (BaseDLL) Native.loadLibrary("base", BaseDLL.class);

        System.out.println(baseDll.SomaValores(5, 4)); // 9
        System.out.println(baseDll.SomaValores(13, 18)); // 31

        Native.unregister(BaseDLL.class);
    }
}
