package coco.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

abstract class CCFileLoader {
	/***************************
	 * �p��:CompileErrorLoader, KindLoader
	 ***************************/

	protected void loadData(String filename) {
		try {
			BufferedReader breader = new BufferedReader(new InputStreamReader(
					new FileInputStream(filename), "SJIS"));
			String line;
			while ((line = breader.readLine()) != null) {
				separeteData(line);
			}
			breader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	abstract protected void separeteData(String line) throws IOException;
}