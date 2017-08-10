package jsoup

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.jsoup.nodes.Element


class RemoveController {

    def index() {
        File input = new File("/home/shipra/Downloads/dean_0.html");
        Document document = Jsoup.parse(input, "UTF-8");

//        Elements elements = document.select("tr td:eq(0)")
//        elements.remove()
////
        for (Element element : document.select("*")) {
            if (!element.hasText() && element.isBlock()) {
                element.remove();
            }
        }

        System.out.println(document.body().html())
    }


}
