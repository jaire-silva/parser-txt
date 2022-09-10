package model;

import java.util.ArrayList;
import java.util.List;

public final class Player {
    private int id;
    private String nome;
    private int kills;
    private List<String> old_names;

    protected Player(int id, String nome) {
        setId(id);
        setNome(nome);
        setKills(0);
        this.old_names = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getOld_names() {
        return old_names;
    }

    public void addOld_name(String old_name) {
        this.old_names.add(old_name);
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        if ((getKills() + kills) >= 0) {
            this.kills += kills;
        }
    }
}
