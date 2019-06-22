package com.qualitysolutions.fresh_and_clean_web_app.vistas.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DecimalFormat;
import java.util.Map;

@Component("BoletaPDF")
public class BoletaPDF extends AbstractPdfView {

    PdfPCell cell;
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter pdfWriter, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        Boleta boleta = (Boleta)map.get("boleta");
        DecimalFormat format = new DecimalFormat("###,###.##");
        String mes = (String)map.get("mes");
        String año = String.valueOf(map.get("año"));
        BaseFont bf = BaseFont.createFont(
                BaseFont.HELVETICA_BOLD,
                BaseFont.CP1252,
                BaseFont.EMBEDDED);
        Font font = new Font(bf, 12);
        //Logo Fresh&Clean
        Image image = Image.getInstance("src/main/resources/static/img/logo-negro.png");
        image.scaleAbsolute(100, 60);
        //Titulo
        Font chapterFont = FontFactory.getFont(FontFactory.HELVETICA, 16, Font.HELVETICA);
        Chunk chunk = new Chunk("Boleta", chapterFont);
        Phrase phrase = new Phrase();
        phrase.add(chunk);
        Paragraph paragraph = new Paragraph();
        paragraph.add(phrase);
        paragraph.setAlignment(Element.ALIGN_CENTER);
        paragraph.setSpacingAfter(20);
        //Tabla Boletas
        PdfPTable table = new PdfPTable(4);
        cell = new PdfPCell(new Phrase("ID Boleta",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Descripción",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Fecha emisión",font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Monto",font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(cell);
        table.addCell(boleta.getIdBoleta().toString());
        table.addCell(boleta.getDescripcionBoleta());
        table.addCell(boleta.getFechaBoleta().toLocalDate().toString().concat(" ").concat(boleta.getFechaBoleta().toLocalTime().toString()));
        cell = new PdfPCell(new Phrase("$"+format.format(boleta.getMontoTotal()),font));
        cell.setHorizontalAlignment(PdfPCell.ALIGN_RIGHT);
        table.addCell(cell);
        document.add(image);
        document.add(paragraph);
        document.add(table);
        httpServletResponse.setContentType(MediaType.APPLICATION_PDF_VALUE);
        httpServletResponse.addHeader(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=BOLETA_".concat(boleta.getIdBoleta().toString()).concat(".pdf"));
    }
}
