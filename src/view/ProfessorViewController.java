/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ProfessorController;
import dao.AgendaDAO;
import dao.ClassDAO;
import dao.ExamDAO;
import dao.RegistrationDAO;
import dao.StudentDAO;
import exceptions.InvalidFieldException;
import exceptions.MissingFieldException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.JOptionPane;
import model.Agenda;
import model.Class;
import model.Exam;
import model.Registration;
import model.Student;

/**
 * FXML Controller class
 * @author JoãoVitor
 */
public class ProfessorViewController implements Initializable, ControlledScreen {
    
    ScreensController myController;
    /**
     * Initializes the controller class.
     */
    
    private final String ERROR_TITLE_EXAM = "Erro - Avaliação";
    private final String ERROR_TITLE_AGENDA = "Erro - Diário";
    private final String ERROR_DATE_FORMAT = "Data em formato inválido. Por favor use dd/MM/yyyy. Ex: 30/11/2015";
    private final String ERROR_MISSING_FIELD = "Por favor preencha todos os campos.";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        hideAllPanels();
    }
    
    @Override
    public void setScreenParent(ScreensController screenParent){
        myController = screenParent;
    }
    
    private void hideAllPanels(){
        insertExamPane.setVisible(false);
        listExamsPane.setVisible(false);
        editExamPane.setVisible(false);
        listAgendaPane.setVisible(false);
        formAgendaPane.setVisible(false);
    }
    
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    // *****************************  LIST AVALIAÇÕES *****************************   
    @FXML
    private Pane listExamsPane;
    
    @FXML
    private ComboBox classListComboBox;
    
    @FXML
    private ComboBox studentListComboBox;
    
    @FXML
    private TableView examsTable;
    
    private ArrayList<model.Class> tmpListClasses;
    private ArrayList<model.Student> tmpListStudents;
    
    private void populateClassesComboBoxByProfessorLogged(ComboBox cb){
        ArrayList<model.Class> classes;
        try {
            classes = ClassDAO.getClassByProfessorId(myController.getUser().getType().getId());
            
            cb.getItems().clear();
            classes.stream().forEach((next) -> {
                cb.getItems().add(next.getSubject().getName());
            });
            
            tmpListClasses = classes;
            
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void populateStudentsComboBoxByClass(ComboBox cb, Class selectedClass){
        ArrayList<Student> students;
        try {
            students = StudentDAO.getStudentsByClassId(selectedClass.getId());
            
            cb.getItems().clear();
            for(Student student : students){
                cb.getItems().add(student.getName());
            }

            tmpListStudents = students;
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML 
    private void handleListExamLink(ActionEvent event){
        hideAllPanels();
        listExamsPane.setVisible(true);
        
        studentListComboBox.getItems().clear();
        examsTable.getItems().clear();
        populateClassesComboBoxByProfessorLogged(classListComboBox);
    }
    
    Class selectedClass = null;
    @FXML 
    private void handleClassSelected(ActionEvent event){
        selectedClass = null;
        for (Class next : tmpListClasses) {
            if(next.getSubject().getName().equals( classListComboBox.getValue() )){
                selectedClass = next;
                break;
            }
        }
        
        if(selectedClass == null)
           return;
        
        populateStudentsComboBoxByClass(studentListComboBox, selectedClass);
    }
    
    @FXML 
    private void handleStudentSelected(ActionEvent event){
        selectedStudent = null;
        for (Student next : tmpListStudents) {
            if(next.getName().equals( studentListComboBox.getValue() )){
                selectedStudent = next;
                break;
            }
        }
        
        if(selectedStudent != null){
            try {
                Registration registration = RegistrationDAO.getRegistrationByStudentIdAndClassId(
                        selectedStudent.getId(), selectedClass.getId());
                
                ArrayList<Exam> exams = ExamDAO.getExamsByRegistrationId(registration.getId());
            
                examsTable.setEditable(true);

                TableColumn idCol = new TableColumn("Id");

                idCol.setCellValueFactory(
                        new PropertyValueFactory<Student,String>("id")
                );

                TableColumn descriptionCol = new TableColumn("Description");
                descriptionCol.setCellValueFactory(
                        new PropertyValueFactory<Student,String>("description")
                );

                TableColumn deliveryCol = new TableColumn("Data de Entrega");
                deliveryCol.setCellValueFactory(
                        new PropertyValueFactory<Student,String>("delivery_date")
                );

                TableColumn gradeCol = new TableColumn("Nota");
                gradeCol.setCellValueFactory(
                        new PropertyValueFactory<Student,String>("grade")
                );
                
                
                if(exams != null){
                    ObservableList<Exam> list = observableArrayList();
                    list.addAll(exams);

                    examsTable.getItems().clear();
                    examsTable.setItems(list);
                }

                examsTable.getColumns().clear();
                examsTable.getColumns().addAll(idCol, descriptionCol, deliveryCol, gradeCol);
            } catch (SQLException ex) {
                Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    // *****************************  DELETAR AVALIAÇÃO ***************************** 
    @FXML
    private Button deleteBtn;
    
    @FXML
    public void handleDeleteBtn(ActionEvent event){
        Exam exam = (Exam) examsTable.getSelectionModel().getSelectedItem();
        ProfessorController.deleteExam(exam);
        handleStudentSelected(event);
        infoBox("Avaliação deletada com sucesso", "Avaliação");
    }
    
    // *****************************  EDITAR AVALIAÇÃO ***************************** 
    private Exam selectedExam;
    
    @FXML
    private Button editBtn;
    
    @FXML
    private Pane editExamPane;
    
    @FXML
    private ComboBox editClassesComboBox;
    
    @FXML
    private ComboBox editStudentsComboBox;
    
    @FXML
    private TextArea editDescriptionText;
    
    @FXML
    private TextField editDateText;
    
    @FXML
    private TextField editGradeText;
    
    @FXML
    public void handleEditBtn(ActionEvent event){
        Exam exam = (Exam) examsTable.getSelectionModel().getSelectedItem();
        selectedExam = exam;
        hideAllPanels();
        editExamPane.setVisible(true);
        
        populateClassesComboBoxByProfessorLogged(editClassesComboBox);
        editClassesComboBox.getSelectionModel().select(selectedClass.getSubject().getName());
        
        populateStudentsComboBoxByClass(editStudentsComboBox, selectedClass);
        editStudentsComboBox.getSelectionModel().select(selectedStudent.getName());
        
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate;
        try {
            myDate = formatter.parse(exam.getDelivery_date().toString());
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            editDateText.setText(format.format(myDate));
            
        } catch (ParseException ex) {
            infoBox(ERROR_DATE_FORMAT, ERROR_TITLE_EXAM);
        }
        
        editDescriptionText.setText(exam.getDescription());
        
        editGradeText.setText(exam.getGrade());
    }
    
    @FXML
    public void handleClassSelectedEdit(ActionEvent event){
        selectedClass = null;
        for (Class next : tmpListClasses) {
            if(next.getSubject().getName().equals( classListComboBox.getValue() )){
                selectedClass = next;
                break;
            }
        }
        
        if(selectedClass == null)
           return;
        
        populateStudentsComboBoxByClass(editStudentsComboBox, selectedClass);
    }
    
    @FXML
    private void handleStudentSelectedEdit(ActionEvent event){
        selectedStudent = null;
        for (Student next : tmpListStudents) {
            if(next.getName().equals( insertSudentsComboBox.getValue() )){
                selectedStudent = next;
                break;
            }
        }
    }
    
    @FXML
    public void handleSaveBtn(ActionEvent event){
        try {
            ProfessorController.editExam(selectedExam, editDescriptionText.getText(),editGradeText.getText(), editDateText.getText());
            handleListExamLink(event);
            
            infoBox("Avaliação alterada com sucesso.", "Avaliação");
        } catch (ParseException ex) {
            infoBox(ERROR_DATE_FORMAT, ERROR_TITLE_EXAM);
        } catch (MissingFieldException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFieldException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // *****************************  INSERIR AVALIAÇÃO ***************************** 
    @FXML
    private Pane insertExamPane;
    
    @FXML
    private ComboBox insertClassComboBox;
    
    @FXML
    private ComboBox insertSudentsComboBox;
    
    @FXML
    private TextArea descriptionTextArea;
    
    @FXML 
    private TextField dateTextField;
    
    @FXML
    private TextField gradeTextField;
    
    private Student selectedStudent;
    
    @FXML
    private void handleInsertExamLink(ActionEvent event) throws SQLException{
        hideAllPanels();
        insertExamPane.setVisible(true);
        
        populateClassesComboBoxByProfessorLogged(insertClassComboBox);
    }
    
    @FXML
    private void handleClassSelectedInsert(ActionEvent event){
        selectedClass = null;
        for (Class next : tmpListClasses) {
            if(next.getSubject().getName().equals( insertClassComboBox.getValue() )){
                selectedClass = next;
                break;
            }
        }
        
        if(selectedClass == null)
           return;
        
        populateStudentsComboBoxByClass(insertSudentsComboBox, selectedClass);
    }
    
    @FXML
    private void handleStudentSelectedInsert(ActionEvent event){
        selectedStudent = null;
        for (Student next : tmpListStudents) {
            if(next.getName().equals( insertSudentsComboBox.getValue() )){
                selectedStudent = next;
                break;
            }
        }
    }
    
    @FXML
    public void handleInsertBtn(ActionEvent event){
        try {
            ProfessorController.insertExam(selectedStudent, selectedClass, dateTextField.getText(),
                    descriptionTextArea.getText(), gradeTextField.getText());
            
            infoBox("Avaliação inserida com sucesso.", "Info - Avaliação");
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
            infoBox("Erro ao inserir avaliação no banco.", ERROR_TITLE_EXAM);
            
        } catch (ParseException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
            // TODO aqui colocar a exibição dos erros
            infoBox(ERROR_DATE_FORMAT, ERROR_TITLE_EXAM);
        } catch (exceptions.MissingFieldException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
            
            infoBox(ERROR_MISSING_FIELD, ERROR_TITLE_EXAM);
        } catch (InvalidFieldException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
            
            infoBox(ex.getMessage(), ERROR_TITLE_EXAM);
        }
    }
    
    // *****************************  LISTAR DIÁRIOS DE CLASSE *****************************    
    @FXML
    private Pane listAgendaPane;
    
    private ComboBox classesComboBox;
    
    private TableView agendaTable;
    
    private void makeTopPane(String title, Pane pane){
        VBox innerPane = new VBox(8);
        pane.getChildren().setAll(innerPane);
        
        innerPane.setPadding( new Insets(10));
        
        Label titleLabel = new Label();
        titleLabel.setText(title);
        titleLabel.setFont(Font.font("system", FontWeight.BOLD, 15));
        innerPane.getChildren().add(titleLabel);
    }
    
    private void addElementToPane(Pane pane, Node n){
        VBox innerPane = (VBox) pane.getChildren().get(0);
        innerPane.getChildren().add(n);
    }
    
    private void createTableViewAgendas(Pane pane){
        agendaTable = new TableView();
        agendaTable.setMaxSize(200, 200);
        
        TableColumn idCol = new TableColumn("Id");

        idCol.setCellValueFactory(
                new PropertyValueFactory<Agenda,String>("id")
        );
        
        TableColumn dateCol = new TableColumn("Data");

        dateCol.setCellValueFactory(
                new PropertyValueFactory<>("date")
        );
        
        TableColumn descriptionCol = new TableColumn("Descrição");

        descriptionCol.setCellValueFactory(
                new PropertyValueFactory<>("description")
        );
        
        agendaTable.getColumns().addAll(idCol, dateCol, descriptionCol);
        
        addElementToPane(pane, agendaTable);
    }
    
    private void createClassesComboBox(Pane pane, ArrayList<Class> classes){
        classesComboBox.getItems().clear();
        classes.stream().forEach((next) -> {
            classesComboBox.getItems().add(next.getSubject().getName());
        });
        
        classesComboBox.setOnAction(new EventHandler() {
            @Override
            public void handle(Event event) {
                selectedClass = null;
                for (Class next : tmpListClasses) {
                    if(next.getSubject().getName().equals( classesComboBox.getValue() )){
                        selectedClass = next;
                        break;
                    }
                }
                
                try {
                    agendaTable.getItems().clear();
                    agendaTable.getItems().addAll(AgendaDAO.getAgendasByClassId(selectedClass.getId()));
                } catch (SQLException ex) {
                    Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        tmpListClasses = classes;
    }
    

    
    private void createActions(Pane pane){
        HBox actions = new HBox(5);
        
        Button editBtn = new Button("Editar");
        actions.getChildren().add(editBtn);
        
        editBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Agenda agenda = (Agenda) agendaTable.getSelectionModel().getSelectedItem();
                selectedClass = null;
                
                for (Class next : tmpListClasses) {
                    if(next.getSubject().getName().equals( classesComboBox.getValue() )){
                        selectedClass = next;
                        break;
                    }
                }
                
                createFormAgenda();
                descriptionAgenda.setText(agenda.getDescription());
                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");                
                
                dateAgenda.setText(formatter.format(agenda.getDate()) );
                createConfirmButton(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        Agenda agenda = (Agenda) agendaTable.getSelectionModel().getSelectedItem();
                        try {
                            ProfessorController.editAgenda(agenda, descriptionAgenda.getText(), dateAgenda.getText(), selectedClass);
                            
                            handleListAgendaLink(event);
                            infoBox("Avaliação alterada com sucesso", "Avaliação");
                        } catch (ParseException ex) {
                            infoBox(ERROR_DATE_FORMAT,ERROR_TITLE_AGENDA);
                        } catch (MissingFieldException ex) {
                            infoBox(ERROR_MISSING_FIELD,ERROR_TITLE_AGENDA);
                        }
                        
                    }
                });
            }
        });
        
        Button deleteBtn = new Button("Excluir");
        actions.getChildren().add(deleteBtn);
        
        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Agenda agenda = (Agenda) agendaTable.getSelectionModel().getSelectedItem();
                ProfessorController.deleteAgenda(agenda);
                
                handleListAgendaLink(event);
                infoBox("Avaliação excluída com sucesso", "Avaliação");
            }
        });
        
        Button insertBtn = new Button("Inserir");
        actions.getChildren().add(insertBtn);
        
        insertBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                selectedClass = null;
                for (Class next : tmpListClasses) {
                    if(next.getSubject().getName().equals( classesComboBox.getValue() )){
                        selectedClass = next;
                        break;
                    }
                }
                
                createFormAgenda();
                createConfirmButton(new EventHandler<ActionEvent>() {

                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            ProfessorController.insertAgenda(selectedClass, dateAgenda.getText(), descriptionAgenda.getText());

                            infoBox("Diário Inserido com sucesso", "Info - Diário");
                            handleListAgendaLink(event);

                        } catch (SQLException ex) {
                            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (ParseException ex) {
                            infoBox(ex.getMessage(),ERROR_TITLE_AGENDA);
                        } catch (MissingFieldException ex) {
                            infoBox( ex.getMessage(), ERROR_TITLE_AGENDA);
                        } catch (InvalidFieldException ex) {
                            infoBox(ex.getMessage(),ERROR_TITLE_AGENDA);
                        }
                    }
                });
            }
        });
        
        addElementToPane(pane, actions);
    }
    
    @FXML
    public void handleListAgendaLink(ActionEvent event){
        hideAllPanels();
        makeTopPane("Listar Diários de Classe", listAgendaPane);
        classesComboBox = new ComboBox();
        Label classLabel = new Label("Turma: ");
        addElementToPane(listAgendaPane, classLabel);
        
        try {
            ArrayList<Class> classes = ClassDAO.getClassByProfessorId(myController.getUser().getType().getId());
            
            createClassesComboBox(listAgendaPane, classes);
            addElementToPane(listAgendaPane, classesComboBox);
            createTableViewAgendas(listAgendaPane);

            createActions(listAgendaPane);
            listAgendaPane.setVisible(true);

            
        } catch (SQLException ex) {
            Logger.getLogger(ProfessorViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
    
    // *****************************  INSERIR DIARIO DE CLASSE *****************************    
    @FXML
    public Pane formAgendaPane;
    
    public TextField descriptionAgenda;
    public TextField dateAgenda;
    
    public void createFormAgenda(){
        hideAllPanels();
        formAgendaPane.setVisible(true);
        
        makeTopPane("Formulário Diário", formAgendaPane);
        
        Label classLabel = new Label("Turma: "+selectedClass.getSubject().getName());
        addElementToPane(formAgendaPane, classLabel);
        descriptionAgenda = createFormInput("Descrição: ");
        
        dateAgenda = createFormInput("Data: ");
        

    }
    
    private void createConfirmButton(EventHandler<ActionEvent> handler){
        Button confirmBtn = new Button("Confirmar");
        addElementToPane(formAgendaPane, confirmBtn);
        
        confirmBtn.setOnAction(handler);
    }
    
    
    private TextField createFormInput(String name){
        Label l = new Label(name);
        addElementToPane(formAgendaPane, l);
        
        TextField tf = new TextField();
        addElementToPane(formAgendaPane, tf);
        return tf;
    }
    
}
