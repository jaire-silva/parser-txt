package model;

public final class Game {
    private int game;
    private Status status;

    public Game(int game) {
        setGame(game);
        this.status = new Status();
    }

    public int getGame() {
        return game;
    }

    public void setGame(int game) {
        this.game = game;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public void novoPlayer(String[] linha) {
        status.addPlayer(linha);
    }

    public void novaKill(String[] linha) {
        status.atribuirKill(linha);
    }

}
