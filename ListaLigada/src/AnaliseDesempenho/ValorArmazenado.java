public class ValorArmazenado {
    private int valor;
    private ValorArmazenado proximo;

    public ValorArmazenado(int novoValor){
        this.valor = novoValor;
    }

    public int getValor() {
        return valor;
    }

    public ValorArmazenado getProximo() {
        return proximo;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public void setProximo(ValorArmazenado proximo){
        this.proximo = proximo;
    }
}
