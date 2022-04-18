package com.example.vpndatabase;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import org.w3c.dom.Text;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.*;
import java.util.concurrent.TimeUnit;
//import org.w3c.dom.events.MouseEvent;

public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnSignUp1;

    @FXML
    private Button btnSignIn;

    @FXML
    private Pane pnlSignUp;

    @FXML
    private Pane connectionPage;

    @FXML
    private Pane connectpage;

    @FXML
    private Pane pnlSigIn;

    @FXML
    private Pane connetingplane;

    @FXML
    private TextField tfEmail;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private Label labelText;

    @FXML
    private Button btnSignIn1;

    @FXML
    private TextField tfEmail1;

    @FXML
    private TextField tfEmail2;

    @FXML
    private PasswordField signupPass1;

    @FXML
    private PasswordField signupPass2;

    @FXML
    private Label labelText2;

    @FXML
    private RadioButton logBtn;

    @FXML
    private RadioButton nologBtn;

    @FXML
    private TextField timer;

    @FXML
    private Button connectVTN;

    @FXML
    private Button disconnect;

    @FXML
    private Label connectinggg;

    @FXML
    private Button start;


    public void signuponAction(ActionEvent e) throws SQLException, MessagingException, IOException {
        if (tfEmail1.getText().isBlank() == false && tfEmail2.getText().isBlank() == false && signupPass1.getText().isBlank() == false && signupPass2.getText().isBlank() == false)
        {
            if (signupPass1.getText().equals(signupPass2.getText()))
            {
                DatabaseConnection connectionInput = new DatabaseConnection();
                Connection connection = connectionInput.getConnection();

                String sql = "INSERT INTO user_info (User_Name, email, password) VALUES ('"+ tfEmail2.getText() +"', '"+ tfEmail1.getText() +"', '"+ signupPass1.getText() +"')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);

                Mail mail = new Mail();
                mail.setupServerProperties();
                mail.draftEmail(tfEmail1.getText());
                mail.sendEmail();

                labelText2.setText("Your info has been registered");
            }

           else {
               labelText2.setText("Password does not match!");
            }

        }
        else{
            labelText2.setText("Enter all the data");
        }
    }

    public void  loginButtonOnAction(ActionEvent eve) throws SQLException {
        if (tfEmail.getText().isBlank() == false && tfPassword.getText().isBlank() == false)
        {
            //labelText.setText("Trying to log in");
            int a = validateLogin();

            if (a == 1)
            {
                if (eve.getSource() == btnSignIn)
                {
                    connectpage.toFront();
                }
            }

            if (logBtn.isSelected() == true && nologBtn.isSelected() == true)
            {
                labelText.setText("You cannot keep and not keep log...");
            }
            else if(logBtn.isSelected() == true && nologBtn.isSelected() == false)
            {
                DatabaseConnection connectionInput = new DatabaseConnection();
                Connection connection = connectionInput.getConnection();

                String sql = "INSERT INTO logsystem (User_Name, password, ConnectionTime) VALUES ('"+ tfEmail.getText() +"', NOW(), '"+ tfPassword.getText() +"')";
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            }

        }
        else
        {
            labelText.setText("It usually works if you type something!");
        }

    }

    public void connectOnAction(ActionEvent eve){
        if (eve.getSource() == connectVTN)
        {
            connetingplane.toFront();
        }

    }

    public void startConnection(ActionEvent eve){
        connection con = new connection();
        con.estalishConnection(true);

        try
        {
            Thread.sleep(10000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        if (eve.getSource() == start)
        {
            connectionPage.toFront();
        }

        //Time time = new Time(new CurrentTime().currentTime());
        time tim = new time("00:00:0");

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1),
                        e -> {
                            tim.oneSecondPassed();
                            timer.setText(tim.getCurrentTime());
                        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }


    @FXML
    private void sigh(ActionEvent nice)
    {
        try
        {
            Thread.sleep(10000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        if (nice.getSource() == btnSignUp)
        {
            pnlSignUp.toFront();
        }

        if (nice.getSource() == btnSignUp1)
        {
            pnlSignUp.toBack();
        }
    }

    public int validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connnect = connectNow.getConnection();

        String verifyLogin = "SELECT count(1) FROM user_info WHERE User_Name = '" + tfEmail.getText() +"' AND password = '" + tfPassword.getText() +"'";

        try {
            Statement statement = connnect.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while (queryResult.next())
            {
                if (queryResult.getInt(1) == 1){
                    return 1;
                }
                else {
                    labelText.setText("You sure you registered?");
                    return 0;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return 0;
    }

    public void setDisconnect(ActionEvent e)
    {
        try
        {
            Thread.sleep(3000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        if (e.getSource() == disconnect){
            connection con = new connection();
            con.estalishConnection(false);

            connectionPage.toBack();
            connectpage.toBack();
            connetingplane.toBack();
        }
    }


    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}

