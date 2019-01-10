package tela;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.gson.Gson;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

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

public class CameraWC implements Runnable, ThreadFactory {
	private Executor executor = Executors.newSingleThreadExecutor(this);

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Tela();
			}
		});
	}

	private Webcam webcam;
	JFrame frame;

	public void camera() throws InterruptedException {

		// pega uma referência para a webcam
		webcam = Webcam.getDefault();
		// configura o tamanho da tela a ser considerada
		if (webcam.isOpen() == false)
			webcam.setViewSize(WebcamResolution.VGA.getSize());
		// cria um painel para mostrar
		// WebcamPanel é subclasse de JPanel
		WebcamPanel webcamPanel = new WebcamPanel(webcam);
		// configura para espelhar
		webcamPanel.setMirrored(true);
		
		executor.execute(this);

		// cria o frame para adicionar o panel
		frame = new JFrame("Lê QRCode");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if (webcam != null)
					webcam.close();
			}
		});
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.add(webcamPanel, BorderLayout.CENTER);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	@Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		Thread t = new Thread(r, "example-runner");
		t.setDaemon(true);
		return t;
	}

	@Override
	public void run() {
		int x;
		// TODO Auto-generated method stub
		do {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			x = 0;
			Result result = null;
			BufferedImage image = null;

			if (webcam.isOpen()) {

				if ((image = webcam.getImage()) == null) {
					continue;
				}

				final StringBuilder resultado = new StringBuilder("");
				LuminanceSource source = new BufferedImageLuminanceSource(image);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

				try {
					result = new MultiFormatReader().decode(bitmap);
					resultado.append(result.getText());

					if (result != null) {

//						.verConteudoQRCodeLabel.setText(result.getText());
						// constrói os objetos
						// agora vamos usar a biblioteca GSON
						Gson gson = new Gson();
						String documentoEmFormatoJSON = gson.toJson(result);
						System.out.println(documentoEmFormatoJSON);

						// vamos disparar uma nova thread para não travar
						// a interface com o usuário
//						Tela tela = new Tela();
						if (Tela.fillDocument(gson, resultado))
							x++;
					}
				} catch (NotFoundException e) {
					// fall thru, it means there is no QR code in image
				}
			}

			if (x != 0) {
				frame.dispose();
				webcam.close();
			}

		} while (x == 0);
	}
}
