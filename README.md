# PDF-doc-Thumbnal-generation
This application will create a thumbnail of the first page of any PDF document

##Thumbnail generation of a first page of PDF file using Java

*When we view PDF files in thumbnail preview, we normally get to see the pdf logo of adobe acrobar reader or any pdf viewer(Microsoft Edge in Windows 10). But we might need to see the first page of the PDF document as thumbnail like picture preview. This code snippet serves the purpose.*

### Jars needed
* PDFRenderer-0.9.x.jar ( It is to be found at https://java.net/projects/pdf-renderer under source license LGPL-2.1 )

####Explanations and Code sample

I have taken the pdf file from E:\ drive . You can set it as you want or take it as input from console. 

```
File file = new File("E:\\test.pdf");
```
After that load the file in FileBuffer class and use PDFPage class of the third party jar to get the first page.

```
PDFPage page = pdffile.getPage(0);
```
Next get the image from the page & save it in file. Tadaaa

```
Image img = page.getImage(
		                rect.width, rect.height, //width & height
		                rect, // clip rect
		                null, // null for the ImageObserver
		                true, // fill background with white
		                true  // block until drawing is done
		                );
```
