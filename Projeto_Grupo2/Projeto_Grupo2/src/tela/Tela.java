package tela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

import Utilitary.Metodo;
import model.Cnh;
import model.Ctps;
import model.ETitulo;
import model.Resposta;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import service.CnhService;
import service.CtpsService;
import service.ETituloService;

public class Tela extends JFrame implements ActionListener {
	/// Componentes
	private Container cnt;
	private BufferedImage buffImage;
	private JButton btnReadWebcam, btnReadFile, btnClear;
	private JPanel pnlHeader, pnlRight, pnlCnh, pnlCtps, pnlETitulo, pnlSideButtons;
	private JLabel lblHeader;
	private static JTabbedPane tpType;

	// CNH
	private static JTextField txtFullname_cnh, txtCpf_cnh, txtBirth_date_cnh, txtEmission_at_cnh, txtRegister_cnh,
			txtCategory, txtCity_cnh, txtUf_cnh, txtDue_date_at, txtResponse_cnh, txtResult_cnh;

	private JLabel lblFullname_cnh, lblCpf_cnh, lblBirth_date_cnh, lblEmission_at_cnh, lblRegister_cnh, lblCategory,
			lblCity_cnh, lblUf_cnh, lblDue_date_at, lblResponse_cnh, lblResult_cnh;
	//ctps
	private static JTextField txtFullname_ctps, txtCpf_ctps, txtBirth_date_ctps, txtEmission_at_ctps, txtNumber_ctps,
			txtSerie_ctps, txtCity_ctps, txtUf_ctps, txtResponse_ctps, txtResult_ctps;

	private JLabel lblFullname_ctps, lblCpf_ctps, lblBirth_date_ctps, lblEmission_at_ctps, lblNumber_ctps,
			lblSerie_ctps, lblCity_ctps, lblUf_ctps, lblResponse_ctps, lblResult_ctps;
	//e-titulo
	private static JTextField txtFullname_et, txtCpf_et, txtBirth_date_et, txtEmission_at_et, txtRegister_et, txtZone,
			txtSection, txtCity_et, txtUf_et, txtResponse_et, txtResult_et;

	private JLabel lblFullname_et, lblCpf_et, lblBirth_date_et, lblEmission_at_et, lblRegister_et, lblZone, lblSection,
			lblCity_et, lblUf_et, lblResponse_et, lblResult_et;
	
	private static JTextField[]  cnhFields, ctpsFields, etFields;

