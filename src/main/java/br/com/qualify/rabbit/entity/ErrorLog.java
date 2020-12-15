package br.com.qualify.rabbit.entity;

public class ErrorLog {
    private String ambiente;
    private String servidor;
    private String type;
    private String mensagem;

    public ErrorLog(String ambiente, String servidor, String type, String mensagem) {
        this.ambiente = ambiente;
        this.servidor = servidor;
        this.type = type;
        this.mensagem = mensagem;
    }

    public String getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
