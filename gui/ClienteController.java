package gui;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;

import control.pessoas.pessoasDB.ClienteDao;
import control.pessoas.pessoasDB.ConstrutoresAuxil;
import control.pessoas.pessoasDB.EnderecoDao;
import control.pessoas.pessoasDB.PessoaDao;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.pessoas.Cliente;
import model.pessoas.Endereco;
import model.pessoas.Pessoa;

public class ClienteController implements Initializable{
// essa seria uma classe "Tela" e as controles extenderiam a tela

	@FXML
	private Label Menu;
	@FXML
	private Label MenuClose;
	@FXML
	private AnchorPane slider;
	
	@FXML
	private Button Cliente;
	@FXML
	private Button Funcionario;
	@FXML
	private Button Gerente;
	@FXML
	private Button Categoria;
	@FXML
	private Button Estoque;
	
	@FXML	
	private Button inserir;
	@FXML
	private Button atualizar;
	@FXML
	private Button buscar;
	@FXML
	private Button remover;
	
	@FXML
	private AnchorPane pPrincipal;
	@FXML
	private AnchorPane pSecundarioCadastro;
	@FXML
	private AnchorPane pSecundarioLogin;
	@FXML
	private AnchorPane pSecundarioAtualizar;
	@FXML
	private AnchorPane pSecundarioRemover;
	@FXML
	private AnchorPane pSecundarioPessoa;
	
	@FXML
	private Button btnProximoPessoa;
	@FXML
	private Button btnCadastrar;
	@FXML
	private Button btnFazerLogin;
	@FXML
	private Button btnAtualizar;
	@FXML
	private Button btnRemover;
	@FXML
	private Label labelClasse;
	
//	VARIAVEIS DOS CAMPOS
	@FXML
	private TextField insNome;
	@FXML
	private TextField insRg;
	@FXML
	private TextField insCpf;
	@FXML
	private TextField insEmail;
	@FXML
	private TextField insTelefone;
	@FXML
	private TextField insIdade;
	@FXML
	private TextField insCep;
	@FXML
	private TextField insNCasa;
	@FXML
	private TextField insComplemento;
	@FXML
	private TextField insReferencia;
	@FXML
	private TextField insUsuario;
	@FXML 
	private TextField insSenha;
	@FXML
	private TextField logUsuario;
	@FXML
	private TextField logSenha;
	@FXML
	private TextField updtNnome;
	@FXML
	private TextField updtNsenha;
	@FXML
	private TextField updtAnome;
	@FXML
	private TextField updtAsenha;
	@FXML
	private TextField remvSenha;
	
	private static boolean login;
	private static Cliente cli;
	private static ClienteDao clienteDao = new ClienteDao();
	private static EnderecoDao endDao = new EnderecoDao();
	private static PessoaDao pesDao = new PessoaDao();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		slider.setTranslateX(0);
		esconderPane();
		
		Menu.setOnMouseClicked(event -> {
			TranslateTransition slide = new TranslateTransition();
			slide.setDuration(Duration.seconds(0.3));
			slide.setNode(slider);
			
			slide.setToX(0);
			slide.play();
			
			slider.setTranslateX(-176);
			
			slide.setOnFinished((ActionEvent e) -> {
				Menu.setVisible(false);
				MenuClose.setVisible(true);
			});
		});
		
		MenuClose.setOnMouseClicked(event -> {
			TranslateTransition slide = new TranslateTransition();
			slide.setDuration(Duration.seconds(0.3));
			slide.setNode(slider);
			
			slide.setToX(-176);
			slide.play();
			
			slider.setTranslateX(0);
			
			slide.setOnFinished((ActionEvent e) -> {
				Menu.setVisible(true);
				MenuClose.setVisible(false);
			});
		});
		
		inserir.setOnAction(event -> {
			esconderPane();
			if(labelClasse.getText().equals("Cliente")) {
				pSecundarioPessoa.setVisible(true);
			}
		});
		buscar.setOnAction(event -> {
			esconderPane();
			if(labelClasse.getText().equals("Cliente")) {
				pSecundarioLogin.setVisible(true);
			}
		});
		atualizar.setOnAction(event -> {
			if(login) {
				esconderPane();
				if(labelClasse.getText().equals("Cliente")) {
					pSecundarioAtualizar.setVisible(true);
				}
			} else {
				alerta("Usuário precisa estar logado para fazer modificações.");
			}
		});
		remover.setOnAction(event -> {
			if(login) {
				esconderPane();
				if(labelClasse.getText().equals("Cliente")) {
					pSecundarioRemover.setVisible(true);
				}		
			} else {
				alerta("Usuário precisa estar logado para remover conta.");
			}
		});
		
