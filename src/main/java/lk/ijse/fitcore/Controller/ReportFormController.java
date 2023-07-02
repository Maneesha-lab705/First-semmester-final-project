
package lk.ijse.fitcore.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import lk.ijse.fitcore.db.DBConnection;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;

public class ReportFormController {
        @FXML
        private AnchorPane root;

        @FXML
        private JFXButton btnprint;


        @FXML
        void btnPrintOnAction(ActionEvent event) throws JRException, SQLException {
//String id = "O001";
//                        JasperDesign load = null;
//                        load = JRXmlLoader.load(new File("C:\\Final Project\\Project fitCore\\src\\main\\resources\\report\\orders.jrxml"));
//                        JRDesignQuery newQuery = new JRDesignQuery();
//                        String sql="SELECT i.Description AS name,od.Unit_Price AS unitPrice,od.Item_Qty ,od.Total AS total,od.Unit_Price*od.Item_Qty AS subtotal"+" FROM item i INNER JOIN order_detail od ON i.Item_Code =od.Order_Id where od.Order_Id = '"+id+"'";
//                        newQuery.setText(sql);
//                        load.setQuery(newQuery);
//                        JasperReport js = JasperCompileManager.compileReport(load);
//                        HashMap<String,Object>hm=new HashMap<>();
//                        hm.put("printOrder","Name");
//                         JasperPrint jp = JasperFillManager.fillReport(js, null, DBConnection.getInstance().getConnection());
//                        JasperViewer viewer = new JasperViewer(jp, false);
//                        viewer.show();




        }

    }



