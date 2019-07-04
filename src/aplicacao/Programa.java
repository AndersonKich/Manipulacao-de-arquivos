package aplicacao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entidade.Produto;

public class Programa {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);

		List<Produto> produto = new ArrayList<>();

		String linha;
		String localArquivo = "C:\\arquivo\\produto.txt ";//-------------------------Endereço do arquivo para leitura
		try (BufferedReader br = new BufferedReader(new FileReader(localArquivo))) {//Leitura do arquivo pelo File, o BufferedReader acelera a leitura
			do {//-------------------------------------------------Laço de repetição para ler todas as linhas do arquivo
				linha = br.readLine();//---------------------------Leitura de linhas do arquivo
				String vec[] = linha.split(",");//-----------------Cria um vetor de strigs apos cada virgula
				String nome = vec[0];//----------------------------Primeiro dado do vetor
				double preco = Double.parseDouble(vec[1]);//-------Segundo dado do vetor convertendo a string para um valor Double
				int quantidade = Integer.parseInt(vec[2]);//-------Terceiro dado do vetor convertendo a string para um valor inteiro
				produto.add(new Produto(nome, preco, quantidade));//Criando um novo produto da lista de produtos
			} while (linha != null);//-----------------------------Se não ouver mais dados para ler sai do laço
		} catch (IOException e) {//--------------------------------Tratamento de exceções
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.getMessage();
		}

		File pasta = new File(localArquivo);//---------------------Endereco do arquivo de leitura
		new File(pasta.getParent() + "\\out").mkdir();//-----------Criação de uma sub pasta no endereço primario
		try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\arquivo\\out\\sumario.csv",true))) {//Criação do arquivo (SUMARIO.CSV) na sub pasta criada
			for (Produto p : produto) {//--------------------------Listando os produtos da lista
				String dados = p.toString();//---------------------Pegando o To String de cada produto
				bw.write(dados);//---------------------------------Inserindo os dado no novo arquivo criado 
				bw.newLine();//------------------------------------Quebra de linha
			}

		} catch (IOException e) {
			e.getMessage();
		}

	}

}
