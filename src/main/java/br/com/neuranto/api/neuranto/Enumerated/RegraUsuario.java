package br.com.neuranto.api.neuranto.Enumerated;

public enum RegraUsuario {
    ADMIN("adim"),
    CASUAL("casual"),
    PROFISSIONAL("profissional");

    private String regra;


    public String getRegra() {
        return regra;
    }

    RegraUsuario(String regra){
        this.regra = regra;
    }
}
