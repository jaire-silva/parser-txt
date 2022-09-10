import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.Game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    public static String[] quebraLinha(String linha) {
        linha = linha.trim();
        String[] palavras = linha.split(" ");
        return palavras;
    }

    public static void main(String[] args) {
        List<Game> games = new ArrayList<>();
        Game novoGame = null;
        String path;
        String linha;


        Scanner scanner = new Scanner(System.in);

        System.out.println("Entre com o caminho para o arquivo de log conforme o exemplo abaixo:");
        System.out.println("C:\\Users\\Jaire\\Desktop\\Quake.txt");
        System.out.print("Caminho: ");
        path = scanner.nextLine();

        // Carregar o documento txt para a memória do programa.
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            do {
                linha = br.readLine();

                // Verifica se a linha lida esta nula.
                if (linha == null) {
                    continue;
                }

                // Converte a linha do txt num verto de palavras.
                String[] palavras = quebraLinha(linha);

                switch (palavras[1]) {

                    // Se encontra o inicio de um jogo tratar os dados da partida.
                    case "InitGame:":
                        // Verificar se a lista de jogos esta vazia para armazenar o total de jogos do txt.
                        if (games.isEmpty()) {
                            novoGame = new Game(1);
                        } else {
                            novoGame = new Game(games.size() + 1);
                        }
                        games.add(novoGame);
                        break;

                    // Adicionar um novo jogador na partida.
                    case "ClientUserinfoChanged:":
                        novoGame.novoPlayer(palavras);
                        break;

                    // Adicionar uma nova kill na partida.
                    case "Kill:":
                        novoGame.novaKill(palavras);
                        break;


                }
            } while (linha != null);
        } catch (IOException e) {
            System.out.println("Error: O sistema não pode encontrar o arquivo especificado");
        }

        // Converter a lista de partidas para o formato de Json.
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonGames = gson.toJson(games);

        System.out.println(jsonGames);

    }
}
