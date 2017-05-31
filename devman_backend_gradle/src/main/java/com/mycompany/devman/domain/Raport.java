/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.devman.domain;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
// import com.lowagie.text.Utilities;
import com.lowagie.text.PageSize;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.devman.repositories.ProjectRepository;
import java.io.File;

import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public class Raport {

    public static void generatePdf(File file) throws Exception {

        Document document = new Document(); // Tworzymy dokument
        
        //* Ustawianie rozmiarów dokumentu
        Rectangle rect = new Rectangle(PageSize.A4); //Tworzenie elementu - rozmiaru dokumentu, który będzie kwadratem o rozmiarze 210mm x 297mm - format a4
        // Utilities.millimetersToPoints(210), Utilities.millimetersToPoints(297)
        document.setPageSize(rect);
        
        Font[] fonts = {
            new Font(),
            new Font(Font.HELVETICA, 14, Font.NORMAL)
        };
        
        List<Project> projects = ProjectRepository.findAllProjects();
        for(Project project : projects) {
            System.out.println("Nazwa: "+project.getName());
            System.out.println("Data rozpoczecia: "+project.getStartDate());
            System.out.println("Data zakonczenia: "+project.getEndDate());
        }
        //paragrafy w pętli
        
        try { //Blok Try jest po to ponieważ nie zawsze możemy mieć miejsce tam gdzie chcemy zapisać pdf
            PdfWriter.getInstance(document, new FileOutputStream(file));
            
            document.open(); //Otwarcie dokumentu - teraz możemy do niego wsadzać co kolwiek chcemy
            Paragraph paragraph = new Paragraph();
            paragraph.add("Raport");
            document.add(paragraph); //dodanie paragrafu do dokumentu
            document.close();
        } catch (Exception e) {
            e.printStackTrace(); // Wyświetli error
        }

    }
}
