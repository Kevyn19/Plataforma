package br.com.plataforma.ws.utils;

public enum ServicosEnum {
	
	VARRER_OU_ASPIRAR("varrerOuAspirar"), LIMPAR_JANELAS("limparJanela"), ARRUMAR_CAMA("arrumarCama");
    
    private final String valor;
    
    ServicosEnum(String valorOpcao){
        valor = valorOpcao;
    }
    public String getValor(){
        return valor;
    }

}
