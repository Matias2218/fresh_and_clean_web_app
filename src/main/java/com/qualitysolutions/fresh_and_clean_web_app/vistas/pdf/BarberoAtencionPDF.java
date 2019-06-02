package com.qualitysolutions.fresh_and_clean_web_app.vistas.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("BarberoAtencionPDF")
public class BarberoAtencionPDF extends AbstractPdfView {

    private PdfPCell cell;
    @Override
    protected void buildPdfDocument(Map<String, Object> map,
                                    Document document,
                                    PdfWriter pdfWriter,
                                    HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse) throws Exception {

        Image image = Image.getInstance("src/main/resources/static/img/logo-negro.png");
        image.scaleAbsolute(100, 60);
        List<String[]> barberos = (List<String[]>)map.get("barberos");
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA_BOLD,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
        Font font = new Font(bf, 12);

        //Titulo
        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.HELVETICA);
        Chunk chunk = new Chunk("Barberos con más atenciones a clientes", chapterFont);
        Phrase phrase = new Phrase();
        phrase.add(chunk);
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);
        //Tabla
        PdfPTable table = new PdfPTable(3);
        cell = new PdfPCell(new Phrase("Nombre",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Apellido",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cantidad de atenciones",font));
        table.addCell(cell);

        barberos.stream().forEach(strings -> {
            table.addCell(strings[0]);
            table.addCell(strings[1]);
            table.addCell(strings[2]);
        });

        document.add(image);
        document.add(paragraph);
        document.add(table);
        httpServletResponse.setContentType(MediaType.APPLICATION_PDF_VALUE);
        httpServletResponse.addHeader(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=BarberoAtenciones.pdf");
    }
}
