package com.yc.bean4;

import java.io.IOException;
import java.util.Map;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DownLoadUi {

	protected Shell shell;
	private Text txtHttpdldirqqcominvcqqpinyinqqpinyinsetupexe;

	private Button button;
	private Combo combo;

	private long sum;

	private ProgressBar progressBar;

	private Xunlei1 xl;
	private String ip;
	private int threadSize;
	private Label label_2;
	private Label label_3;

	private long totalsize;
	
	private Map<Integer, Long> map;
	
	private DownLoadBiz dlb;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DownLoadUi window = new DownLoadUi();
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
		shell.setSize(743, 484);
		shell.setText("SWT Application");

		Label label = new Label(shell, SWT.NONE);
		label.setBounds(96, 35, 54, 12);
		label.setText("\u4E0B\u8F7D\u5730\u5740:");

		txtHttpdldirqqcominvcqqpinyinqqpinyinsetupexe = new Text(shell,
				SWT.BORDER);
		txtHttpdldirqqcominvcqqpinyinqqpinyinsetupexe
				.setText("http://dl_dir.qq.com/invc/qqpinyin/QQPinyin_Setup_4.6.2028.400.exe");
		txtHttpdldirqqcominvcqqpinyinqqpinyinsetupexe.setBounds(173, 29, 460,
				18);

		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setBounds(96, 87, 54, 12);
		label_1.setText("\u7EBF\u7A0B\u6570:");

		combo = new Combo(shell, SWT.READ_ONLY);
		combo.setItems(new String[] { "2", "3", "4", "5", "6", "8", "10" });
		combo.setBounds(173, 79, 87, 20);
		combo.select(0);

		button = new Button(shell, SWT.NONE);

		button.setBounds(173, 133, 72, 22);
		button.setText("\u5F00\u59CB\u4E0B\u8F7D");

		progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(96, 219, 522, 17);

		label_2 = new Label(shell, SWT.NONE);
		label_2.setBounds(96, 190, 432, 12);
		label_2.setText("\u8096\u524D\u4E0B\u8F7D\u7684\u6587\u4EF6:");

		label_3 = new Label(shell, SWT.NONE);
		label_3.setBounds(229, 242, 380, 12);
		label_3.setText("New Label");

		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ip = txtHttpdldirqqcominvcqqpinyinqqpinyinsetupexe.getText()
						.toString().trim();
				threadSize = Integer
						.parseInt(combo.getText().toString().trim());
				button.setEnabled(false);
				txtHttpdldirqqcominvcqqpinyinqqpinyinsetupexe.setEnabled(false);
				combo.setEnabled(false);

				xl = new Xunlei1();

				label_2.setText("当前下载的文件:" + xl.getFileName(ip));

				try {
					// TODO: 调用业务层，判断是否下载过，并记录初始下载条件.
					 dlb = new DownLoadBiz();
					 map = dlb.find(ip, threadSize);

					// 重新计算sum: sum表示已经下载长度
					for (Map.Entry<Integer, Long> entry : map.entrySet()) {
						sum += entry.getValue();
					}

					totalsize = xl.getFileSize(ip);
					progressBar.setMaximum((int) totalsize);

					progressBar.setSelection((int) sum);
					label_3.setText(sum + "/" + totalsize);

				} catch (Exception e2) {
					e2.printStackTrace();
					return;
				}

				 new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							xl.download(map, ip, threadSize,
									new OnSizeChangeListener() {
										@Override
										public void notify(long size) {
											sum += size;
											// System.out.println( "已经下载了"+
											// sum);
											Display.getDefault().asyncExec(
													new Runnable() {
														@Override
														public void run() {
															progressBar
																	.setSelection((int) sum);
															label_3
																	.setText(sum
																			+ "/"
																			+ totalsize);
															if (totalsize == sum) {
																button
																		.setEnabled(true);
																txtHttpdldirqqcominvcqqpinyinqqpinyinsetupexe
																		.setEnabled(true);
																combo
																		.setEnabled(true);
																
																dlb.delete(ip);
															}
														}
													});

										}
									});
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}).start();
									

			}
		});

	}
}
