package projeto.ia;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextPane;
import java.awt.Toolkit;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;

public class Principal {

	private JFrame frmCorretorDoUso;
	private static JFormattedTextField campoEntrada;
	private static JButton btnAnalisar;

	/**
	 * Launch the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal window = new Principal();
					window.frmCorretorDoUso.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public Principal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmCorretorDoUso = new JFrame();
		String raiz = System.getProperty("user.dir");
		frmCorretorDoUso.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Dell\\Documents\\Workspace padrão\\Proj IA\\favicon.ico"));
		frmCorretorDoUso.getContentPane().addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				if (!campoEntrada.getText().isEmpty())
					btnAnalisar.setEnabled(true);
				else
					btnAnalisar.setEnabled(false);
			}
		});
		frmCorretorDoUso.setResizable(false);
		frmCorretorDoUso.setTitle("Corretor inteligente do uso da crase");
		frmCorretorDoUso.setBounds(100, 100, 583, 412);
		frmCorretorDoUso.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCorretorDoUso.getContentPane().setLayout(null);

		JLabel lblTitle = new JLabel("Corretor ortografico de crase");
		lblTitle.setForeground(new Color(30, 144, 255));
		lblTitle.setFont(new Font("Tw Cen MT", Font.BOLD | Font.ITALIC, 28));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(41, 11, 500, 27);
		frmCorretorDoUso.getContentPane().add(lblTitle);

		JLabel lblCaixaDeTexto = new JLabel(
				"Escreva no campo abaixo a frase contendo \"à\" ou \"às\" que deve ser analisada:");
		lblCaixaDeTexto.setBounds(10, 63, 557, 14);
		frmCorretorDoUso.getContentPane().add(lblCaixaDeTexto);

		JLabel lblCrase = new JLabel("à");
		lblCrase.setForeground(Color.LIGHT_GRAY);
		lblCrase.setHorizontalAlignment(SwingConstants.CENTER);
		lblCrase.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblCrase.setBounds(272, 157, 29, 31);
		frmCorretorDoUso.getContentPane().add(lblCrase);

		JLabel lblTextoEsquerda = new JLabel("...");
		lblTextoEsquerda.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTextoEsquerda.setBounds(10, 164, 252, 21);
		frmCorretorDoUso.getContentPane().add(lblTextoEsquerda);

		JLabel lblTextoDireita = new JLabel("...");
		lblTextoDireita.setHorizontalAlignment(SwingConstants.LEFT);
		lblTextoDireita.setBounds(311, 164, 256, 21);
		frmCorretorDoUso.getContentPane().add(lblTextoDireita);

		JTextPane campoResposta = new JTextPane();
		campoResposta.setEditable(false);
		campoResposta.setBounds(10, 218, 557, 57);
		frmCorretorDoUso.getContentPane().add(campoResposta);

		JButton btnExcecao = new JButton("A crase sucedidade de " + lblTextoDireita.getText().split(" ")[0] + " deveria ser aceita");
		btnExcecao.setEnabled(false);
		btnExcecao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registraExcecao(lblCrase, lblTextoDireita);
				btnExcecao.setEnabled(false);
				btnExcecao.setText("CASO REGISTRADO");
			}
		});
		btnExcecao.setBounds(58, 318, 468, 23);
		frmCorretorDoUso.getContentPane().add(btnExcecao);

		JButton btnProibicao = new JButton(
				"A crase precedidade de " + lblTextoDireita.getText().split(" ")[0] + " deveria ser aceita");
		btnProibicao.setEnabled(false);
		btnProibicao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				registraProibicao(lblCrase, lblTextoDireita);
				btnProibicao.setEnabled(false);
				btnProibicao.setText("CASO REGISTRADO");

			}
		});
		btnProibicao.setBounds(58, 349, 468, 23);
		frmCorretorDoUso.getContentPane().add(btnProibicao);
		
		btnAnalisar = new JButton("Analisar");
		btnAnalisar.setEnabled(false);
		btnAnalisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				analise(lblTextoEsquerda, lblTextoDireita, lblCrase, campoResposta, btnExcecao, btnProibicao);
			}
		});
		btnAnalisar.setBounds(10, 120, 125, 23);
		frmCorretorDoUso.getContentPane().add(btnAnalisar);

		

		JLabel lblNewLabel = new JLabel("Incrementar base de conhecimento:");
		lblNewLabel.setToolTipText("Registrar ocorrenicas indevidas para o programa aprender novos casos e exceções");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setBounds(58, 301, 448, 14);
		frmCorretorDoUso.getContentPane().add(lblNewLabel);

		campoEntrada = new JFormattedTextField();
		campoEntrada.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				if (!campoEntrada.getText().isEmpty())
					btnAnalisar.setEnabled(true);
				else
					btnAnalisar.setEnabled(false);
			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == 10)
					analise(lblTextoEsquerda, lblTextoDireita, lblCrase, campoResposta, btnExcecao, btnProibicao);
			}
		});
		campoEntrada.setBounds(10, 79, 557, 30);
		frmCorretorDoUso.getContentPane().add(campoEntrada);

	}

	private void analise(JLabel lblTextoEsquerda, JLabel lblTextoDireita, JLabel lblCrase, JTextPane campoResposta, JButton btnExcecao, JButton btnProibicao) {
		try {
			Analise an = new Analise(campoEntrada.getText());
			boolean resultado = an.analisar();
			lblTextoEsquerda.setText(an.getTextoAnterior());
			lblTextoDireita.setText(an.getTextoPosterior());
			lblCrase.setText(an.getTipo().getExtenso());
			
			

			if (resultado) {
				lblCrase.setForeground(Color.RED);
				campoResposta.setText(an.getDica());
				btnExcecao.setEnabled(true);
				btnProibicao.setEnabled(false);
				btnExcecao.setText("A crase sucedida de '" + an.getTextoPosterior().split(" ")[0] + "' deve ser aceita sempre.");
				btnProibicao.setText("A crase sucedida de ... não deveria ser aceita.");
				if (an.getPrecedenteProibido())
					lblTextoEsquerda.setForeground(Color.MAGENTA);
				if (an.getSucessorProibido())
					lblTextoDireita.setForeground(Color.MAGENTA);
			} else {
				lblCrase.setForeground(Color.GREEN);
				lblTextoEsquerda.setForeground(Color.BLACK);
				lblTextoDireita.setForeground(Color.BLACK);
				campoResposta.setText("Nao foi detectado o uso indevido da crase nessa frase.");
				btnProibicao.setEnabled(true);
				btnExcecao.setEnabled(false);
				btnProibicao.setText("A crase sucedida de '" + an.getTextoPosterior().split(" ")[0] + "' não deveria ser aceita." );
				btnExcecao.setText("A crase sucedida de ... deve ser aceita sempre.");
			}
		} catch (Exception e) {
			campoResposta.setText(e.getMessage());
			lblCrase.setForeground(Color.GRAY);
			lblTextoEsquerda.setForeground(Color.BLACK);
			lblTextoEsquerda.setText("...");
			lblTextoDireita.setForeground(Color.BLACK);
			lblTextoDireita.setText("...");
			btnExcecao.setEnabled(false);
			btnProibicao.setEnabled(false);
		}
	}

	private void registraProibicao(JLabel lblCrase, JLabel texto) {
		BufferedWriter out = null;
		String arquivo;
		if (lblCrase.getText().equals(TipoCrase.A.getExtenso())) {
			arquivo = "\\caso_singular.pl";
		} else
			arquivo = "\\caso_plural.pl";

		PrintWriter pw = null;
		String raiz = System.getProperty("user.dir");
		try {
			File file = new File(raiz + arquivo);
			FileWriter fw = new FileWriter(file, true);
			pw = new PrintWriter(fw);
			pw.println("proibida(" + texto.getText().split(" ")[0] + ").");
		} catch (IOException exc) {
			exc.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	
	private void registraExcecao(JLabel lblCrase, JLabel texto) {
		BufferedWriter out = null;
		String arquivo;
		if (lblCrase.getText().equals(TipoCrase.A.getExtenso())) {
			arquivo = "\\caso_singular.pl";
		} else
			arquivo = "\\caso_plural.pl";

		PrintWriter pw = null;
		String raiz = System.getProperty("user.dir");
		try {
			File file = new File(raiz + arquivo);
			FileWriter fw = new FileWriter(file, true);
			pw = new PrintWriter(fw);
			pw.println("excecao(" + texto.getText().split(" ")[0] + ").");
		} catch (IOException exc) {
			exc.printStackTrace();
		} finally {
			if (pw != null) {
				pw.close();
			}
		}
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
