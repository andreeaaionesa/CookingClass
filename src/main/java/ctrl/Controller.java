package ctrl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import model.CookingClass;
import model.Subscription;
import services.Service;
import services.ServicesException;

import java.util.List;

public class Controller {

    @FXML
    private TextField Name, Type, Price, Starting_date, Max_n;
    @FXML
    private Text FId;

    @FXML
    private TextField Tname, Phone, Address;

    @FXML
    private TextField fname;

    @FXML
    private TextField fname2;

    @FXML
    private TableView<CookingClass> fResults;
    @FXML
    private TableView<CookingClass> fResults2;

    @FXML
    private TableView<Subscription> teamResults;


    private Service Services;

    public Controller() {

    }

    public void setService(Service service) {
        this.Services = service;
    }

   /* @FXML
   // public void initialize() {
        //StringConverter<LocalDate> converter = new StringConverter<>() {
           // @Override
           // public String toString(LocalDate date) {
              //  return (date != null) ? dateFormatter.format(date) : "";
          //  }

            @Override
            public LocalDate fromString(String string) {
                return (string != null && !string.isEmpty())
                        ? LocalDate.parse(string, dateFormatter)
                        : null;
            }
        };

        Date.setConverter(converter);
        Date.setValue(LocalDate.now());
        Date.setEditable(false);
    } */

    @FXML
    public void addfHandler(ActionEvent actionEvent) {
        String name = Name.getText();
        String type = Type.getText();
        String price = Price.getText();
        String starting_date = Starting_date.getText();
        String max_n = Max_n.getText();
        if (checkString(name) && checkString(type) && checkString(price) && checkString(starting_date) && checkString(max_n)) {
            try {
                int max_nn = Integer.parseInt(Max_n.getText());
                int id = Services.addF(name, type, price, starting_date, max_nn);
                FId.setText("CookingClass identification number is " + id);
                showNotification("CookingClass successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("The max_nn must be a number! ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        } else
            showNotification("You have to fill in all the fields! ", Alert.AlertType.ERROR);
    }

    @FXML
    public void clearFieldsHandler(ActionEvent actionEvent) {
        Name.setText("");
        Type.setText("");
        Price.setText("");
        Starting_date.setText(" ");
        Max_n.setText(" ");
    }

    private void showNotification(String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle("Notification");
        alert.setContentText(message);
        alert.showAndWait();
    }

   // private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private boolean checkString(String s) {
        return s == null || s.isEmpty() ? false : true;
    }

    @FXML
    public void getAllF(ActionEvent actionEvent) {
        List<CookingClass> results = Services.ar.findAllcookingclasses();
        fResults.getItems().clear();
        fResults.getItems().addAll(results);
    }

    @FXML
    public void addTeamHandler(ActionEvent actionEvent) {
        int selectedRace = fResults2.getSelectionModel().getSelectedIndex();
        if (selectedRace < 0) {
            showNotification("You must select a cookingclass first! ", Alert.AlertType.ERROR);
            return;
        }
        String tn = Tname.getText();
        String c = Phone.getText();
        String ga = Address.getText();

        if (checkString(tn) && checkString(c) && checkString(ga)) {
            try {
                CookingClass f = fResults2.getItems().get(selectedRace);


                Services.addTeam(tn,c, ga,f);
                fResults2.getItems().remove(selectedRace);
                showNotification("Subscription successfully added! ", Alert.AlertType.INFORMATION);
            } catch (NumberFormatException ex) {
                showNotification("went wrong ", Alert.AlertType.ERROR);
                return;
            } catch (ServicesException ex) {
                showNotification(ex.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    public void searchfHandler(ActionEvent actionEvent) {
        String searchName = fname.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<CookingClass> results = Services.getFByName(searchName);
        fResults2.getItems().clear();
        fResults2.getItems().addAll(results);
    }

    @FXML
    public void searchteamHandler(ActionEvent actionEvent) {
        String searchName = fname2.getText();
        if (!checkString(searchName)) {
            showNotification("You must introduce a name! ", Alert.AlertType.ERROR);
            return;
        }
        List<Subscription> results = Services.getSubscriptionByCooking(searchName);
        teamResults.getItems().clear();
        teamResults.getItems().addAll(results);
    }
}