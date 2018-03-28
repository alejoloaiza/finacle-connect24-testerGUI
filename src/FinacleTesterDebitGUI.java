import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import java.util.ArrayList;

public class FinacleTesterDebitGUI {

	protected Shell shlDebitoACuenta;
	private Text txtServer;
	private Text txtPort;
	private Text txtField2;
	private Text txtField3;
	private Text txtField4;
	private Text txtField11;
	private Text txtField12;
	private Text txtField17;
	private Text txtField24;
	private Text txtField32;
	private Text txtField41;
	private Text txtField49;
	private Text txtField102;
	private Text txtField103;
	private Text txtField123;
	private Text txtField125;
	private List lstSent;
	private List lstResponse;
	private Text txtRepeat;
	private Button chkField125;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FinacleTesterDebitGUI window = new FinacleTesterDebitGUI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlDebitoACuenta.open();
		shlDebitoACuenta.layout();
		while (!shlDebitoACuenta.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @throws Exception 
	 */
	public void fillList(ArrayList arlinput,List lstPopulate){
		 for ( int j=0; j<arlinput.size(); j++ ){
		      lstPopulate.add(arlinput.get(j).toString());
		  }
	}

	public void brnEnviarPress() throws Exception {
		ArrayList arlMsgSent = new ArrayList();
		ArrayList arlMsgResponse = new ArrayList();	
		String txtRepeatValue = txtRepeat.getText();
		for(int a=0;a<Integer.parseInt(txtRepeatValue);a++){
			lstSent.removeAll();
			lstResponse.removeAll();
			FinacleTesterDebit tester = new FinacleTesterDebit();
			arlMsgSent = tester.FinacleTestDebitPrepare(txtField2.getText(),txtField3.getText(),txtField4.getText(),txtField11.getText(),txtField12.getText(),txtField17.getText(),txtField24.getText(),txtField32.getText(),txtField49.getText(),txtField102.getText(),txtField103.getText(),txtField123.getText(),txtField125.getText(),chkField125.getSelection());
			arlMsgResponse = tester.FinacleTestDebitConnect(txtServer.getText(),Integer.parseInt(txtPort.getText()));
			fillList(arlMsgSent,lstSent);
			fillList(arlMsgResponse,lstResponse);
		}
		//System.out.println(arlMsgResponse.toString());
	}
	protected void createContents() {
		shlDebitoACuenta = new Shell();
		shlDebitoACuenta.setSize(621, 780);
		shlDebitoACuenta.setText("Debito a cuenta (POS/ATM Withdraw)");
		
		txtServer = new Text(shlDebitoACuenta, SWT.BORDER);
		txtServer.setText("10.8.72.107");
		txtServer.setBounds(65, 7, 85, 23);
		
		Button btnEnviar = new Button(shlDebitoACuenta, SWT.NONE);
		btnEnviar.addSelectionListener(new SelectionAdapter()  {
			@Override
			public void widgetSelected(SelectionEvent e)  {
				try {
					brnEnviarPress() ;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnEnviar.setBounds(346, 7, 68, 23);
		btnEnviar.setText("Enviar");
		
		Label lblNewLabel = new Label(shlDebitoACuenta, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 49, 13);
		lblNewLabel.setText("Servidor:");
		
		Label lblPort = new Label(shlDebitoACuenta, SWT.NONE);
		lblPort.setText("Port:");
		lblPort.setBounds(176, 10, 29, 13);
		
		txtPort = new Text(shlDebitoACuenta, SWT.BORDER);
		txtPort.setText("16327");
		txtPort.setBounds(210, 7, 49, 23);
		
		lstSent = new List(shlDebitoACuenta, SWT.BORDER);
		lstSent.setBounds(10, 209, 585, 238);
		
		Composite composite = new Composite(shlDebitoACuenta, SWT.NONE);
		composite.setBounds(10, 36, 514, 167);
		
		Label lblCampo = new Label(composite, SWT.NONE);
		lblCampo.setBounds(10, 8, 106, 19);
		lblCampo.setText("(Customer ID) 2:");
		
		Label lblCampo_1 = new Label(composite, SWT.NONE);
		lblCampo_1.setText("(Processing code) 3:");
		lblCampo_1.setBounds(10, 29, 106, 19);
		
		Label lblCampo_2 = new Label(composite, SWT.NONE);
		lblCampo_2.setText("(Amount) 4:");
		lblCampo_2.setBounds(10, 50, 84, 13);
		
		Label lblCampo_3 = new Label(composite, SWT.NONE);
		lblCampo_3.setText("(Trace number) 11:");
		lblCampo_3.setBounds(10, 72, 106, 13);
		
		Label lblCampo_4 = new Label(composite, SWT.NONE);
		lblCampo_4.setText("(Local txn time) 12:");
		lblCampo_4.setBounds(10, 95, 106, 13);
		
		Label lblCampo_5 = new Label(composite, SWT.NONE);
		lblCampo_5.setText("(Capture date) 17:");
		lblCampo_5.setBounds(10, 118, 93, 13);
		
		txtField2 = new Text(composite, SWT.BORDER);
		txtField2.setText("0");
		txtField2.setBounds(122, 4, 93, 19);
		
		txtField3 = new Text(composite, SWT.BORDER);
		txtField3.setText("480000");
		txtField3.setBounds(122, 26, 93, 19);
		
		txtField4 = new Text(composite, SWT.BORDER);
		txtField4.setText("1000");
		txtField4.setBounds(122, 48, 93, 19);
		
		txtField11 = new Text(composite, SWT.BORDER);
		txtField11.setText("<auto>");
		txtField11.setBounds(122, 71, 93, 19);
		
		txtField12 = new Text(composite, SWT.BORDER);
		txtField12.setText("20160705101010");
		txtField12.setBounds(122, 93, 93, 19);
		
		txtField17 = new Text(composite, SWT.BORDER);
		txtField17.setText("20160705");
		txtField17.setBounds(122, 115, 93, 19);
		
		txtField24 = new Text(composite, SWT.BORDER);
		txtField24.setText("200");
		txtField24.setBounds(122, 138, 93, 19);
		
		Label lblCampo_6 = new Label(composite, SWT.NONE);
		lblCampo_6.setText("(Function code) 24:");
		lblCampo_6.setBounds(10, 141, 106, 13);
		
		Label lblCampo_7 = new Label(composite, SWT.NONE);
		lblCampo_7.setText("(Acquirer bank code) 32:");
		lblCampo_7.setBounds(227, 8, 146, 19);
		
		txtField32 = new Text(composite, SWT.BORDER);
		txtField32.setText("0");
		txtField32.setBounds(379, 4, 113, 19);
		
		Label lblCampo_8 = new Label(composite, SWT.NONE);
		lblCampo_8.setEnabled(false);
		lblCampo_8.setText("(Terminal ID) 41:");
		lblCampo_8.setBounds(227, 29, 122, 13);
		
		txtField41 = new Text(composite, SWT.BORDER);
		txtField41.setEnabled(false);
		txtField41.setEditable(false);
		txtField41.setText("CBLIB");
		txtField41.setBounds(379, 26, 113, 19);
		
		Label lblCampo_9 = new Label(composite, SWT.NONE);
		lblCampo_9.setText("(Currency Code) 49:");
		lblCampo_9.setBounds(227, 50, 122, 13);
		
		txtField49 = new Text(composite, SWT.BORDER);
		txtField49.setText("COP");
		txtField49.setBounds(379, 48, 113, 19);
		
		Label lblCampo_10 = new Label(composite, SWT.NONE);
		lblCampo_10.setText("(Debit Account) 102:");
		lblCampo_10.setBounds(227, 72, 122, 13);
		
		txtField102 = new Text(composite, SWT.BORDER);
		txtField102.setText("1000       BC00000199101000009");
		txtField102.setBounds(379, 71, 113, 19);
		
		txtField103 = new Text(composite, SWT.BORDER);
		txtField103.setEnabled(false);
		txtField103.setText("1012251106207001");
		txtField103.setBounds(379, 93, 113, 19);
		
		Label lblCampo_11 = new Label(composite, SWT.NONE);
		lblCampo_11.setEnabled(false);
		lblCampo_11.setText("(Credit account) 103:");
		lblCampo_11.setBounds(227, 95, 122, 13);
		
		Label lblCampo_12 = new Label(composite, SWT.NONE);
		lblCampo_12.setText("(Delivery channel ID) 123:");
		lblCampo_12.setBounds(227, 118, 146, 13);
		
		txtField123 = new Text(composite, SWT.BORDER);
		txtField123.setText("SUC");
		txtField123.setBounds(379, 115, 113, 19);
		
		txtField125 = new Text(composite, SWT.BORDER);
		txtField125.setText("ATM");
		txtField125.setBounds(379, 138, 113, 19);
		
		chkField125 = new Button(composite, SWT.CHECK);
		chkField125.setText("Private use 1 (125)");
		chkField125.setBounds(231, 140, 118, 16);
		
		lstResponse = new List(shlDebitoACuenta, SWT.BORDER);
		lstResponse.setBounds(10, 453, 585, 287);
		
		txtRepeat = new Text(shlDebitoACuenta, SWT.BORDER);
		txtRepeat.setText("1");
		txtRepeat.setBounds(426, 7, 39, 21);

	}
}