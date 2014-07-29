package com.test.dajie;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Save2excel {

	private WritableWorkbook workbook;

	public Save2excel() {

	}

	public void save(String name, List<GetSaveMsg> msgs) throws IOException, RowsExceededException, WriteException {
		String pathname = name + ".xls";
		File file = new File(pathname);
		if (!file.exists()) {
			file.createNewFile();
		}
		OutputStream os = new FileOutputStream(file);
		workbook = Workbook.createWorkbook(os);
		WritableSheet sheet = workbook.createSheet("ÕÐÆ¸", 0);
		GetSaveMsg msg;
		for (int i = 0; i < msgs.size(); i++) {
			msg = msgs.get(i);
			Label label_name = new Label(0, i, msg.getName());
			Label label_time = new Label(1, i, msg.getTime());
			sheet.addCell(label_time);
			sheet.addCell(label_name);
		}
		workbook.write();
		workbook.close();
		System.out.println("´æ´¢³É¹¦");
	}
}
