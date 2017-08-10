package jsoup

import org.apache.commons.lang.StringUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.jsoup.safety.Whitelist
import org.jsoup.select.Elements

class ParseController {

    def parseWithUrl() {
        Document document = Jsoup.connect("https://grails.org/").userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .post();

        String title = document.title()
        println("======TITLE========${title}")
        Elements elements = document.select("article")
        elements.each { element ->
            println("===========Element====${element.select("h1").text()}")
        }
    }


    def parseWithFile() {
        File file = new File("/home/shipra/Downloads/Jsoup.html")
        Document document = Jsoup.parse(file, "UTF-8")
        Elements elements = document.select(".nav-sections li")
        elements.each { element ->
            String text = element.select("a").text()
            String attr = element.select("a").attr("href")
            println("***********TEXT**********${text}")
            println("***********ATTRIBUTE**********${attr}")

        }

    }

    def modifyFile() {
        File file = new File("/home/shipra/Downloads/Jsoup.html")
        Document document = Jsoup.parse(file, "UTF-8")
        Elements elements = document.select(".nav-sections li")
        Elements element = elements.get(0).select("a").attr("href", "www.google.com")
        println("=============ELELMENT======${element}")

//        Elements element = elements.get(0).select("a")
//        def output = element.tagName("span")
//        println("==================OUTPUT=======${output}")

    }

    def setHTML() {
        File file = new File("/home/shipra/Downloads/Jsoup.html")
        Document document = Jsoup.parse(file, "UTF-8")
        Element div = document.select("div").first();
        div.html("<p>paragraph</p>");
        div.prepend("<p>First</p>");
        div.append("<p>Last</p>");

        println("=======DIV=====${document.body()}")
    }

    def cleanHTML() {
        String unsafe = "<p><a href='http://example.com/' name='hjhjhj' value='jkjkjkjk' onclick='render()'>Link</a></p>";
        String safe = Jsoup.clean(unsafe, Whitelist.basic());
        println("=======JSOUP======${safe}")


    }
}
