package modelo;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Facturas {

    public static void facturas(alquiler alquiler) {
        try {
            // Crear un documento PDF
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("./facturas/" + alquiler.getID() + ".pdf"));
            document.open();

            // Contenido de la factura
            document.add(new Paragraph("\t\t" + Inventario.getNombreCompania()));
            document.add(new Paragraph("\t\t Id Factura: " + alquiler.getID()));
            document.add(new Paragraph("------------------------------------------------------------------------ "));
            document.add(new Paragraph("\n\n\n"));
            LocalDate fechaActual = LocalDate.now();
            document.add(new Paragraph("Fecha: " + fechaActual));
            document.add(new Paragraph("Direccion: " + alquiler.getReserva().getSedeEntregar()));
            document.add(new Paragraph("\n------------------------------------------------------------------------"));
            document.add(new Paragraph("Datos Cliente: "));
            document.add(new Paragraph("Nombre: " + alquiler.getReserva().getCliente().getNombre()));
            document.add(new Paragraph("Telefono: " + alquiler.getReserva().getCliente().getTelefono()));
            document.add(new Paragraph("Mail: " + alquiler.getReserva().getCliente().getMail()));
            document.add(new Paragraph("\n------------------------------------------------------------------------ "));
            document.add(new Paragraph("Descripcion: "));
            document.add(new Paragraph("\t\t\t\t\t\t\t\t\tMonto: " ));
            document.add(new Paragraph("Alquiler vehiculo tipo: " + alquiler.getReserva().getCategoria()));
            document.add(new Paragraph("\t\t\t"+Double.toString(alquiler.getPagoFinal())));
            document.add(new Paragraph("por " + Reserva.calcularDuracionRenta(alquiler.getReserva().getFechaRecoger(),
                    alquiler.getReserva().getHoraRecoger(), alquiler.getReserva().getFechaEntregar(),
                    alquiler.getReserva().getHoraEntregar()) + " días."));
            document.add(new Paragraph("------------------------------------------------------------------------ "));

            // Cerrar el documento
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cliente cliente= new Cliente("n.perezr", "10293", 1032382323, "Nicolas", "nperezramos0@gmail.com", 312493238, 20050210, "Colombia");
        // Ejemplo de uso
        List<Integer> horarioSemana = new ArrayList<>();
        horarioSemana.add(700);
        horarioSemana.add(1630);
        List<Integer> horarioFinSemana = new ArrayList<>();
        horarioFinSemana.add(730);
        horarioFinSemana.add(1230);
        Sede sedeEntregar=new Sede("SedeUsme", "Cra. 7 #20-2 a 20-109 Bogota, Cundinamarca",horarioSemana,horarioFinSemana );
        Sede sedeRecoger=new Sede("SedeUsme", "Cra. 7 #20-2 a 20-109 Bogota, Cundinamarca",horarioSemana,horarioFinSemana );
        Categoria categoria= new Categoria("sedan_SUPREME", 5, 1.7, 0.7, 175000, 875000, 8750000, 350000, 0);
        Facturas factura = new Facturas(); // Asegúrate de tener una clase Alquiler con un constructor apropiado
        Reserva reserva= new Reserva(20231215, 20231219, 1300, 1400, false, sedeRecoger, sedeEntregar, categoria, cliente);
        alquiler alquiler=new alquiler(reserva);
        facturas(alquiler);
    }
}
