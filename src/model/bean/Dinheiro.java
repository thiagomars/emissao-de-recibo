package model.bean;

public class Dinheiro {
    
    private int id_dinheiro;
    private double troco_para;
    private double valor_troco;
    private String obs;
    private double valor;

    public int getId_dinheiro() {
        return id_dinheiro;
    }

    public void setId_dinheiro(int id_dinheiro) {
        this.id_dinheiro = id_dinheiro;
    }

    public double getTroco_para() {
        return troco_para;
    }

    public void setTroco_para(double troco_para) {
        this.troco_para = troco_para;
    }

    public double getValor_troco() {
        return valor_troco;
    }

    public void setValor_troco(double valor_troco) {
        this.valor_troco = valor_troco;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    
    
}
