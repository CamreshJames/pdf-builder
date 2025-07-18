import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

public class Main {
    public static void main(String[] args) {
        try {
            // Create output directory if it doesn't exist
            Files.createDirectories(Paths.get("src/out"));
            
            // Generate P9 Report PDF
            generatePdfFromHtml(
                "src/main/resources/p-nine-report.html",
                "src/out/P9-Report.pdf"
            );
            
            // Generate Account Statement PDF
            generatePdfFromHtml(
                "src/main/resources/account-statements.html",
                "src/out/Account-Statement.pdf"
            );
            
            System.out.println("PDFs generated successfully!");
            System.out.println("- P9 Report: src/out/P9-Report.pdf");
            System.out.println("- Account Statement: src/out/Account-Statement.pdf");
            
        } catch (Exception e) {
            System.err.println("Error generating PDFs: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void generatePdfFromHtml(String htmlFilePath, String outputPdfPath) throws IOException {
        // Read HTML content from file
        String htmlContent = new String(Files.readAllBytes(Paths.get(htmlFilePath)));
        
        // Create PDF from HTML
        try (OutputStream os = Files.newOutputStream(Paths.get(outputPdfPath))) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(htmlContent, null);
            builder.toStream(os);
            builder.run();
        }
        
        System.out.println("Generated: " + outputPdfPath);
    }
}
