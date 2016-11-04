import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import com.openbravo.pos.ticket.TicketInfo;

/**
 * 2016-07
 * @author Ing. Victor Suárez - victor.suarez.is@gmail.com - vsuarez@as-solucionesintegrales.com.ve 
 * Script Java que permite obtener Datos Impresora Fiscal Bixolon.
 */

String fiscalNumber = "", fiscalSerial = "", creditNote = "";
Object result = "";
String path = "C:\\IntTFHKA\\";
String spoolerBixolon = "";
String line = "";
FileReader file = null;
BufferedReader in = null;
int fromFN = 0, toFN = 0, fromFS = 0, toFS = 0, fromCN = 0, toCN = 0;
try {
    // Get Status
    System.out.println("--------------------    OBTENIENDO DATOS DE LA IMPRESORA FISCAL BIXOLON    --------------------");
    spoolerBixolon = path+"IntTFHKA.exe UploadStatusCmd(S1)";
    System.out.println("Se empieza a ejecutar el comando 'IntTFHKA.exe UploadStatusCmd(S1)'");
    //new ProcessBuilder(spoolerBixolon).start().waitFor();
    Runtime.getRuntime().exec(spoolerBixolon, null, new File(path)).waitFor();
    System.out.println("Se termina de ejecutar el comando 'IntTFHKA.exe UploadStatusCmd(S1)'");
    file = new FileReader(path+"Status.txt");
    in = new BufferedReader(file);
    if(in == null) {
        System.out.println("No se pudo capturar la información de la Impresora Fiscal");
        return "";
    }
    line = in.readLine();
    if(line == null) {
        System.out.println("No se pudo capturar la información de la Impresora Fiscal");
        return "";
    }
    if(line == "" || line.isEmpty()) {
        System.out.println("No se pudo capturar la información de la Impresora Fiscal");
        return "";
    }
    in.close();
//    if (ticket.getTicketType() == TicketInfo.RECEIPT_NORMAL) {
        fiscalNumber = line.substring(21,29);
        System.out.println("Número de Factura Fiscal: " + fiscalNumber);
//        ticket.setFiscalNumber(fiscalNumber);
        fiscalSerial = line.substring(66, 76);
        System.out.println("Serial de la Impresora Fiscal: " + fiscalSerial);
//      ticket.setFiscalSerial(fiscalSerial);
//    } else if (ticket.getTicketType() == TicketInfo.RECEIPT_REFUND) {
//       fiscalNumber = ticket.getFiscalNumber() == null ? "" : ticket.getFiscalNumber();
        System.out.println("Número de Factura Fiscal: " + fiscalNumber);
//        fiscalSerial = ticket.getFiscalSerial() == null ? "" : ticket.getFiscalSerial();
        System.out.println("Serial de la Impresora Fiscal: " + fiscalSerial);
        creditNote = line.substring(88, 96);
        System.out.println("Número de Nota de Crédito: " + creditNote);
  //      ticket.setFiscalNumber(fiscalNumber + "_NC" + creditNote);
//    }
    result = fiscalSerial + "_" + fiscalNumber + ("".equals(creditNote) ? "" : "_NC" + creditNote);
    System.out.println("INFORMACIÓN FISCAL: " + result);
} catch (FileNotFoundException ex) {
    JOptionPane.showMessageDialog(null, "Error al verificar Datos de la Impresora Fiscal\n" + ex);
    return "";
} catch (IOException ex) {
    JOptionPane.showMessageDialog(null, "Error al verificar Datos de la Impresora Fiscal\n" + ex);
    return "";
} catch (InterruptedException ex) {
    JOptionPane.showMessageDialog(null, "Error al verificar Datos de la Impresora Fiscal\n" + ex);
    return "";
}
return result;