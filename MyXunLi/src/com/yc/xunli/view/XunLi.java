package com.yc.xunli.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.yc.xunli.service.DownloadService;

public class XunLi {

	protected Shell shell;
	private Text txtUrlPath;
	private Text txtDownloadSavePath;
	private Button btnSavePath;
	private Button btnDownload;
	private Combo comboThreadCount;
	private ProgressBar progressBar;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			XunLi window = new XunLi();
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
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(403, 270);
		shell.setText("SWT Application");
		
		Label label = new Label(shell, SWT.NONE);
		label.setBounds(10, 29, 54, 12);
		label.setText("\u4E0B\u8F7D\u5730\u5740\uFF1A");
		
		txtUrlPath = new Text(shell, SWT.BORDER);
		txtUrlPath.setText("http://dldir1.qq.com/qqfile/qq/QQ8.4/18380/QQ8.4.exe");
		txtUrlPath.setBounds(70, 23, 305, 18);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("\u7EBF\u7A0B\u6570\u91CF\uFF1A");
		label_1.setBounds(10, 66, 54, 12);
		
		comboThreadCount = new Combo(shell, SWT.NONE);
		comboThreadCount.setItems(new String[] {"3", "4", "5", "6", "7", "8", "9", "10"});
		comboThreadCount.setBounds(70, 58, 87, 18);
		comboThreadCount.setText("2");
		
		progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(10, 112, 365, 17);
		
		btnDownload = new Button(shell, SWT.NONE);
		btnDownload.setBounds(225, 197, 72, 22);
		btnDownload.setText("\u4E0B \u8F7D");
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("\u6682 \u505C");
		button_1.setBounds(303, 197, 72, 22);
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setText("\u4FDD\u5B58\u8DEF\u5F84\uFF1A");
		label_2.setBounds(10, 157, 54, 12);
		
		txtDownloadSavePath = new Text(shell, SWT.BORDER);
		txtDownloadSavePath.setText("d:\\myxunli\\download\\");
		txtDownloadSavePath.setBounds(70, 151, 227, 18);
		
		btnSavePath = new Button(shell, SWT.NONE);
		btnSavePath.setFont(SWTResourceManager.getFont("宋体", 9, SWT.NORMAL));
		btnSavePath.setBounds(303, 155, 72, 16);
		btnSavePath.setText("\u6D4F\u89C8...");
		
		addEventContent();

	}

	private void addEventContent() {
		
		//下载目录
		btnSavePath.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.SAVE);
				fd.setText("MyXunLi下载文件保存位置");
				fd.setFileName("a");
				String file = fd.open();
				if (file != null){
					txtDownloadSavePath.setText(file.substring(0, file.length()-1));
				}
				
			}
		});
		
		//下载 
		btnDownload.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String urlPath = txtUrlPath.getText().trim();
				String downloadDir = txtDownloadSavePath.getText();
				int threadCount = Integer.valueOf(comboThreadCount.getText());
				final DownloadService ds = new DownloadService();
				ds.downloadFile(urlPath, downloadDir, threadCount);
				
				new Thread(){
					public void run() {
						while(true){
							shell.getDisplay().asyncExec(new Runnable() {
								@Override
								public void run() {
									progressBar.setSelection((int)(ds.getDownloadAllLength() * 100 / ds.getContentLength()));
								}
							});
							
						}
					};
					
				}.start();
			}
		});
	}
}