	public Tela() {
		// Titulo do formulario
		super("Validador de QR Code para Documentos Digitais");

		// Instanciando container
		cnt = getContentPane();

		// Instanciando botoes
		btnReadWebcam = new JButton();
		btnReadFile = new JButton();
		btnClear = new JButton();

		// Instanciando paineis
		pnlHeader = new JPanel();
		pnlRight = new JPanel();
		pnlCnh = new JPanel();
		pnlCtps = new JPanel();
		pnlETitulo = new JPanel();
		pnlSideButtons = new JPanel();

		// Cabecalho
		lblHeader = new JLabel("Leitor de Documentos Digitais");

		// Instanciando abas
		tpType = new JTabbedPane();

		// CNH
		txtFullname_cnh = new JTextField("", 20);
		txtCpf_cnh = new JTextField("", 20);
		txtBirth_date_cnh = new JTextField("", 20);
		txtEmission_at_cnh = new JTextField("", 20);
		txtRegister_cnh = new JTextField("", 20);
		txtCategory = new JTextField("", 20);
		txtCity_cnh = new JTextField("", 20);
		txtUf_cnh = new JTextField("", 20);
		txtDue_date_at = new JTextField("", 20);
		txtResponse_cnh = new JTextField("", 20);
		txtResult_cnh = new JTextField("", 20);
		lblFullname_cnh = new JLabel("Nome:");
		lblCpf_cnh = new JLabel("CPF:");
		lblBirth_date_cnh = new JLabel("Data de Nascimento:");
		lblEmission_at_cnh = new JLabel("Data de Emissão:");
		lblRegister_cnh = new JLabel("Nº Registro:");
		lblCategory = new JLabel("Categoria:");
		lblCity_cnh = new JLabel("Municipio:");
		lblUf_cnh = new JLabel("UF:");
		lblDue_date_at = new JLabel("Data de Vencimento:");
		lblResponse_cnh = new JLabel("QRCode lido:");
		lblResult_cnh = new JLabel("QRCode descriptografado:");
		
		// CTPS
		txtFullname_ctps = new JTextField("", 20);
		txtCpf_ctps = new JTextField("", 20);
		txtBirth_date_ctps = new JTextField("", 20);
		txtEmission_at_ctps = new JTextField("", 20);
		txtNumber_ctps = new JTextField("", 20);
		txtSerie_ctps = new JTextField("", 20);
		txtCity_ctps = new JTextField("", 20);
		txtUf_ctps = new JTextField("", 20);
		txtResponse_ctps = new JTextField("", 20);
		txtResult_ctps = new JTextField("", 20);
		lblFullname_ctps = new JLabel("Nome:");
		lblCpf_ctps = new JLabel("CPF:");
		lblBirth_date_ctps = new JLabel("Data de Nascimento:");
		lblEmission_at_ctps = new JLabel("Data de Emissão:");
		lblNumber_ctps = new JLabel("Numero:");
		lblSerie_ctps = new JLabel("Serie:");
		lblCity_ctps = new JLabel("Cidade Natal:");
		lblUf_ctps = new JLabel("UF Natal:");
		lblResponse_ctps = new JLabel("QRCode lido:");
		lblResult_ctps = new JLabel("QRCode descriptografado:");
		
		// E-Titulo
		txtFullname_et = new JTextField("", 20);
		txtCpf_et = new JTextField("", 20);
		txtBirth_date_et = new JTextField("", 20);
		txtEmission_at_et = new JTextField("", 20);
		txtRegister_et = new JTextField("", 20);
		txtZone = new JTextField("", 20);
		txtSection = new JTextField("", 20);
		txtCity_et = new JTextField("", 20);
		txtUf_et = new JTextField("", 20);
		txtResponse_et = new JTextField("", 20);
		txtResult_et = new JTextField("", 20);
		lblFullname_et = new JLabel("Nome:");
		lblCpf_et = new JLabel("CPF:");
		lblBirth_date_et = new JLabel("Data de Nascimento:");
		lblEmission_at_et = new JLabel("Data de Emissão:");
		lblRegister_et = new JLabel("N Registro:");
		lblZone = new JLabel("Zona:");
		lblSection = new JLabel("Secao:");
		lblCity_et = new JLabel("Cidade:");
		lblUf_et = new JLabel("UF:");
		lblResponse_et = new JLabel("QRCode lido:");
		lblResult_et = new JLabel("QRCode descriptografado:");
		
		// Definindo layouts
		cnt.setLayout(new BorderLayout());
		pnlHeader.setLayout(new FlowLayout());
		pnlRight.setLayout(new BorderLayout());
		pnlSideButtons.setLayout(new GridLayout(4, 1, 0, 10)); // rows, columns, horizontal gap, vertical gap
		pnlCnh.setLayout(new GridBagLayout());
		pnlCtps.setLayout(new GridBagLayout());
		pnlETitulo.setLayout(new GridBagLayout());

		// Declarando GridBagLayouts
		GridBagConstraints cnh_gbc = new GridBagConstraints();
		GridBagConstraints ctps_gbc = new GridBagConstraints();
		GridBagConstraints etitulo_gbc = new GridBagConstraints();

		// Alimentando paineis
		pnlHeader.add(lblHeader);
		pnlSideButtons.add(new JLabel());
		pnlSideButtons.add(btnReadWebcam);
		pnlSideButtons.add(btnReadFile);
		pnlSideButtons.add(btnClear);
		pnlRight.add(BorderLayout.NORTH, pnlSideButtons);
		tpType.add("CNH", pnlCnh);
		tpType.add("CTPS", pnlCtps);
		tpType.add("E-Título", pnlETitulo);
		cnt.add(BorderLayout.NORTH, pnlHeader);
		cnt.add(BorderLayout.EAST, pnlRight);
		cnt.add(BorderLayout.CENTER, tpType);

		// Ajustes de panel
		pnlSideButtons.setPreferredSize(new Dimension(200,250));
		pnlHeader.setBackground(Color.DARK_GRAY);
		lblHeader.setForeground(Color.WHITE);
		pnlRight.setSize(200, 500);
		pnlCnh.setSize(800, 600);
		pnlETitulo.setSize(800, 600);
		pnlCtps.setSize(800, 600);
		
		// tpType
		tpType.add("CNH", pnlCnh);
		tpType.add("CTPS", pnlCtps);
		tpType.add("E-Titulo", pnlETitulo);

		// cnt
		cnt.add(BorderLayout.NORTH, pnlHeader);
		cnt.add(BorderLayout.EAST, pnlRight);
		cnt.add(BorderLayout.CENTER, tpType);
		
		// Ajustes de pnl
		JPanel[] darkPainels = { pnlCnh, pnlCtps, pnlETitulo, pnlRight, pnlSideButtons };
		JPanel[] darkHeader = { pnlHeader };
		
		JLabel[] cnhLabels = 
			{
					lblFullname_cnh, lblCpf_cnh, lblBirth_date_cnh, lblEmission_at_cnh, lblRegister_cnh, lblCategory,
					lblCity_cnh, lblUf_cnh, lblDue_date_at, lblResponse_cnh, lblResult_cnh
			};
		
		JLabel[] ctpsLabels = 
			{
					lblFullname_ctps, lblCpf_ctps, lblBirth_date_ctps, lblEmission_at_ctps, lblNumber_ctps, lblSerie_ctps,
					lblCity_ctps, lblUf_ctps, lblResponse_ctps, lblResult_ctps
			};
		
		JLabel[] etLabels = 
			{
					lblFullname_et, lblCpf_et, lblBirth_date_et, lblEmission_at_et, lblRegister_et, lblZone, lblSection,
					lblCity_et, lblUf_et, lblResponse_et, lblResult_et
			};
		
		JLabel[] headerLabel = { lblHeader };
		
		JTextField[] tabA = 
			{
				txtFullname_cnh, txtCpf_cnh, txtBirth_date_cnh, txtEmission_at_cnh,
				txtRegister_cnh, txtCategory, txtCity_cnh, txtUf_cnh, txtDue_date_at,
				txtResponse_cnh, txtResult_cnh
			};
		JTextField[] tabB =
			{
				txtFullname_ctps, txtCpf_ctps, txtBirth_date_ctps,
				txtEmission_at_ctps, txtNumber_ctps, txtSerie_ctps, txtCity_ctps, txtUf_ctps,
				txtResponse_ctps, txtResult_ctps
			};
		JTextField[] tabC =
			{
				txtFullname_et, txtCpf_et, txtBirth_date_et,
				txtEmission_at_et, txtRegister_et, txtZone, txtSection, txtCity_et, txtUf_et,
				txtResponse_et, txtResult_et
			};
		
		cnhFields = tabA;
		ctpsFields = tabB;
		etFields = tabC;

		JButton[] btn = { btnReadFile, btnReadWebcam, btnClear };

		Metodo.setBorderStyle(tpType);
		Metodo.setStyle(darkPainels, 64, 64, 64);
		Metodo.setStyle(darkHeader, 51, 51, 51);
		Metodo.setForegroundColor(Metodo.sumVector(Metodo.sumVector(headerLabel, cnhLabels), Metodo.sumVector(ctpsLabels, etLabels)), 255, 255, 255);
		Metodo.setButtonLayout(btn);
		Metodo.setStyle(Metodo.sumVector(Metodo.sumVector(tabA, tabB), tabC), 219, 234, 250);
		cnt.setBackground(new Color(64, 64, 64));
		
		//Preencher baglayout com os campos
		Metodo.setGridBagLayout(pnlCnh, cnh_gbc, cnhFields, cnhLabels);
		Metodo.setGridBagLayout(pnlCtps, ctps_gbc, ctpsFields, ctpsLabels);
		Metodo.setGridBagLayout(pnlETitulo, etitulo_gbc, etFields, etLabels);

		// Ajustes de btn
		btnReadWebcam.setText("Webcam");
		btnReadFile.setText("Arquivo");
		btnClear.setText("Limpar");
		
		// action
		btnReadFile.addActionListener(this);
		btnReadWebcam.addActionListener(this);
		btnClear.addActionListener(this);
		
		// Ajustes de label
		lblHeader.setFont(new Font("Arial", Font.BOLD, 28));

		// Ajustes de tela
		setSize(1024, 640); // Tamanho inicial
		setLocationRelativeTo(null); // Aparecer no centro da tela
		setDefaultCloseOperation(Tela.EXIT_ON_CLOSE); // Sair ao fechar
		setVisible(true); // Visibilidade
		setResizable(false);
	}
	
