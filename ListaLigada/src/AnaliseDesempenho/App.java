import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {

    //LEIA ANTES DE USAR!!!!
    //PARA QUE O ARQUIVO SEJA LIDO DO DIRETORIO CERTO DO SEU COMPUTADOR 
    //USE O EXPLORADOR DE ARQUIVOS PARA LOCALIZAR O ARQUIVO "arq.txt"
    //DEPOIS CLIQUE COM O BOTÃO DIREITO, VÁ EM PROPRIEDADES E COPIE O >LOCAL<
    //ENTÃO O ADICIONE NO CAMINHO DO CCÓDIGO EM: File arquivoFile = new File(AQUI O LOCAL DO ARQUIVO)
    public static void main(String[] args) throws Exception {
        System.out.println("Executando...");
        LinkedList linkedList = new LinkedList();
        File arquivoFile = new File("C:\\Users\\isaac\\Downloads\\Projetos em Java\\ListaLigada\\ListaLigada\\src\\arq.txt");
        Scanner scanner = null;
        //ler o arquivo com scanner apenas no try para fechar na função finally

        try {
            scanner = new Scanner(arquivoFile);
            if(scanner.hasNextLine()){
                String[] valoresIniciais = scanner.nextLine().split(" ");
                for(String val : valoresIniciais) {
                    linkedList.Adicionar(Integer.parseInt(val));
                }        
            }

            int numeroAcoes = 0;
            if (scanner.hasNextInt()) {
                numeroAcoes = scanner.nextInt();
            }

            int contador = 0;
            while (scanner.hasNextLine() && contador < numeroAcoes) {
                String linha = scanner.nextLine();
                String[] partes = linha.split(" "); //split dividir em partes a linha do texto do arquivo pro switch case
                
                switch (partes[0]) {
                    case "A":
                        int valor = Integer.parseInt(partes[1]);
                        int posicao = Integer.parseInt(partes[2]);
                        ValorArmazenado novo = new ValorArmazenado(valor);
                        if(posicao == 0) {
                            novo.setProximo(linkedList.getPrimeiro());
                            linkedList.setPrimeiro(novo);
                            if(linkedList.getTamanho() == 0){
                                linkedList.setUltimo(novo);
                            }
                            linkedList.setTamanho(linkedList.getTamanho() + 1);//é bom criar metodo pra incrementar tamanho incrementarTamanho();
                        }
                        else if(posicao >= linkedList.getTamanho()){
                                linkedList.Adicionar(valor);
                        }
                        else {
                                ValorArmazenado anterior = linkedList.getPosicao(posicao - 1);
                                novo.setProximo(anterior.getProximo());
                                anterior.setProximo(novo);
                                linkedList.setTamanho(linkedList.getTamanho() + 1);
                        }
                        break;
                    case "R":
                        linkedList.Remover(Integer.parseInt(partes[1]));
                        break;
                    case "P":
                        ValorArmazenado atual = linkedList.getPrimeiro();
                        while (atual != null) {
                            System.out.print(atual.getValor() + " ");
                            atual = atual.getProximo();
                        }
                        System.out.println();
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());

        }
        finally {
            if(scanner != null){
                scanner.close();
            }
        }
    }
}
