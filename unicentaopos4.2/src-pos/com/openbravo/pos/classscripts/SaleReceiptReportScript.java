package com.openbravo.pos.classscripts;

import com.openbravo.pos.ticket.TicketInfo;
import com.openbravo.pos.ticket.TicketLineInfo;
import java.util.*;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Yougeshwar
 */
public class SaleReceiptReportScript implements ClassScript {

    @Override
    public void run(Object obj) {
        TicketInfo ticket = (TicketInfo) obj;
        List dataList = new ArrayList();
        for (int i = 0; i < ticket.getLinesCount(); i++) {
            TicketLineInfo line = ticket.getLine(i);
            Map map = new HashMap();
            map.put("ITEM_NAME", line.getProductName());
            map.put("PRICE", line.getPriceTax());
            map.put("QTY", line.getMultiply());

            dataList.add(map);
        }
        try {
            //JasperDesign jd = JRXmlLoader.load(Object.class.getClass().getResourceAsStream("/com/openbravo/reports/sale-receipt-report.jrxml"));
            //JasperReport jr = JasperCompileManager.compileReport(jd);
            JasperReport jr = (JasperReport) JRLoader.loadObject(new java.io.File(SaleReceiptReportScript.class.getClass().getResource("/com/openbravo/reports/sale-receipt-report.jasper").toURI()));
            JRDataSource data = new JRMapCollectionDataSource(dataList);

            Map reportparams = new HashMap();
            reportparams.put("STORE_NAME", ticket.printStore());
            reportparams.put("CUSTOMER_NAME", ticket.printCustomerFull());
            reportparams.put("DUE_DATE", ticket.printDueDate());
            reportparams.put("INVOICE_NO", ticket.printId());
            reportparams.put("SALES_MAN", ticket.printUser());
            reportparams.put("BANK_DETAILS", "TODO: Bank Details");
            //reportparams.put("REPORT_RESOURCE_BUNDLE", ResourceBundle.getBundle(res));

            JasperPrint jp = JasperFillManager.fillReport(jr, reportparams, data);
            JasperPrintManager.printReport(jp, true);
        } catch (MissingResourceException e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error: " + e);
            //MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotloadresourcedata"), e);
            //msg.show(this);
        } catch (Exception e) {
            e.printStackTrace();
            javax.swing.JOptionPane.showMessageDialog(null, "Error: " + e);
            //MessageInf msg = new MessageInf(MessageInf.SGN_WARNING, AppLocal.getIntString("message.cannotfillreport"), e);
            //msg.show(this);
        }
    }

}
