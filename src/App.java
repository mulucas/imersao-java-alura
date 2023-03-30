import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Faz uma conexão HTTP e busca os dados do endereco passado
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();
        //System.out.println(body);

        // Extrair só os dados que nos interessa (titulo, poster, classificação)
        var parse = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parse.parse(body);
        
        // Exibir os dados
        for (Map<String,String> filme : listaDeFilmes) {

            InputStream inputStream = new URL(filme.get("image")).openStream();

            new GeradoraDeFigurinhas().cria(inputStream, filme.get("title")+".png");

            System.out.println(filme.get("title"));
            //System.out.println(filme.get("image"));
            //System.out.println(filme.get("imDbRating"));
        }

    }

}
