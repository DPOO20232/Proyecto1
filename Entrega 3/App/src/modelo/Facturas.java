package modelo;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;

public class Facturas {

    public static void facturas(alquiler alquiler) {
        try {
            // Crear un documento PDF
            File file=new File("./facturas/" + alquiler.getID() + ".pdf");
            PdfWriter pdfWriter=new PdfWriter(file);
            PdfDocument pdfDocument=new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);


            // Contenido de la factura
            document.add(new Paragraph("        " + Inventario.getNombreCompania()));
            document.add(new Paragraph("         Id Factura: " + alquiler.getID()));
            document.add(new Paragraph("------------------------------------------------------------------------ "));
            document.add(new Paragraph("\n\n\n"));
            LocalDate fechaActual = LocalDate.now();
            document.add(new Paragraph("Fecha: " + fechaActual));
            document.add(new Paragraph("Direccion: " + alquiler.getReserva().getSedeEntregar().getUbicacion()));
            document.add(new Paragraph("\n------------------------------------------------------------------------"));
            document.add(new Paragraph("\n\n\n"));
            document.add(new Paragraph("Datos Cliente: "));
            document.add(new Paragraph("Nombre: " + alquiler.getReserva().getCliente().getNombre()));
            document.add(new Paragraph("Telefono: " + alquiler.getReserva().getCliente().getTelefono()));
            document.add(new Paragraph("Mail: " + alquiler.getReserva().getCliente().getMail()));
            document.add(new Paragraph("\n------------------------------------------------------------------------ "));
            document.add(new Paragraph("\n\n\n"));
            document.add(new Paragraph("Descripcion:                        Monto:"));
            document.add(new Paragraph("Alquiler vehiculo tipo: "+"         "+Double.toString(alquiler.getPagoFinal())));
            document.add(new Paragraph(alquiler.getReserva().getCategoria().getnombreCategoria()));
            document.add(new Paragraph("por " + Integer.toString(Reserva.calcularDuracionRenta(alquiler.getReserva().getFechaRecoger(),
                    alquiler.getReserva().getHoraRecoger(), alquiler.getReserva().getFechaEntregar(),
                    alquiler.getReserva().getHoraEntregar())) + " días."));
            document.add(new Paragraph("------------------------------------------------------------------------ "));
             // Agregar imagen en la parte inferior de la página
            String imagePath = "C:\\Users\\USUARIO\\Documents\\Proyecto1\\imagenes\\Firma.png"; // Reemplaza con la ruta de tu imagen
            Image img = new Image(ImageDataFactory.create(imagePath));
            img.setWidth(100); // Ajusta el ancho de la imagen según tus necesidades
            img.setTextAlignment(TextAlignment.CENTER);

            // Obtener la última página del documento
            PdfPage lastPage = pdfDocument.getLastPage();

            // Agregar la imagen en la parte inferior
            float x = (lastPage.getPageSize().getLeft() + lastPage.getPageSize().getRight()) / 2;
            float y = lastPage.getPageSize().getBottom() + 30; // Puedes ajustar la posición vertical según tus necesidades
            img.setFixedPosition(x, y);

            // Agregar la imagen al documento
            document.add(img);
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
        factura.facturas(alquiler);
    }
}
