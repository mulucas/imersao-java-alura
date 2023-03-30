import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo{
    public List<Conteudo> extraiConteudos(String json){

         // Extrair só os dados que nos interessa (Exemplo: titulo)
         var parse = new JsonParser();
         List<Map<String, String>> listaDeAtributos = parse.parse(json);
 
         List<Conteudo> conteudos = new ArrayList<>();  
         
         for (Map<String, String> atributos : listaDeAtributos) {
             String titulo = atributos.get("title");
             String urlImagem = atributos.get("url");
             var conteudo = new Conteudo(titulo, urlImagem);
 
             conteudos.add(conteudo);
         }
 
         return conteudos;
    }

}
