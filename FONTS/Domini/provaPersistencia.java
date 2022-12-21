package FONTS.Domini;

import FONTS.Persistencia.Persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class provaPersistencia {
    public static void main (String[] args) throws Exception{
        ControladorDomini domini = new ControladorDomini();
        ControladorFormat CtrlFormat = new ControladorFormat();
        List<String> expressions = Persistencia.recuperarExpressions();
        for (String s : expressions) domini.queryCrearExpressioBooleana(s);

        List<HashMap<String, Integer>> freq = Persistencia.recuperarFreq();
        for(int i = 0; i < freq.size(); ++i) {
            HashMap<String, Integer> f = freq.get(i);
            for (HashMap.Entry<String, Integer> entry : f.entrySet())
                System.out.println(entry.getKey()+" "+entry.getValue());
        }
        List<String> documents = Persistencia.recuperarDocuments();
        for (String s : documents) {
            List<String> d = CtrlFormat.extractTitolAutorContingutDocument(s, "txt");
            System.out.println(d.get(0)+" "+d.get(1));
        }
    }
}
