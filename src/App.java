import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
       
        String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
       
        String json = new ClienteHttp().buscaDados(url);
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // Exibir os dados
        for (Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            new GeradoraDeFigurinhas().cria(inputStream, conteudo.getTitulo()+".png");

            System.out.println(conteudo.getTitulo());
        }
    }
}
