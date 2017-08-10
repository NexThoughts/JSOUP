package jsoup

import grails.transaction.Transactional
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

@Transactional
class ValidateService {

    def validate() {
        String invalidHtml = "<div id=\"myDivId\" ' class = claasnamee value='undaa' > <<p> p tagil vanne <br> <span> span close cheythillee!!  </p> </div>";

        Document initialDoc = Jsoup.parse(invalidHtml);
        println("=======INITIAL====${initialDoc.body()}")

        Document validatedDoc = Jsoup.connect("http://validator.w3.org/check")
                .data("fragment", initialDoc.html())
                .data("st", "1").userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();

        println("******");
        println("Errors");
        println("******");
        for (Element error : validatedDoc.select("li.msg_err")) {
            println(error.select("em").text() + " : " + error.select("span.msg").text());
        }

        println();
        println("**************");
        println("Cleaned output");
        println("**************");
        Document cleanedOuput = Jsoup.parse(validatedDoc.select("pre.source").text());
        cleanedOuput.select("meta[name=generator]").first().remove();
        cleanedOuput.outputSettings().prettyPrint(true);
        println(cleanedOuput.html());
    }

}

