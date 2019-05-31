package com.qualitysolutions.fresh_and_clean_web_app.vistas.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.qualitysolutions.fresh_and_clean_web_app.modelos.Boleta;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component("FinanzaVistaPDF")
public class FinanzaVistaPDF extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document,
                                    PdfWriter pdfWriter, HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse) throws Exception {


        List<Boleta> boletas = (List<Boleta>)map.get("boletas");
        String mes = (String)map.get("mes");
        String año = String.valueOf(map.get("año"));
        PdfPTable table = new PdfPTable(4);
        table.addCell("ID Boleta");
        table.addCell("Descripcion Boleta");
        table.addCell("Monto Boleta");
        table.addCell("Fecha Boleta");
        boletas.stream().forEach(boleta -> {
            table.addCell(boleta.idBoleta.toString());
            table.addCell(boleta.descripcionBoleta);
            table.addCell("$"+boleta.montoTotal.toString());
            table.addCell(boleta.fechaBoleta.toLocalDate().toString().concat(" ").concat(boleta.fechaBoleta.toLocalTime().toString()));
        });
        httpServletResponse.setContentType(MediaType.APPLICATION_PDF_VALUE);
        httpServletResponse.addHeader(HttpHeaders.CONTENT_DISPOSITION,"inline; filename=FINANZAS_".concat(año).concat("_").concat(mes).concat(".pdf"));
        document.add(table);
    }
}
