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
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.FormText;
import java.util.StringTokenizer;

public class FinacleTesterCreditGUI {

	protected Shell shlCreditoACuenta;
	private Text txtServer;
	private Text txtPort;
	private List lstSent;
	private List lstResponse;
	private Text txtField124;
	private Label label;
	private Label label_1;
	private Text txtField123;
	private Text txtField103;
	private Label label_2;
	private Text txtField125;
	private Text txtField49;
	private Label label_4;
	private Text txtField41;
	private Label label_5;
	private Text txtField32;
	private Label label_6;
	private Text txtField2;
	private Label label_7;
	private Label label_8;
	private Text txtField3;
	private Text txtField4;
	private Text txtField11;
	private Text txtField12;
	private Text txtField17;
	private Text txtField24;
	private Label label_9;
	private Label label_10;
	private Label label_11;
	private Label label_12;
	private Label label_13;
	private Text txtRepeat;
	private Button chkField125;
	private Text lstAccounts;
	private Button btnAuto;
	private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FinacleTesterCreditGUI window = new FinacleTesterCreditGUI();
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
		shlCreditoACuenta.open();
		shlCreditoACuenta.layout();
		while (!shlCreditoACuenta.isDisposed()) {
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
		String delims = ",";
		ArrayList arlMsgSent = new ArrayList();
		ArrayList arlMsgResponse = new ArrayList();
		String txtRepeatValue = txtRepeat.getText();
		String listofAccounts = lstAccounts.getText();
		
		for(int a=0;a<Integer.parseInt(txtRepeatValue);a++){
			lstSent.removeAll();
			lstResponse.removeAll();
			FinacleTesterCredit tester = new FinacleTesterCredit();
			arlMsgSent = tester.FinacleTestCreditPrepare(txtField2.getText(),txtField3.getText(),txtField4.getText(),txtField11.getText(),txtField12.getText(),txtField17.getText(),txtField24.getText(),txtField32.getText(),txtField49.getText(),txtField125.getText(),txtField103.getText(),txtField123.getText(),txtField125.getText(),chkField125.getSelection(),btnAuto.getSelection(),listofAccounts.split("\\n"),a);
			arlMsgResponse = tester.FinacleTestCreditConnect(txtServer.getText(),Integer.parseInt(txtPort.getText()));
			fillList(arlMsgSent,lstSent);
			fillList(arlMsgResponse,lstResponse);
		}
		//System.out.println(arlMsgResponse.toString());
	}
	protected void createContents() {
		shlCreditoACuenta = new Shell();
		shlCreditoACuenta.setSize(870, 746);
		shlCreditoACuenta.setText("Credito a cuenta (Deposit)");
		
		txtServer = new Text(shlCreditoACuenta, SWT.BORDER);
		txtServer.setText("10.8.72.107");
		txtServer.setBounds(65, 7, 85, 23);
		
		Button btnEnviar = new Button(shlCreditoACuenta, SWT.NONE);
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
		
		Label lblNewLabel = new Label(shlCreditoACuenta, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 49, 13);
		lblNewLabel.setText("Servidor:");
		
		Label lblPort = new Label(shlCreditoACuenta, SWT.NONE);
		lblPort.setText("Port:");
		lblPort.setBounds(176, 10, 29, 13);
		
		txtPort = new Text(shlCreditoACuenta, SWT.BORDER);
		txtPort.setText("16327");
		txtPort.setBounds(210, 7, 49, 23);
		
		lstSent = new List(shlCreditoACuenta, SWT.BORDER);
		lstSent.setBounds(10, 209, 834, 238);
		
		Composite composite = new Composite(shlCreditoACuenta, SWT.NONE);
		composite.setBounds(10, 36, 834, 167);
		
		txtField124 = new Text(composite, SWT.BORDER);
		txtField124.setEnabled(false);
		txtField124.setEditable(false);
		txtField124.setText("ATM");
		txtField124.setBounds(379, 121, 113, 19);
		
		label = new Label(composite, SWT.NONE);
		label.setEnabled(false);
		label.setText("(Terminal type) 124:");
		label.setBounds(231, 124, 132, 16);
		
		label_1 = new Label(composite, SWT.NONE);
		label_1.setText("(Delivery channel ID) 123:");
		label_1.setBounds(231, 102, 146, 13);
		
		txtField123 = new Text(composite, SWT.BORDER);
		txtField123.setText("SUC");
		txtField123.setBounds(379, 99, 113, 19);
		
		txtField103 = new Text(composite, SWT.BORDER);
		txtField103.setText("  1000       BC00000199101000008");
		txtField103.setBounds(379, 77, 180, 19);
		
		label_2 = new Label(composite, SWT.NONE);
		label_2.setText("(Credit account) 103:");
		label_2.setBounds(227, 80, 122, 13);
		
		txtField125 = new Text(composite, SWT.BORDER);
		txtField125.setText("SBA                                                                                                                                  BCOL2TTEST                                                                                                                                                                                                                    SIVABALAJI");
		txtField125.setBounds(379, 146, 445, 19);
		
		txtField49 = new Text(composite, SWT.BORDER);
		txtField49.setText("COP");
		txtField49.setBounds(379, 54, 113, 19);
		
		label_4 = new Label(composite, SWT.NONE);
		label_4.setText("(Currency Code) 49:");
		label_4.setBounds(227, 56, 122, 13);
		
		txtField41 = new Text(composite, SWT.BORDER);
		txtField41.setEnabled(false);
		txtField41.setEditable(false);
		txtField41.setText("CBLIB");
		txtField41.setBounds(379, 32, 113, 19);
		
		label_5 = new Label(composite, SWT.NONE);
		label_5.setEnabled(false);
		label_5.setText("(Terminal ID) 41:");
		label_5.setBounds(227, 35, 122, 13);
		
		txtField32 = new Text(composite, SWT.BORDER);
		txtField32.setText("0");
		txtField32.setBounds(379, 10, 113, 19);
		
		label_6 = new Label(composite, SWT.NONE);
		label_6.setText("(Acquirer bank code) 32:");
		label_6.setBounds(227, 14, 146, 19);
		
		txtField2 = new Text(composite, SWT.BORDER);
		txtField2.setText("0");
		txtField2.setBounds(122, 10, 93, 19);
		
		label_7 = new Label(composite, SWT.NONE);
		label_7.setText("(Customer ID) 2:");
		label_7.setBounds(10, 14, 106, 19);
		
		label_8 = new Label(composite, SWT.NONE);
		label_8.setText("(Processing code) 3:");
		label_8.setBounds(10, 35, 106, 19);
		
		txtField3 = new Text(composite, SWT.BORDER);
		txtField3.setText("490000");
		txtField3.setBounds(122, 32, 93, 19);
		
		txtField4 = new Text(composite, SWT.BORDER);
		txtField4.setText("1000");
		txtField4.setBounds(122, 54, 93, 19);
		
		txtField11 = new Text(composite, SWT.BORDER);
		txtField11.setText("<auto>");
		txtField11.setBounds(122, 77, 93, 19);
		
		txtField12 = new Text(composite, SWT.BORDER);
		txtField12.setText("20160717101010");
		txtField12.setBounds(122, 99, 93, 19);
		
		txtField17 = new Text(composite, SWT.BORDER);
		txtField17.setText("20160717");
		txtField17.setBounds(122, 121, 93, 19);
		
		txtField24 = new Text(composite, SWT.BORDER);
		txtField24.setText("200");
		txtField24.setBounds(122, 144, 93, 19);
		
		label_9 = new Label(composite, SWT.NONE);
		label_9.setText("(Function code) 24:");
		label_9.setBounds(10, 147, 106, 13);
		
		label_10 = new Label(composite, SWT.NONE);
		label_10.setText("(Capture date) 17:");
		label_10.setBounds(10, 124, 93, 13);
		
		label_11 = new Label(composite, SWT.NONE);
		label_11.setText("(Local txn time) 12:");
		label_11.setBounds(10, 101, 106, 13);
		
		label_12 = new Label(composite, SWT.NONE);
		label_12.setText("(Trace number) 11:");
		label_12.setBounds(10, 78, 106, 13);
		
		label_13 = new Label(composite, SWT.NONE);
		label_13.setText("(Amount) 4:");
		label_13.setBounds(10, 56, 84, 13);
		
		chkField125 = new Button(composite, SWT.CHECK);
		chkField125.setBounds(231, 149, 118, 16);
		chkField125.setText("Private use 1 (125)");
		
		btnAuto = new Button(composite, SWT.CHECK);
		btnAuto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnAuto.getSelection()) {
					lstAccounts.setEnabled(true);
				}else{
					lstAccounts.setEnabled(false);
				}
			}
		});
		btnAuto.setBounds(565, 80, 58, 16);
		btnAuto.setText("Auto");
		
		lstAccounts = new Text(composite, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		lstAccounts.setEnabled(false);
		lstAccounts.setBounds(625, 10, 199, 130);
		
		lstResponse = new List(shlCreditoACuenta, SWT.BORDER);
		lstResponse.setBounds(10, 453, 834, 248);
		
		txtRepeat = new Text(shlCreditoACuenta, SWT.BORDER);
		txtRepeat.setText("1");
		txtRepeat.setBounds(420, 9, 39, 21);

	}
}