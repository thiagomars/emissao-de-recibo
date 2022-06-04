package model.bean;

public class Recibo {
    
    private int id;
    private double valor_da_nfe;
    private double porcentagem_do_inss;
    private double valor_do_inss;
    private String data_da_nfe;

    public String getData_da_nfe() {
        return data_da_nfe;
    }

    public void setData_da_nfe(String data_da_nfe) {
        this.data_da_nfe = data_da_nfe;
    }
    private String obs_do_inss;
    private double taxa_de_adm;
    private double integralizacao;
    private double mensalidades;
    private double doacoes;
    private double rateio_de_perdas;
    private double valor_total_repasse;
    private double valor_total_liquido;
    private String forma_de_pagamento;
    private int id_dinheiro;
    private int id_cheque;
    private String cpf_cooperado;

    public String getCpf_cooperado() {
        return cpf_cooperado;
    }

    public void setCpf_cooperado(String cpf_cooperado) {
        this.cpf_cooperado = cpf_cooperado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValor_da_nfe() {
        return valor_da_nfe;
    }

    public void setValor_da_nfe(double valor_da_nfe) {
        this.valor_da_nfe = valor_da_nfe;
    }

    public double getPorcentagem_do_inss() {
        return porcentagem_do_inss;
    }

    public void setPorcentagem_do_inss(double porcentagem_do_inss) {
        this.porcentagem_do_inss = porcentagem_do_inss;
    }

    public double getValor_do_inss() {
        return valor_do_inss;
    }

    public void setValor_do_inss(double valor_do_inss) {
        this.valor_do_inss = valor_do_inss;
    }

    public String getObs_do_inss() {
        return obs_do_inss;
    }

    public void setObs_do_inss(String obs_do_inss) {
        this.obs_do_inss = obs_do_inss;
    }

    public double getTaxa_de_adm() {
        return taxa_de_adm;
    }

    public void setTaxa_de_adm(double taxa_de_adm) {
        this.taxa_de_adm = taxa_de_adm;
    }

    public double getIntegralizacao() {
        return integralizacao;
    }

    public void setIntegralizacao(double integralizacao) {
        this.integralizacao = integralizacao;
    }

    public double getMensalidades() {
        return mensalidades;
    }

    public void setMensalidades(double mensalidades) {
        this.mensalidades = mensalidades;
    }

    public double getDoacoes() {
        return doacoes;
    }

    public void setDoacoes(double doacoes) {
        this.doacoes = doacoes;
    }

    public double getRateio_de_perdas() {
        return rateio_de_perdas;
    }

    public void setRateio_de_perdas(double rateio_de_perdas) {
        this.rateio_de_perdas = rateio_de_perdas;
    }

    public double getValor_total_repasse() {
        return valor_total_repasse;
    }

    public void setValor_total_repasse(double valor_total_repasse) {
        this.valor_total_repasse = valor_total_repasse;
    }

    public double getValor_total_liquido() {
        return valor_total_liquido;
    }

    public void setValor_total_liquido(double valor_total_liquido) {
        this.valor_total_liquido = valor_total_liquido;
    }

    public String getForma_de_pagamento() {
        return forma_de_pagamento;
    }

    public void setForma_de_pagamento(String forma_de_pagamento) {
        this.forma_de_pagamento = forma_de_pagamento;
    }

    public int getId_dinheiro() {
        return id_dinheiro;
    }

    public void setId_dinheiro(int id_dinheiro) {
        this.id_dinheiro = id_dinheiro;
    }

    public int getId_cheque() {
        return id_cheque;
    }

    public void setId_cheque(int id_cheque) {
        this.id_cheque = id_cheque;
    }
    
}