		btnProximoPessoa.setOnAction(event -> {
			esconderPane();
			pSecundarioCadastro.setVisible(true);
		});
		btnCadastrar.setOnAction(event -> {
			try {
				Endereco end = new Endereco((insCep.getText()+""+insNCasa.getText()), insCep.getText(), Integer.parseInt(insNCasa.getText()), insComplemento.getText(), insReferencia.getText());
				endDao.insert(end);
				Pessoa p = new Pessoa(insNome.getText(), insRg.getText(), insCpf.getText(), ConstrutoresAuxil.construirEmail(insEmail.getText()), ConstrutoresAuxil.construirTelefone(insTelefone.getText()), Integer.parseInt(insIdade.getText()), end);
				pesDao.insert(p);
				Cliente c = new Cliente(p.getNome(), p.getRg(), p.getCpf(), p.getEmail(), p.getTelefone(), p.getIdade(), p.getEndereco(), insUsuario.getText(),insSenha.getText());
				clienteDao.insert(c);
				alerta("Usuário cadastrado!");
			} catch (Exception e) {
				alerta(e.getMessage());
			}
		});
		btnFazerLogin.setOnAction(event -> {
			try {
				cli = new Cliente();
				cli.setIdUsuario(logUsuario.getText(), logSenha.getText());
				
				if(clienteDao.findById(cli.getIdUsuario()).getIdUsuario().equals(cli.getIdUsuario())) {
						login = true;
						cli = clienteDao.findById(cli.getIdUsuario());
						alerta("Usuário Logado");
				}
			} catch (Exception e) {
				alerta("Usuário Inválido");
			}
//			criar um novo pane com os dados no banco, inclui dados de pessoa	
		});
		btnAtualizar.setOnAction(event -> {
			if(telaConfirmacao("Deseja mesmo atualizar este usuário?")) {
				try {
					if(updtAnome.getText().equals(cli.getNomeUsuario()) && updtAsenha.getText().equals(cli.getSenhaUsuario())) {
						Cliente c = cli;
						c.setNome(updtNnome.getText());
						c.setSenhaUsuario(updtNsenha.getText());
						clienteDao.update(c);
						
						Label lb = new Label("Informações Atualizadas!");
						lb.setLayoutX(254);
						lb.setLayoutY(174);
						pSecundarioAtualizar.getChildren().add(lb);
						
					} else {
						throw new Exception("Informações inválidas.");
					}
				} catch (Exception e) {
					alerta(e.getMessage());
				}
			}
		});
		btnRemover.setOnAction(event -> {
			if(telaConfirmacao("Deseja mesmo deletar este usuário?")) {
				try {
					if(remvSenha.getText().equals(cli.getSenhaUsuario())) {
						clienteDao.delete(cli.getIdUsuario());
						login = false;
						Label lb = new Label("Usuário Removido!");
						lb.setLayoutX(217);
						lb.setLayoutY(143);
						pSecundarioRemover.getChildren().add(lb);
						
					} else {
						throw new Exception("Senha inválida");
					}						
				} catch (Exception e) {
					alerta(e.getMessage());
				}
			}
		});
	}
	
	static boolean confirmResposta;
	public boolean telaConfirmacao(String message) {
		Stage wind = new Stage();
		wind.initModality(Modality.APPLICATION_MODAL);
		wind.setTitle("Confirmar Operação");
		wind.setMinWidth(250);
		Label label = new Label(message);
			
		Button btnSim = new Button("Confirmar");
		Button btnNao = new Button("Cancelar");
		VBox spam = new VBox(10.0);
		spam.setSpacing(16);
		spam.setPadding(new Insets(5, 5, 5, 5));
		spam.getChildren().addAll(label, btnSim, btnNao);
		
		btnSim.setOnAction(event -> {
			confirmResposta = true;
			wind.close();
		});
		btnNao.setOnAction(event -> {
			confirmResposta = false;
			wind.close();
		});
		
		Scene scene = new Scene(spam);
		wind.setScene(scene);
		wind.showAndWait();
		
		return confirmResposta;
	}
	
	public void alerta(String message) {
		Stage wind = new Stage();
		wind.initModality(Modality.APPLICATION_MODAL);
		wind.setTitle("Alerta");
		wind.setMinWidth(250);
		Label label = new Label(message);
		
		VBox spam = new VBox(10.0);
		spam.setPadding(new Insets(7, 7, 7, 7));
		spam.getChildren().addAll(label);

		Scene scene = new Scene(spam);
		wind.setScene(scene);
		wind.showAndWait();
	}
	
	@FXML
	public void ClienteOnAction() {
		labelClasse.setText("Cliente");
	}
	
	@FXML
	public void FuncionarioOnAction() {
		labelClasse.setText("Funcionário");
	}
	
	@FXML
	public void GerenteOnAction() {
		labelClasse.setText("Gerente");
	}
	
	@FXML
	public void CategoriaOnAction() {
		labelClasse.setText("Categoria");
	}
	
	@FXML
	public void EstoqueOnAction() {
		labelClasse.setText("Estoque");
	}
	
	@FXML
	public void esconderPane() {
		pSecundarioCadastro.setVisible(false);
		pSecundarioLogin.setVisible(false);
		pSecundarioAtualizar.setVisible(false);
		pSecundarioRemover.setVisible(false);
		pSecundarioPessoa.setVisible(false);
	}
}
