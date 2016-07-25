/**
 * Created by fabio on 25/07/2016.
 * Testes JNA (Java Native API)
 */

public class JnTests {

    public static void main(String[] args) {

        // MessageBox (tipo 0x40 = ícone de exclamação)
        new JnBase().criaMessageBox(null, "Testando MessageBox JNA, Teste...", "Título da mensagem", 0x40);


    }

}
