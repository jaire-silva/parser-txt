package model;

import java.util.ArrayList;
import java.util.List;

public final class Status {
    private int total_kills;
    private List<Player> players;

    protected Status() {
        setTotal_kills(0);
        this.players = new ArrayList<>();
    }

    public int getTotal_kills() {
        return total_kills;
    }

    public void setTotal_kills(int total_kills) {
        this.total_kills = total_kills;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void addKill() {
        this.total_kills++;
    }

    public String buscaNome(String[] palavras) {
        String nome = "";

        // Quando o nome do jogador é simples, o vetor de palavras so tem 4 elementos.
        if (palavras.length == 4) {
            for (int i = 2; i < palavras[3].length(); i++) {
                if ((palavras[3].charAt(i)) != '\\') {
                    nome += palavras[3].charAt(i);
                } else break;
            }
        }
        // Caso contrario é preciso buscas as palavras que forma o nome do jogador.
        else {
            nome = palavras[3].substring(2);
            String lastNome = "";

            for (int i = 4; i < palavras.length; i++) {
                lastNome += " ";
                for (int j = 0; j < palavras[i].length(); j++) {
                    if ((palavras[i].charAt(j)) != '\\') {
                        lastNome += palavras[i].charAt(j);
                    } else break;
                }
            }
            nome += lastNome;
        }

        return nome;
    }

    public int buscaId(String[] linha) {
        int id = Integer.parseInt(linha[2]);
        return id;
    }

    public void addPlayer(String[] linha) {
        int id = buscaId(linha);
        String nome = buscaNome(linha);

        // Verificar se o jogador ja existe.
        for (Player player : players) {
            if ((player.getId()) == id) {
                String oldName = player.getNome();

                if (!(oldName.equals(nome))) {
                    player.addOld_name(oldName);
                    player.setNome(nome);
                }
                return;
            }
        }

        // Caso não exista criar um novo.
        Player novoPlayer = new Player(id, nome);
        this.players.add(novoPlayer);
    }

    public void atribuirKill(String[] linha) {
        addKill();

        int idKiller = Integer.parseInt(linha[2]);
        int idKilled = Integer.parseInt(linha[3]);

        // O mundo tem codigo de jogador 1022, se foi ele quem matou o jogador morto deve perder uma kill.
        if (idKiller == 1022) {
            for (Player player : players) {
                if (player.getId() == idKilled) {
                    player.setKills(-1);
                }
            }
        }
        // Caso contrario quem realizou a kill deve ganhar uma kill.
        else if (idKiller != idKilled) {
            for (Player player : players) {
                if (player.getId() == idKiller) {
                    player.setKills(1);
                }
            }
        }
    }
}
