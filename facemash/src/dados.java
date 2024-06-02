import java.util.Random;
import java.util.Comparator;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;



public class dados {

    public static Musica[] lerCSV(String arquivo) {
        Musica[] musicas = null;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            // Lê a primeira linha para determinar o tamanho do array
            String primeiraLinha = br.readLine();
            int quantidade_pessoas = contarLinhas(arquivo) - 1; // Exclui o cabeçalho

            musicas = new Musica[quantidade_pessoas];

            // Preenche os objetos Pessoa com os dados do arquivo
            String linha;
            int indicePessoa = 0;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(","); // Assume que os dados são separados por vírgula

                if (dados.length >= 3) {
                    String nome = dados[0];
                    String imagem = dados[1];
                    String descricao = dados[2];

                    Musica musica = new Musica(nome, imagem, descricao);
                    musicas[indicePessoa] = musica;
                    indicePessoa++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return musicas;
    }

    private static int contarLinhas(String arquivo) throws IOException {
        int contador = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            while (br.readLine() != null) {
                contador++;
            }
        }

        return contador;
    }

    public static int numeroRandom(){
        Random random = new Random();

        return random.nextInt(345);
    }

    public static int numeroRandom(int numeroAnterior){
        Random random = new Random();
        int numeroRandom = random.nextInt(345);
        while (numeroRandom == numeroAnterior)
        {
            numeroRandom = random.nextInt(345);
        }

        return numeroRandom;
    }
    public static String gerarRanking(Musica[] musicas) {
        // Classificar as pessoas pelo score
        Arrays.sort(musicas, Comparator.comparingInt(Musica::getScore).reversed());

        StringBuilder ranking = new StringBuilder();
        for (int i = 0; i < musicas.length; i++) {
            Musica musica = musicas[i];
            ranking.append(i + 1).append(". ").append(musica.getNome()).append(" - Score: ").append(musica.getScore());
            ranking.append("\n");
        }

        return ranking.toString();
    }
    public static ArrayList<String> gerarRankingArrayList(Musica[] musicas) {
        // Ordena as músicas por pontuação em ordem decrescente
        Arrays.sort(musicas, Comparator.comparingInt(Musica::getScore).reversed());

        // Cria um mapa para agrupar as músicas por descrição
        Map<String, Integer> scorePorDescricao = new HashMap<>();

        // Calcula a pontuação total para cada descrição
        for (Musica musica : musicas) {
            String descricao = musica.getDesc();
            int pontuacao = musica.getScore();

            scorePorDescricao.put(descricao, scorePorDescricao.getOrDefault(descricao, 0) + pontuacao);
        }

        // Cria o ranking final agrupando por descrição
        ArrayList<String> ranking = new ArrayList<>();
        int posicao = 1;
        for (Map.Entry<String, Integer> entry : scorePorDescricao.entrySet()) {
            String descricao = entry.getKey();
            int pontuacaoTotal = entry.getValue();

            String rankingEntry = posicao + ". " + descricao + " - Pontuação Total: " + pontuacaoTotal;
            ranking.add(rankingEntry);
            posicao++;
        }

        return ranking;
    }



}
