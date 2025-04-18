import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws Exception {
        LinkedList linkedList = new LinkedList();

        try {
            File arquivoFile = new File("arq.txt");
            Scanner scanner = new Scanner(arquivoFile);
            if(scanner.hasNextLine()){
                String[] valoresIniciais = scanner.nextLine().split(" ");
                for(String val : valoresIniciais) {
                    linkedList.Adicionar(Integer.parseInt(val));
                }        
            }

            int numeroAcoes = 0;
            if (scanner.hasNextLine()) {
                numeroAcoes = Integer.parseInt(scanner.nextLine());
            }

            int contador = 0;
            while (scanner.hasNextLine() && contador < numeroAcoes) {
                String linha = scanner.nextLine();
                String[] partes = linha.split("");//split dividir em partes a linha do texto do arquivo pro switch case
                
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
                            linkedList.setTamanho(linkedList.getTamanho() + 1);
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
                        for(int i = 0; i < linkedList.getTamanho(); i++){
                            linkedList.getPosicao(i).getValor();
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());

        }
    }
}
