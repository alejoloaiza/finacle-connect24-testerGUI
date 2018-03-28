import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class FinacleTesterMain {

	protected Shell shlFinacleTesterMain;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FinacleTesterMain window = new FinacleTesterMain();
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
		shlFinacleTesterMain.open();
		shlFinacleTesterMain.layout();
		while (!shlFinacleTesterMain.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlFinacleTesterMain = new Shell();
		shlFinacleTesterMain.setSize(511, 448);
		shlFinacleTesterMain.setText("Finacle C24 Tester Dev. by Alejandro Loaiza");
		
		Label lblNewLabel = new Label(shlFinacleTesterMain, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("Segoe UI", 30, SWT.NORMAL));
		lblNewLabel.setBounds(69, 10, 374, 62);
		lblNewLabel.setText("Connect 24 - Tester");
		
		Button btnConsulta = new Button(shlFinacleTesterMain, SWT.NONE);
		btnConsulta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FinacleTesterBalInqGUI window = new FinacleTesterBalInqGUI();
				window.open();
			}
		});
		btnConsulta.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnConsulta.setBounds(106, 92, 267, 62);
		btnConsulta.setText("Consulta Saldo");
		
		Button btnDebito = new Button(shlFinacleTesterMain, SWT.NONE);
		btnDebito.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FinacleTesterDebitGUI window = new FinacleTesterDebitGUI();
				window.open();
			}
		});
		btnDebito.setText("Debito a Cuenta");
		btnDebito.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnDebito.setBounds(106, 200, 267, 62);
		
		Button btnCredito = new Button(shlFinacleTesterMain, SWT.NONE);
		btnCredito.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FinacleTesterCreditGUI window = new FinacleTesterCreditGUI();
				window.open();
			}
		});
		btnCredito.setText("Credito a Cuenta");
		btnCredito.setFont(SWTResourceManager.getFont("Segoe UI", 14, SWT.NORMAL));
		btnCredito.setBounds(106, 312, 267, 62);

	}
}
