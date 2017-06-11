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
import com.lowagie.text.FontFactory;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import com.mycompany.devman.repositories.ProjectRepository;
import com.mycompany.devman.repositories.TaskRepository;
import com.mycompany.devman.repositories.TeamRepository;
import com.mycompany.devman.repositories.UserRepository;
import com.mycompany.devman.repositories.WorkTimeRepository;
import java.io.File;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public class Raport {

    public static void generateMonthTimePdf(File file) throws Exception {

        Document document = new Document();
        Rectangle rect = new Rectangle(PageSize.A4); //Tworzenie elementu - rozmiaru dokumentu, który będzie kwadratem o rozmiarze 210mm x 297mm - format a4
        document.setPageSize(rect);

        List<User> users = UserRepository.findEmployees();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph paragraph = new Paragraph();
            paragraph.add("Miesięczny raport o czasie pracy. \n");
            paragraph.add("\n");

            LocalDate today = LocalDate.now();
            today.getMonth();

            for (User user : users) {
                paragraph.add("Imię: " + user.getName() + "\n");
                paragraph.add("Nazwisko: " + user.getLastName() + "\n");

                List<WorkTime> works = WorkTimeRepository.findByUser(user);
                int userWorkTimeMonth = 0;
                
                for (WorkTime work : works) {
                    if (today.getMonth().equals(work.getDate().getMonth())) {
                        userWorkTimeMonth += work.getWorkTime();
                    }
                }

                paragraph.add("Czas pracy w danym miesiącu: " + userWorkTimeMonth + "\n");
                paragraph.add("\n");
            }

            document.add(paragraph);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateSettlementPdf(File file) throws Exception {

        Document document = new Document();
        Rectangle rect = new Rectangle(PageSize.A4);
        document.setPageSize(rect);

        List<Project> projects = ProjectRepository.findAllProjects();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph paragraph = new Paragraph();
            paragraph.add("Miesięczny raport rozliczeniowy. \n");
            paragraph.add("\n");

            for (Project project : projects) {
                paragraph.add("Nazwa: " + project.getName() + "\n");
                paragraph.add("Data rozpoczecia: " + project.getStartDate() + "\n");
                paragraph.add("Data zakonczenia: " + project.getEndDate() + "\n");
                paragraph.add("\n");
            }

            document.add(paragraph);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateTeamEfficiencyPdf(File file) throws Exception {

        Document document = new Document();
        Rectangle rect = new Rectangle(PageSize.A4);
        document.setPageSize(rect);

        List<Team> teams = TeamRepository.findAllTeams();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();
            Paragraph paragraph = new Paragraph();

            paragraph.add("Miesięczny raport o efektywności zespołu. \n");
            paragraph.add("\n");

            LocalDate today = LocalDate.now();
            today.getMonth();

            for (Team team : teams) {
                paragraph.add("Nazwa zespołu: " + team.getName() + "\n");

                List<Task> tasks = TaskRepository.findCompletedTasksByTeam(team);
                int completedTasks = 0;

                for (Task task : tasks) {
                    if (today.getMonth().equals(task.getEndDate().getMonth()) == true) {
                        completedTasks++;
                    }
                }
                paragraph.add("Liczba wykonanych zadań: " + completedTasks + "\n");
                paragraph.add("\n");
            }

            document.add(paragraph);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