	public void actionPerformed(ActionEvent e) {
		//Ler arquivo
		if (e.getSource() == btnReadFile) {
			final StringBuilder resultado = new StringBuilder("");

			JFileChooser fChooser = new JFileChooser("/home/vinicius/Downloads/teste_PI/Projeto_Grupo2/Projeto_Grupo2/documents");
			fChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			if (fChooser.showOpenDialog(this) != 1) {
				File fImage = fChooser.getSelectedFile();
				try {
					buffImage = ImageIO.read(fImage);
				} catch (IOException evet) {
					// TODO Auto-generated catch block
					evet.printStackTrace();
				}
			}

			LuminanceSource source = new BufferedImageLuminanceSource(buffImage);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			MultiFormatReader reader = new MultiFormatReader();
			com.google.zxing.Result result;
			try {
				result = reader.decode(bitmap);
				resultado.append(result.getText());
			} catch (NotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// constri os objetos
			// agora vamos usar a biblioteca GSON
			Gson gson = new Gson();
			String documentoEmFormatoJSON = gson.toJson(resultado);
			System.out.println(documentoEmFormatoJSON);

			// vamos disparar uma nova thread para no travar
			// a interface com o usurio
			new Thread() {
				public void run() {
					fillDocument(gson, resultado);
				}

			}.start();
		}

		if (e.getSource() == btnReadWebcam) {
			CameraWC teste = new CameraWC();
			try {
				teste.camera();
				;
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		if (e.getSource() == btnClear) {
			Metodo.clearFields(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Tela();
			}
		});
	}

	public static boolean fillDocument(Gson gson, final StringBuilder resultado) {
		try {
			OkHttpClient client = new OkHttpClient();
			MediaType MimeType = MediaType.parse("application/json; charset=utf-8");
			System.out.println(resultado);
			RequestBody body = RequestBody.create(MimeType, resultado.toString());
			Request request = new Request.Builder().url("https://whispering-hollows-24920.herokuapp.com/descriptografar")
					.post(body).build();
	
			try {
				Response response = client.newCall(request).execute();
				String json = response.body().string();
				System.out.println(json);
				
				Resposta resposta = gson.fromJson(json, Resposta.class);
				String mensagem = resposta.getDocumento_descriptografado();
				switch (mensagem.substring(10, 11)) {
				case "A":
					try {
						Cnh cnhBase = gson.fromJson(mensagem, Cnh.class);
						Cnh cnhTo = new Cnh();
						cnhTo.setUser_id(cnhBase.getUser_id());
	
						CnhService cnhS = new CnhService();
	
						if (cnhS.load(cnhTo, cnhBase)) {
							txtFullname_cnh.setText(cnhTo.getFullname());
							txtBirth_date_cnh.setText(Metodo.dateFormat(cnhTo.getBirth_date()));
							txtCity_cnh.setText(cnhTo.getCity());
							txtCpf_cnh.setText(cnhTo.getCpf());
							txtDue_date_at.setText(Metodo.dateFormat(cnhTo.getDue_date_at()));
							txtEmission_at_cnh.setText(Metodo.dateFormat(cnhTo.getEmission_at()));
							txtRegister_cnh.setText(cnhTo.getRegister());
							txtUf_cnh.setText(cnhTo.getUf());
							txtCategory.setText(cnhTo.getCategory());
							txtResponse_cnh.setText(resultado.toString());
							txtResult_cnh.setText(json);
							tpType.setSelectedIndex(0);
							Metodo.clearFields(ctpsFields);
							Metodo.clearFields(etFields);
							return true;
						} else {
							Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
							
							return false;
						}
					} catch (Exception e) {
						Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
						
						e.printStackTrace();
						return false;
					}
				case "B":
					try {
						Ctps ctpsBase = gson.fromJson(mensagem, Ctps.class);
						Ctps ctpsTo = new Ctps();
						ctpsTo.setUser_id(ctpsBase.getUser_id());
	
						CtpsService ctpsS = new CtpsService();
						if (ctpsS.load(ctpsTo, ctpsBase)) {
							txtFullname_ctps.setText(ctpsTo.getFullname());
							txtBirth_date_ctps.setText(Metodo.dateFormat(ctpsTo.getBirth_date()));
							txtCity_ctps.setText(ctpsTo.getCity());
							txtCpf_ctps.setText(ctpsTo.getCpf());
							txtNumber_ctps.setText(ctpsTo.getNumber());
							txtEmission_at_ctps.setText(Metodo.dateFormat(ctpsTo.getEmission_at()));
							txtSerie_ctps.setText(ctpsTo.getSerie());
							txtUf_ctps.setText(ctpsTo.getUf());
							txtResponse_ctps.setText(resultado.toString());
							txtResult_ctps.setText(json);
							tpType.setSelectedIndex(1);
							Metodo.clearFields(cnhFields);
							Metodo.clearFields(etFields);
							return true;
						} else {
							Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
							
							return false;
						}
					} catch (Exception e) {
						Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
						
						e.printStackTrace();
						return false;
					}
				case "C":
					try {
						ETitulo etBase = gson.fromJson(mensagem, ETitulo.class);
						ETitulo etTo = new ETitulo();
						etTo.setUser_id(etBase.getUser_id());
	
						ETituloService etS = new ETituloService();
						if (etS.load(etTo, etBase)) {
							txtFullname_et.setText(etTo.getFullname());
							txtBirth_date_et.setText(Metodo.dateFormat(etTo.getBirth_date()));
							txtCity_et.setText(etTo.getCity());
							txtCpf_et.setText(etTo.getCpf());
							txtRegister_et.setText(etTo.getRegister());
							txtEmission_at_et.setText(Metodo.dateFormat(etTo.getEmission_at()));
							txtZone.setText("" + etTo.getZone());
							txtUf_et.setText(etTo.getUf());
							txtSection.setText(etTo.getSection());
							txtResponse_et.setText(resultado.toString());
							txtResult_et.setText(json);
							tpType.setSelectedIndex(2);
							Metodo.clearFields(ctpsFields);
							Metodo.clearFields(cnhFields);
							return true;
						} else {
							Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
							
							return false;
						}
					} catch (Exception e) {
						Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
						
						e.printStackTrace();
						return false;
					}
				default:
					Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
					return false;
				}
			} catch (IOException e) {
				Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
				
				e.printStackTrace();
				return false;
			}
		}
		catch (Exception e) {
			Metodo.exceptionFillDocument(Metodo.sumVector(Metodo.sumVector(cnhFields, ctpsFields), etFields));
			
			e.printStackTrace();
			return false;
		}
	}
}
