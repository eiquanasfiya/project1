package com.corsarineri.portale.utils;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFReader{
	public static String getText(File pdfFile) throws IOException {
	    PDDocument doc = PDDocument.load(pdfFile);
	    return new PDFTextStripper().getText(doc);
	}
}